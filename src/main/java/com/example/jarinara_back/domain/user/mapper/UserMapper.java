package com.example.jarinara_back.domain.user.mapper;

import com.example.jarinara_back.common.mapper.GenericMapper;
import com.example.jarinara_back.domain.user.entity.UserEntity;
import com.example.jarinara_back.domain.user.request.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, UserEntity> {
}
