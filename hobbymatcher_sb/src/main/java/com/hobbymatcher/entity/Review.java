package com.hobbymatcher.entity;

import java.util.Date;
import java.util.List;

public class Review extends Comment {

	private Integer rate;
	private List<Comment> comments;

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
