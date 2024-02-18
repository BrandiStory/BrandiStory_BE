package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.config.security.JwtTokenProvider;
import com.supercoding.brandiStory.repository.entity.UserEntity;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
import com.supercoding.brandiStory.repository.entity.enums.RoleType;
import com.supercoding.brandiStory.repository.entity.enums.SexType;
import com.supercoding.brandiStory.service.exceptions.JwtTokenException;
import com.supercoding.brandiStory.service.exceptions.NotAcceptException;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.web.dto.Login;
import com.supercoding.brandiStory.web.dto.SignUp;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean signUp( SignUp signUpRequest) {
        log.info("signUpRequest : {}", signUpRequest);
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();
        String address = signUpRequest.getAddress();
        String phoneNumber = signUpRequest.getPhone();
        String gender = signUpRequest.getGender();
        String username = signUpRequest.getUsername();
//        MultipartFile profile = signUpRequest.getProfile();

        if (userJpaRepository.existsByEmail(email)) {
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
                                .gender(SexType.valueOf(gender))
                                .build()
                ));
        return true;
    }

    public String login(Login loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserEntity userEntity = userJpaRepository.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("email로 user를 찾을 수 없습니다."));

            List<String> roles = Arrays.stream(RoleType.values())
                                        .map(Enum::name)
                                        .collect(Collectors.toList());

//            log.info("login roles : {}", roles);

            return jwtTokenProvider.createAccessToken(email, roles);
        } catch (Exception e) {
            throw new NotAcceptException("로그인 할 수 없습니다.");
        }
    }

    public void logout(HttpServletRequest httpServletRequest) throws JwtTokenException{
        try {
            String accessToken = jwtTokenProvider.resolveToken(httpServletRequest);
            log.info("accessToken: {}", accessToken);
            jwtTokenProvider.expireAccessToken(accessToken);

        } catch (Exception e) {
            String eMessage = e.getMessage();
            log.error("logout error: {}", eMessage);
            throw new JwtTokenException(eMessage);
        }
    }
}
