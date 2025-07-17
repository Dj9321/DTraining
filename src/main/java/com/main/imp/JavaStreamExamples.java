package com.main.imp;

import java.util.Arrays;
import java.util.Collection; // Required for flatMap
import java.util.Collections;
import java.util.Comparator; // Required for sorted
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Required for reduce, findFirst, findAny
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.main.classes.Person;
import com.main.classes.PersonFullDetails;

public class JavaStreamExamples {

	public static void main(String[] args) {
		JavaStreamExamples examples = new JavaStreamExamples();
		examples.filterAndPeekExample();
		examples.evenNumbers();
		examples.sortingList();
		examples.mappingOrExtracting();
		examples.mapExample();
		examples.flatMapExample();
		examples.reduceExample();
		examples.distinctExample();
		examples.sortedExample();
		examples.matchExamples();
		examples.findExamples();
		examples.streamCreationExamples(); // Call the new streamCreationExamples method
		examples.collectingAndGrouping();
		examples.intStreams();
	}

	List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	List<String> namesList = List.of("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay");
	List<String> namesArrayList = Arrays.asList("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay", "Ravi",
			"Kiran");
	List<Person> personList = Arrays.asList(new Person("Dheeraj ", "1"), new Person("Evanshi ", "2"),
			new Person("Malathi ", "3"), new Person("Siramdas ", "4"));
	List<String> addresses = List.of("abc, area, pincode", "def, area, pincode2");
	PersonFullDetails fullDetails1 = new PersonFullDetails("Dhj", 44, "01-01-2020", addresses);
	PersonFullDetails fullDetails2 = new PersonFullDetails("SDj", 33, "01-01-1990", addresses);
	List<PersonFullDetails> personListFull = Arrays.asList(fullDetails1, fullDetails2);

	private void filterAndPeekExample() {
		System.out.println("Filter and Peek Example ***Starts***");
		Stream<String> stringStream = namesList.parallelStream();
		// Use toUpperCase to filter both small and capital letters (a or A)
		stringStream.filter(a -> a.toUpperCase().startsWith("A")).forEach(System.out::println);
		// same as .forEach(b -> System.out:println(b));
//		we can write peek in between 2 filters (Multiple filters) or debugging what element is going next
		List<String> names = namesList.stream().peek(a -> System.out.printf("%s ", a))
				.filter(a -> a.toUpperCase().startsWith("A")).collect(Collectors.toList());
//		System.out.println(names);
	}

