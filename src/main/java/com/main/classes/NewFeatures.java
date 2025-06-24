package com.main.classes;

import java.time.Month;

public class NewFeatures {

	public static void main(String[] args) {
		NewFeatures n = new NewFeatures();
		n.textBlocksExample("Dheeraj");
		n.switchExample("S");
	}

	public void textBlocksExample(String name) {
		var sentence = "hello";
		var textBlockSentence = """
				this is a textblock
				sentence with %s space in between
				""";
		var textBlockSentenceWithIndentChange = """
					this is a textblock
					sentence with   space in between
				""";

		var multiline = """
				Replacing name: %s
				in a multiline string
				""".formatted(name); // inserting name

		System.out.println(textBlockSentence);
		System.out.println(textBlockSentenceWithIndentChange);
		System.out.println(multiline);
	}

	public void switchExample(String day) {
		String result = switch (day) {
		// Java allows use of operator ->(arrow) instead of : (colon) to denote the return expression.
		case "M", "W", "F" -> "Monday, Wednesday or Friday";
		case "T", "TH", "S" -> "Tuesday, Thursday or Saturday"; // break is not needed
		default -> { // default is required. If multi line use {} and use yield to return value.
			if (day.isEmpty())

				yield "Please insert a valid day.";
			else
				yield "Looks like a Sunday.";
		}
		case "A" -> "No weekday starting with A";

		};
		System.out.println(result);
	}
	
	public void switchExampleE2(Month month) {
		String result = switch (month) {
		// Java allows use of operator ->(arrow) instead of : (colon) to denote the return expression.
		case JANUARY, FEBRUARY, MARCH -> "Monday, Wednesday or Friday";
		case  APRIL, MAY, JUNE, JULY -> "Tuesday, Thursday or Saturday"; // break is not needed
		case  AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER -> "Tuesday, Thursday or Saturday"; // break is not needed
		// default is not needed here as we wrote all the possible enum values for Month.
		};
		System.out.println(result);
	}

}
