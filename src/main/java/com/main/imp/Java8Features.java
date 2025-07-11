package com.main.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.main.classes.PersonDetails;

public class Java8Features {

	public static void main(String[] args) {
		Java8Features j = new Java8Features();
		j.defaultFuntionalInterfaces();
		j.defaultFuncIntPredicate();

	}

	PersonDetails dheeraj = new PersonDetails("Dheeraj", "33", "08-December");
	PersonDetails ravi = new PersonDetails("Ravi", "34", "10-January");
	List<PersonDetails> personsList = List.of(dheeraj, ravi);
	Consumer<PersonDetails> c1 = (s) -> System.out.println(s.name() + " ");
	Consumer<PersonDetails> c2 = (s) -> System.out.print(s.age() + " ");
	Consumer<PersonDetails> c3 = (s) -> System.out.println(s.Dob() + " ");
	Consumer<PersonDetails> c4 = c1.andThen(c2).andThen(c3);

	public void defaultFuntionalInterfaces() {
		Runnable r = () -> System.out.println("Thread running with lambda!");
		new Thread(r).start();

		// we can directly give in thread argument as below
		new Thread(() -> System.out.println("Thread running!")).start();

		// Consumer accept(); forEach() has consumer argument
		Consumer<String> c = (s) -> System.out.println(s.toUpperCase());
		c.accept("Hello Dheeraj");
		// You can call a consumer on any object so that the logic will be executed on
		// that object
		c1.accept(dheeraj);
		// Consumer chaining
		personsList.forEach(c1.andThen(c2).andThen(c3));

		// filtering > Write .accept() at the end because we are passing consumer
		// already and then passing a consumer implementation b -> {} to forEach()
		personsList.forEach(b -> {
			if (Integer.valueOf(b.age()) > 33) {
//				c1.andThen(c2).andThen(c3).accept(b);
				c4.accept(b);
				System.out.println(b.name());
			}
		});
		System.out.println("Chained consumer ");
		personsList.forEach(c4);
		System.out.println("Chained consumer end");
		BiConsumer<PersonDetails, PersonDetails> b = (e, d) -> System.out
				.println("Age : " + e.age() + " and " + d.age());
		b.accept(dheeraj, ravi);

		// Here we can't pass biconsumer directly to forEach so we pass as Consumer
		// lambda
		personsList.forEach(f -> b.accept(f, f));
	}

	public void defaultFuncIntPredicate() {
		// Predicate
		System.out.println("Predicate: ");
		Predicate<Integer> p1 = p -> p % 2 == 0;
		Predicate<Integer> p2 = p -> p % 5 == 0;

		boolean b1 = p1.test(15);
		System.out.println(b1);

		boolean b2 = p1.and(p2).test(50);
//		or() in Predicate: 
		boolean b3 = p1.or(p2).test(33);
		System.out.println(b2 + " " + b3);

//		negate() in Predicate:
		boolean b4 = p1.or(p2).negate().test(33);
		System.out.println("b4: " + b4);

		Predicate<PersonDetails> pp = g -> Integer.valueOf(g.age()) > 33;
		personsList.forEach(bp -> {
//			we can use predicate here instead of the if-condition
//			if (Integer.valueOf(b.age()) > 33) {
			if (pp.test(bp)) {
				c4.accept(bp);
			}
		});

		BiPredicate<PersonDetails, Integer> bip = (g1, g2) -> Integer.valueOf(g1.age()) > g2;
		System.out.println("Bi Predicate: ");
		personsList.forEach(bp -> {
			if (bip.test(bp, 21)) {
				c4.accept(bp);
			}
		});
		
		//removeIf() from Collection. List.of() is immutable so we can't remove.
//		personsList.removeIf(pp);
//		ArrayList<PersonDetails> personModifiableList = new ArrayList<> (List.of(personList));
	}

	public void predicateAsArgument(Predicate<PersonDetails> p) {
		personsList.forEach(bp -> {
			if (p.test(bp)) {
				c4.accept(bp);
			}
		});
	}
}
