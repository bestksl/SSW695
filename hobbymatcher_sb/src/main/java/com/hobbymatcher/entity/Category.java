package com.hobbymatcher.entity;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 3886854875271518921L;

	private String id;
	private String name;

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
}
