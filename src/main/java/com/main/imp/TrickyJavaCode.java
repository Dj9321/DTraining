package com.main.imp;

import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.main.classes.AbstractClassA;
import com.main.classes.Car;
import com.main.classes.InterfaceA;
import com.main.classes.Person;
import com.main.classes.Planet;
import com.main.classes.TProduct;
import com.main.classes.Transport;
import com.main.classes.Vehicle;
import static java.lang.Math.*;

public class TrickyJavaCode extends AbstractClassA implements InterfaceA {

	// we can have same variable name as in super class
	public double price;

	public static void main(String[] args) {

		// Mostly from: https://www.examtopics.com/exams/oracle/1z0-808/

		// 1. update by value
		System.out.println("\n 1: ");
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
		System.out.println("\n 2: ");
		IO.println("\n *** Local date ***");
		String date = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE);
		// if you are using date and time use LocalDateTime and not just LocalDate
		String date1 = LocalDateTime.parse("2014-05-04T00:00:00").format(DateTimeFormatter.ISO_DATE_TIME);
		LocalDate date3 = LocalDate.now();
		System.out.println(date);
		System.out.println(date1);
		System.out.println(date3);

		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2014, 06, 20); // yyyy-mm-dd
		System.out.println(ld1);
		System.out.println(ld2);

		// 3. short, Short, int, Integer, long, Long
		System.out.println("\n 3: ");
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
		System.out.println("\n Sum is " + s3);
		System.out.println("\n Sum is " + s5);
		System.out.println("\n Sum is " + s7);
		System.out.println("\n Sum is " + l1);
		double do1 = 100; // can keep as 100.0 as well
		System.out.println(do1); // prints as 100.0

		// 4 See Car class in another package. Child class constructor calling parent

		// 5 StringBuffer and String
		System.out.println("\n 5: ");
		StringBuffer sb = new StringBuffer("Java");
		String s = "Java";
		String sbs = sb.toString();
		String sbs1 = sbs;
		if (sbs == sbs1) {
			System.out.println("\n String buffer toString and assigning to String");
		}
		if (sb.toString() == s) {
			System.out.println("\n Doesn't reach here");
		}
		if (sb.equals(s)) {
			System.out.println("\n StringBuffer matched s");
		} else if (sb.toString().equals(s)) {
			System.out.println("\n StringBuffer converted to string matched string");
		}

		// 6: Abstract class need not implement methods in an Interface.

		// 7: concat
		System.out.println("\n 7: ");
		String ta = "A ";
		ta = ta.concat("B ");
		String tb = "C ";
		ta = ta.concat(tb);
		ta.replace('C', 'D'); // this line does nothing as ta = is not there (assignment)
		ta = ta.concat(tb);
		System.out.println(ta);

		// 8: for loop to skip numbers
		System.out.println("\n 8: ");
		int in[] = { 1, 2, 3, 4, 5 };
		for (int k = 0; k < 5; k += 2) { // we can write like this as well k+=2 > increases k by 2
			System.out.print(in[k]);
		}

		// 9: import a.* doesn't import a.b.* (b is a subfolder)

		// 10:
		System.out.println("\n 10: ");
		TProduct t = new TProduct();
		t.price = 100; // we can also set like this also without calling the setter method.
		IO.println();
		System.out.println("\n Price: " + t.price);

		// 11. 2D array
		System.out.println("\n 11: ");
		String[][] twoDArray = new String[2][2];
		twoDArray[0][0] = "red";
		twoDArray[0][1] = "blue";
		twoDArray[1][0] = "small";
		twoDArray[1][1] = "medium";

		for (String[] array1 : twoDArray) { // first x = 0, then x=1
			for (String str : array1) { // iterates: y=0,1
				System.out.println(str);
			}
			System.out.println("\n End of a loop");
		}

		// 12: for Runtime exceptions no try-catch or throws is needed. Only for compile
		// time it is needed.
		System.out.println("\n 12: ");
		try {
			c.abc();
		} catch (Exception e) {
			e.printStackTrace();
		}
		c.def();

		// 13. x-- in another method doesn't work here
		System.out.println("\n 13: ");
		int x = 5;
		while (isAvailable(x)) {
			System.out.println(x);
			x--;
		}
		// 14. boolean true in switch-case
		System.out.println("\n 14: ");
		boolean opt = true;