	private void evenNumbers() {
		System.out.println("Even Numbers ***Starts***");
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

	private void mappingOrExtracting() {
		System.out.println(" ======= Map or Extracting =======");
		List<Integer> lengths = namesArrayList.stream().map(String::length) // or map(fruit -> fruit.length())
				.collect(Collectors.toList());
		System.out.println(lengths);

		List<Integer> incrementNumebrs = numbers.stream().map(n -> n + 100) // or map(fruit -> fruit.length())
				.collect(Collectors.toList());
		System.out.println(incrementNumebrs);

		List<String> upperCaseNames = namesArrayList.stream().peek(System.out::println)
				.map(names -> names.toUpperCase()).collect(Collectors.toList());
		System.out.println(upperCaseNames);
		List<String> numberConcatenateToString = numbers.stream().map(n -> "Number: " + n).collect(Collectors.toList()); // Filter
		// even
		System.out.println(numberConcatenateToString);

		// Extracting only few fields from an object and pushing it to a list
		// Input is person List > output is Name and id only. Transforming object
		// You can even chain .map() methods
		List<String> personNames = personList.stream().map(a -> a.getName() + " " + a.getId()).map(String::toUpperCase)
				.toList();
		System.out.println(personNames);

		// Change only one field in an object and return the entire object using return
		// in map
		List<Person> changedPersons = personList.stream().map(a -> {
			a.setName(a.getName() + " New ");
			return a;
		}).toList();
		System.out.println(changedPersons);

	}

	public void mapExample() {
		// Square each number in a list of integers
		List<Integer> integerNumbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squaredNumbers = integerNumbers.stream().map(n -> n * n).collect(Collectors.toList());
		System.out.println("Squared numbers: " + squaredNumbers);
	}

	public void flatMapExample() {
		System.out.println("===================== Flat Map =====================");
		List<List<String>> listOfLists = Arrays.asList(Arrays.asList("alpha", "beta", "gamma"),
				Arrays.asList("delta", "epsilon"), Arrays.asList("zeta", "eta", "theta"));

		List<String> flattenedList = listOfLists.stream().flatMap(Collection::stream) // or .flatMap(list ->
																						// list.stream())
				.collect(Collectors.toList());
		System.out.println("Flattened list: " + flattenedList);
//		We get Stream<List<String>> > only if we flatten all we can collect as List<String>
		// because it is List we gave List::stream. If it was Set > Set::stream
		var onlyAdd = personListFull.stream().map(f -> f.addresses()).flatMap(List::stream)
				.collect(Collectors.toList());
		System.out.println(onlyAdd);
	}

	public void reduceExample() {
		System.out.println("===================== Reduce Example =====================");
		// 1. Sum of integers
		System.out.println("reduce Example ***Starts***");
		var numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Integer sum = numberList.stream().peek(System.out::print).reduce(0, (a, b) -> a + b);
		System.out.println("Sum of integers: " + sum);

		// 2. Concatenate strings
		List<String> stringList = Arrays.asList("Java", " ", "Streams", " ", "are", " ", "powerful!");
		String concatenatedString = stringList.stream().reduce("", (s1, s2) -> s1 + s2);
		System.out.println("Concatenated string: " + concatenatedString);
	}

	public void distinctExample() {
		// 1. Distinct strings
		List<String> duplicateStrings = Arrays.asList("apple", "banana", "apple", "cherry", "banana", "apple");
		List<String> distinctStrings = duplicateStrings.stream().distinct().collect(Collectors.toList());
		System.out.println("Original strings: " + duplicateStrings);
		System.out.println("Distinct strings: " + distinctStrings);

		// 2. Distinct integers
		List<Integer> duplicateIntegers = Arrays.asList(1, 2, 3, 2, 4, 5, 1, 6, 3, 7, 7, 8);
		List<Integer> distinctIntegers = duplicateIntegers.stream().distinct().collect(Collectors.toList());
		System.out.println("Original integers: " + duplicateIntegers);
		System.out.println("Distinct integers: " + distinctIntegers);
	}

	public void sortedExample() {
		// 1. Sort strings alphabetically
		List<String> unsortedStrings = Arrays.asList("banana", "apple", "cherry", "date", "blueberry");
		List<String> sortedStringsNatural = unsortedStrings.stream().sorted().collect(Collectors.toList());
		System.out.println("Unsorted strings: " + unsortedStrings);
		System.out.println("Sorted strings (natural): " + sortedStringsNatural);

		// 2. Sort integers in descending order
		List<Integer> unsortedIntegers = Arrays.asList(5, 1, 8, 3, 10, 4, 7);
		List<Integer> sortedIntegersDescending = unsortedIntegers.stream().sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		System.out.println("Unsorted integers: " + unsortedIntegers);
		System.out.println("Sorted integers (descending): " + sortedIntegersDescending);

		// 3. Sort strings by length
		List<String> unsortedByLength = Arrays.asList("kiwi", "strawberry", "apple", "banana", "fig");
		List<String> sortedByLength = unsortedByLength.stream().sorted(Comparator.comparingInt(String::length))
				.collect(Collectors.toList());
		System.out.println("Unsorted strings (by length): " + unsortedByLength);
		System.out.println("Sorted strings (by length): " + sortedByLength);

		// Sorting based on 3rd character String
		List<Person> sortedPersonList = personList.stream()
				.sorted((a, b) -> a.getName().substring(2).compareTo(b.getName().substring(2))).toList();
		System.out.println("Sorted Person List: " + sortedPersonList);

		// Sorting based on 3rd character String
		List<Person> sortedBasedOnIdPersonList = personList.stream().sorted((a, b) -> a.getId().compareTo(b.getId()))
				.toList();
		System.out.println("Sorted Person List Based on Id: " + sortedBasedOnIdPersonList);
	}

	public void matchExamples() {
		// 1. anyMatch: Check if any string contains 'a'
		List<String> stringList = Arrays.asList("apple", "banana", "cherry", "date");
		boolean anyMatchA = stringList.stream().anyMatch(s -> s.contains("a"));
		System.out.println("Any string contains 'a': " + anyMatchA);

		List<String> stringListNoA = Arrays.asList("rhythm", "sky", "fly");
		boolean anyMatchANo = stringListNoA.stream().anyMatch(s -> s.contains("a"));
		System.out.println("Any string in " + stringListNoA + " contains 'a': " + anyMatchANo);

		// 2. allMatch: Check if all numbers are greater than 0
		List<Integer> intListPositive = Arrays.asList(1, 2, 3, 4, 5);
		boolean allMatchPositive = intListPositive.stream().allMatch(n -> n > 0);
		System.out.println("All numbers in " + intListPositive + " are > 0: " + allMatchPositive);

		List<Integer> intListMixed = Arrays.asList(1, 2, -3, 4, 5);
		boolean allMatchPositiveMixed = intListMixed.stream().allMatch(n -> n > 0);
		System.out.println("All numbers in " + intListMixed + " are > 0: " + allMatchPositiveMixed);

		// 3. noneMatch: Check if no string is empty
		List<String> nonEmptyStrings = Arrays.asList("hello", "world", "java");
		boolean noneMatchEmpty = nonEmptyStrings.stream().noneMatch(String::isEmpty);
		System.out.println("No string in " + nonEmptyStrings + " is empty: " + noneMatchEmpty);

		List<String> withEmptyString = Arrays.asList("hello", "", "java");
		boolean noneMatchEmptySome = withEmptyString.stream().noneMatch(String::isEmpty);
		System.out.println("No string in " + withEmptyString + " is empty: " + noneMatchEmptySome);
	}

	public void findExamples() {
		System.out.println("============= Find first ==============");
		// 1. findFirst: Find the first string starting with 'b'
		List<String> items = Arrays.asList("apple", "banana", "avocado", "blueberry", "cherry");
		Optional<String> firstB = items.stream().filter(s -> s.startsWith("b")).findFirst();
		System.out.println("findFirst Optional: " + firstB.get());
		System.out.println("First string starting with 'b': " + firstB.orElse("Not found"));

		// if Optional is empty you get Optional.empty as output > you can check is
		// empty orElse ifPresent ifPresentOrElse
		Optional<String> firstX = items.stream().filter(s -> s.startsWith("x")).findFirst();
		System.out.println("findFirst Optional (for 'x'): " + firstX);
		firstX.ifPresentOrElse(s -> System.out.println("First string starting with 'x': " + s),
				() -> System.out.println("No string starting with 'x' found."));

		// 2. findAny: Find any number divisible by 3
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Optional<Integer> anyDivisibleBy3 = nums.stream().filter(n -> n % 3 == 0).findAny();
		System.out.println("findAny Optional (divisible by 3): " + anyDivisibleBy3);
		anyDivisibleBy3.ifPresent(n -> System.out.println("Any number divisible by 3: " + n));

		System.out.println("Demonstrating findAny with parallel stream (multiple matches):");
		List<Integer> parallelNums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
		for (int i = 0; i < 5; i++) {
			Optional<Integer> anyParallel = parallelNums.parallelStream().filter(n -> n % 3 == 0).findAny();
			anyParallel.ifPresent(n -> System.out.print(n + " "));
		}
		System.out.println("\n(Note: Output for parallel findAny might vary or appear consistent in simple examples)");
	}

	// New method to demonstrate various ways to create streams
	public void streamCreationExamples() {
		// 1. Stream from array
		String[] array = { "one", "two", "three", "four", "five" };
		List<String> listFromArray = Arrays.stream(array).collect(Collectors.toList());
		System.out.println("Stream from array: " + listFromArray);

		// 2. Stream.of()
		List<String> listFromOf = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toList());
		System.out.println("Stream.of(): " + listFromOf);

		// 3. Stream.iterate() - first 5 powers of 2
		List<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2).limit(10).collect(Collectors.toList());
		// Integer.MAX_VALUE, which is 2,147,483,647
		List<Integer> powersOfNumber = Stream.iterate(3, n -> new Double(Math.pow(n, 2)).intValue()).limit(10)
				.collect(Collectors.toList());
		System.out.println("Stream.iterate() (powers of 2): " + powersOfTwo);
		System.out.println("Stream.iterate() (powers of Number): " + powersOfNumber);

