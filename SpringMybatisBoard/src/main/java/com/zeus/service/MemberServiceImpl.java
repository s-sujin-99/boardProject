package com.zeus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zeus.domain.Member;
import com.zeus.domain.MemberAuth;
import com.zeus.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;

	@Override
	@Transactional
	public boolean insert(Member member) throws Exception {

		if (member == null || member.getUserId().isEmpty() == true || member.getUserPw().isEmpty() == true) {
			return false;
		}

		int count = mapper.insertMember(member);
		log.info("memberServiceImpl = " + member.toString());

		MemberAuth auth = new MemberAuth();
		auth.setUserNo(member.getUserNo());
		auth.setAuth("ROLE_USER");
		int count2 = mapper.insertAuth(auth);

		return count == 0 || count2 == 0 ? false : true;
	}

	@Override
	public Member select(Member member) throws Exception {
		return mapper.selectMember(member);
	}

	@Override
	@Transactional
	public boolean update(Member member) throws Exception {

		int count = mapper.updateMember(member);

		return count == 0 ? false : true;
	}

	@Override
	@Transactional
	public boolean delete(Member member) throws Exception {

		int count = mapper.deleteMember(member);

		return count == 0 ? false : true;
	}
	
	@Override
	@Transactional
	public boolean deleteAuth(Member member) throws Exception {
		int count = mapper.deleteAuth(member);

		return count == 0 ? false : true;
	}

	@Override
	@Transactional
	public List<Member> list() throws Exception {
		return mapper.list();
	}

}