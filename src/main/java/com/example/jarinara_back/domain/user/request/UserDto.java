package com.example.jarinara_back.domain.user.request;

import lombok.Data;

@Data
public class UserDto {

	private String userId;
	private String password;
	private String userName;
	private String age;

	private String token;
}
