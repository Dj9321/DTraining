package com.test.main.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import com.main.classes.EmployeeDetails;
import com.main.classes.EmployeeSealedClass;
import com.main.classes.Person;
import com.main.imp.JuniExamples;
import com.main.imp.NewFeatures;

class JunitExamplesTest {

	@Mock
	Person p;
	NewFeatures nf = new NewFeatures();

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

	@ParameterizedTest
	@MethodSource("input")
	void pattern(Object value, String expectedResult) {
		var output = nf.patternMatching(value);
		assertEquals(expectedResult, output);
	}

	private static Stream<Arguments> input() {
		return Stream.of(Arguments.of("Hello", "Hello"), Arguments.of(33, "Integer33"));
	}
}
