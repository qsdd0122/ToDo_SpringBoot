package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{
	//스프링데이터 JPA가 메서드 이름을 파싱하여 쿼리 실행
	List<TodoEntity> findByUserId(String userId);
	
}
