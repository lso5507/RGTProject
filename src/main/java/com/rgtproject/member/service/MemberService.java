package com.rgtproject.member.service;

import java.sql.SQLException;

import javax.naming.AuthenticationException;

import com.rgtproject.member.dto.RequestMemberDto;
import com.rgtproject.member.dto.ResponseMemberDto;
import com.rgtproject.member.vo.MemberVO;

public interface MemberService {
	public ResponseMemberDto memberSignup(RequestMemberDto dto) throws SQLException;
	public ResponseMemberDto memberSignIn(RequestMemberDto dto) throws SQLException, AuthenticationException;
	public Long selectId(String memberId);
}
