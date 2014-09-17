package com.northeast.minigenius.model;

public class QuestionState {

	private Long id;
	private QuestionStatus status;
	
	public QuestionState(Long id, QuestionStatus status) {
		this.id = id;
		this.status = status;
	}
	
	public Long getId() {
		return id;
	}
	
	public QuestionStatus getStatus() {
		return status;
	}
}
