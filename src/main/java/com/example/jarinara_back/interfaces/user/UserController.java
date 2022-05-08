package com.example.jarinara_back.interfaces.user;

import com.example.jarinara_back.application.user.UserService;
import com.example.jarinara_back.application.user.jwt.TokenProvider;
import com.example.jarinara_back.domain.user.entity.UserEntity;
import com.example.jarinara_back.domain.user.mapper.UserMapper;
import com.example.jarinara_back.domain.user.request.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@AllArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;
	private final TokenProvider tokenProvider;

	@PostMapping("/signUp")
	public ResponseEntity<UserDto> postUser (@RequestBody UserDto userRequest) {
		UserEntity userEntity = userMapper.toEntity(userRequest);
		userEntity = userService.create(userEntity);
		UserDto userResponse = userMapper.toDto(userEntity);

		System.out.println("userRequest : " + userRequest);
		System.out.println("userEntity = " + userEntity);
		System.out.println("userResponse = " + userResponse);

		return ResponseEntity.ok(userResponse);
	}

	@PostMapping("/signIn")
	public ResponseEntity<UserDto> postLogin (@RequestBody UserDto userRequest) {
		UserEntity userEntity = userService.getByCredentials(userRequest.getUserId(), userRequest.getPassword());

		System.out.println("userEntity = " + userEntity);
		if(userEntity != null) {
			final String token = tokenProvider.create(userEntity);
			UserDto userResponse = userMapper.toDto(userEntity);
			userResponse.setToken(token);

			System.out.println("userResponse = " + userResponse);

			return ResponseEntity.ok(userResponse);
		}
		return null;
	}

}
