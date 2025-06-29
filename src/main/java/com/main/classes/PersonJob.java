package com.main.classes;

public final class PersonJob extends PersonAbstractSealed {

	private String jobType;
	private String jobDesignation;

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobDesignation() {
		return jobDesignation;
	}

	public void setJobDesignation(String jobDesignation) {
		this.jobDesignation = jobDesignation;
	}

	@Override
	public String jobType() {
		return jobType;
	}

	@Override
	public String jobDesignation() {
		return jobDesignation;
	}

}
