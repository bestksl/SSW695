package com.hobbymatcher.entity;

import java.util.Date;

public class Review {

	private int id;
	private String ownerType;
	private int ownerId;
	private int parentId;
	private int byUserId;
	private String byUserFirst;
	private String byUserLast;
	private String byUserPhotoId;
	private String content;
	private Date onDatetime;
	private int rate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getByUserId() {
		return byUserId;
	}

	public void setByUserId(int byUserId) {
		this.byUserId = byUserId;
	}

	public String getByUserFirst() {
		return byUserFirst;
	}

	public String getByUserLast() {
		return byUserLast;
	}

	public String getByUserPhotoId() {
		return byUserPhotoId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getOnDateTime() {
		return onDatetime;
	}

	public void setOnDateTime(Date onDateTime) {
		this.onDatetime = onDateTime;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
