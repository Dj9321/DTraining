package com.main.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamExamples {

	public static void main(String[] args) {
		JavaStreamExamples examples = new JavaStreamExamples();
		examples.filterExample();
		examples.evenNumbers();
		examples.sortingList();
	}

	List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	List<String> namesList = List.of("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay");
	List<String> namesArrayList = Arrays.asList("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay", "Ravi",
			"Kiran");

	private void filterExample() {
		Stream<String> stringStream = namesList.parallelStream();
		// Use toUpperCase to filter both small and capital letters (a or A)
		stringStream.filter(a -> a.toUpperCase().startsWith("A")).forEach(System.out::println);
		// same as .forEach(b -> System.out:println(b));

	}

	private void evenNumbers() {
		// Using a Stream to filter even numbers and then double them
		List<Integer> evenNumber = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()); // Filter even
																											// numbers
//				.toList(); // Collect the results into a new list
		System.out.println("Even Numbers List: " + evenNumber); // [2, 4, 6, 8, 10]
	}

	private void sortingList() {
//		Collections.sort(namesList); // we can't sort List.of()
		Collections.sort(namesArrayList); // default sorting
		System.out.println(namesArrayList);
		Collections.sort(namesArrayList, Collections.reverseOrder()); // reverse order returns a comparator
		System.out.println(namesArrayList);
		// Custom sorting based on comparision of 3rd character
		Collections.sort(namesArrayList, (s1, s2) -> s1.substring(2).compareTo(s2.substring(2)));
		System.out.println(namesArrayList);
	}
}
