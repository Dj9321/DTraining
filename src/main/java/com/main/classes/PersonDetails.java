package com.main.classes;

public record PersonDetails(String name, int age, String Dob) implements PersonInterface {

	@Override
	public String getJobType() {
		return "Business Analyst";
	}


}