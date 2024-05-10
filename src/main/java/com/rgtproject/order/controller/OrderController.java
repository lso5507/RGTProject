package com.rgtproject.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rgtproject.global.auth.AuthenticationContextHolder;
import com.rgtproject.global.common.ErrorResponse;
import com.rgtproject.member.dto.ResponseMemberDto;
import com.rgtproject.member.vo.MemberVO;
import com.rgtproject.order.dto.RequestOrderDto;
import com.rgtproject.order.dto.ResponseOrderDto;
import com.rgtproject.order.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order API")
public class OrderController {
	private final OrderService orderService;

	@Operation(
		security = {
			@SecurityRequirement(name = "JWT")},
		summary = "주문요청", description = "주문요청 기능구현", responses = {
		@ApiResponse(responseCode = "200", description = "성공",content = @Content(schema = @Schema(implementation = ResponseOrderDto.class))),
		@ApiResponse(responseCode = "403", description = "권한없음",content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
		@ApiResponse(responseCode = "500", description = "예외발생. 관리자 문의", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@PostMapping("/save")
	public ResponseEntity<?> orderSave(@RequestBody RequestOrderDto dto){
		getAuthentication(dto);
		ResponseOrderDto responseOrderDto = orderService.orderSave(dto);
		return ResponseEntity.ok().body(responseOrderDto);
	}


	private static void getAuthentication(RequestOrderDto dto) {
		//get Authentication
		MemberVO authentication = AuthenticationContextHolder.getAuthentication();
		dto.setUserAccount(authentication.getUserId());
	}
}
