package com.supercoding.brandiStory.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUp {
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String gender;
//    private MultipartFile profile;
}
