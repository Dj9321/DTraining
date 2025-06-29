package com.main.classes;

public sealed interface PersonInterface permits PersonDetails {

	String getJobType();

}
