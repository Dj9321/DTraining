package com.test.main.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.test.main.classes.JuniExamples;
import com.test.main.classes.Person;

class JunitExamplesTest {

	@Mock
	Person p;

	@BeforeAll
	public List<Person> addPersons() {

		Person p1 = new Person("Dheeraj", "1");
		Person p2 = new Person("Dheeraj1", "2");
		List<Person> p = new ArrayList<Person>();
		p.add(p1);
		p.add(p2);
		return p;
	}

	@Test
	void test() {
		JuniExamples j1 = new JuniExamples();
		List<Person> p2 = addPersons();
		j1.aStream(p2);
		List.of(p).stream().filter(a -> a.getName() != null).forEach(a -> assertNotNull(a.getName()));
		assertThat(p.getName(), isNotNull());
		assertTrue(true);
	}
}
