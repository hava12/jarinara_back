package com.example.jarinara_back.config.security;

import com.example.jarinara_back.application.user.jwt.TokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final TokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		try {
			String token = parseBearerToken(request);
			log.info("Filter is running");
			log.info("Token : " + token);

			if (token != null && !token.equalsIgnoreCase("null")) {
				// userId 가져오기. 위조된 경우 예외 처리된다.
				String userId = tokenProvider.validateAndGetUserId(token);
				log.info("Authenticated user Id : " + userId);

				// 인증 완료. SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
				UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);
			}
		} catch (Exception e) {
			log.error("Could not set user authentication in security context", e);
		}
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		// Http 요청의 헤더를 파싱해 Bearer 토큰을 리턴한다.
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}
}
