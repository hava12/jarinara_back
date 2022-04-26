package com.example.jarinara_back.domain.user.controller;

import com.example.jarinara_back.domain.user.request.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class UserController {

	@PostMapping("/user")
	public ResponseEntity<String> postUser (@RequestBody UserRequest userRequest) {
		System.out.println(userRequest);
		return ResponseEntity.ok("ok");
	}

}
