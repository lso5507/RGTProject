package com.rgtproject.member.service;

import java.sql.SQLException;
import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import com.rgtproject.global.common.GlobalCode;
import com.rgtproject.global.util.utils.PasswordUtils;
import com.rgtproject.member.dto.RequestMemberDto;
import com.rgtproject.member.dto.ResponseMemberDto;
import com.rgtproject.member.entity.Member;
import com.rgtproject.member.repository.MemberRepository;
import com.rgtproject.member.vo.MemberVO;
import com.rgtproject.token.dto.TokenInfo;
import com.rgtproject.token.provider.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	private final MemberRepository memberRepository;
	private final JwtProvider jwtProvider;
	@Override
	public ResponseMemberDto memberSignup(RequestMemberDto dto) throws SQLException {
		log.info("###Member_Signup###UserId::{}",dto.getId());
		passwordEncrypt(dto);
		Member member = new Member(dto);
		memberRepository.save(member);
		//토큰 생성
		TokenInfo tokenInfo = tokenGenerate(member);
		return new ResponseMemberDto(tokenInfo.getAccessToken(),HttpStatus.OK.value());
	}
	@Override
	public ResponseMemberDto memberSignIn(RequestMemberDto dto) throws SQLException, AuthenticationException {
		log.info("###Member_SignIn###UserId::{}",dto.getId());
		Member rstMember = memberRepository.findByUserId(dto.getId());
		Assert.notNull(rstMember,"###MemberNotfound###");
		if(!PasswordUtils.checkPw(dto.getPassword(),rstMember.getPassword())){
			log.info("###Member_SignIn###Password_Incorrect");
			 throw new AuthenticationException("Password_Incorrect");
		}
		//토큰 생성
		TokenInfo tokenInfo = tokenGenerate(rstMember);
		return new ResponseMemberDto(tokenInfo.getAccessToken(), HttpStatus.OK.value());
	}

	private TokenInfo tokenGenerate(Member rstMember) {
		return jwtProvider.generateToken(new MemberVO(rstMember));
	}
	private  void passwordEncrypt(RequestMemberDto dto) {
		dto.setPassword(PasswordUtils.hashPassword(dto.getPassword()));
	}

}
