package com.rgtproject.global.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum GlobalCode {
	SUCCESS("Success"),
	FAIL("Fail");
	private String value;
}
