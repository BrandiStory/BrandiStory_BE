package com.supercoding.brandiStory.web.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.supercoding.brandiStory.repository.entity.enums.SexType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {
        private Integer usersId;
        private String email;
        private String username;
        private String password;
        private String phoneNumber;
        private String address;
        private SexType gender;
        private LocalDateTime createdAt;
}
