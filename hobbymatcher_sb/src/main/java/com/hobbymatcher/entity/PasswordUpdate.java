package com.hobbymatcher.entity;

import java.io.Serializable;

public class PasswordUpdate implements Serializable {

	private static final long serialVersionUID = -1171939848141912952L;

	private String current;
	private String newPassword;
	private String confirmed;

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
}