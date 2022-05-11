package com.example.jarinara_back.application.user;

import com.example.jarinara_back.domain.user.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
	public UserEntity create(final UserEntity userEntity);

	UserEntity getByCredentials(String userId, String password, final PasswordEncoder passwordEncoder);
}
