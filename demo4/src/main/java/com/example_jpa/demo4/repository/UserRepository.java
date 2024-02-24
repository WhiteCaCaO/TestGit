package com.example_jpa.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example_jpa.demo4.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);    // 쿼리 메서드
}
