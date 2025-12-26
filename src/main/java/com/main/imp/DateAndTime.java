package com.main.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
		LocalDate.of(2015, Month.JANUARY, 32); // throws DateTimeException Invalid value for DayOfMonth (valid values 1
												// - 28/31): 32
// 		OLD way:  The Date class represented both the date and time whether you wanted it to or not.
		Date d = new Date();
		
		// creating a particular date in OLD way
		Calendar c = Calendar.getInstance();
		c.set(2015, Calendar.­JANUARY, 1);
		Date jan = c.getTime();
		
		// Manipulating Dates and Times
		LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
		System.out.println(date);          // 2014-01-20
		date = date.plusDays(2);
		System.out.println(date);          // 2014-01-22
		date = date.plusWeeks(1);
		System.out.println(date);          // 2014-01-29
		// Java is smart enough to realize February 29, 2014 does not exist and gives us February 28, 2014 instead. 
		date = date.plusMonths(1);
		System.out.println(date);          // 2014-02-28
		date = date.plusYears(5);
		System.out.println(date);          // 2019-02-28


	}
}
