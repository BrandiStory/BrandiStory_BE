package com.supercoding.brandiStory.web.controller;

import com.supercoding.brandiStory.service.MypageService;
import com.supercoding.brandiStory.web.dto.CartItemDTO;
import com.supercoding.brandiStory.web.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MypageController implements ApiController{

    private final MypageService mypageService;

    @Operation(summary = "마이페이지 ")
    @PostMapping("/mypage")
    public UserDTO getCartItems(@RequestBody UserDTO userDTO){
        return mypageService.getUserInfo(userDTO);
    }
}
