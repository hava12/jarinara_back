package com.example.jarinara_back.interfaces.todo;

import com.example.jarinara_back.application.todo.TodoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/todo")
	public ResponseEntity<?> testTodo() {
		String str = todoService.saveTodo();
		List<String> list = new ArrayList<>();
		list.add(str);
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/todo/{id}")
	public ResponseEntity<?> testTodoOne(@PathVariable String id) {

		String str = todoService.saveTodo();
		List<String> list = new ArrayList<>();
		list.add(str);
		return ResponseEntity.ok().body(null);
	}
}
