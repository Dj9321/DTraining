package com.main.imp;

import javax.accessibility.AccessibleEditableText;

public class TrickyJavaCodePart2 {

	public static void main(String[] args) {
		System.out.println(" 1: String concatenation");
		System.out.println(1 + 2); // 3
		System.out.println("a" + "b"); // ab
		System.out.println("a" + "b" + 3); // ab3
		System.out.println(1 + 2 + "c"); // 3c
		int three = 3;
		String four = "4";
		System.out.println(1 + 2 + three + four);

		// trick to see if you remember String is immutable
		String s1 = "1";
		String s2 = s1.concat("2");
		s2.concat("3");
		System.out.println(s2); // gives 12 only and not 123 as it is not assigned back to s2

		String string = "animals";
		System.out.println(string.charAt(0)); // a
		System.out.println(string.charAt(6)); // s
//		System.out.println(string.charAt(7));  // throws exception StringIndexOutOfBoundsException

		// indexOf can work with an individual character or a whole String as input. It
		// can also start from a requested position.
		System.out.println(string.indexOf(4)); // there is no character of 4 in animals its not number > -1 if it
												// doesn't find
		String s3 = "animals7";
		System.out.println(s3.indexOf(7)); // you can't enter number
		System.out.println(s3.indexOf("7")); // enter string "7"
		System.out.println(s3.indexOf("a", 4)); // start index: 4 using String
		System.out.println(s3.indexOf('a', 4)); // start index: 4 using char
		System.out.println(s3.indexOf("mals", 0)); // mals is starting from 3rd index

		System.out.println("\n 2: substring > using indexOf inside ");
		System.out.println(string.substring(string.indexOf('m'))); // mals
		System.out.println(string.substring(3, 4)); // ONLY m starting with index 3 until, but not including, the
													// character at index 4
		System.out.println(string.substring(3, 3)); // EMPTY string > tart and end with the same index, there are no
													// characters in between.
//		System.out.println(string.substring(3, 2)); // throws exception range (3,2) out of bounds for length 7
//		System.out.println(string.substring(3, 8)); // throws exception

		System.out.println("abc".equals("ABC")); // false
		System.out.println("ABC".equals("ABC")); // true
		System.out.println("abc".equalsIgnoreCase("ABC")); // true

		System.out.println("\n 3: startsWith() & endsWith()");

		System.out.println("abc".startsWith("a")); // true
		System.out.println("abc".startsWith("A")); // *** false > a case-sensitive check
		System.out.println("abc".endsWith("c")); // true
		System.out.println("abc".endsWith("a")); // false

		// The contains() method is a convenience method so you don't have to write
		// str.indexOf(otherString) != -1 .
		System.out.println("abc".contains("b")); // true
		System.out.println("abc".contains("B")); // false > case sensitive search

		System.out.println("\n 4: replace()");
//		String replace(char oldChar, char newChar)
//		String replace(CharSequence oldChar, CharSequence newChar)

		System.out.println("abcabc".replace('a', 'A')); // AbcAbc using char
		System.out.println("abcabc".replace("ab", "A")); // using CharSequence > String, StringBuffer,
															// StringBuilder etc
		System.out.println("\t   a b c\n".trim()); // a b c > \t is for tab > removes \n new line as well
		System.out.println("\t   a b c\n");
		System.out.println("a\tb\tc");

		// Method Chaining
		String result = "AniMaL   ".trim().toLowerCase().replace('a', 'A');
		System.out.println(result);

		String a = "abc";
		String b = a.toUpperCase();
		b = b.replace("B", "2").replace('C', '3');
		System.out.println("a=" + a); // a is not changed > abc
		System.out.println("b=" + b);

		String alpha = "";
		for (char current = 'a'; current <= 'z'; current++) // appending char gives another char of alphabets
			alpha += current; // creates 27 objects including one blank string initially > all eligible for
								// garbage collection
		System.out.println(alpha);

		String alpha1 = "";
		for (char current = 'A'; current <= 'z'; current++) // appending char gives another char of alphabets
			alpha1 += current; // creates small & capital letters > all eligible for garbage collection
		System.out.println(alpha1);

		System.out.println("\n 5: There are three ways to construct a StringBuilder");
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder("animal");
		StringBuilder sb3 = new StringBuilder(10); // capacity

		StringBuilder alpha2 = new StringBuilder();
		for (char current = 'a'; current <= 'z'; current++)
			alpha2.append(current);
		System.out.println(alpha2);

		StringBuilder sb = new StringBuilder("start");
		sb.append("+middle"); // sb = "start+middle" > returns reference to this object
		StringBuilder same = sb.append("+end"); // "start+middle+end"
		System.out.println(same + " " + sb); // here there is only one object and both references point to that object
												// only

		StringBuilder aa = new StringBuilder("abc");
		StringBuilder ba = aa.append("de");
		ba = ba.append("f").append("g");
		System.out.println("a=" + aa);
		System.out.println("b=" + ba);

		StringBuilder ab = new StringBuilder("D");
		StringBuilder ac = ab.append("J");
		ab.append("S");
		ac.append("-SDJ");
		System.out.println(ab + " " + ac); // both are same here as both point to same object
		ab.isEmpty();
		String s = "d";
		s.isBlank();
		s.isEmpty();

		StringBuilder sb4 = new StringBuilder("animals");
		String sub = sb4.substring(sb4.indexOf("a"), sb4.indexOf("al")); // string starting with index 0 and ending
																			// right before index 4 or al
		int len = sb4.length();
		char ch = sb4.charAt(6); // returns String and not StringBuilder
		System.out.println(sub + " " + len + " " + ch); // anim 7 s

		// different method signatures > argument changes
		StringBuilder sb5 = new StringBuilder().append(1).append('c'); // append() is called directly after the
																		// constructor
		sb5.append("-").append(true);
		System.out.println(sb5); // 1c-true

//		Pay attention to the offset
		StringBuilder sb6 = new StringBuilder("animals");
		sb6.insert(7, "-"); // sb6 = animals-
		sb6.insert(0, "-"); // sb6 = -animals-
		sb6.insert(4, "-"); // sb6 = -ani-mals Insert dash at index 4 (starts index at 0)
		System.out.println(sb6);

		// delete & deleteCharAt()
		StringBuilder sb7 = new StringBuilder("abcdef");
		sb7.delete(1, 3); // sb7 = adef > ending before 3 so till 2 only it will delete
		sb7.deleteCharAt(3); // ade
		System.out.println(sb7);

		// reverse
		StringBuilder sb8 = new StringBuilder("ABC");
		sb8.reverse();
		System.out.println(sb8);

		// Maybe we want to send to an argument which is expecting String
		String ad = sb8.toString();

		System.out.println("\n 6: Equality");
		StringBuilder one = new StringBuilder();
		StringBuilder two = new StringBuilder();
		StringBuilder three1 = one.append("a");
		System.out.println(one == two); // false > different objects
		System.out.println(one == three1); // true > same objects > references are referring to the same object

		// one is computed at compile time and another at runtime so a new String is
		// created although both are same after evaluating
		String x = "Hello World";
		String z = " Hello World".trim();
		System.out.println(x == z); // false

		String x1 = new String("Hello World"); // you have specifically requested a different String object, the pooled
												// value isn't shared.
		String y1 = "Hello World";
		System.out.println(x1 == y1); // false
		System.out.println(x1.equals(y1)); // true

//		 the authors of StringBuilder did not implement equals(). If you call equals() on two StringBuilder instances, it will check reference equality.
		StringBuilder one1 = new StringBuilder().append("SDJ");
		StringBuilder two1 = new StringBuilder().append("SDJ");
		; // 2 o rmore semicolons ? not an issue
		System.out.println(one1.equals(two1)); // ITS A FALSE **

		// Object equality
		TrickyJavaCodePart2 t1 = new TrickyJavaCodePart2();
		TrickyJavaCodePart2 t2 = new TrickyJavaCodePart2();
		System.out.println(t1.equals(t2)); // false as it is same as == checks same object as we didn't implement
											// equals() method
	}

}
