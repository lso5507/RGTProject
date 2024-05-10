package com.rgtproject.member.dto;

import com.rgtproject.global.util.utils.PasswordUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "MemberRequestDto")
public class RequestMemberDto {
	@Schema(description = "UserId")
	private String id;
	@Schema(description = "Psssword")
	private String password;
}
