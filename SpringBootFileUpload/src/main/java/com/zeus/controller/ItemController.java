package com.zeus.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zeus.domain.Item;
import com.zeus.service.ItemService;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

@Slf4j
@Controller

public class ItemController {

	@Autowired
	private ItemService itemService;

	@Value("${uploadPath:C:/upload}")
	private String uploadPath;

	// file upload 화면 요청
	@GetMapping(value = "/item/insertForm")
	public String itemInsertForm(Item item, Model model) throws Exception {
		return "item/insertForm";
	}

	// file upload 저장 요청
	@PostMapping(value = "/item/insert")
	public String itemInsert(Item item, Model model) throws Exception {
		MultipartFile file = item.getPicture();

		log.info("originalName : " + file.getOriginalFilename());
		log.info("size : " + file.getSize());
		log.info("contentType : " + file.getContentType());

		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());

		item.setPictureUrl(createdFileName);

		boolean result = itemService.insert(item);
		if (result == false) {
			return "item/fail";
		}
		return "item/success";
	}

	// file upload list
	@GetMapping(value = "/item/list")
	public String list(Item item, Model model) throws Exception {
		List<Item> list = itemService.list();

		model.addAttribute("list", list);
		return "item/list";
	}

	@PostMapping(value = "/item/delete")
	public String itemDelete(Item item, Model model) throws Exception {

		// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
		item = itemService.select(item);
		String createFileName = item.getPictureUrl();

		if (createFileName != null) {
			File file = new File(uploadPath + File.separator + createFileName);
			if (file.exists() == true) {
				file.delete();
			}
		}

		boolean result = itemService.delete(item);
		if (result == false) {
			return "item/fail";
		}
		return "item/success";

	}

	@GetMapping(value = "/item/deleteForm")
	public String itemDeleteForm(Item item, Model model) throws Exception {
		item = itemService.select(item);

		model.addAttribute("item", item);
		return "item/deleteForm";
	}

	@PostMapping(value = "/item/update")
	public String itemUpdate(Item item, Model model) throws Exception {

		// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
		MultipartFile file = item.getPicture();
		String oldFileName = item.getPictureUrl();
		// 2. 사용자가 새로운 파일 선택 점검
		if (file != null && file.getSize() > 0) {
			// 새로운 파일 생성
			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			item.setPictureUrl(createdFileName);
			//기존 파일 삭제
			if (oldFileName != null) {
				File _file = new File(uploadPath + File.separator + oldFileName);
				if (_file.exists() == true) {
					_file.delete();
				}
			}
			
		} 

		boolean result = itemService.update(item);
		if (result == false) {
			return "item/fail";
		}
		return "item/success";

	}

	@GetMapping(value = "/item/updateForm")
	public String itemUpdateForm(Item item, Model model) throws Exception {
		item = itemService.select(item);

		model.addAttribute("item2", item);
		return "item/updateForm";
	}

	@GetMapping(value = "/item/display")
	@ResponseBody
	public ResponseEntity<byte[]> itemDisplay(Item item, Model model) throws Exception {
		// 1. InputStream 변수선언
		InputStream in = null;

		// 2. ResponseEntity<byte[]> 변수 선언
		ResponseEntity<byte[]> entity = null;

		try {
			// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
			item = itemService.select(item);
			String createFileName = item.getPictureUrl();

			// 2. 이미지 확장자 추출
			int index = createFileName.lastIndexOf(".");
			String formatName = createFileName.substring(index + 1);

			// 3. MediaType 결정 (getMediaType 메소드는 컨트롤러 내부에 정의되어 있어야 합니다)
			MediaType mediaType = getMediaType(formatName);

			// 4. HTTP 헤더 설정
			HttpHeaders headers = new HttpHeaders();

			// 5. 파일 읽어오기
			in = new FileInputStream(uploadPath + File.separator + createFileName);

			if (mediaType != null) {
				headers.setContentType(mediaType);
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return entity;
	}

	// *****************************************************************************
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String createdFileName = uid.toString() + "_" + originalName;

		File target = new File(uploadPath, createdFileName);
		FileCopyUtils.copy(fileData, target);
		return createdFileName;
	}

	private MediaType getMediaType(String formatName) {
		if (formatName != null) {
			if (formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}

			if (formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}

			if (formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}

		return null;
	}
}