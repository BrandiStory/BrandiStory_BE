package com.supercoding.brandiStory.web.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supercoding.brandiStory.config.security.JwtTokenProvider;
import com.supercoding.brandiStory.service.exceptions.ErrorMessage;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

        } catch (JwtException ex) {
            String message = ex.getMessage();
            if(ErrorMessage.UNKNOWN_ERROR.getErrMsg().equals(message)) {
                setResponse(response, ErrorMessage.UNKNOWN_ERROR);
            }
            //잘못된 타입의 토큰인 경우
            else if(ErrorMessage.WRONG_TYPE_TOKEN.getErrMsg().equals(message)) {
                setResponse(response, ErrorMessage.WRONG_TYPE_TOKEN);
            }
            //토큰 만료된 경우
            else if(ErrorMessage.EXPIRED_TOKEN.getErrMsg().equals(message)) {
                setResponse(response, ErrorMessage.EXPIRED_TOKEN);
            }
            //지원되지 않는 토큰인 경우
            else if(ErrorMessage.UNSUPPORTED_TOKEN.getErrMsg().equals(message)) {
                setResponse(response, ErrorMessage.UNSUPPORTED_TOKEN);
            }
            else {
                setResponse(response, ErrorMessage.UNKNOWN_ERROR);
            }
        }
    }

    private void setResponse(HttpServletResponse response, ErrorMessage errorMessage) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().print(errorMessage.getErrMsg());
    }
}
