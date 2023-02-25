package com.xadmin.usermanegment.bean;


public class User {

	protected int id;
	protected String name;
	protected String email;
	protected String state;
	
	
	
	
	
	public User(String name, String email, String state) {
		super();
		this.name = name;
		this.email = email;
		this.state = state;
	}
	public User(int id, String name, String email, String state) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
