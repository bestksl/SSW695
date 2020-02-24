package com.hobbymatcher.entity;

public class Permission {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getPermTag() {
		return permTag;
	}

	public void setPermTag(String permTag) {
		this.permTag = permTag;
	}

	private String id;
	private String permName;
	private String permTag;
}
