package com.main.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {
	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		// This time displays hours, minutes, seconds, and nanoseconds.
		System.out.println(LocalTime.now());
		// Java uses T to separate the date and time when converting LocalDateTime to a
		// String.
		System.out.println(LocalDateTime.now());

		LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
		// it is good to use the Month constants (to make the code easier to read),
		// Java counts starting with 0. Well, months are an exception. For months in the
		// new date and time methods, Java counts starting from 1 like we human beings
		// do
		LocalDate date2 = LocalDate.of(2015, 1, 20); // Year, month, date

		LocalTime time1 = LocalTime.of(6, 15); // hour and minute
		LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
		LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
		System.out.println(time1);
		System.out.println(time2);
		System.out.println(time3);

		LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		LocalDateTime dateTime2 = LocalDateTime.of(date1, time1);
		System.out.println(dateTime1);
		System.out.println(dateTime2);

		LocalDateTime dateTime3 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
		// Create date and time separtely and then combine as below:
		LocalDateTime dateTime4 = LocalDateTime.of(date1, time1);

//		LocalDate d = new LocalDate(); // DOES NOT COMPILE
//		LocalDate.of(2015, Month.JANUARY, 32); // throws DateTimeException Invalid value for DayOfMonth (valid values 1
		// - 28/31): 32
// 		OLD way:  The Date class represented both the date and time whether you wanted it to or not.
		Date d = new Date();

		// creating a particular date in OLD way
		Calendar c = Calendar.getInstance();
		c.set(2015, 0, 1); // OLD ways starts with 0
		Date jan = c.getTime();

		// Manipulating Dates and Times > plus Days, Weeks, Months, Years
		LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
		System.out.println(date); // 2014-01-20
		date = date.plusDays(2);
		System.out.println(date); // 2014-01-22
		date = date.plusWeeks(1);
		System.out.println(date); // 2014-01-29
		// Java is smart enough to realize February 29, 2014 does not exist and gives us
		// February 28, 2014 instead.
		date = date.plusMonths(1);
		System.out.println(date); // 2014-02-28
		date = date.plusYears(5);
		System.out.println(date); // 2019-02-28

		// minus Days, Hours, Minutes, Seconds, Nanos
		LocalDate date3 = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time = LocalTime.of(5, 15);
		LocalDateTime dateTime = LocalDateTime.of(date3, time);
		System.out.println(dateTime); // 2020-01-20T05:15
		dateTime = dateTime.minusDays(1);
		System.out.println(dateTime); // 2020-01-19T05:15
		dateTime = dateTime.minusHours(10);
		System.out.println(dateTime); // 2020-01-18T19:15
		dateTime = dateTime.minusSeconds(30);
		// Displays seconds as we are using seconds in above line.
		System.out.println(dateTime); // 2020-01-18T19:14:30

		// Chaining methods:
		LocalDateTime dateTime5 = LocalDateTime.of(date2, time).minusDays(1).minusHours(10).minusSeconds(30);

		// Whenever you see immutable types, pay attention to make sure the return value
		// of a method call isn't ignored.
		LocalDate date4 = LocalDate.of(2020, Month.JANUARY, 20);
		date4.plusDays(10);
		System.out.println(date4);

		// Date does not contain time
//		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
//		date = date.plusMinutes(1);     // DOES NOT COMPILE

		LocalDate start = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
		// isBefore()
		performAnimalEnrichment(start, end);

		// LocalDate > toEpochDay(), LocalDateTime has toEpochTime() > Time from Jan 1
		// 1970 GMT
		// LocalTime doesn't have this method as it represents a time that occurs on any
		// date
		System.out.println(date2.toEpochDay());
//		System.out.println(dateTime.toEpochSecond());

		// Period:
		Period period = Period.ofMonths(1); // create a period
		performAnimalEnrichment(start, end, period);

//		There are five ways to create a Period class:
		Period annually = Period.ofYears(1); // every 1 year
		Period quarterly = Period.ofMonths(3); // every 3 months
		Period everyThreeWeeks = Period.ofWeeks(3); // every 3 weeks
		Period everyOtherDay = Period.ofDays(2); // every 2 days
		Period everyYearAndAWeek = Period.of(1, 0, 7); // every year and 7 days
		// There's one catch. You cannot chain methods when creating a Period.

//		Period wrong = Period.ofYears(1).ofWeeks(1);          // every week  DOESN'T work
//		Period wrong = Period.ofYears(1);
//		wrong = Period.ofWeeks(7);

		LocalDate date5 = LocalDate.of(2015, 1, 20);
		LocalTime time4 = LocalTime.of(6, 15);
		LocalDateTime dateTime6 = LocalDateTime.of(date5, time4);
		Period period1 = Period.ofMonths(1);
		System.out.println(date5.plus(period1)); // 2015-02-20
		System.out.println(dateTime6.plus(period1)); // 2015-02-20T06:15
//		System.out.println(time4.plus(period)); // UnsupportedTemporalTypeException > Unsupported unit: Months
		
		// More information from Date: getDayOfWeek(), getMonth(), getYear(), getDayOfYear()
		LocalDate date6 = LocalDate.of(2020, Month.JANUARY, 20);
		System.out.println(date6.getDayOfWeek());     // MONDAY
		System.out.println(date6.getMonth());          // JANUARY
		System.out.println(date6.getYear());          // 2020
		System.out.println(date6.getDayOfYear());     // 20
		
//		To format date the way we like we can use DateTimeFormatter.
		
	}

	private static void performAnimalEnrichment(LocalDate start, LocalDate end, Period period) { // uses the generic
																									// period
		LocalDate upTo = start;
		while (upTo.isBefore(end)) {
			System.out.println("give new toy: " + upTo);
			upTo = upTo.plus(period); // adds the period
		}
	}

	private static void performAnimalEnrichment(LocalDate start, LocalDate end) {
		LocalDate upTo = start;
		while (upTo.isBefore(end)) { // check if still before end
			System.out.println("give new toy: " + upTo);
			upTo = upTo.plusMonths(1); // add a month
		}
	}
}
