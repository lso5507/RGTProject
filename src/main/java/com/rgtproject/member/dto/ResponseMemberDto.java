package com.rgtproject.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseMemberDto {
	@Schema(title = "JWT_AccessToken")
	private String accessToken;
	@Schema(title = "status")
	private Integer status;
}
