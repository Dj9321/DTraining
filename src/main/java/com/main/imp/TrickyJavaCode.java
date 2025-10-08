package com.main.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.main.classes.AbstractClassA;
import com.main.classes.InterfaceA;
import com.main.classes.TProduct;

public class TrickyJavaCode extends AbstractClassA implements InterfaceA {

	// we can have same variable name as in super class
	public double price;

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

		// 4 See Car class in another package. Child class constructor calling parent

		// 5 StringBuffer and String

		StringBuffer sb = new StringBuffer("Java");
		String s = "Java";
		if (sb.equals(s)) {
			System.out.println("StringBuffer matched s");
		} else if (sb.toString().equals(s)) {
			System.out.println("StringBuffer converted to string matched string");
		}

		// 6: Abstract class need not implement methods in an Interface.

		// 7: concat
		String ta = "A ";
		ta = ta.concat("B ");
		String tb = "C ";
		ta = ta.concat(tb);
		ta.replace('C', 'D'); // this line does nothing as ta = is not there (assignment)
		ta = ta.concat(tb);
		System.out.println(ta);

		// 8: for loop to skip numbers
		int in[] = { 1, 2, 3, 4, 5 };
		for (int k = 0; k < 5; k += 2) { // we can write like this as well k+=2 > increases k by 2
			System.out.print(in[k]);
		}

		// 9: import a.* doesn't import a.b.* (b is a subfolder)

		// 10:
		TProduct t = new TProduct();
		t.price = 100; // we can also set like this also without calling the setter method.
		IO.println();
		System.out.println("Price: " + t.price);

		// 11. 2D array
		String[][] twoDArray = new String[2][2];
		twoDArray[0][0] = "red";
		twoDArray[0][1] = "blue";
		twoDArray[1][0] = "small";
		twoDArray[1][1] = "medium";

		for (String[] array1 : twoDArray) { // first x = 0, then x=1
			for (String str : array1) { // iterates: y=0,1
				System.out.println(str);
			}
			System.out.println("End of a loop");
		}

		// 12: for Runtime exceptions no try-catch or throws is needed. Only for compile
		// time it is needed.
		try {
			c.abc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.def();

		// 13. x-- in another method doesn't work here
		int x = 5;
		while (isAvailable(x)) {
			System.out.println(x);
			x--;
		}
		// 14. boolean true in switch-case
		boolean opt = true;
//		Cannot switch on a value of type boolean. Only convertible int values, strings or enum variables are permitted
//		switch(opt) {}

		// 15 do-while > do works in the first iteration before it reaches while
		// condition (if it fails)
		int num = 5;
		do {
			System.out.println(num-- + " ");
		} while (num == 0);

		// 16: x value increases on ++
		int x1 = 100;
		int a1 = x1++;
		int b1 = ++x1;
		int c1 = x1++;
		int d1 = (a1 < b1) ? (a1 < c1) ? a1 : (b1 < c1) ? b1 : c1 : x1;
		// a1 = 100 b1 = 102; c1 = 102; d1 = 100
		System.out.println("D1: " + d1);
		System.out.println(x1); // here with ++ x value changes gradually
	}

	public static boolean isAvailable(int x) {
//		if(x-- > 0) {
//			System.out.println(x);
//			return true;
//		} else {
//			System.out.println(x);
//			return false;
//		}
		// here even though it reduces x after the statement, it is pass by value so x
		// in the calling method doesn't change.
		return x-- > 0 ? true : false;
	}

	void abc() throws Exception {
		System.out.println("abc method");
	}

	void def() throws RuntimeException {
		System.out.println("def method");
	}

	// We can keep protected from default if the calls is in the same package.
	// default > package level to protected = package + subclasses
	// If it is in different package then it doesn't work as it is private to a
	// different package > so only option is to make it either public or protected
	// to access in another package.
	public void abstractMethod() {

	}

	public void updatePrice(TProduct product, double price, String e) {
		price = price * 2;
		product.price = product.price + price;
		e = e + " World";
		e = e.concat(" W");
	}

}
