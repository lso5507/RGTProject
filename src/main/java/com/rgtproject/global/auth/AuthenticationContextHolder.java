package com.rgtproject.global.auth;
import com.rgtproject.member.vo.MemberVO;

public class AuthenticationContextHolder {

	private static final ThreadLocal<MemberVO> authenticationHolder = new ThreadLocal<>();

	public static void clearContext() {
		authenticationHolder.remove();
	}

	public static MemberVO getAuthentication() {
		MemberVO memberVO = authenticationHolder.get();
		if (memberVO == null) {
			memberVO = new MemberVO();
			authenticationHolder.set(memberVO);
		}
		return memberVO;
	}

	public static void setAuthentication(MemberVO memberVO) {
		authenticationHolder.set(memberVO);
	}

}