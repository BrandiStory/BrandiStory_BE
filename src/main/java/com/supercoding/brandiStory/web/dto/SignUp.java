package com.supercoding.brandiStory.web.dto;

import com.supercoding.brandiStory.repository.users.enums.SexType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUp {
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String sex;
//    private MultipartFile profile;
}
