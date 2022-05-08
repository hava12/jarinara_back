package com.example.jarinara_back.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity // 테이블과 매핑한다고 JPA에게 알려줌 엔티티 클래스
@Table(name="USER") // 매핑할 테이블 정보를 알려준다.
@Data
public class UserEntity {

	@Id // 기본 키
	@Column(name = "user_id") // 필드를 컬럼에 매핑
	private String userId;

	@Column(name = "user_name")
	private String userName;
	private String password;

	private Integer age; // 매핑 어노테이션 생략 시 필드명을 사용해서 컬럼명으로 매핑한다.
}
