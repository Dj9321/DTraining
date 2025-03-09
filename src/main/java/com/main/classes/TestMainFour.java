package com.main.classes;

import java.io.File;
import java.io.IOException;

import com.test.others.Car;

public class TestMainFour {
	public static void main(String[] args) throws IOException {
		int octal1 = 0234;
		System.out.println("Hex: " + Long.toHexString(156));
		System.out.println(octal1 + " Octal");
		byte a = 3;
		byte b = 127;
		Integer g = 1000;
		System.out.println((a + b));
		System.out.println("Binary to " + Integer.valueOf("9c", 16));

		int hex = 0xaff;
		System.out.println("Hex " + hex);

		float f = 333.33f;
		int h = (int) f;
		System.out.println(h);

		int i = 1000;
		float j = i;
		System.out.println(j);

		String s1 = "pool";
		String s2 = "pool1";
		System.out.println(s1 == s2);
		System.out.println(s1.length());

		Car c = new Car();
		System.out.println(c.getCar("def"));

		int value = 23;
		assert value >= 20 : " Underweight";
		System.out.println("value is " + value);
		File fs = new File("abc.txt"); // no file yet
		File fs1 = new File("a\\abc.txt");
		fs.exists();
		fs.createNewFile(); //
		fs1.mkdir();
		fs1.createNewFile();

	}
}
