package com.example.jarinara_back.application.todo;

import com.example.jarinara_back.domain.todo.entity.TodoEntity;
import com.example.jarinara_back.infrastructure.todo.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

	private final TodoRepository todoRepository;

	public TodoServiceImpl(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public String saveTodo() {

		// TodoEntity 생성
		TodoEntity todoEntity = TodoEntity.builder().title("My first todo item").build();

		// TodoEntity 저장
		todoRepository.save(todoEntity);

		// TodoEntity 검생
		TodoEntity savedEntity = todoRepository.findById(todoEntity.getId()).get();

		return savedEntity.getTitle();
	}
}
