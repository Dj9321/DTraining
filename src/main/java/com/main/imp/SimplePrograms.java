package com.main.imp;

public class SimplePrograms {

	public static void main(String[] args) {
		SimplePrograms s = new SimplePrograms();
		String reverse = s.reverseOfString("DHJ");
		System.out.println(reverse);
		s.forLoop();
	}

	public void forLoop() {
		for (int i = 0; i < 10; i++) {
			if (i == 8) {
				// returning without completing the loop. Nothing will run after this.
//				return;
			}
			System.out.println("I value is " + i);
		}

		// Enhanced for loop
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int a : arr) {
			System.out.print(a);
		}
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
