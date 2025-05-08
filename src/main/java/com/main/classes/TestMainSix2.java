package com.main.classes;

public class TestMainSix2 {

	public static void main(String[] args) {
		char a;

		Person p = new Person("D", "D");
		Object c = (Object) p.getId();
		a = (char) c;
		System.out.println(" Printing: " + a);

	}
}
