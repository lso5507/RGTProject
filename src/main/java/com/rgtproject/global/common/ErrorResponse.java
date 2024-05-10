package com.rgtproject.global.common;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "실패에 대한 응답 값")
@AllArgsConstructor
public class ErrorResponse {
	@Schema(title = "HTTP 상태값")
	private int status;

	@Schema(title = "String 배열형태의 에러메시지들입니다.")
	private List<String> message = new ArrayList<>();
	@Schema(title = "HTTP 상태코드")
	private String code;

}