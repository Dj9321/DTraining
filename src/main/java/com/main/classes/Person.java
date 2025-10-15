package com.main.classes;

public class Person {

	public String name;
	String id;
	String department;
	String designation;

	public Person(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public Person(String name, String id, String department, String designation) {
//		this.name = name;
//		this.id = id;
		this(name, id); // you can call like this as well
//		Person(name, id); // CAN'T call like this
		this.department = department;
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.getName() + " " + this.getId();
	}
}
