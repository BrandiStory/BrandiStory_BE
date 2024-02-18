package com.supercoding.brandiStory.service.mapper;

import com.supercoding.brandiStory.repository.users.UserEntity;
import com.supercoding.brandiStory.web.dto.SignUp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.format.DateTimeFormatter;

@Mapper
public interface UserMapper {

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
