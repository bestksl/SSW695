package com.hobbymatcher.entity;

import java.util.Date;

public class Participation {

	private Integer userId;
	private Integer eventId;
	private Date requestedOnDatetime;
	private Date approvedOnDatetime;
	private Date participatedOnDatetime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Date getRequestedOnDatetime() {
		return requestedOnDatetime;
	}

	public void setRequestedOnDatetime(Date requestedOnDatetime) {
		this.requestedOnDatetime = requestedOnDatetime;
	}

	public Date getApprovedOnDatetime() {
		return approvedOnDatetime;
	}

	public void setApprovedOnDatetime(Date approvedOnDatetime) {
		this.approvedOnDatetime = approvedOnDatetime;
	}

	public Date getParticipatedOnDatetime() {
		return participatedOnDatetime;
	}

	public void setParticipatedOnDatetime(Date participatedOnDatetime) {
		this.participatedOnDatetime = participatedOnDatetime;
	}
}