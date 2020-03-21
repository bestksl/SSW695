package com.hobbymatcher.entity;

import java.io.Serializable;

public class Hobby implements Serializable {

	private static final long serialVersionUID = 3886854875271518921L;

	private String id;
	private String name;
	private Integer categoryId;
	private String description;
	private Boolean plus18Only;
	private String photoId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getPlus18Only() {
		return plus18Only;
	}

	public void setPlus18Only(Boolean plus18Only) {
		this.plus18Only = plus18Only;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
}
