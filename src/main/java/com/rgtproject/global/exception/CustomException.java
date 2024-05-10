package com.rgtproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
	@AllArgsConstructor
	@Getter
	public static class InvalidTokenException extends  CustomException{
		private Integer code;
		private String message;
	}
}
