package com.rgtproject.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
	CONFIRM("수락됨"),
	CANCEL("취소"),
	COMPLETE("완료");
	private String value;
}
