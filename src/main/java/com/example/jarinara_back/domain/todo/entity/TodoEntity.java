package com.example.jarinara_back.domain.todo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator = "system-uuid") // ID 자동 생성
	@GenericGenerator(name = "system-uuid", strategy = "uuid") // Hibernate가 제공하는 기본 Generator가 아닌 다만의 Generator를 사용하고 싶을 경우 이용
	private String id;
	private String userId;
	private String title;
	private boolean done;
}
