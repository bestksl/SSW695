package com.hobbymatcher.entity;

import java.util.Date;

public class Participant {

	private Integer id;
	private String firstName;
	private String nickName;
	private String lastName;
	private String email;
	private Gender gender;
	private String photoId;
	private Date requestedOnDatetime;
	private Date approvedOnDatetime;
	private Date participatedOnDatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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