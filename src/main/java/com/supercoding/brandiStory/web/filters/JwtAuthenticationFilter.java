package com.supercoding.brandiStory.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercoding.brandiStory.config.security.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwtToken = jwtTokenProvider.resolveToken(request);

            if (jwtToken != null && jwtTokenProvider.validateAccessToken(jwtToken)) {
                Authentication auth = jwtTokenProvider.getAuthentication(jwtToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);
        } catch (MalformedJwtException e) {
            setResponse(response, 401, "손상된 토큰입니다");
        } catch (ExpiredJwtException e) {
            setResponse(response, 401,"만료된 토큰입니다");
        } catch (UnsupportedJwtException e) {
            setResponse(response, 401,"지원하지 않는 토큰입니다");
        } catch (SignatureException e) {
            setResponse(response, 401,"시그니처 검증에 실패한 토큰입니다");
        }
    }

    private void setResponse(HttpServletResponse response, Integer errorCode, String msg) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode);
        response.getWriter().print(msg);
    }
}
