package com.main.classes;

public record EmployeeRecord(String name, int age, String department, int salary) {

	public EmployeeRecord {
		if (name.isBlank()) {
			throw new IllegalArgumentException("Name cannot be blank");
		}
	}

//	public EmployeeRecord(String name) {
//			EmployeeRecord(name, age, department);
//
//	}
}
