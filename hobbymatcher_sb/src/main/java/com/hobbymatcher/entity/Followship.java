package com.hobbymatcher.entity;

import java.util.Date;

public class Followship {

	private Integer userId;
	private Integer hobbyId;
	private Date onDatetime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(Integer hobbyId) {
		this.hobbyId = hobbyId;
	}

	public Date getOnDatetime() {
		return onDatetime;
	}

	public void setOnDatetime(Date onDatetime) {
		this.onDatetime = onDatetime;
	}
}