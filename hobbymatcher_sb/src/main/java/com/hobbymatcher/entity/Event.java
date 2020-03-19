package com.hobbymatcher.entity;

import java.util.Date;

public class Event {

	private Integer id;
	private Integer hobbyId;
	private String title;
	private Date onDatetime;
	private String location;
	private Integer capacity;
	private String description;
	private Boolean plus18Only;
	private String organizer;
	private String photoId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHobbyId() {
		return hobbyId;
	}

	public void setHobbyId(Integer hobbyId) {
		this.hobbyId = hobbyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getOnDatetime() {
		return onDatetime;
	}

	public void setOnDatetime(Date onDatetime) {
		this.onDatetime = onDatetime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
}