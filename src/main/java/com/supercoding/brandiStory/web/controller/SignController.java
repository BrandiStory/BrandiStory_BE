package com.supercoding.brandiStory.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( value = "/v1/api/sign")
public class SignController {

//    private final AuthService authService;
//
//    @PostMapping(value = "/register")
//    public String register(@RequestBody SignUp signUpRequest){
//        boolean isSuccess = authService.signUp(signUpRequest);
//        return isSuccess ? "회원가입 성공하였습니다." : "회원가입 실패하였습니다.";
//    }
//
//    @PostMapping(value = "/login")
//    public String login(@RequestBody Login loginRequest, HttpServletResponse httpServletResponse){
//        String token = authService.login(loginRequest);
//        httpServletResponse.setHeader("X-AUTH-TOKEN", token);
//        return "로그인이 성공하였습니다.";
//    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
