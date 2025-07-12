package com.main.imp;

import java.util.ArrayList;
import java.util.List;

import com.main.classes.Person;

public class JunitExamples {

	public void aStream(List<Person> personList) {
		personList.stream().filter(a -> a.getName() != null).forEach(b -> System.out.println(b.getName()));
	}

	public static void main(String[] args) {
		ArrayList<Person> personList = new ArrayList<Person>();
		Person p = new Person("Dheeraj", "1");
		Person p1 = new Person("Siramdas", "2");
		personList.add(p);
		personList.add(p1);
		JuniExamples j = new JuniExamples();
		j.aStream(personList);
	}

}
