package com.main.imp;

public class ClassB {
	
	String str = "abcde BDE cdef CDE";
	// get valid capital letter count
	
	public static void main(String[] args) {
		ClassB c = new ClassB();
		// capital letters are from 65 to 90 & small from 97 - 122
		c.validCapLetters();
		
	}

	public void validCapLetters() {
		char [] c = str.toCharArray();
		int d = c[7];
		int d1 = 'a';
		int d2 = 'z';
		int d3 = 'A';
		int d4 = 'Z';
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(d3);
		System.out.println(d4);
	}
}
