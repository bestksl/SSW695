package com.hobbymatcher.entity;

import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Comment {

	private Integer id;
	@NonNull
	private String ownerType;
	@NonNull
	private Integer ownerId;
	private Integer parentId;
	@NonNull
	private Integer byUserId;
	private String byUserFirst;
	private String byUserLast;
	private String byUserPhotoId;
	@NotBlank
	private String content;
	private Date onDatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getByUserId() {
		return byUserId;
	}

	public void setByUserId(Integer byUserId) {
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
}
