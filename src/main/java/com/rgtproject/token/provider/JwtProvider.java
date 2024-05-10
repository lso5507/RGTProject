package com.rgtproject.token.provider;

import java.security.Key;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rgtproject.member.entity.Member;
import com.rgtproject.member.vo.MemberVO;
import com.rgtproject.token.dto.TokenInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

	private final Key key;

	public JwtProvider(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}


	/** 유저 정보를 가지고 AccessToken을 생성하는 메서드
	 * 토큰 유효시간 - 10 Hours
	 */
	public TokenInfo generateToken(MemberVO member) {
		LocalDateTime now = LocalDateTime.now();
		// Access Token 생성
		LocalDateTime accessTokenExpiresIn = null;
		LocalDateTime refreshTokenExpiresIn = null;
		accessTokenExpiresIn = now.plus(10, ChronoUnit.HOURS); // 2시간
		String accessToken = Jwts.builder()
			.setSubject(member.getUserId())
			.setExpiration(Date.from(accessTokenExpiresIn.atZone(ZoneId.systemDefault()).toInstant()))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();

		return TokenInfo.builder().grantType("Bearer").accessToken(accessToken).build();
	}

	// JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
	public MemberVO getAuthentication(String token) {
		// 토큰 복호화
		Claims claims = validateToken(token);

		if (claims.get("auth") == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}

		// 클레임에서 권한 정보 가져오기
		return new MemberVO(claims.getSubject());
	}

	// 토큰 정보를 검증하는 메서드
	public Claims validateToken(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

	}

}