package com.supercoding.brandiStory.service;

import com.supercoding.brandiStory.repository.entity.UserEntity;
import com.supercoding.brandiStory.repository.users.UserJpaRepository;
import com.supercoding.brandiStory.service.exceptions.NotFoundException;
import com.supercoding.brandiStory.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class MypageService {
    private final UserJpaRepository userJpaRepository;

    public UserDTO getUserInfo(UserDTO userDTO) {
        UserEntity mypageEntity = userJpaRepository.findById(userDTO.getUsersId())
                .orElseThrow(() -> new NotFoundException("마이페이지를 불러올 수 없습니다."));
        return convertToDto(mypageEntity);
    }

    private UserDTO convertToDto(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsersId(userEntity.getUsersId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setName(userEntity.getUsername());
        userDTO.setPhone(userEntity.getPhoneNumber());
        userDTO.setAddress(userEntity.getAddress());
        userDTO.setGender(userEntity.getGender());
        userDTO.setCreatedAt(userEntity.getCreatedAt());
        return userDTO;
    }

}
