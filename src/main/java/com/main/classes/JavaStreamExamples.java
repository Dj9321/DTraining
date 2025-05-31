package com.main.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional; // Required for reduce, findFirst, findAny
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collection; // Required for flatMap
import java.util.Comparator; // Required for sorted

public class JavaStreamExamples {

	public static void main(String[] args) {
		JavaStreamExamples examples = new JavaStreamExamples();
		examples.filterExample();
		examples.evenNumbers();
		examples.sortingList();
		examples.mapExample();
		examples.flatMapExample();
		examples.reduceExample();
		examples.distinctExample();
		examples.sortedExample();
		examples.matchExamples();
		examples.findExamples();
		examples.streamCreationExamples(); // Call the new streamCreationExamples method
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

	public void mapExample() {
		// Convert list of strings to uppercase
		List<String> words = Arrays.asList("hello", "world", "java", "streams");
		List<String> uppercaseWords = words.stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println("Uppercase words: " + uppercaseWords);

		// Square each number in a list of integers
		List<Integer> integerNumbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squaredNumbers = integerNumbers.stream()
				.map(n -> n * n)
				.collect(Collectors.toList());
		System.out.println("Squared numbers: " + squaredNumbers);
	}

	public void flatMapExample() {
		List<List<String>> listOfLists = Arrays.asList(
				Arrays.asList("alpha", "beta", "gamma"),
				Arrays.asList("delta", "epsilon"),
				Arrays.asList("zeta", "eta", "theta")
		);

		List<String> flattenedList = listOfLists.stream()
				.flatMap(Collection::stream) // or .flatMap(list -> list.stream())
				.collect(Collectors.toList());

		System.out.println("Flattened list: " + flattenedList);
	}

	public void reduceExample() {
		// 1. Sum of integers
		List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Integer sum = numberList.stream()
				.reduce(0, (a, b) -> a + b);
		System.out.println("Sum of integers: " + sum);

		// 2. Concatenate strings
		List<String> stringList = Arrays.asList("Java", " ", "Streams", " ", "are", " ", "powerful!");
		String concatenatedString = stringList.stream()
				.reduce("", (s1, s2) -> s1 + s2);
		System.out.println("Concatenated string: " + concatenatedString);
	}

	public void distinctExample() {
		// 1. Distinct strings
		List<String> duplicateStrings = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		List<String> distinctStrings = duplicateStrings.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Original strings: " + duplicateStrings);
		System.out.println("Distinct strings: " + distinctStrings);

		// 2. Distinct integers
		List<Integer> duplicateIntegers = Arrays.asList(1, 2, 3, 2, 4, 5, 1, 6, 3, 7, 7, 8);
		List<Integer> distinctIntegers = duplicateIntegers.stream()
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Original integers: " + duplicateIntegers);
		System.out.println("Distinct integers: " + distinctIntegers);
	}

	public void sortedExample() {
		// 1. Sort strings alphabetically
		List<String> unsortedStrings = Arrays.asList("banana", "apple", "cherry", "date", "blueberry");
		List<String> sortedStringsNatural = unsortedStrings.stream()
				.sorted()
				.collect(Collectors.toList());
		System.out.println("Unsorted strings: " + unsortedStrings);
		System.out.println("Sorted strings (natural): " + sortedStringsNatural);

		// 2. Sort integers in descending order
		List<Integer> unsortedIntegers = Arrays.asList(5, 1, 8, 3, 10, 4, 7);
		List<Integer> sortedIntegersDescending = unsortedIntegers.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("Unsorted integers: " + unsortedIntegers);
		System.out.println("Sorted integers (descending): " + sortedIntegersDescending);

		// 3. Sort strings by length
		List<String> unsortedByLength = Arrays.asList("kiwi", "strawberry", "apple", "banana", "fig");
		List<String> sortedByLength = unsortedByLength.stream()
				.sorted(Comparator.comparingInt(String::length))
				.collect(Collectors.toList());
		System.out.println("Unsorted strings (by length): " + unsortedByLength);
		System.out.println("Sorted strings (by length): " + sortedByLength);
	}

	public void matchExamples() {
		// 1. anyMatch: Check if any string contains 'a'
		List<String> stringList = Arrays.asList("apple", "banana", "cherry", "date");
		boolean anyMatchA = stringList.stream()
				.anyMatch(s -> s.contains("a"));
		System.out.println("Any string contains 'a': " + anyMatchA);

		List<String> stringListNoA = Arrays.asList("rhythm", "sky", "fly");
		boolean anyMatchANo = stringListNoA.stream()
				.anyMatch(s -> s.contains("a"));
		System.out.println("Any string in " + stringListNoA + " contains 'a': " + anyMatchANo);

		// 2. allMatch: Check if all numbers are greater than 0
		List<Integer> intListPositive = Arrays.asList(1, 2, 3, 4, 5);
		boolean allMatchPositive = intListPositive.stream()
				.allMatch(n -> n > 0);
		System.out.println("All numbers in " + intListPositive + " are > 0: " + allMatchPositive);

		List<Integer> intListMixed = Arrays.asList(1, 2, -3, 4, 5);
		boolean allMatchPositiveMixed = intListMixed.stream()
				.allMatch(n -> n > 0);
		System.out.println("All numbers in " + intListMixed + " are > 0: " + allMatchPositiveMixed);

		// 3. noneMatch: Check if no string is empty
		List<String> nonEmptyStrings = Arrays.asList("hello", "world", "java");
		boolean noneMatchEmpty = nonEmptyStrings.stream()
				.noneMatch(String::isEmpty);
		System.out.println("No string in " + nonEmptyStrings + " is empty: " + noneMatchEmpty);

		List<String> withEmptyString = Arrays.asList("hello", "", "java");
		boolean noneMatchEmptySome = withEmptyString.stream()
				.noneMatch(String::isEmpty);
		System.out.println("No string in " + withEmptyString + " is empty: " + noneMatchEmptySome);
	}

	public void findExamples() {
		// 1. findFirst: Find the first string starting with 'b'
		List<String> items = Arrays.asList("apple", "banana", "avocado", "blueberry", "cherry");
		Optional<String> firstB = items.stream()
				.filter(s -> s.startsWith("b"))
				.findFirst();
		System.out.println("findFirst Optional: " + firstB);
		System.out.println("First string starting with 'b': " + firstB.orElse("Not found"));

		Optional<String> firstX = items.stream()
				.filter(s -> s.startsWith("x"))
				.findFirst();
		System.out.println("findFirst Optional (for 'x'): " + firstX);
		firstX.ifPresentOrElse(
			s -> System.out.println("First string starting with 'x': " + s),
			() -> System.out.println("No string starting with 'x' found.")
		);

		// 2. findAny: Find any number divisible by 3
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Optional<Integer> anyDivisibleBy3 = nums.stream()
				.filter(n -> n % 3 == 0)
				.findAny();
		System.out.println("findAny Optional (divisible by 3): " + anyDivisibleBy3);
		anyDivisibleBy3.ifPresent(n -> System.out.println("Any number divisible by 3: " + n));

		System.out.println("Demonstrating findAny with parallel stream (multiple matches):");
		List<Integer> parallelNums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
		for (int i = 0; i < 5; i++) {
			Optional<Integer> anyParallel = parallelNums.parallelStream()
					.filter(n -> n % 3 == 0)
					.findAny();
			anyParallel.ifPresent(n -> System.out.print(n + " "));
		}
		System.out.println("\n(Note: Output for parallel findAny might vary or appear consistent in simple examples)");
	}

	// New method to demonstrate various ways to create streams
	public void streamCreationExamples() {
		// 1. Stream from array
		String[] array = {"one", "two", "three", "four", "five"};
		List<String> listFromArray = Arrays.stream(array)
				.collect(Collectors.toList());
		System.out.println("Stream from array: " + listFromArray);

		// 2. Stream.of()
		List<String> listFromOf = Stream.of("a", "b", "c", "d", "e")
				.collect(Collectors.toList());
		System.out.println("Stream.of(): " + listFromOf);

		// 3. Stream.iterate() - first 5 powers of 2
		List<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2)
				.limit(5)
				.collect(Collectors.toList());
		System.out.println("Stream.iterate() (powers of 2): " + powersOfTwo);

		// 4. Stream.generate() - 5 random numbers
		List<Double> randomNumbers = Stream.generate(Math::random)
				.limit(5)
				.collect(Collectors.toList());
		System.out.println("Stream.generate() (random numbers): " + randomNumbers);
	}
}
