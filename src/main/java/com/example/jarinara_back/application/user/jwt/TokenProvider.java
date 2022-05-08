package com.example.jarinara_back.application.user.jwt;

import com.example.jarinara_back.domain.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenProvider {
	private static final String SECRET_KEY = "asjfa9sdfsz9j391";

	public String create(UserEntity userEntity) {
		// 기한은 지금부터 1일로 설정
		Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

		return Jwts.builder()
			// header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
			.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
			// payload에 들어갈 내용
			.setSubject(userEntity.getUserId())
			.setIssuer("jarinara application")
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.compact();

		/*
		{
			"alg" : "HS512"
		},
		{
			// payload
			"sub" : "userId",
			"iss" : "jarinara application",
			"iat" : 1595733657,
			"exp" : 1596597657
		},
		// SECRET_KET를 이용해 서명한 부분
		blabla
		 */
	}

	public String validateAndGetUserId(String token) {
		// parseClaimsJws 메서드가 Base64로 디코딩 및 파싱
		// 헤더와 페이로드를 setSigningKey로 넘어온 시크릿을 이용해 서명한 후 token의 서명값과 비교
		// 위조되지 않았다면 페이로드(Claims) 리턴, 위조라면 예외를 던짐
		// 그 중 우리는 userId가 필요하므로 getBody를 부른다.
		Claims claims = Jwts.parser()
			.setSigningKey(SECRET_KEY)
			.parseClaimsJws(token)
			.getBody();

		return claims.getSubject();

	}
}
