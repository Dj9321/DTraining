package com.main.imp;

import java.util.Arrays;

public class UnicodeDemo {
	public static void main(String[] args) {
		System.out.println("Java Unicode demo\n");

		// 1) Using Unicode escapes in string and char literals
		char c = '\u00A9'; // © (copyright sign)
		String sEsc = "\u00A9 2025";
		System.out.println("char c (unicode escape) = " + c);
		System.out.println("string with unicode escape = " + sEsc);

		char c1[] = Character.toChars(0x1F60A); // Returns {'\uD83D', '\uDE0A'}
		System.out.println(new String(c1));
		System.out.println(Arrays.toString(c1));
		for (char ch : c1) {
			System.out.println("char" + ch);
		}
		System.out.println("Printing emoji. Doesn't work without new String or print directly without this"
				+ new String(Character.toChars(0x1F60A))); // directly printing emoji using Character.toChars /

		// 2) Characters beyond BMP (e.g. emoji U+1F60A SMILING FACE WITH SMILING EYES)
		String emojiLiteral = "😊"; // direct literal (source file must be UTF-8)
		String emojiEscapes = "\uD83D\uDE0A"; // surrogate pair escapes that represent the same emoji
		int codePoint = emojiLiteral.codePointAt(0);

		System.out.println("emojiLiteral = " + emojiLiteral);
		System.out.println("emojiEscapes = " + emojiEscapes);
		System.out.println("emojiLiteral.length() (char count) = " + emojiLiteral.length());
		System.out.println("emojiLiteral.codePointCount(0, emojiLiteral.length()) = "
				+ emojiLiteral.codePointCount(0, emojiLiteral.length()));
		System.out.println(codePoint);
		System.out.println("emoji code point (hex) = U+" + Integer.toHexString(codePoint).toUpperCase());

		// 3) Converting a code point into char[] and a String
		int smileCodePoint = 0x1F60A; // decimal 128522
		// Converts the specified character (Unicode code point) to its UTF-16
		// representation stored in a char array. If the specified code point is a BMP
		// (Basic Multilingual Plane or Plane 0) value, the resulting char array has the
		// same value as codePoint. If the specified code point is a supplementary code
		// point, the resulting char array has the corresponding surrogate pair.
		String fromCodePoint = new String(Character.toChars(smileCodePoint));
		System.out.println(String.valueOf(Character.toChars(97)));
		System.out.println("fromCodePoint via Character.toChars = " + fromCodePoint);
		System.out.println(fromCodePoint);

		// 4) Demonstrate differences between char index and code point index
		String mix = "A" + emojiLiteral + "B"; // A😊B
		System.out.println("mix = " + mix);
		System.out.println("mix.length() = " + mix.length());
		System.out.println("mix.codePointCount(0, mix.length()) = " + mix.codePointCount(0, mix.length()));
		System.out.println("char at index 1 = " + mix.charAt(1)); // high surrogate
		System.out.println("codePointAt(1) = U+" + Integer.toHexString(mix.codePointAt(1)).toUpperCase());

		// 5) Unicode escapes are processed before compilation/tokenization
		// You can even use a unicode escape to spell an identifier. The next line
		// declares an int whose name is the Unicode escape for 'a' (\u0061 -> 'a').
		int \u0061 = 42; // equivalent to: int a = 42;
		System.out.println("value of identifier named 'a' (declared with unicode escape) = " + a);

		// 6) Useful helper methods
		String s = "\u03A9 OMEGA"; // Omega sign U+03A9
		int cp = s.codePointAt(0);
		System.out.println("s = " + s + ", codePointAt(0)=U+" + Integer.toHexString(cp).toUpperCase());

		// 7) Tips:
		System.out.println("\nTips:");
		System.out.println("- Save source files as UTF-8 to include Unicode characters directly.");
		System.out.println("- Use \\uXXXX escapes when you need to avoid non-ASCII in the source or for tooling.");
		System.out.println(
				"- Characters > U+FFFF are represented as surrogate pairs in char[]/String (length 2). Use codePointCount/Character.toChars to handle them safely.");
	}
}