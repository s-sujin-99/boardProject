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

import com.zeus.domain.Item2;
import com.zeus.service.Item2Service;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

@Slf4j
@Controller
public class Item2Controller {

	@Autowired
	private Item2Service item2Service;

	@Value("${uploadPath:C:/upload}")
	private String uploadPath;

	// file upload 화면 요청
	@GetMapping(value = "/item2/insertForm")
	public String itemInsertForm(Item2 item, Model model) throws Exception {
		model.addAttribute("item", item);
		return "item2/insertForm";
	}

	// file upload 저장 요청
	@PostMapping(value = "/item2/insert")
	public String itemInsert(Item2 item, Model model) throws Exception {
		List<MultipartFile> fileList = item.getPictures();
		int count = 0;
		for (MultipartFile file : fileList) {

			log.info("originalName : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
			log.info("contentType : " + file.getContentType());

			String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
			if (count == 0) {
				item.setPictureUrl(createdFileName);
			} else {
				item.setPictureUrl2(createdFileName);
			}

			count++;
		}

		boolean result = item2Service.insert(item);
		if (result == false) {
			return "item2/fail";
		}
		return "item2/success";
	}

	// file upload list
	@GetMapping(value = "/item2/list")
	public String list(Item2 item, Model model) throws Exception {
		List<Item2> list = item2Service.list();

		model.addAttribute("list", list);
		return "item2/list";
	}

	@PostMapping(value = "/item2/delete")
	public String item2Delete(Item2 item, Model model) throws Exception {

		// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
		item = item2Service.select(item);

		for (int i = 0; i < 2; i++) {
			String createFileName = i == 0 ? item.getPictureUrl() : item.getPictureUrl2();

			if (createFileName != null) {
				File file = new File(uploadPath + File.separator + createFileName);
				if (file.exists() == true) {
					file.delete();
				}
			}
		}

		boolean result = item2Service.delete(item);

		if (result == false) {
			return "item2/fail";
		}
		return "item2/success";
	}

	@GetMapping(value = "/item2/deleteForm")
	public String item2DeleteForm(Item2 item, Model model) throws Exception {
		Item2 oldItem = item2Service.select(item);

		model.addAttribute("item", item);
		return "item2/deleteForm";
	}

	@PostMapping(value = "/item2/update")
	public String item2Update(Item2 item, Model model) throws Exception {

		// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
		Item2 oldItem = item2Service.select(item); 
	    
	    List<MultipartFile> fileList = item.getPictures();
	    int count = 0;
	    
	    for (MultipartFile file : fileList) {
	        String oldFileName = null;

	        // 사용자가 새로운 파일을 업로드한 경우에만 교체 작업을 진행합니다.
	        if (file != null && file.getSize() > 0) {
	            // 외장하드에 새 파일 저장 후 저장된 고유 파일명 반환
	            String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
	            
	            if (count == 0) {
	                // 1. 첫 번째 이미지 교체
	                oldFileName = oldItem.getPictureUrl(); // 기존 1번 파일명 백업
	                item.setPictureUrl(createdFileName);   // 객체에 새 1번 파일명 세팅
	            } else if (count == 1) {
	                // 2. 두 번째 이미지 교체
	                oldFileName = oldItem.getPictureUrl2(); // 기존 2번 파일명 백업
	                item.setPictureUrl2(createdFileName);   // 객체에 새 2번 파일명 세팅
	            }
	            
	            // 기존 파일이 존재했다면 외장하드(디렉토리)에서 삭제
	            if (oldFileName != null && !oldFileName.isEmpty()) {
	                File _file = new File(uploadPath + File.separator + oldFileName);
	                if (_file.exists()) {
	                    _file.delete();
	                }
	            }
	        } else {
	            // 사용자가 새 파일을 선택하지 않았다면 기존 파일명을 그대로 유지시킵니다.
	            if (count == 0) {
	                item.setPictureUrl(oldItem.getPictureUrl());
	            } else if (count == 1) {
	                item.setPictureUrl2(oldItem.getPictureUrl2());
	            }
	        }
	        count++;
	    }

		boolean result = item2Service.update(item);
		if (result == false) {
			return "item2/fail";
		}
		return "item2/success";

	}

	@GetMapping(value = "/item2/updateForm")
	public String item2UpdateForm(Item2 item, Model model) throws Exception {
		item = item2Service.select(item);

		model.addAttribute("item", item);
		return "item2/updateForm";
	}

	@GetMapping(value = "/item2/display")
	@ResponseBody
	public ResponseEntity<byte[]> item2Display(Item2 item, int no, Model model) throws Exception {
		// 1. InputStream 변수선언
		InputStream in = null;

		// 2. ResponseEntity<byte[]> 변수 선언
		ResponseEntity<byte[]> entity = null;

		try {
			// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
			item = item2Service.select(item);
			String createFileName = no == 1 ? item.getPictureUrl() : item.getPictureUrl2();
			
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

	@GetMapping(value = "/item2/display2")
	@ResponseBody
	public ResponseEntity<byte[]> item2Display2(Item2 item, Model model) throws Exception {
		// 1. InputStream 변수선언
		InputStream in = null;

		// 2. ResponseEntity<byte[]> 변수 선언
		ResponseEntity<byte[]> entity = null;

		try {
			// 1. 외장하드에 있는 이미지 파일명 정보 가져오기
			item = item2Service.select(item);
			String createFileName = item.getPictureUrl2();

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