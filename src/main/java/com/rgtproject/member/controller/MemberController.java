package com.rgtproject.member.controller;

import java.sql.SQLException;

import javax.naming.AuthenticationException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgtproject.global.common.ErrorResponse;
import com.rgtproject.member.dto.RequestMemberDto;
import com.rgtproject.member.dto.ResponseMemberDto;
import com.rgtproject.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User API")
public class MemberController {
	private final MemberService memberService;
	@Operation(
		summary = "회원가입", description = "회원가입 기능구현", responses = {
		@ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = ResponseMemberDto.class))),
		@ApiResponse(responseCode = "500", description = "예외발생. 관리자 문의", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	})
	/**
	 * 회원가입
	 * @param dto
	 * @return
	 */
	@PostMapping(value="/signup",consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>memberSignUp(@RequestBody RequestMemberDto dto) throws SQLException {
		ResponseMemberDto responseMemberDto = memberService.memberSignup(dto);
		return ResponseEntity.ok().body(responseMemberDto);
	}
	@Operation(security = {
		@SecurityRequirement(name = "JWT")},
		summary = "로그인", description = "로그인 기능구현", responses = {
		@ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = ResponseMemberDto.class))),
		@ApiResponse(responseCode = "401", description = "로그인 실패",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "500", description = "예외발생. 관리자 문의", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
	})
	/**
	 * 로그인
	 * @param dto
	 * @return
	 */
	@PostMapping(value="/signin" ,consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?>memberSignIn(@RequestBody RequestMemberDto dto) throws SQLException,
		AuthenticationException {
		ResponseMemberDto responseMemberDto = memberService.memberSignIn(dto);
		return ResponseEntity.ok().body(responseMemberDto);
	}




}
