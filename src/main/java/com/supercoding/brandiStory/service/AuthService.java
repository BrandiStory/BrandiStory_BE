package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.config.security.JwtTokenProvider;
import com.supercoding.brandiStory.repository.entity.UserEntity;
import com.supercoding.brandiStory.repository.entity.enums.SexType;
import com.supercoding.brandiStory.repository.entity.role.Roles;
import com.supercoding.brandiStory.repository.entity.role.UserRoles;
import com.supercoding.brandiStory.repository.userRole.RolesRepository;
import com.supercoding.brandiStory.repository.userRole.UserRolesRepository;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;
    private final UserRolesRepository userRolesRepository;

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

        // 아이디 동일 여부 체크
        if (userJpaRepository.existsByEmail(email)) {
            return false;
        }

        // email 동일 여부 체크후, 통과시 유저 생성
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

        // 기본 ROLE_USER 저장
        Roles roles = rolesRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new NotFoundException("ROLE_USER를 찾을 수가 없습니다."));

        userRolesRepository.save(
            UserRoles.builder()
                    .userEntity(userEntity)
                    .roles(roles)
                    .build()
        );
        return true;
    }

    @Transactional
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

            List<String> roles = userEntity.getUserRoles()
                    .stream()
                    .map(UserRoles::getRoles)
                    .map(Roles::getName)
                    .collect(Collectors.toList());
//            log.info("login roles : {}", roles);

            return jwtTokenProvider.createAccessToken(email, roles);
        } catch (Exception e) {
            throw new NotAcceptException("로그인 할 수 없습니다.");
        }
    }

    @Transactional
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

    @Transactional
    public void deleteUser(String email) {
        Optional<UserEntity> userOptional = userJpaRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            userJpaRepository.delete(userEntity);
        } else {
            throw new NotFoundException("해당 email(" + email + ")은 존재하지 않습니다.");
        }
    }

    public void searchToken(String token) {
        jwtTokenProvider.validateAccessToken(token);
    }

    public String printTokenInfo(String token) {
        return jwtTokenProvider.getAccessTokenInfo(token);
    }

    public String getEmail(String token) {
        return jwtTokenProvider.getEmailFromToken(token);
    }

    public Integer getUserId(String email) {
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("email로 해당 user를 찾을 수 없습니다."));
        Integer userId = userEntity.getUsersId();
        return userId;
    }
}
