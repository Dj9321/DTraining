package com.main.imp;

import java.util.List;
import java.util.function.Consumer;

import com.main.classes.PersonDetails;

public class Java8Features {

	public static void main(String[] args) {
		Java8Features j = new Java8Features();
		j.defaultFuntionalInterfaces();

	}

	public void defaultFuntionalInterfaces() {
		Runnable r = () -> System.out.println("Thread running with lambda!");
		new Thread(r).start();

		// we can directly give in thread argument as below
		new Thread(() -> System.out.println("Thread running!")).start();

		// Consumer accept(); forEach() has consumer argument
		Consumer<String> c = (s) -> System.out.println(s.toUpperCase());
		c.accept("Hello Dheeraj");

		PersonDetails dheeraj = new PersonDetails("Dheeraj", "33", "08-December");
		PersonDetails ravi = new PersonDetails("Ravi", "34", "10-January");
		List<PersonDetails> personsList = List.of(dheeraj, ravi);
		Consumer<PersonDetails> c1 = (s) -> System.out.print(s.name() + " ");
		Consumer<PersonDetails> c2 = (s) -> System.out.print(s.age() + " ");
		Consumer<PersonDetails> c3 = (s) -> System.out.print(s.Dob() + " ");
		// Consumer chaining
		personsList.forEach(c1.andThen(c2).andThen(c3));
	}
}
