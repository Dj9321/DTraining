package com.main.imp;

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
	}

}
