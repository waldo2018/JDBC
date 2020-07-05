package com.java.test.apache.dbutils;

public class User {
	
	private int id;
	
	private String name;
	
	private String password;
	
	private String address;

	public User() {
		
	}

	public User(int id, String name, String password, String address) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", address=" + address + "]";
	}
	
	
}