		// 4. Stream.generate() - 5 random numbers
		List<Double> randomNumbers = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
		List<Integer> randomNumbersOne = Stream.generate(() -> 1 + 3).limit(5).collect(Collectors.toList());
		AtomicInteger counter = new AtomicInteger(10);
		Stream.generate(counter::getAndIncrement).limit(5).forEach(System.out::print); // Output: 0 1 2 3 4
		System.out.println();
		System.out.println("Stream.generate() (random numbers): " + randomNumbers);
		System.out.println("Stream.generate() (random numbers): " + randomNumbersOne);
	}

	private void collectingAndGrouping() {
		System.out.println("Collecting and Grouping ***Starts***");
		List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd", "a");
		// Collecting to a LinkedList
		List<String> result = givenList.stream().collect(Collectors.toCollection(LinkedList::new));
		System.out.println(result);
		List<String> resultList = givenList.stream().toList();
		System.out.println(resultList);
		// Here Collectors.toSet() not simply toSet() > unordered and no duplicates
		Set<String> resultSet = givenList.stream().collect(Collectors.toSet());
		System.out.println(resultSet);
		// Converts to Map. Doesn't work if there are duplicates.
		// (IllegalStateException) Funtion.identity same as a -> a
//		Map<String, Integer> resultMap = givenList.stream()
//				.collect(Collectors.toMap(Function.identity(), String::length));
//		System.out.println(resultMap);
		// if there are duplicates, then use this 3rd parameter to merge collisions
		// (Binary operator). as both are equal we can pick any one.
		Map<String, Integer> resultMapForDuplicates = givenList.stream()
				.collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));
		System.out.println(resultMapForDuplicates);
		// collectingAndThen > after collecting converting to unmodifiable list
		// similarly you can use Collections::unmodifiableSet, unmodifiableMap
		List<String> resultImmutable = givenList.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
		System.out.println(resultImmutable);
		Map<String, Integer> resultMapForDuplicatesAndCollecting = givenList.stream()
				.collect(Collectors.collectingAndThen(
						Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item),
						Collections::unmodifiableMap));
		System.out.println(resultMapForDuplicatesAndCollecting);
	}

	public void intStreams() {
		// rangeClosed includes 0 and 100 as well.
		int sum = IntStream.rangeClosed(0, 100).parallel().sum();
		System.out.println(sum);
	}

}
