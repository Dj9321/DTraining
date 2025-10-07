package com.main.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.main.classes.TProduct;

public class TrickyJavaCode {

	public static void main(String[] args) {
		// 1. update by value
		TrickyJavaCode c = new TrickyJavaCode();
		TProduct prt = new TProduct();
		prt.price = 200;
		double newPrice = 100;
		String d = "Hello";
		d.concat(" D");
		d = d.concat(" S");
		// here it is pass by value. Objects are pass by reference, Product reference
		// changes. Here String is not changing
		c.updatePrice(prt, newPrice, d);
		System.out.println(prt.price + " : " + newPrice + ": " + d);

		// 2. LocalDate > Below code gives: UnsupportedTemporalTypeException:
		// Unsupported field: HourOfDay
		// because formatting date and time but we are passing only date > so use
		// ISO_DATE instead of ISO_DATE_TIME
		String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE);
		// if you are using date and time use LocalDateTime and not just LocalDate
		String date1 = LocalDateTime.parse("2014-05-04T00:00:00").format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(date);
		System.out.println(date1);

		// 3. short, Short, int, Integer, long, Long
//		The maximum value of the Java short data type is 32,767
		Short s1 = 32700; // Object Short
		short s6 = 32700;
		Integer s2 = 400;
		Long s3 = (long) s1 + s2; // line n1 // its deducting 600 here. Same with short or Object Short
//		Integer s5 = (Integer) s1 + s2; // Can't cast from short to Integer. 
		Integer s5 = (int) s1 + s2; // But we can cast from short to int
//		String s4 = (String) (s3 * s2); // line n2 // Cannot cast from long to String
		// we can also use: Long.valueOf(s1) or s1.longValue();
		Long s7 = s1.longValue() + s2;
		Long l1 = (s3 * s2); // Integer * Long works
//		Integer i1 = (s3 * s2); // Cannot convert from long to Integer (C: Compilation issue)
//		Double d1 = (s3* s2); // Cannot convert from long to Double (C: Compilation issue)
		System.out.println("Sum is " + s3);
		System.out.println("Sum is " + s5);
		System.out.println("Sum is " + s7);
		System.out.println("Sum is " + l1);
	}

	public void updatePrice(TProduct product, double price, String e) {
		price = price * 2;
		product.price = product.price + price;
		e = e + " World";
		e = e.concat(" W");
	}

}
