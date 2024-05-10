package com.rgtproject.global.auth;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rgtproject.global.exception.CustomException;
import com.rgtproject.global.util.utils.CustomTokenUtil;
import com.rgtproject.member.vo.MemberVO;
import com.rgtproject.token.provider.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * 인가 작업을 위한 Interceptor
 */
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
	private final JwtProvider jwtProvider;
	private static final Pattern AUTH_REQUIRED_URI_PATTERN = Pattern.compile(
		"^/orders.*");
	@Override
	public void postHandle(
		HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		AuthenticationContextHolder.clearContext();
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

		if(!requiresAuthentication(request.getRequestURI())) return true;

		String accessToken = CustomTokenUtil.resolveToken(request);
		if(accessToken==null){
			//인증실패
			throw new CustomException.InvalidTokenException(HttpStatus.FORBIDDEN.value(),"InvalidToken");
		}

		MemberVO authentication = jwtProvider.getAuthentication(accessToken);
		AuthenticationContextHolder.setAuthentication(authentication);

		return true;
	}
	// 권한이 필요한 URI 패턴인지 확인하는 메소드
	private boolean requiresAuthentication(String requestURI) {
		return AUTH_REQUIRED_URI_PATTERN.matcher(requestURI).matches();
	}

}
