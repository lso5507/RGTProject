package com.rgtproject.member.vo;

import com.rgtproject.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberVO {
	private String userId;
	public MemberVO(Member member){
		this.userId=member.getUserId();

	}
}
