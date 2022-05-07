package com.example.jarinara_back.application.user;

import com.example.jarinara_back.domain.user.entity.UserEntity;
import com.example.jarinara_back.infrastructure.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl {

	private final UserRepository userRepository;

	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getId() == null) {
			throw new RuntimeException("Invalid Id");
		}

		final String userId = userEntity.getId();

		if(userRepository.existsById(userId)) {
			log.warn("Id Already exists {}", userId);
			throw new RuntimeException("userId already Exists");
		}

		return userRepository.save(userEntity);
	}
}