//		Cannot switch on a value of type boolean. Only convertible int values, strings or enum variables are permitted
//		switch(opt) {}

		// 15 do-while > do works in the first iteration before it reaches while
		// condition (if it fails)
		System.out.println("\n 15: ");
		int num = 5;
		do {
			System.out.println(num-- + " ");
		} while (num == 0);

		// 16: x value increases on ++
		System.out.println("\n 16: ");
		int x1 = 100;
		int a1 = x1++;
		int b1 = ++x1;
		int c1 = x1++;
		int d1 = (a1 < b1) ? (a1 < c1) ? a1 : (b1 < c1) ? b1 : c1 : x1;
		// a1 = 100 b1 = 102; c1 = 102; d1 = 100
		System.out.println("\n D1: " + d1);
		System.out.println(x1); // here with ++ x value changes gradually

		// 17: list.remove() removes only first occurrence. Use removeAll() for all
		System.out.println("\n 17: ");
		IO.println("\n *** 17 ***");
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
		System.out.println("\n 19: ");
		c.st1 = 5;
		TrickyJavaCode tricky1 = new TrickyJavaCode();
		tricky1.st1 = 6;
		System.out.println(c.st1); // Prints 6 and not 5 as another instance changed this static instance

		// 20
		System.out.println("\n 20: ");
		int array[] = new int[5];
		int array1[] = { 1, 2, 4, 5 }; // just in time array

		System.out.println("\n Hello " + args[0]); // Run configurations > Pass arguments
		// with javac abc.java (you have to mention .java) with java abc (no .java)

		// 21: for loop not initializing
		System.out.println("\n 21: ");
		int i8 = 0, j8 = 7; // we can club same types
		for (i8 = 0; i8 < j8; i8 = i8 + 2) { // here we are initializing i8 but not declaring type int i8
			System.out.println(i8 + " ");
		}

		// 22: qty > 90 discount: 0.5 qut between 80 to 90: discount 0.2
		System.out.println("\n 22: ");
		double qty = 0;
		// even though it is > 80 already > 90 condition is crossed so that won't come
		// > so effectively its 80 to 90
		TrickyJavaCode.discount = qty >= 90 ? 0.5 : qty > 80 ? 0.2 : 0;

		// 23
		System.out.println("\n 23: ");
		if ("S".equals("S") ? true : false) {
			System.out.println("\n conditional > Tertiary");
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
		System.out.println("\n 24: ");
		ArrayList<String> myList = new ArrayList<>();
		String[] sArray;

		try {
			// commented below line as it takes lot of time
//			while (true) { 
//				myList.add("hello"); // "main" java.lang.OutOfMemoryError: Java heap space > This is Runtime Exception
//			}
		} catch (RuntimeException e) {
			System.out.println("\n Runtime exception caught"); // It doesn't come here
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace(); // doesn't come here as well.
		}

		// 25: sysout String
		System.out.println("\n 25: ");
		System.out.println("\n 5 + 3 = " + 5 + 3); // here it prints 53 and not 8
		System.out.println("\n 5 + 3 = " + (5 + 3));

		// 26: for loop
		System.out.println("\n 26: ");
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
		System.out.println("\n 27: ");
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
		System.out.println("\n 28: ");

//		public Car() {
//			super();
//			this(10);// Constructor cannot have more than one explicit constructor call
//		}

		System.out.println(new Car(10)); // it will print toString() if we implemented else it will print Car@hashCode
		System.out.println(new TrickyJavaCode()); // prints : com.main.imp.TrickyJavaCode@7b23ec81

		// 29: Numbers: putting values in another types
		System.out.println("\n 29: ");
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
		// file.
		// abstract methods don't have {} just ; abstract methodA();
		// abstract classes can have constructors

		// 31: arrays are like dimensions: 2D [1][3] means 1 row, 3 columns
		int[][] numA = new int[1][3]; // 0 1, 0 2, 0 3
		for (int i = 0; i < numA.length; i++) {
			for (int j = 0; j < numA[i].length; j++) {
				numA[i][j] = 10;
			}
		}
		System.out.println(numA[0][1]);

		// 31
		System.out.println("\n 31: ");
		Planet[] planets1 = { new Planet("Mercury", 0), new Planet("Venus", 0), new Planet("Earth", 1),
				new Planet("Mars", 2) };
		System.out.println(planets1);
		System.out.println(planets1[2].name); // even though it doesn't have setters or getters > we are setting
												// directly
		System.out.println(planets1[2].moons);

		// 32: private is not allowed for local variable. No Static. Only final
		System.out.println("\n 32: ");
		System.out.println(c.doStuff());

		// 33: package declaration should be the first line in the file

		// 34: double comparision with == 0 works
		System.out.println("\n 34: ");
		c.methodA();

		// 35: byte, short, char, int, enums, and since Java 7, also String and wrapper
		// classes like Byte, Short, Integer, and Character.
		System.out.println("\n 35: ");
		float fl = 3;
		// if we don't keep f in 2.0 > says cannot convert from double to float. double
		// is default here.
		float y = 2.0f; // here f is mandatory if we keep .0 or other decimals
		float fl1 = (float) 2.0; // converts from double to float
		long ln = 3L;
		c.methodB();

		float flt = 100.00F; // Ok
		// You can place underscores between digits in a numeric literal, including for
		// float, double, int, and long types.
		float flt1 = (float) 1_11.00; // Ok
//		Float flt2 = 100.00; // Error - need cast to Float.
		double y1 = 203.22;
//		float flt = y1; // Error - need cast to float.
		int y2 = 100;
		float flt3 = (float) y2; // Ok

		// 36: Boolean
		System.out.println("\n 36: ");
		Boolean[] bol = new Boolean[2]; // no need of brackets () here
//		Boolean bb = new Boolean; // can't write like this without ()
		bol[0] = Boolean.parseBoolean("true");
		bol[1] = new Boolean(null); // gives false
		bol[1] = Boolean.valueOf(null); // gives false
		boolean bol2 = Boolean.valueOf(null); // even valueOf(null) gives false
		System.out.println("\n valueOf null " + bol2);
		boolean bol3 = Boolean.valueOf(args[0]); // all other string values are false
		System.out.println("\n Value of 1: " + bol3);
		System.out.println(bol[0] + " " + bol[1]);
//		System.out.println(bol[0] + bol[1]); // can't write like this
		try {
			System.out.println(Math.random());
			// method1() catches the exception so it doesn't need to go to catch block here
			method1();
		} catch (Exception e) {
			System.out.println("\n A");
		}

		// Polymorphism: More efficient code at runtime and more flexible & reusable
		// code. NOT: More dynamic code at runtime

		// 37:
		System.out.println("\n 37: ");
		int[] nums1 = { 1, 2, 3 };
		int[] nums2 = { 1, 2, 3, 4, 5, 6 };
		nums2 = nums1; // array will get replaced
		for (int k1 : nums2) {
			System.out.println("\n  X after changing nums: " + k1);
		}

		// 38:
		System.out.println("\n 38: ");
		int n[][] = { { 1, 3 }, { 2, 4 } };
		for (int i = n.length - 1; i >= 0; i--) {
			for (int ya1 : n[i]) {
				System.out.println(ya1);
			}
		}

		// 39:
		System.out.println("\n 39: ");
		try {
			int na1 = 10;
			int div = 0;
			int ans = num / div;
		} catch (ArithmeticException ae) {
//			ans = 0; // ans cannot be resolved to a variable
			System.out.println("\n Number can't be divided by 0");
		} catch (Exception e) {
			System.out.println("\n Invalid calculation");
		}
//		System.out.printIn("Answer = " + ans); // ans cannot be resolved to a variable

		// 40:
		System.out.println("\n 40: ");
		// In a method: if arg = arg; it has no effect as this.arg is not used
		// Also, arg = this.arg; may be this.arg is already 0 for int so arg will also
		// be 0. Hence we have to use this.a = a;

		// 41:
		System.out.println("\n 41: ");
		Person p1 = new Person("D", "DS");
		Person p2 = p1;
		p1.name = "S";
		p2.name = "Sir"; // this will replace above
		System.out.println(p1.name + " p2: " + p2.name); // both point to same object

		// 42:
		System.out.println("\n 42: ");
		int aVar = 9;
		if (aVar++ < 10) { // works as it will increase after this statement
			System.out.println(aVar + " Hello Universe!");
		} else {
			System.out.println(aVar + " Hello World!");
		}

		String sa1 = "Java SE 8 1";
		int len = sa1.trim().length();
		System.out.println(len);

		// 43:
		System.out.println("\n 43: ");
		int[][] arr1 = new int[2][4];
		arr1[0] = new int[] { 1, 3, 5, 7 };
		arr1[1] = new int[] { 1, 3 };
		for (int[] a : arr1) {
			for (int i : a) {
				System.out.print(i + "");
			}
			System.out.println();
		}

		// 44:
		System.out.println("\n 44: ");
		StringBuilder sb1 = new StringBuilder();
		sb1.append("SDJ");
//		sb.deleteAll(); // no such method
//		sb.removeAll(); // no such method
		sb1.delete(0, sb1.length());
		System.out.println("\n Sb1 value: " + sb1); // gives blank

		System.out.println("\n 45: String and tertiary operators");
		int aa1 = 1;
		String sas1 = "H";
		String sas2 = ""; // should initialize this for sysout
		sas1 = aa1 > 0 ? sas2 = "D" : "E"; // here still sas1 will be D even though we have another statement
		System.out.println(sas1 + " " + sas2);

		// 46:
		System.out.println("\n 46: ");
		System.out.println("\n Result A: " + 1 + 1); // gives 01
		System.out.println("\n Result A: " + (1 + 1)); // gives 1 (addition)
		System.out.println("\n Result A: " + (0) + (1));

		// 47: Constructors
		System.out.println("\n 47: ");
		// in a class Person you have 1 & 2 arg constructors. You call in 2 arg
		// constructor as: Person(name) > Wrong as we have to call this(name)

		// 48: static changes from 2 objects
		System.out.println("\n 48: ");
		TrickyJavaCode c2 = new TrickyJavaCode();
		c.changeCount();
		c2.changeCount();
		System.out.println(c.count + " " + c2.count); // c2.changeCount());
		// gives 10 10 > as count is static and is increased in 2 objects twice

		ArrayList<Integer> points = new ArrayList<>();
		points.add(1);
		points.add(2);
		points.add(3);
		points.add(4);
		points.add(null);
		points.remove(1); // removes element at position 1
//		points.remove(null); // removes null 
		System.out.println(points);

		// 49: Array of Numbers
		System.out.println("\n 49: ");
		int[] numbers1 = new int[2];
		numbers1[0] = 1;
		numbers1[1] = 2;

		numbers1 = new int[4]; // here we are initializing again so all will be empty from starting
		numbers1[2] = 3;
		numbers1[3] = 4;

		for (int k : numbers1) {
			System.out.println("\n Numbers: " + k); // first 2 numbers will be 0
		}

		// 50:
		System.out.println("\n 50: ");
		c.printAll();

		// 51:
		System.out.println("\n 51: ");
		// The parameter in a catch block is of Throwable type.
		// All subclasses of the RuntimeException class are not recoverable.
		// Error is extendable & throwable > It is not a RuntimeException or Exception

		String str4 = "Hello World ";
		str4.trim();
		System.out.println(str4.length());
		str4 = str4.trim();
		System.out.println(str4.length());
		System.out.println(str4.indexOf(" ")); // gives first index of space

		String a = "hello" + " world!";
		String b = "hello world!";
		boolean compare2 = (a == b); // This return true
		System.out.println(compare2);

		String st1 = "Java";
		String[] st2 = { "J", "a", "v", "a" };
		String str3 = "";
		for (String str : st2) {
			str3 = str3 + str;
		}
		boolean ba1 = (st1.equals(str3));
		boolean b2 = (st1 == str3); // here comparision fails
		System.out.println(ba1 + ", " + b2);
		System.out.println(st1 + " " + str3); // here st1 + str3 works

		String g1 = "Java";
		String g2 = "" + "Java";
		if (g1 == g2) {
			System.out.println("\n Both equal"); // still equal
		}

		// 52: Date
		System.out.println("\n 52: ");
		LocalDateTime dt = LocalDateTime.of(2014, 7, 31, 1, 1);
		dt = dt.plusDays(30); // you need to reassign else it won't change > dt =
		dt = dt.plusMonths(1);
		System.out.println(dt.format(DateTimeFormatter.ISO_DATE));

		// 53: we keep this. only when the names are same else we don't need it
//		public Person(String n, String id) {
//			name = n;
//		}

		// 54:
		float var11 = (12345.01 <= 12345.00) ? 12.456f : 124_56.02f; // shows dead code as they are constants and it
																		// evaluates already
		double var12 = (0.1 > 0.0) ? 1f : 2f;
		System.out.println(var12);
		int cc = (1 > 2) ? 1 : 2;
		System.out.println(cc);
		float var22 = var11 + 1024;
		System.out.println("\n 54: " + var22);

		if (0.1 > 0.0) {
			System.out.println("\n  0.1 > 0.0");
		}

		// 55: Interfaces
		Transport tt = new Vehicle(); // even thought we kept of type interface > the new Vehicle will be called as
										// there is overridden method
		tt.transport();

		// 56: static imports
		// with static imports you import till the ClassA.staticMethod() > in code you
		// directly use staticMethod()

		System.out.println("\n 57: missing break");

		int wd = 0;
		String days[] = { "sun", "mon", "wed", "sat" };
		for (String ss1 : days) {
			switch (ss1) {
			case "sat":
			case "sun":
				wd--;
				System.out.println(wd + "sun ");
				break;
			case "mon":
				wd++;
				System.out.println(wd + " mon "); // here there is no break, so ti will continue below
			case "wed":
				wd += 2;
				System.out.println(wd + " wed ");
			}
		}
		System.out.println(wd + "");

		// 58:
//		LocalDate dated1 = LocalDate.of(2012, 12, 32); // DateTimeException is throw at Runtime and not compile time
//		System.out.println(dated1);

		System.out.println("\n 59: ");
		int j = 20;
		int kk1 = (j += 10) / 5; // evaluates and keeps 30
		System.out.println(kk1);

		// 60:
		// abstract class need NOT implement all methods in interface.
		// Also if same method is there in multiple interfaces and then it is
		// implemented once it is fine or if it extends a class that implements the
		// method, then also it is fine
		// See > public abstract class FourWheeler extends Vehicle implements Transport,
		// Transport2

		// interfaces extend other interfaces > they DON't implement other interface (s)

		// abstract classes MUST declare methods as abstract if they are abstract else
		// add body {} for non abstract methods
		// class can be abstract or final > NOT BOTH
		// Always think that a Father can be a son but a son cannot be a father.
		// we cannot reduce the visibility from interface to a class. However public is
		// allowed from default (as in interface by default it is public)
		// we can also use super.instanceVariable to print or use for others
		// if you create a class > its constructor calls super() > its extended class
		// constructor

		List<String> colors = new ArrayList();
		colors.add("Green");
		colors.add("Blue");
		colors.add("Red");
		colors.add("Yelow");
		colors.remove(2);
//		colors.add(4, "Cyan"); // you can't add 4th element with index like this > array size is 3 now
		colors.add(3, "orange");
		colors.add(4, "orange"); // here it works adding next element
		colors.add(5, "orange");
		System.out.println(colors);

		System.out.println("\n 61: looping array");
		int[] xa = { 10, 20, 30, 40, 50 };
		int xaL = xa.length;
		while (xaL > 0) { // > 0 so it will be 5, 4, 3, 2, 1
//			xaL--; // this works if we remove --xaL in below line
			System.out.println(xa[--xaL]);
//			xaL--; // this doesn't work if we don't keep --xaL above because xa[5] isn't there max is xa[4]
		}

		System.out.println("\n 62: array removeIf");
		String[] arn = { "Hi", "How", "Are", "You" };
		List<String> arrList = new ArrayList<>(Arrays.asList(arn));
//		arrList.removeIf(sas -> sas.length() <= 2);
		if (arrList.removeIf((String ssa) -> {
			return ssa.length() <= 2;
		})) {
			System.out.println(arrList); // [How, Are, You]
//			System.out.println(ssa + "removed"); // ssa cannot be resolved to a variable as it is in removeIf predicate
			// scope
		}

		System.out.println("\n 63: protected static final can be used in instance variable");

		System.out.println("\n 64: try-catch");
		c.throwingException(); // no need of throws here as try-catch is already there

		try {
			c.throwingException1(); // you need try-catch here as the method throws exception (as in method
									// signature) and doesn't handle it
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\n 65: int array");
		int[][] arr2 = new int[2][4];
//		arr2[0]={1,2,3}; // can't write like this > Array constants can only be used in initializers
		arr2[0] = new int[] { 1, 2, 3 };
		arr2[1] = new int[] { 4, 5, 6 };
//		arr2[2] = new int[] { 4, 5, 6 }; // Index 2 out of bounds for length 2
		for (int[] aa2 : arr2) {
			for (int ii1 = 0; ii1 < arr2.length; ii1++) { // here used arr2 instead of aa2 (TRICK)
				System.out.println(aa2[ii1]);
			}
			System.out.println();
		}

		int[][] arr3 = { { 1, 2 }, { 3, 4 } };
		for (int ai = arr3.length - 1; ai >= 0; ai--) {
			for (int aj = arr3[ai].length - 1; aj >= 0; aj--) { // here length - 1 is important. Even in above line as
																// well
				System.out.println(arr3[ai][aj]);
			}
		}

		System.out.println("\n 66: String array");
		String[] arr4 = { "Hi", "How", "Are", "You" };
		List<String> arrList1 = new ArrayList<>(Arrays.asList(arr4));
		if (arrList1.removeIf(ss1 -> {
			System.out.print(ss1);
			// if none satisfy this condition, then removeIf() finally gives false. Even if
			// one satisfies it gives true > true if any elements were removed
			return ss1.length() <= 2; // this true is only for removing elements. First all elements go to this.
		})) {
			System.out.println("\n  removed");
		}

		System.out.println("\n 67: try-catch");
		try {
			throw 3 < 10 ? new IOException() : new Exception();
		} catch (IOException e) {
			System.out.println("\n IOException ");
		} catch (Exception e) { // you have to write this catch also as we wrote new Exception above
			e.printStackTrace();
		}

		System.out.println("\n 68: Character default value is null; char default value is blank");

		System.out.println("\n 69: String Array");
		String[][] chs = new String[5][2]; // length is always first one > like row > {} in main {}
		chs[0] = new String[2];
		chs[1] = new String[2];
		int i = 97;
		System.out.println(chs.length);
		for (int aa2 = 0; aa2 < chs.length; aa2++) {
			for (int bb = 0; bb < chs[aa2].length; bb++) {
				chs[aa2][bb] = "" + i;
				i++;
			}
		}
		for (String[] ca : chs) {
			for (String c3 : ca) {
				System.out.print(c3 + " ");
			}
			System.out.println();
		}

		System.out.println("\n 70: LocalDateTime");
		LocalDateTime dt1 = LocalDateTime.of(2014, 7, 31, 1, 1, 2); // even if we don't keep 2 (seconds it works)
		System.out.println(dt1);

		System.out.println("\n 71: Float & Double");
		c.doSum(3, 4); // by default it takes int > goes to float for next closest thing
		c.doSum(3.0, 4.0); // by default it is double

		System.out.println("\n 71: Hello : " + new Person("D", "J").name); // we can directly call instance variable
																			// like
																			// this
		System.out.println("\n 72: x++ inside while");
		int xb = 0;
		do {
			System.out.print("*"); // prints 4 stars because xb++ increases in the next iteration
		} while (xb++ < 3);

		System.out.print("73: bitwise |");
		int xc = 10;
		int yb = ++xc; // 11
		int z = 0;
		System.out.println(xc); // the value of xc changes even though it is not assigned back to xc > because
								// of ++xc
		if (yb >= 10 | yb <= ++xc) { // evaluates both
			z = xc;
		} else {
			z = xc++;
		}
		System.out.println(z);

		System.out.println("\n 73: Order (Multiplication, Division, additiona) of numbes");
		// Parentheses: () are always evaluated first and can be used to override the
		// default order.
		// Multiplication, Division, Modulus: ∗,/ are next, evaluated from left to
		// right.
		// Addition and Subtraction: +,−+,− are lower, also evaluated from left to right
		// if appearing together.
		int ac = 3;
		int bc = 2;
		int cc1 = 1;
		int r1 = ac * bc / cc1 + 1; // 3 * 2/1 + 1 = 6+1 = 7
		int r2 = ac / bc * cc1 + 1; // 3/2 * 1 + 1 = 1.5*1 + 1 = 1 + 1 = 2 >(1.5 gives 1 in int)
		int r3 = ac * (bc / (cc1 + 1)); // 3 * (2/2) = 3 *1 = 3
		System.out.println(r1 + ":" + r2 + ":" + r3);

		System.out.println(3 / 2); // Gives 1 & NOT 1.5

		System.out.println("\n 74: Static instance variables also get default values: " + ss);
//		int arrayl] = new int[3] {1, 2, 3}; // You can't put like this > initialize and then set size (Both > WRONG)
//		int array[3] = new int[] {1, 2, 3}; // left side you CAN'T Keep size
		int array4[] = new int[] { 1, 2, 3 }; // this is fine
		System.out.println(array4); // prints object > if we want values we need to iterate

		System.out.println("\n 74: Local variable can't be accessed outside");
		int sum = 0;
		for (int xa1 = 1; xa1 <= 5; xa1++) {
			sum = sum + xa1;
		}
//		System.out.println("\n The sum of " + xa1 + " numbers is: " + sum); // here xa1 can't be accessed outside the for loop

		List<String> arrayList = new ArrayList<>();
		arrayList.add("Tech");
		arrayList.add("Expert");
		arrayList.set(0, "Java");
		System.out.println(arrayList);
		arrayList.forEach(aa3 -> aa3.concat("Forum")); // this line has not impact as we are not collecting
		System.out.println(arrayList);
		arrayList.replaceAll(sa3 -> sa3.concat("Group")); // this works > Replaces each element
		System.out.println(arrayList);

		System.out.println("\n 75: Boolean");
		Boolean ba = true; // this works > directly initializing
		Boolean ba2 = Boolean.valueOf("True"); // WORKS
		System.out.println(ba2);
//		Boolean ba3 = "true"; // DOESN"T WORK > cannot convert from String to Boolean

		System.out.println(
				"76: Methods no need to be static in order to use static variables. However, static methods cannot use instance variables.");
		System.out.println("\n 77: subclass having same method as super class");
		c.displayOnTv(10);
//		super.displayonTv(); // Cannot use super in a static context
		// You can call super.displayOnTv() for super class method

		System.out.println("\n 78: class A extends B implements I");
		// A a = new A(); B b = new B(); a instanceof B > true a instance of I > True
		InterfaceA ia = new TrickyJavaCode();
		boolean bl1 = ia instanceof InterfaceA;
		boolean bl2 = c instanceof InterfaceA;
		System.out.println(bl1 + " " + bl2);

		System.out.println("\n 79: Overriding in a constructor: ");
		System.out.println(c.greet);

		System.out.println("\n 80: equals");
		c.greet = "Hello";
		System.out.println(c.equals("Hello")); // false
		System.out.println("\n Hello".equals(c.greet)); //

		System.out.println("\n 81: continue in Switch > Doesn't work as it is for a loop");

		System.out.println("\n 82: charAt(), indexOf() in String");
		String str = "Sweet Sweat";
		// indexOf() > -1 if there is no such occurrence.
		String stra2 = str.trim().charAt(6) + " " + str.indexOf("Sw", 1); // 1 start of substring > so it ignores the
																			// first Sw
		System.out.println(stra2); // S 6
		System.out.println("\n 82: for loop");
		for (int ia1 = 100; ia1 < 100; ia1++) { // it checks the condition ia1 < 100 as well before entering the loop
			System.out.println("\n Welcome " + ia1);
		}

		System.out.println("\n 83: variables");
		c.doStuff("7007");

		System.out.println("\n 84: String to Objects");
		Object oa, ob, oc;
		oa = new String("A");
		ob = new String("B");
		oc = oa;
		oa = ob;
		System.out.println("\n " + oc);
		boolean ba3 = false;
		boolean ba4 = false;
//		if(ba4!=ba3=!ba4) {} // can't write like this > Multiple conditions
		int index = 1;
		String[] strA = new String[5];
		String myStr = strA[index];
		System.out.println(myStr);

		System.out.println("\n 85: Comments");
		// add /** and enter on any method for java comments. If you hover on the method
		// call you will get comments
		Random r = new Random();
		System.out.println(r.nextInt(10));

		System.out.println("\n 86: Can't import classes from default package");
//		MainClassOne asdfasd = new MainClassOne();

		System.out.println("\n 86: Static imports > we can also import System.out > so you can write out.println()");
		System.out.println(Math.min(2, 3));
		// The following works after static import of Math >
		// import static java.lang.Math.*;
		// even static variables we can import without Class name
		System.out.println(min(2, 3));

		System.out.println("\n 87: Inner class");
		// You have to write like this c.new and NOT directly
		InnerClassA aci = c.new InnerClassA();
		System.out.println(aci.sayHi());

		System.out.println("\n 88: You can have non public class in the same file. We can access it as below");
		OusideClassInSameFile oci = new OusideClassInSameFile();
		oci.sayHello();
		// ClassChangedName without public can be kept in another file:
		// ClassWithAnotherName
		// ClassChangedName > void main() worked without public static
		// In practice, you can write String[] args, String args[] or String… args; the
		// compiler accepts any of these.
		// java Zoo "San Diego" Zoo > 2 arguments

//		/*
//		 * /* ferret */ > You can't close here itself and call another closing below. Doesn't work
//		 */

		System.out.println(
				"\n 89 Instance initializer will be called when object is created > see > Instance initializer");

		System.out.println(
				"\n 90: Integer can hold max value of : 2^32 / 2 for positive and negative and a 0. Get using: "
						+ Integer.MAX_VALUE + " Short max value : 2^16 / 2 -1 (for 0): " + Short.MAX_VALUE);
//		long valueV = 3123456789;  // DOES NOT COMPILE as default is int
		long valueV = 3123456789L; // now Java knows it is a long

		System.out.println("\n 91: Octal, HexaDecimal and others");
		int octal1 = 0234; // prefix with 0; For Hexa prefix with 0x or 0X; For binary use 0b or 0B
		System.out.println("Hex: " + Long.toHexString(156));
		System.out.println(octal1 + " Octal");
		byte aby = 3;
		byte bby = 127;
		Integer g = 1000;
		System.out.println((aby + bby));
		System.out.println("Binary to Integer " + Integer.valueOf("9c", 16));

		System.out.println(0b11); // 3
		System.out.println(017); // 15 8^1 + 8^0*7 = 8 + 7 = 15
		System.out.println(0x1F); // 31 16^0*15 + 16^1 = 31

		System.out.println("\n 92:");
		int hex = 0xaff;
		System.out.println("Hex " + hex);

		float f = 333.33f;
		int h = (int) f;
		System.out.println(h);

		// You can add underscores anywhere except at the beginning of a literal, the
		// end of a literal, right before a decimal point, or right after a decimal
		// point.
//		double notAtStart = _1000.00;        // DOES NOT COMPILE
//		double notAtEnd = 1000.00_;          // DOES NOT COMPILE
//		double notByDecimal = 1000_.00;      // DOES NOT COMPILE
		double annoyingButLegal = 1_00_0.0_0; // this one compiles

// Primitive types will give you a compiler error if you attempt to assign them null.
//		int value = null;   // DOES NOT COMPILE
		System.out.println("\n 93:");
		// You can declare many variables in the same declaration as long as they are
		// all of the same type.
		String s3a = "yes", s4a = "no";
		int i1, i2, i3 = 0;
		int i1a;
		int i2a;// still fine if you write in a same line
//		int i3; i4; // Doesn't work as we kept semicolon instead of comma
		System.out.println("\n 94:");
		String $a1 = "dd"; // valid as The name must begin with a letter or the symbol $ or _.
		String _a2 = "EE";
		// Java is case sensitive, so you can use versions of the keywords that only
		// differ in case. Please don't, though.
		String satic = "Some";
//		String hollywood@vine; // Doesn't work  @ is not a letter, digit, $ or _

		System.out.println("\n 95:");
		String aa3;
		if (i3 == 0) {
			aa3 = "True";
		} else {
			aa3 = "False";
		}
		System.out.println(aa3); // if you don't give else part it doesn't compile. Because somewhere aa3 needs
									// to be initialized before it is used as it is a local variable
		System.out.println(c.ccc1); // default char value > '\u0000' (NUL)

//	Local variables can never have a scope larger than the method they are defined in. However, they can have a smaller scope.
		System.out.println("\n 96:");
		boolean hungry = true;
		if (hungry) {
			int bitesOfCheese = 1;
		} // bitesOfCheese goes out of scope here
//			System.out.println(bitesOfCheese);// DOES NOT COMPILE

		int amount = 0b11110;
		int hexI = 0xE;
		double hexD = 0xE;
		System.out.println(amount); // converted to decimal
		System.out.println(hexI); // converted to decimal
		System.out.println(hexD); // // converted to decimal (Double 14.0)

		System.out.println("\n 97: Promotion rules of numbers");
//		If two values have different data types, Java will automatically promote one of the values to the larger of the two data types. If int and float > it will promote to float.
//		Smaller data types, namely byte, short, and char, are first promoted to int any time they're used with a Java binary arithmetic operator, even if neither of the operands is int.

		int i9 = 10;
		double d9 = 11;
		System.out.println(i9 + d9); // int + double promotes to double
		double e9 = i9 + d9;
		System.out.println(e9);

		// as multiplication and division have same priority they are evaluated from
		// left right
		System.out.println(6 * 2 / 4); // 12/4 = 1
		System.out.println(6 / 2 * 4); // 3 * 4 = 12

//		floating-point literals are assumed to be double, unless postfixed with an f
		float yss = 13; // only if we keep decimals we need to keep f else not needed

//		int x = !5;  // DOES NOT COMPILE
//		boolean y = -true;  // DOES NOT COMPILE
//		boolean z = !0;  // DOES NOT COMPILE

		int xa2 = 3;
		int ya2 = ++xa2 * 5 / xa2-- + --xa2; // 4 * 5 / 4 + 2 = 20/4 + 2 = 5 + 2 = 7
		System.out.println("x is " + xa2);
		System.out.println("y is " + ya2);
//		long t = 192301398193810323;  // DOES NOT COMPILE as it is out of range for int (Default is int)
		long tl = 192301398193810323l;
		BigInteger ts = BigInteger.valueOf(192301398193810323l);
//		int z = 9f; // doesn't compile
		int za = (int) 9f; // casting to int
		int za1 = (int) 9.1f; // casting to int with decimal > Still works as it fits float to int
		System.out.println(za1);
		short ya = (short) 1921222; // Stored as 20678 numeric overflow occurs
		System.out.println(ya);
		short ya1 = (short) 32768 + 32767;
		System.out.println("Overflow: repeats from positive max to negative max and loops till value is done: " + ya1);
		System.out.println(Short.MAX_VALUE);
		// Since 2147483647 is the maximum int value, adding any strictly positive value
		// to it will cause it to wrap to the next negative number.
		System.out.print(2147483647 + 1); // -2147483648

		short xa1 = 10;
		short ya3 = 3;
		// Even though 30 fits in short. By default the 10 is promoted to int. So cast
		// is needed.
//		short za3 = xa1 * ya3;  // DOES NOT COMPILE 
		// You are telling the compiler that you have taken additional steps to prevent
		// overflow or underflow.
		short za3 = (short) (xa1 * ya3);

		long xa3 = 10;
		int ya4 = 5;
//		ya4 = ya4 * xa3; // DOES NOT COMPILE as it gets promoted to bigger of them i.e long or you can use cast
		ya4 = (int) (ya4 + xa3);
		System.out.println("\n 98: First casts to long then does multiplication and then casts long to int");
		ya4 += xa3; // you can do like this without cast using compound operator
		xa3 = xa3 + ya4; // you can do this way also
		long ya5 = ya4 * xa3;

		long yb1 = (x = 3); // Still works
		System.out.println(yb1);

	}

	char ccc1;

	{
		System.out.println("Instance initializer ");
		char ccc1; // this variable can't go to any method like instance variable
	}

	private class InnerClassA { // we can even put private here
		// but outside classes can't have this. (only abstract, final are allowed)

		String sayHi() {
			return "Hii how are you doing ?";
		}

	}

	String myStr = "9009";

	public void doStuff(String str) {
		int myNum = 0;
		try {
			String myStr = str; // this can't be accessed outside try
			myNum = Integer.parseInt(myStr);
		} catch (NumberFormatException n) {
//			System.out.println(myStr); // NOT even here
			System.out.println("\n Error");
		}
		System.out.println("\n myStr: " + myStr + ", myNum: " + myNum);
	}

	public void switchExample() {
		String s = "D";
		switch (s) {
		case "D":
			System.out.println("\n Letter D");
//			continue; // continue only works inside a loop > switch is not a loop
		case "E":
			System.out.println("\n Letter E");
		}
	}

	public boolean equals(TrickyJavaCode tc) {
		return this.greet.equals(tc.greet);
	}

	String greet = "instance varaible";

	TrickyJavaCode() {
		String greet = "Inside constructor"; // here it is a local variable
	}

	public void displayOnTv(int x) {
		super.displayOnTv();
		displayOnTv(); // calls child class method as it is overridden
		this.displayOnTv(); // calls child class method as it is overridden
	}

	public void displayOnTv() {
		System.out.println("\n Displaying on TV in Subclass");
	}

	static int ss;

	public void doSum(float a, float b) {
		System.out.println("\n Float sum is : " + (a + b));
		System.out.println(++ss); // we can access static variable in a non static method
	}

	public void doSum(double a, double b) {
		System.out.println("\n Double sum is : " + (a + b));
	}

	public void throwingException() {
		try {
			System.out.println("\n try code");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void throwingException1() throws IOException {
		System.out.println("\n try code");
		throw new IOException();
	}

	protected static final int ii1 = 10; // can have final static variable
	char c;
	boolean b;
	float f;

	void printAll() {
//		char c; // if we give here, it says local variable is not initialized
//		boolean b;
//		float f;
		System.out.println("\n c = " + c); // blank
		System.out.println("\n b = " + b); /// false
		System.out.println("\n f = " + f); // 0.0
	}

	private static int count = 0;
	int i = 0;

	void changeCount() {
		while (i < 5) {
			i++;
			count++;
		}

	}

	public static void method1() {
		try {
			// here throw > throws > doesn't actually calculate Math.random() or a > 2
			// No exception of type Object can be thrown; an exception type must be a
			// subclass of Throwable
//			throw Math.random() > 0.5 ? new TrickyJavaCode() : new RuntimeException();  
//			throw Math.random() > 0.5 ? new Exception() : new RuntimeException();
			int a = 0;
			throw a > 2 ? new Exception() : new RuntimeException();

		} catch (Exception e) {
			System.out.println("\n B");
		}
	}

	public void main(String fileName) throws IOException {
	}

	double area;

	void methodA() {
		double p, b, h;
		if (area == 0) {
			System.out.println("\n Double value comparision == with 0");
		}
	}

	void methodB() {
//		byte x = 1; // works for int 
//		String x = "1";
		int x = 1; // works
		// The constructor Integer(int) is deprecated since version
		Integer integer = Integer.valueOf(10); // Preferred to ensure better performance and efficient memory
												// utilization.
//		Integer x = new Integer(1); // works but new Integer() is deprecated
//		long x = 1;
		float y = 1.0f; // switch doesn't work for float
//		Long x = 1L;
		//
		switch (x) {
		case 1:
			System.out.println("\n Integer");
			break; // not mandatory > it will continue below code as well if not given
		default: // not mandatory
			System.out.println("\n Nothing");
		}

		char colorCode = 'y';
		switch (colorCode) {
		case 'y':
			int color = 10; // we can declare new variable int here as well but scope will be inside switch
							// only
			System.out.println(color);
			break;
		case 'z':
			color = 11; // we can use color in the same scope of swith here as well
			break;
		case 'x':
			color = 12;
			break;
		}
//		System.out.println(color); // color cannot be resolved to a variable
		/*
		 * A switch expression yields a value for each possible selector (input value)
		 * using either arrow cases (case L -> V;) or by using a yield statement if the
		 * case uses a block.
		 */
		String day = "Monday";
		int result = switch (day) {
		case "MONDAY" -> {
			System.out.println("\n Monday");
			yield 1; // yield is compulsory here
		} // no ; is needed here
		case "TUESDAY" -> 2;
		default -> 0;
		};
		System.out.println("\n Result: " + result);

	}

	/**
	 * 
	 * @return
	 */

	int doStuff() {
		int x = 100;
		return x++;
	}

	public static void main(int[] args) {
		System.out.println("\n Int main " + args[0]);
	}

	public static void main(Object[] args) {
		System.out.println("\n Object main " + args[0]);
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
		System.out.println("\n abc method");
	}

	void def() throws RuntimeException {
		System.out.println("\n def method");
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

class OusideClassInSameFile {

	String sayHello() {
		return "Hello all. Welcome to Tricky Java Code";
	}
}
