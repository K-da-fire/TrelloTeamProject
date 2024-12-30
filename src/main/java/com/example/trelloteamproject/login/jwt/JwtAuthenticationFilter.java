package com.example.trelloteamproject.login.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // JWT 토큰을 Authorization 헤더에서 가져오기
        String token = resolveToken(request);

        if (token != null && jwtTokenProvider.isExpired(token)) {
            // 토큰이 만료되었으면 인증 정보를 설정하지 않음
            filterChain.doFilter(request, response);
            return;
        }

        if (token != null && !jwtTokenProvider.isExpired(token)) {
            // 토큰이 유효하면 인증 객체를 생성하여 SecurityContext에 설정
            String username = jwtTokenProvider.getUsername(token);
            String role = jwtTokenProvider.getRole(token);

            // UsernamePasswordAuthenticationToken 생성
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, null); // 여기에 추가적인 권한을 설정할 수 있음

            // SecurityContext에 인증 객체를 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response); // 다음 필터로 요청을 전달
    }

    // HTTP 요청에서 토큰을 추출하는 메서드
    private String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7); // "Bearer "를 제거하고 토큰만 반환
        }
        return null;
    }
}
