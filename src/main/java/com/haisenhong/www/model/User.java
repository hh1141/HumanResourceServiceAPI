package com.haisenhong.www.model;

public class User {

	private Long id;
	private String name;
	private String department;
	private String title;
	private Integer age;
	public User() {}
	public User(String name, String department, String title, Integer age) {
		this.name = name;
		this.department = department;
		this.title = title;
		this.age = age;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
