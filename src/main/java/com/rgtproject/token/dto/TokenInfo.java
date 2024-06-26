package com.rgtproject.token.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {
	@Schema(title = "Bearer")
	private String grantType;
	@Schema(title = "액세스토큰")
	private String accessToken;
}