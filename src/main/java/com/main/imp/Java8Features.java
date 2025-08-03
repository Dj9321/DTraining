package com.main.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import com.main.classes.InterfaceA;
import com.main.classes.Person;
import com.main.classes.PersonDetails;

public class Java8Features implements InterfaceA {

	public static void main(String[] args) {
		Java8Features j = new Java8Features();
		j.defaultFuntionalInterfaces();
		j.defaultFuncIntPredicate();
		j.funtionInterface();
		j.unaryBinaryOperators();
		j.supplierFunction();
		j.methodAndConstructorReferences();
		j.lamdas();
		Java8Features.abc();
		InterfaceA.abc();
		j.defaultAndComparatorMethods();
		j.forEachMethods();
		j.lambdaExpressions(55);
	}

	// similar method abc() in InterfaceA interface
	static void abc() {
		System.out.println("in the impelmented method Java8Features ");
	}

	PersonDetails dheeraj = new PersonDetails("Dheeraj", 33, "08-December");
	PersonDetails ravi = new PersonDetails("Ravi", 34, "10-January");
	PersonDetails sudheer = new PersonDetails("Sudheer", 30, "10-March");
	PersonDetails mahesh = new PersonDetails("Mahesh", 28, "10-February");
	List<PersonDetails> personsList = List.of(dheeraj, ravi, sudheer, mahesh);
	Consumer<PersonDetails> c1 = (s) -> System.out.println(s.name() + " ");
	Consumer<PersonDetails> c2 = (s) -> System.out.print(s.age() + " ");
	Consumer<PersonDetails> c3 = (s) -> System.out.println(s.Dob() + " ");
	Consumer<PersonDetails> c4 = c1.andThen(c2).andThen(c3);
	Integer fifty = 50;
	Person pn1 = new Person("Dheeraj", "1", "Banking", "Fresher");
	Person pn2 = new Person("Avinash", "2", "Banking", "Fresher");
	Person pn3 = new Person("Deepak", "3", "HR", "Senior Consultant");
	Person pn4 = new Person("Sandeep", "4", "HR", "Fresher");
	Person pn5 = new Person("Rohit", "5", "Automobile", "Fresher");
	Person pn6 = new Person("Ravi", "6", "Automobile", "Senior Consultant");

	List<Person> newPersonList = Arrays.asList(pn1, pn2, pn3, pn4, pn5, pn6);

	public void defaultFuntionalInterfaces() {
		System.out.println("============= Funtional Interface ==============");
		Runnable r = () -> System.out.println("Thread running with lambda!");
		new Thread(r).start();

		// we can directly give in thread argument as below
		new Thread(() -> System.out.println("Thread running!")).start();

		// Consumer accept(); forEach() has consumer argument
		System.out.println("Consumer: ***Starts***");
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
		System.out.println("=============  Predicate Interface ==============");
		// Predicate
		System.out.println("Predicate: ***Starts***");
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
		System.out.println("Bi Predicate: ***Starts*** ");
		personsList.forEach(bp -> {
			if (bip.test(bp, 21)) {
				c4.accept(bp);
			}
		});

		// removeIf() from Collection. List.of() is immutable so we can't remove.
//		personsList.removeIf(pp);
//		ArrayList<PersonDetails> personModifiableList = new ArrayList<> (List.of(personList));
		
		List<Person> p = new ArrayList<>();
		p.add(pn1);
		p.add(pn2);
		p.add(pn3);
		
		// removeIf(Predicate) 
		p.removeIf(c -> c.getDepartment().equals("HR"));
		System.out.println(p);
	}

	public void predicateAsArgument(Predicate<PersonDetails> p) {
		System.out.println("============= Predicate as Argument ==============");
		personsList.forEach(bp -> {
			if (p.test(bp)) {
				c4.accept(bp);
			}
		});
	}

	public void funtionInterface() {
		System.out.println("============= Funtion Interface ==============");
		// Function - BiFunction, UnaryOperator, BinaryOperator - apply() >
		// Function<InputType, OutputType>
		System.out.println("Function Interface: ***Starts***");
		Function<String, String> a = f -> f.toUpperCase();
		Function<String, String> h = f -> f.concat(" Siramdas");
		String b = a.andThen(h).apply("dheeraj");
		System.out.println(b);
		// Returns a composed function that first applies the before function to its
		// input, and then applies this function to the result.
		String c = a.compose(h).apply("dheeraj");
		System.out.println(c);
		// we can use function in another class as well
		String h1 = NewFeatures.addingDefaultText.apply("Dheeraj");
		System.out.println(h1);
		// Creating a map with name and dob
		Function<List<PersonDetails>, Map<String, String>> h2 = j1 -> {
			Map<String, String> personAndDobMap = new HashMap<>();
			j1.forEach(d1 -> {
				personAndDobMap.put(d1.name(), d1.Dob());
			});
			return personAndDobMap;
		};

		Map<String, String> personMap = h2.apply(personsList);
		System.out.println(personMap);

		// BiFunction<Input1, Input2, ReturnType>
		BiFunction<List<PersonDetails>, Predicate<PersonDetails>, Map<String, Integer>> bifunc = (personList,
				personCondition) -> {
			Map<String, Integer> personMap1 = new HashMap<>();
			personList.forEach(pl -> {
				if (personCondition.test(pl)) {
					personMap1.put(pl.name(), pl.age());
				}
			});
			return personMap1;
		};
		Map<String, Integer> map = bifunc.apply(personsList, p -> p.age() > 33);
		System.out.println("Persons with age greater than 33: " + map);
	}

