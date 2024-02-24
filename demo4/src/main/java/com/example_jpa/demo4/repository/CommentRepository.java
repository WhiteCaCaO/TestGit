package com.example_jpa.demo4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example_jpa.demo4.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
