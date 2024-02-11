package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.users.UserAuthRepository;
import com.supercoding.brandiStory.repository.users.UserEntity;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
import com.supercoding.brandiStory.repository.users.enums.SexType;
import com.supercoding.brandiStory.service.mapper.UserMapper;
import com.supercoding.brandiStory.web.dto.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final UserJpaRepository userJpaRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean signUp(SignUp signUpRequest) {
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String address = signUpRequest.getAddress();
        String phoneNumber = signUpRequest.getPhoneNumber();
        String sex = signUpRequest.getSex();
        String username = signUpRequest.getUsername();
//        MultipartFile profile = signUpRequest.getProfile();

        if (userAuthRepository.existsByEmail(email)) {
            return false;
        }
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                .orElseGet(() -> userJpaRepository.save(
                        UserEntity.builder()
                                .email(email)
                                .password(passwordEncoder.encode(password))
                                .address(address)
                                .phoneNumber(phoneNumber)
                                .username(username)
                                .sex(SexType.valueOf(sex))
                                .build()
                ));
        return true;
    }
}
