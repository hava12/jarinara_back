package com.example.jarinara_back.interfaces.user;

import com.example.jarinara_back.application.user.UserService;
import com.example.jarinara_back.domain.user.request.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> postUser (@RequestBody UserRequest userRequest) {
		System.out.println(userRequest);
		return ResponseEntity.ok("ok");
	}

	@PostMapping("/login")
	public ResponseEntity<String> postLogin (@RequestBody UserRequest userRequest) {
		System.out.println(userRequest);
		return ResponseEntity.ok("ok");
	}

}
