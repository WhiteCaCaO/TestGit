package com.example_jpa.demo4.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example_jpa.demo4.config.auth.PrincipalUser;
import com.example_jpa.demo4.model.Board;
import com.example_jpa.demo4.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("insert")
	public String insert() {
		return "board/insert";
	}
	
	@PostMapping("insert")
	public String insert(Board board, 
			@AuthenticationPrincipal PrincipalUser principal) {
//		board.setUser(principal.getUser());
		boardService.insert(board, principal.getUser());
		return "redirect:list";
	}
	
	// 전체보기(페이징+검색)
	@GetMapping("list")
	public String list(Model model,
			@PageableDefault(size = 5, sort = "num", direction = Direction.DESC)Pageable pageable,
			@RequestParam(required = false, defaultValue = "")String field,
			@RequestParam(required = false, defaultValue = "")String word) {
		Page<Board> lists = boardService.findAll(field, word, pageable);
		long count = boardService.count(field, word);
		
		model.addAttribute("count", count);
		model.addAttribute("boards", lists);
		
		return "board/list";
	}
	
	
	// 전체보기(페이징) -> 페이징 정보 + 데이터(Board의 정보)
//	@GetMapping("list")
//	public String list(Model model, 
//			@PageableDefault(size = 5, sort = "num", direction = Direction.DESC)
//			Pageable pageable) {
//		Page<Board> lists = boardService.findAll(pageable);
//		model.addAttribute("count", boardService.count());
//		model.addAttribute("boards", lists);
//		return "board/list";
//	}
	
//	@GetMapping("list")
//	public String list(Model model) {
//		model.addAttribute("count", boardService.count());
//		model.addAttribute("boards", boardService.list());
//		return "board/list";
//	}
	
	@GetMapping("view/{num}")
	public String view(@PathVariable long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "board/view";
	}
	
	@DeleteMapping("delete/{num}")
	@ResponseBody
	public long delete(@PathVariable long num) {
		boardService.delete(num);
		return num;
	}
	
	@GetMapping("update/{num}")
	public String update(@PathVariable long num, Model model) {
		model.addAttribute("board", boardService.view(num));
		return "board/update";
	}
	
	@PutMapping("update")
	@ResponseBody
	public long update(@RequestBody Board board) {
		boardService.update(board);
		return board.getNum();
	}
	
	
}