	public void unaryBinaryOperators() {
		System.out.println("============= Unary Binary Operators ==============");
		System.out.println("Unary / Biinary operator ***Starts***");
		UnaryOperator<String> u = p -> p.strip();
		String d = u.apply("    Dheeraj Siramdas ");
		System.out.println(d);
		// BinaryOperator has minBy() & maxBy() methods.
		BinaryOperator<String> bo = (x, y) -> x.concat(y).repeat(5);
		String de = bo.apply("Siramdas ", "Elite ");
		System.out.println(de);
		BinaryOperator<Double> intB = (w, e) -> Math.pow(w, e);
		System.out.println(intB.apply(2.0, 4.0));
		BinaryOperator<Integer> s = BinaryOperator.maxBy((s1, s2) -> s1.compareTo(s2));
		Integer s3 = s.apply(15, 88);
		System.out.println(s3);
		PersonDetails pd = new PersonDetails("M", 40, null);
		if (pd.Dob() == null) {
			System.out.println("DOB is null ");
		}
	}

	/**
	 * Doesn’t take anything but returns an Object
	 */
	public void supplierFunction() {
		System.out.println("============= Supplier function ==============");
		Supplier<List<PersonDetails>> pd = () -> personsList;
		List<PersonDetails> personListSame = pd.get();
		personListSame.forEach(c1 -> System.out.print(c1.name()));
	}

	public void methodAndConstructorReferences() {
		System.out.println("============= Method & Constructor References ==============");
		Consumer<String> c1 = System.out::println;
		// instead of s -> s.toUpperCase() use below
		Function<String, String> func = String::toUpperCase;
		BiFunction<Double, Double, Double> max = Math::pow;
		Double p1 = max.apply(3d, 5d);
		c1.accept(p1.toString());
		c1.accept(func.apply("dheeraj"));
		// There is no no arg constructor. The Supplier doesn't give any argument
//		Supplier<PersonDetails> newPerson = PersonDetails::new;
		// still there is no constructor that takes one argument or 2 arguments as
		// string
//		BiFunction<String, String, PersonDetails> nPWithArgument = PersonDetails::new;

	}

	public void lamdas() {
		System.out.println("============= Lambdas ==============");
		int a = 100;
		Function<Integer, Integer> num = (b) -> {
//			we can't do a++ or a = 55 > Local variable should be effectively final.
//			a++; a= 55;
			fifty++; // this is an instance variable so works fine.
			if (a == 1000)
				return b * b;
			else
				return b + a + fifty;
		};
		Integer c = num.apply(a);
		System.out.println(c);
		personsList.forEach((p) -> System.out.println(p));
	}

	int instanceVariable = 44;
	
	public void lambdaExpressions(int parameter) {
		instanceVariable = 33; // we can change this
		int localVariable = 10;
		final int finalVariable = 30;
//		localVariable = 99; // we can't write this as local variable should be final or effectively final
		BiFunction<Integer, Integer, Integer> testExpression = (x, y) -> {
			return x + y + instanceVariable + parameter + localVariable + finalVariable;
		};
		Integer d = testExpression.apply(22, 33);
		System.out.println(d);
	}

	public void defaultAndComparatorMethods() {
		System.out.println("============= Default Methods ==============");
		List<String> personList = Arrays.asList("Ravi", "Mahesh", "Santosh", "Rakesh", "Srikanth", "Kiran");
		List<String> personList1 = Arrays.asList("Ravi", "Mahesh", "Santosh", "Rakesh", "Srikanth", "Kiran");
		// 1. Natural order sorting
		personList.sort(Comparator.naturalOrder());
		System.out.println(personList);
		// 2. reverse order
		personList.sort(Comparator.reverseOrder());
		System.out.println(personList);
		// before java 8 we would have used Collections.sort()
		Collections.sort(personList1);
		System.out.println(personList1);
		// 3. Comparator Chaining > thenComparing() > First sorts based on Designation,
		// then if both have same designation sorts based on name
		newPersonList.sort(
				Comparator.comparing(Person::getDesignation).thenComparing(Comparator.comparing(Person::getName)));
		System.out.println(newPersonList);
	}

	/**
	 * forEachMethods
	 */
	public void forEachMethods() {
		System.out.println("============= forEach Methods ==============");
		Map<String, Integer> scores = new HashMap<>();
		scores.put("Alice", 90);
		scores.put("Bob", 85);
		scores.put("Charlie", 95);

		// forEach for HashMap
		scores.forEach((name, score) -> System.out.println(name + " scored " + score));
	}

}
