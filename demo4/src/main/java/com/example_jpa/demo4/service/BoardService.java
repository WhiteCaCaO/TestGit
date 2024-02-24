package com.example_jpa.demo4.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example_jpa.demo4.model.Board;
import com.example_jpa.demo4.model.User;
import com.example_jpa.demo4.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)    // 트랜잭션을 수동으로 처리해야함
@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository; 
	
	
	@Transactional    // 수동처리
	public void insert(Board board, User user) {
		board.setUser(user);
		boardRepository.save(board);
	}
	
	public Page<Board> findAll(String field, String word, Pageable pageable){
		Page<Board> lists = boardRepository.findAll(pageable);
		if(field.equals("title")) {
			lists = boardRepository.findByTitleContaining(word,pageable);
		}else if(field.equals("content")) {
			lists = boardRepository.findByContentContaining(word,pageable);
		}
		return lists;
	}
	
	
	public Page<Board> findAll(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	
	public List<Board> list(){
		return boardRepository.findAll();
	}
	
	public long count(String field, String word) {
		long count = boardRepository.count();
		if(field.equals("title")) {
			count = boardRepository.cntTitleSearch(word);
		}else if(field.equals("content")) {
			count = boardRepository.cntContentSearch(word);
		}
		return count;
	}
	
	public Long count() {
		return boardRepository.count();
	}
	
	@Transactional
	public Board view(long num) {
	Board board	= boardRepository.findById(num).get();
	board.setHitcount(board.getHitcount()+1);
		return board;
	}
	
	@Transactional
	public void delete(long num) {
		boardRepository.deleteById(num);
	}
	
	@Transactional
	public void update(Board board) {
		Board b = boardRepository.findById(board.getNum()).get();
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setRegdate(new Date());
	}
	
}
