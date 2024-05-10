package com.rgtproject.member.vo;

import com.rgtproject.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberVO {
	private String userId;
	private Long id;
	public MemberVO(){

	}

	public void setId(Long id) {
		this.id = id;
	}

	public MemberVO(Member member){
		this.userId=member.getUserId();
	}
	public MemberVO(String userId){
		this.userId=userId;
	}
}
