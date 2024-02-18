package com.supercoding.brandiStory.service.security;

import com.supercoding.brandiStory.repository.userDetails.CustomUserDetails;
import com.supercoding.brandiStory.repository.userPrincipal.UserPrincipal;
import com.supercoding.brandiStory.repository.users.UserEntity;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
import com.supercoding.brandiStory.repository.users.enums.RoleType;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userJpaRepository.findByEmail(email)
                .orElseThrow((() -> new NotFoundException(email + " 에 해당하는 user가 존재하지 않습니다.")));

//        UserPrincipal userPrincipal = u

        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .userId(userEntity.getId())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .authorities(Arrays.stream(RoleType.values()).map(Enum::name).collect(Collectors.toList()))
                .build();
        return customUserDetails;
    }
}
