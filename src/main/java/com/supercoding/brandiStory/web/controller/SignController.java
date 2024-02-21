package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.service.AuthService;
import com.supercoding.brandiStory.web.dto.Login;
import com.supercoding.brandiStory.web.dto.SignUp;
import com.supercoding.brandiStory.web.dto.UserEmail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/v1/api/sign")
public class SignController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public String register(@RequestBody @Valid SignUp signUpRequest) {
        boolean isSuccess = authService.signUp(signUpRequest);
        return isSuccess ? "회원가입 성공하였습니다." : "회원가입 실패하였습니다.";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody Login loginRequest, HttpServletResponse httpServletResponse) {
        String token = authService.login(loginRequest);
        log.info("token: {}", token);
        httpServletResponse.setHeader("X-AUTH-TOKEN", token);
        return "로그인이 성공하였습니다.";
    }

    @DeleteMapping(value = "/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        authService.logout(httpServletRequest);
        return "로그아웃 성공하였습니다.";
    }

    @DeleteMapping(value = "/unregister")
    public String unregister(@RequestBody UserEmail userEmail) {
        authService.deleteUser(userEmail.getEmail());
        return "회원 탈퇴 성공하였습니다.";
    }

    @GetMapping(value = "/show-token")
    public String showToken(@RequestHeader("Token") String token) {
        String tokenInfo = authService.printTokenInfo(token);
        return tokenInfo;
    }
}
