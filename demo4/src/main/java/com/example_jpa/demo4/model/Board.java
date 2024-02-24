package com.example_jpa.demo4.model;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity(name="tbl_board4")
@Getter @Setter
public class Board {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long num;
	
	private String title;
	private String content;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regdate")
	private Date regdate;
	
	private Long hitcount;
	private Long replycnt;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Comment> comments;
 	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@PrePersist
	public void prePersist() {
		this.hitcount = this.hitcount == null ? 0 : this.hitcount;
		this.replycnt = this.replycnt == null ? 0 : this.replycnt;
	}
	
	
	
	
}
