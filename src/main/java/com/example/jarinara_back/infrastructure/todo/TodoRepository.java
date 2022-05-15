package com.example.jarinara_back.infrastructure.todo;

import com.example.jarinara_back.domain.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

}
