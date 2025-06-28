package com.test.main.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.main.classes.EmployeeDetails;
import com.main.classes.EmployeeSealedClass;
import com.main.classes.JuniExamples;
import com.main.classes.Person;

class JunitExamplesTest {

	@Mock
	Person p;

//	@BeforeAll
//	public List<Person> addPersons() {
//		Person p1 = new Person("Dheeraj", "1");
//		Person p2 = new Person("Dheeraj1", "2");
//		List<Person> personList = new ArrayList<Person>();
//		personList.add(p1);
//		personList.add(p2);
//		return personList;
//	}

//	@Test
//	void test() {
//		JuniExamples j1 = new JuniExamples();
//		List<Person> p2 = addPersons();
//		j1.aStream(p2);
//		List.of(p).stream().filter(a -> a.getName() != null).forEach(a -> assertNotNull(a.getName()));
//		assertThat(p.getName(), isNotNull());
//		assertTrue(true);
//	}
	
	@Test
	void testInstanceOfforSealdeClasses() {
		var employee = new EmployeeDetails();
		assertInstanceOf(EmployeeSealedClass.class, employee);
	}
}
