package com.main.classes;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NewFeatures {

	public static void main(String[] args) {
		NewFeatures n = new NewFeatures();
		n.textBlocksExample("Dheeraj");
		n.switchExample("S");
		n.switchExampleE2(Month.FEBRUARY, Year.of(2024)); // Year of 2025
		n.varExample();
		n.recordExample();
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

	public void varExample() {
		var map = Map.ofEntries(Map.entry("a", List.of("b", "c")));
		System.out.println("var Example: " + map);
	}

	public void switchExample(String day) {
		String result = switch (day) {
		// Java allows use of operator ->(arrow) instead of : (colon) to denote the
		// return expression.
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

	public void switchExampleE2(Month month, Year year) {
		String result = switch (month) {
		// Java allows use of operator ->(arrow) instead of : (colon) to denote the
		// return expression.
		case APRIL, JUNE, SEPTEMBER, NOVEMBER -> "30 days"; // break is not needed
		case FEBRUARY -> year.isLeap() ? "29 days" : "28 days";
		default -> "31 days";
		};

		int daysInMonth = switch (month) {
		// default is not needed here as we wrote all the possible enum values for
		// Month.
		case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER -> {
			yield 31; // you can also give directly. If multiline you can give like this.
		} // you don't need a semicolon if you give {}
		case APRIL, JUNE, SEPTEMBER, NOVEMBER -> 30;
		case FEBRUARY -> year.isLeap() ? 29 : 28;
		};
		System.out.println("Days in Month: " + daysInMonth);
		System.out.println(result);

		boolean isLeapYear = Year.isLeap(year.getValue()); // getValue()
		System.out.println(isLeapYear ? year.getValue() + " is a leap year" : year.getValue() + " is Not a leap Year");
	}

	public void recordExample() {
		User u = new User("Dheeraj", 35);
//		u.getName(); > There is no getName() just name();
		System.out.println("Name: " + u.name());
		// as toString is already implemented you can directly print and get meaningful
		// data.
		System.out.println(u);
		User u1 = new User("Dheeraj");
		System.out.println(u1);
		EmployeeRecord e = new EmployeeRecord("", 33, "Electronics");
		try {
			System.out.println(e);
		} catch (Exception ex) {
			ex.getCause();
		}
	}

	public record User(String name, int age) {
		// Just hold the data. Use record instead of class.
		// Have auto generated equals(), hashCode() and toString() functions.
		// Record classes are immutable by default, meaning all properties are final
		// In cases using property types that are inherently mutable, for instance an
		// array, explicitly declaring the accessor method for the property is a way to
		// ensure immutability.

		public User {
			if (name.isBlank()) {
				throw new IllegalArgumentException("User Name can't be blank");
			}
		}

		public User(String user) {
			// If you want a record to have a no-arg constructor, records do allow adding
			// extra constructors or factory methods, as long as the "canonical constructor"
			// that takes all of the record fields as arguments is called.
			this(user, 0);

		}

		@Override
		public final boolean equals(Object arg0) {
			if (this == arg0)
				return true;
			if (!(arg0 instanceof User u2)) {
				return false;
			}
			// here u2 is from above if condition. It should be arg0 but that's an object
			return Objects.equals(name, u2.name);
		}

		@Override
		public final int hashCode() {
			// using Objects.hash and passing all the values.
			return Objects.hash(name, age);
		}
	};

}
