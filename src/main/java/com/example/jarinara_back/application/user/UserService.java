package com.example.jarinara_back.application.user;

import com.example.jarinara_back.domain.user.entity.UserEntity;

public interface UserService {
	public UserEntity create(final UserEntity userEntity);

	UserEntity getByCredentials(String userId, String password);
}
