package com.rgtproject.order.dto;

import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrderDto {
	@Schema(description = "매장ID")
	private String storeId;
	@Schema(description = "테이블번호")
	private Long storeNum;
	@JsonIgnore
	private Long userId;
	@JsonIgnore
	private String userAccount;

}
