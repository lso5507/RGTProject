package com.rgtproject.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class ResponseOrderDto {
	private Integer status;
	private String message;

}
