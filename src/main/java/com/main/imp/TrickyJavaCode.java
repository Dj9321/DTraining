package com.main.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.main.classes.AbstractClassA;
import com.main.classes.Car;
import com.main.classes.InterfaceA;
import com.main.classes.TProduct;

public class TrickyJavaCode extends AbstractClassA implements InterfaceA {

	// we can have same variable name as in super class
	public double price;

	public static void main(String[] args) {

		// Mostly from: https://www.examtopics.com/exams/oracle/1z0-808/

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
		IO.println("*** Local date ***");
		String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE);
		// if you are using date and time use LocalDateTime and not just LocalDate
		String date1 = LocalDateTime.parse("2014-05-04T00:00:00").format(DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(date);
		System.out.println(date1);

		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2014, 06, 20); // yyyy-mm-dd
		System.out.println(ld1);
		System.out.println(ld2);

		// 3. short, Short, int, Integer, long, Long
//		The maximum value of the Java short data type is 32,767
		Short s1 = 32700; // Object Short
		short s6 = 32700;
		Integer s2 = 400;
		Long s3 = (long) s1 + s2; // line n1 // its deducting 600 here if more than 32767.
//		Same with short or Object Short
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
		double do1 = 100; // can keep as 100.0 as well
		System.out.println(do1); // prints as 100.0

		// 4 See Car class in another package. Child class constructor calling parent

		// 5 StringBuffer and String

		StringBuffer sb = new StringBuffer("Java");
		String s = "Java";
		String sbs = sb.toString();
		String sbs1 = sbs;
		if (sbs == sbs1) {
			System.out.println("String buffer toString and assigning to String");
		}
		if (sb.toString() == s) {
			System.out.println("Doesn't reach here");
		}
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

		// 17: list.remove() removes only first occurrence. Use removeAll() for all
		IO.println("*** 17 ***");
		List<String> names = new ArrayList<>();
		names.add("S");
		names.add("D");
		names.add("D"); // D added twice
		names.add("J");
		if (names.remove("D")) { // removes first occurrence & not all
			names.remove("J");
		}
		System.out.println(names);

		// 18: if C extends B and B extends A and all constructors have sysout with
		// letter A or B or C then A B C will be printed in order

		// 19: static variable will be replaced if 2 statements write to it in different
		// instances
		c.st1 = 5;
		TrickyJavaCode tricky1 = new TrickyJavaCode();
		tricky1.st1 = 6;
		System.out.println(c.st1); // Prints 6 and not 5 as another instance changed this static instance

		// 20
		int array[] = new int[5];
		int array1[] = { 1, 2, 4, 5 }; // just in time array

		System.out.println("Hello " + args[0]); // Run configurations > Pass arguments
		// with javac abc.java (you have to mention .java) with java abc (no .java)

		// 21: for loop not initializing
		int i8 = 0, j8 = 7; // we can club same types
		for (i8 = 0; i8 < j8; i8 = i8 + 2) { // here we are initializing i8 but not declaring type int i8
			System.out.println(i8 + " ");
		}

		// 22: qty > 90 discount: 0.5 qut between 80 to 90: discount 0.2
		double qty = 0;
		// even though it is > 80 already > 90 condition is crossed so that won't come
		// > so effectively its 80 to 90
		TrickyJavaCode.discount = qty >= 90 ? 0.5 : qty > 80 ? 0.2 : 0;

		// 23
		if ("S".equals("S") ? true : false) {
			System.out.println("conditional > Tertiary");
		}
		// Objects can share behaviors with other objects. > Polymorphism

		String[] planets = { "Mercury", "Venus", "Earth", "Mars" };
		System.out.println(planets.length); // length is variable here and not method. So no ()
		System.out.println(planets[1].length()); // this is 2nd String length

		StringBuffer sb2 = new StringBuffer("234-S5678-9101-1121");
		String x3 = "XXXX-XXXX-XXXX-";
		System.out.println(x3 + sb2.substring(15, 19));
		StringBuffer x4 = new StringBuffer(x3);
		System.out.println(x4.append(sb2, 15, 19)); // String buffer append also has start, end

		// 24: public class MarutiCar extends Car > You can extend Car class in another
		// package, but if it doesn't have default constructor, then you have to write
		// argument constructor in MarutiCar Class (again super class argument
		// constructor should be protected or public and NOT default)

		// We can't call protected variable (or protected method) in superclass if type
		// of object is
		// superclass type. It should be subclass type only > See MarutiCar.java

		// An object of one type referenced to super type. The object is still subtype
		// so that class methods only will be called > see MarutiCar.java for more
		// Casting doesn't change object itself.

		ArrayList<String> myList = new ArrayList<>();
		String[] sArray;

		try {
			// commented below line as it takes lot of time
//			while (true) { 
//				myList.add("hello"); // "main" java.lang.OutOfMemoryError: Java heap space > This is Runtime Exception
//			}
		} catch (RuntimeException e) {
			System.out.println("Runtime exception caught"); // It doesn't come here
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace(); // doesn't come here as well.
		}

		// 25: sysout String
		System.out.println("5 + 3 = " + 5 + 3); // here it prints 53 and not 8
		System.out.println("5 + 3 = " + (5 + 3));

		// 26: for loop
		String[][] arr = { { "A", "B", "C" }, { "D", "E" } }; // even if you have more elements in 2nd element (sub
																// array) that prints that as well
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
				if (arr[i][j].equals("B")) {
					break; // breaks from this inner loop and goes to outer loop > i will be 1 (next)
				}
			}
			continue; // doesn't matter if we keep this or not as it is at the last line
		}

		// 27: String
		String str1 = " ";
		str1 = str1.trim();
		// for boolean return you can just concatenate with +
		System.out.println(str1.equals("") + "" + str1.isBlank() + str1.isEmpty());

		String[] str2 = new String[2];
		int[] kI = new int[2];
		short[] kS = new short[2];
		long[] kL = new long[2];
		double[] kD = new double[2];

		for (int iStr = 0; iStr < str2.length; iStr++) {
			System.out.println(str2[iStr]); // if we print elements without initializing it just prints null
			IO.println(kI[iStr]); // where as int prints 0 when not initialized
			IO.println(kS[iStr]); // prints 0
			IO.println(kL[iStr]); // prints 0
			IO.println(kD[iStr]); // prints 0.0
		}

		// 28: constructor: see Car.java

//		public Car() {
//			super();
//			this(10);// Constructor cannot have more than one explicit constructor call
//		}

		System.out.println(new Car(10)); // it will print toString() if we implemented else it will print Car@hashCode
		System.out.println(new TrickyJavaCode()); // prints : com.main.imp.TrickyJavaCode@7b23ec81

		// 29: Numbers: putting values in another types
		int iVar = 100;
		float fVar = 100.100f;
		double dVar = 123;
		fVar = iVar;
//		iVar = fVar; // Type mismatch: cannot convert from float to int
//		fVar = dVar;
		dVar = fVar;
//		iVar = dVar;
		dVar = iVar;

		// 30: Overloading main methods don't have any effect when we run the program >
		// only String[] will be considered > see main(int[] args) and Object[] in this
		// file

	}

	public static void main(int[] args) {
		System.out.println("Int main " + args[0]);
	}

	public static void main(Object[] args) {
		System.out.println("Object main " + args[0]);
	}

	static int st1;
	static double discount = 0;

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
