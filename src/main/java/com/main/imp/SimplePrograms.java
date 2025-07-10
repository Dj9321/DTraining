package com.main.imp;

public class SimplePrograms {

	public static void main(String[] args) {
		SimplePrograms s = new SimplePrograms();
		String reverse = s.reverseOfString("DHJ");
		System.out.println(reverse);
	}

	public String reverseOfString(final String input) {
		// recursive termination
		if (input.length() <= 1) {
			return input;
		}
		// seperating first character and remaining characters
		char firstChar = input.charAt(0);
		String remaining = input.substring(1);
		// recursive descent
		return reverseOfString(remaining) + firstChar;
	}

}
