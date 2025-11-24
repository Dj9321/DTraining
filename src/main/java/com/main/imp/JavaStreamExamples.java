package com.main.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection; // Required for flatMap
import java.util.Collections;
import java.util.Comparator; // Required for sorted
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Required for reduce, findFirst, findAny
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import com.main.classes.EmployeeRecord;
import com.main.classes.Person;
import com.main.classes.PersonFullDetails;

public class JavaStreamExamples {
	// System.out.println("============== ================");
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
		examples.sortedAndCountExample();
		examples.matchExamples();
		examples.findExamples();
		examples.streamCreationExamples(); // Call the new streamCreationExamples method
		examples.collectingAndGrouping();
		examples.intStreams();
		examples.limitAndSkip();
		examples.collectorUtilityMethods();
		System.out.println(examples.checkPerformance(examples::sumSequentialStream, 20));
		System.out.println(examples.checkPerformance(examples::sumParallelStream, 20));
		System.out.println("Number of Processors on this Laptop are: " + Runtime.getRuntime().availableProcessors());
		examples.optional();
		HashMap<String, Integer> unsortedMap = new HashMap<>();
		unsortedMap.put("Math", 98);
		unsortedMap.put("Data Structure", 85);
		unsortedMap.put("Database", 91);
		unsortedMap.put("Java", 95);
		unsortedMap.put("Operating System", 79);
		unsortedMap.put("Networking", 80);
		examples.sortHashMapByValue(unsortedMap);
		examples.countLetters();
		examples.countLetters1();
		examples.employeeDepartmentSalary();
	}

	List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
	List<String> namesList = List.of("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay");
	List<String> namesArrayList = Arrays.asList("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay", "Ravi",
			"Kiran");
	List<Person> personList = Arrays.asList(new Person("Dheeraj ", "1"), new Person("Evanshi ", "2"),
			new Person("Malathi ", "3"), new Person("Siramdas ", "4"));

	Person pn1 = new Person("Dheeraj", "1", "Banking", "Fresher");
	Person pn2 = new Person("Avinash", "2", "Banking", "Fresher");
	Person pn3 = new Person("Deepak", "3", "HR", "Senior Consultant");
	Person pn4 = new Person("Sandeep", "4", "HR", "Fresher");
	Person pn5 = new Person("Rohit", "5", "Automobile", "Fresher");
	Person pn6 = new Person("Ravi", "6", "Automobile", "Senior Consultant");

	List<Person> newPersonList = List.of(pn1, pn2, pn3, pn4, pn5, pn6);
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
		String concatenatedString = stringList.stream().reduce("Hello ! ", (s1, s2) -> s1 + "-" + s2);
		System.out.println("Concatenated string: " + concatenatedString);

		// 3. Optional without default value
		// Without First default value, it will give Optional<String>
		Optional<String> concatenatedString1 = stringList.stream().reduce((s1, s2) -> s1 + "-" + s2);
		// we can use optional.isPresent() before calling optional.get() > if value
		// isn't present you will get error: No value present

		// 4. Getting highest value (marks or others) in Person object or Integers
		// If we give default value, and don't pass anything we get default value as max
		// value.
		var highest = numberList.stream().reduce((h1, h2) -> {
			if (h1 > h2) {
				return h1;
			} else {
				return h2;
			}
		});
		var highest1 = numberList.stream().reduce((i1, i2) -> i1 > i2 ? i1 : i2);
		var highest2 = numberList.stream().reduce((Integer::max));
		var highest3 = numberList.stream().reduce((Integer::sum));
		// you can simply write .reduce((h1,h2) -> h1>h2 ? h1 : h2;
		System.out.println("Highest number using reduce is: " + highest.get());
		System.out.println("Highest number using reduce is: " + highest1.get());
		System.out.println("Sum of numbers using reduce is: " + highest2.get());
		System.out.println("Sum of numbers using reduce is: " + highest3.get());

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

	public void sortedAndCountExample() {
		System.out.println("================================== Sorted & Count ==================================");
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
		var sortedBasedOnId = personList.stream().sorted(Comparator.comparing(Person::getName)).toList();
		System.out.println("Same as above but simpler with Comparator.comparing: " + sortedBasedOnId);
		System.out.println("Sorted Person List Based on Id: " + sortedBasedOnIdPersonList);
		var count = personList.stream().sorted((a, b) -> a.getId().compareTo(b.getId())).count();
		System.out.println("Count of personList is: " + count);
	}

	public Map<String, Integer> sortHashMapByValue(HashMap<String, Integer> hm) {
		System.out.println("============= Sorted By HashMap Value ============= ");
		LinkedHashMap lm = hm.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		System.out.println(lm);
		return null;
	}

	public void matchExamples() {
		System.out.println("============= Match Examples ==============");
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
		// 1. findFirst: Find the first string starting with 'b'. As soon as it matches
		// it stops
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
		System.out.println("============= Stream Creation ==============");
		// 1. Stream from array
		String[] array = { "one", "two", "three", "four", "five" };
		List<String> listFromArray = Arrays.stream(array).collect(Collectors.toList());
		System.out.println("Stream from array: " + listFromArray);

		// 2. Stream.of()
		List<String> listFromOf = Stream.of("a", "b", "c", "d", "e").collect(Collectors.toList());
		System.out.println("Stream.of(): " + listFromOf);

		// 3. Stream.iterate() - first 5 powers of 2
		// iterate() and generate() can create infinite elements
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
		Stream.generate(counter::getAndIncrement).limit(5).forEach(w -> System.out.print(w + " ")); // Output: 0 1 2 3 4
		Supplier<Integer> randomNumbersThree = new Random()::nextInt;
		Function<Integer, Integer> randomNumbersFour = new Random()::nextInt;
		System.out.println();
		System.out.println("Stream.generate() (random numbers): " + randomNumbers);
		System.out.println("Stream.generate() (random numbers): " + randomNumbersOne);
		System.out.println("Random Integer:  " + randomNumbersThree.get());
		System.out.println("Random Integer:  " + randomNumbersFour.apply(5));
		// 5. Generating random numbers in 0 to 5 using random
		Stream.generate(() -> {
			return randomNumbersFour.apply(5);
		}).limit(6).forEach(System.out::println);
	}

	private void collectingAndGrouping() {
		System.out.println("============= Collecting and Grouping ============= ");
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
		System.out.println("resultMapForDuplicates:  " + resultMapForDuplicates);
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
		System.out.println("============= Int Streams ============= ");
		// rangeClosed includes 0 and 100 as well.
		int sum = IntStream.rangeClosed(0, 100).parallel().sum();
		System.out.println(sum);
		// 2. Int Summary Statistics
		var intStatistics = IntStream.rangeClosed(0, 49).parallel().summaryStatistics();
		System.out.println(intStatistics);
		List<Integer> integerList = List.of(1, 2, 3, 4, 5);
		integerList.stream().reduce(0, (x, y) -> x + y);
		// here if iIntegerList is List<Integer> then boxing and unboxing will happen.
		// To avoid that you can use: IntStream.rangeClosed(0, 100)
// 		rangeClosed already gives stream.
		IntStream.range(1, 10).forEach(v -> System.out.print(v + ", ")); // Excludes 10
		LongStream.range(1, 10).forEach(v -> System.out.print(v + ", "));
//		DoubleStream doesn't have range or rangeClosed but we can use IntStream.range(1, 10).asDoubleStream

		// 2. Max / Min value & getAsInt()
		var w = IntStream.rangeClosed(1, 100).max();
		System.out.println(w);
		var x = IntStream.of(2, 4, 5, 6, 101, 99).max();
		var y = IntStream.of(2, 4, 5, 6, 101, 99).min();
		System.out.println(x.isPresent() ? x.getAsInt() : 0);
		System.out.println(y.isPresent() ? y.getAsInt() : 0);

		// 3. average() & getAsDouble
		var z = IntStream.of(2, 4, 5, 6, 101, 99).average();
		System.out.println(z.isPresent() ? z.getAsDouble() : "Can't calculate average");

		// 4. IntStream.rangeClosed(1,100) > gives int primitive.
//		boxed() > Primitive to Wrapper
		IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
		// gives Integer (Wrapper class).
		// the following doesn't work without boxed()
//		IntStream.rangeClosed(1,100).collect(Collectors.toList());
		// unboxing (Wrapper to primitive)
		int sum1 = integerList.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum is " + sum1);

		// 5. mapToObj() mapToDouble(), mapToLong()
		var a1 = IntStream.rangeClosed(1, 100).mapToObj(i -> String.valueOf(i)).collect(Collectors.toList());
		System.out.println(" Converting int to String " + a1);
		var a2 = IntStream.rangeClosed(1, 100).mapToLong(i -> i).sum();
		var a3 = IntStream.rangeClosed(1, 100).mapToDouble(i -> i).sum();
		System.out.println(" Converting int to Long " + a2 + " Double: " + a3);
	}

	public void limitAndSkip() {
		System.out.println("============= Limit & Skip ==============");
		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 1. Limits to n numbers / objects
		var first5 = nums.stream().limit(5).reduce(Integer::sum);
		// 2. Skips first n numbers or objects
		var skip5 = nums.stream().skip(5).reduce(Integer::sum);
		System.out.println(first5);
		System.out.println("Skip first 5 elements: " + skip5);
	}

	public void collectorUtilityMethods() {
		System.out.println("============= Collector Utility methods ==============");
		// 1. Joining Collector performs string concatenation on elements of stream.
		// Three overloaded versions.
		var p = personList.stream().map(a -> a.getName()).collect(Collectors.joining());
		var p1 = personList.stream().map(a -> a.getName()).collect(Collectors.joining(" & "));
		// delimeter, prefix, suffix
		var p2 = personList.stream().map(a -> a.getName()).collect(Collectors.joining(" & ", "((( ", ")))"));

		// 2. Counting > Collector returns total number of elements as result.
		var p3 = personList.stream().map(a -> a.getName()).collect(Collectors.counting());
		System.out.println(p);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println("Counting result: " + p3);

		// 3. Mapping > Collector applies a transformation first and then collects.
		var p4 = personList.stream().collect(Collectors.mapping(Person::getId, Collectors.toList()));
		System.out.println(p4);
		// 4. maxBy() minBy
		var p5 = personList.stream().collect(Collectors.maxBy(Comparator.comparing(Person::getId)));
		var p6 = personList.stream().collect(Collectors.minBy(Comparator.comparing(Person::getId)));
		System.out.println(p5);
		System.out.println(p6);

		// 5. summingInt() averagingInt()
		var p7 = personList.stream().collect(Collectors.summingInt(ps -> Integer.valueOf(ps.getId())));
		var p8 = personList.stream().collect(Collectors.averagingInt(ps -> Integer.valueOf(ps.getId())));
		System.out.println(p7);
		System.out.println(p8);

		// 6. GroupingBy by property or customized
		Map<String, List<Person>> partMap = newPersonList.stream()
				.collect(Collectors.groupingBy(Person::getDepartment));
		System.out.println(partMap);
		// customized grouping
		Map<Object, List<Person>> partMap1 = newPersonList.stream()
				.collect(Collectors.groupingBy(pn -> pn.getName().length() < 6 ? "Not OK" : "OK"));
		System.out.println(partMap1);
		// 7. Second level of grouping > can be Map of Map or just Map
		// Sub dividing issues based on Department
		var partMap3 = newPersonList.stream().collect(Collectors.groupingBy(Person::getDepartment,
				Collectors.groupingBy(pn -> pn.getName().length() < 6 ? "Not OK" : "OK")));
		System.out.println(partMap3);
		// adds the name length of all names in a department
		var partMap4 = newPersonList.stream().collect(
				Collectors.groupingBy(Person::getDepartment, Collectors.summingInt(pn -> pn.getName().length())));
		System.out.println(partMap4);
		var partMap5 = newPersonList.stream()
				.collect(Collectors.groupingBy(Person::getName, Collectors.summingInt(pn -> pn.getName().length())));
		System.out.println(partMap5);

		// first argument is the Key, 2nd argument: Return type 3rd argument: value
		var partMap6 = newPersonList.stream()
				.collect(Collectors.groupingBy(Person::getDesignation, LinkedHashMap::new, Collectors.toSet()));
		System.out.println(partMap6);
		// get max length name in each department. Also using collectingAndThen()
		var partMap7 = newPersonList.stream().collect(Collectors.groupingBy(Person::getDepartment, Collectors
				.collectingAndThen(Collectors.maxBy(Comparator.comparing(c -> c.getName().length())), Optional::get)));
		System.out.println(partMap7);
		// partitioningBy() > gives Map<Boolean, List<>> > gives false as well as true
		var partMap8 = newPersonList.stream()
				.collect(Collectors.partitioningBy(c -> c.getName().length() > 6, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparing(c -> c.getName().length())), Optional::get)));
		System.out.println(partMap8);
		// less than 6 Set, greater than 6 Set
		var partMap9 = newPersonList.stream()
				.collect(Collectors.partitioningBy(c -> c.getName().length() > 6, Collectors.toSet()));
		System.out.println(partMap9);

	}

	public long checkPerformance(Supplier<Integer> supplier, int noOfTimes) {
		System.out.println("============= Check Performance ==============");
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < noOfTimes; i++) {
			supplier.get();
		}
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

	public int sumParallelStream() {
		// .stream().parallel() OR .parallelStream()
		return IntStream.rangeClosed(1, 100000).parallel().sum();
	}

	public int sumSequentialStream() {
		return IntStream.rangeClosed(1, 100000).sum();
	}

	public void optional() {
		System.out.println("============= Optional class ==============");
		// 1. Instead of adding multiple null checks > an object can have a list of
		// another objects > we can't check the object null and then the list of null
		// inside it
		Optional<String> opt = Optional.ofNullable(personList.get(0).getDesignation());
		if (opt.isPresent()) {
			System.out.println(opt.get());
		} else {
			System.out.println("No designation assigned");
		}
		// Optional.of() > If you think you will always have value and not null.
		// Otherwise use ofNullable()
		// can give empty if we want optional as empty
		Optional.empty();
		// orElse orElseGet() > can use for default values and others
		var opt1 = Optional.ofNullable(personList.get(0).getDesignation()).orElse("NO Designation");
		System.out.println(opt1);
		var opt2 = Optional.ofNullable(personList.get(0).getDesignation()).orElseGet(() -> "No designation");
		System.out.println(opt2);
		// orElseThrow throws an exception
//		var opt3 = Optional.ofNullable(personList.get(0).getDesignation())
//				.orElseThrow(() -> new RuntimeException("No designation"));
//		System.out.println(opt3);
		// ifPresent > if it is present it will execute Consumer (Takes but doesn't
		// return anything)
		var opt4 = Optional.ofNullable(newPersonList);
		opt4.ifPresent((s) -> System.out.println(s));

		// 2. Optional can also call filter. This filter is NOT forEach but for the
		// whole List<Person>
		// Here we are only printing first one from the list using map
		opt4.filter(s -> s.size() > 0).map(k -> k.get(0)).ifPresent(System.out::println);
		// studentOptional.filter(student -> student.getGpa >= 3.5)
		// .flatMap(Student::getBike).map(Bike::getName);
	}

	public void countLetters() {
		System.out.println("============= Count Letters ==============");
		String word = "AABBBCCDDD";
		String[] d = word.split("");
		System.out.println(d[6]);
		Map<Character, Long> charCountMap = word.chars().mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		// gives 65, 66, for a, b
		word.chars().mapToObj(c -> (char) c).forEach(System.out::println);

//		Map<char[], Long> charCountMap1 = word.toCharArray().stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		char[] charArray = word.toCharArray();
//		Stream<Character> charStream = IntStream.range(0, charArray.length)
//			    .mapToObj(i -> charArray[i]);
//		charStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Map<String, Long> charCountMap1 = Arrays.stream(word.split(""))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(charCountMap);
		System.out.println(charCountMap1);
	}

	public void countLetters1() {
		StringBuilder s = new StringBuilder();
		String word = "ABBCCCSSAAAB";
		// adding space to check the last letter and compare it with space
		word = word + " ";
		int count = 1;
		char[] wordChars = word.toCharArray();
		for (int i = 0; i < wordChars.length - 1; i++) {
			if (wordChars[i] == wordChars[i + 1]) {
				count++;
			} else {
				s.append(wordChars[i] + String.valueOf(count));
				count = 1;
			}

		}
		System.out.println(s);
	}

	public void employeeDepartmentSalary() {
		System.out.println("============== Employee Department Salary ================");
		EmployeeRecord e1 = new EmployeeRecord("Jareehd", 33, "Electronics", 9900);
		EmployeeRecord e2 = new EmployeeRecord("Jareehd", 33, "Electronics", 1000);
		EmployeeRecord e3 = new EmployeeRecord("Jareehd", 33, "IT", 1000);
		EmployeeRecord e4 = new EmployeeRecord("Jareehd", 33, "IT", 9000);

		List<EmployeeRecord> empList = new ArrayList<>();
		empList.add(e1);
		empList.add(e2);
		empList.add(e3);
		empList.add(e4);
		// collect(Collector),
		// collect(supplier, BiConsumer accumulator, BiConsumercombiner)
		var highestSalaryMap = empList.stream().collect(Collectors.groupingBy(EmployeeRecord::department,
				Collectors.maxBy(Comparator.comparingInt(EmployeeRecord::salary))));
		IO.println(highestSalaryMap);
		IO.println(highestSalaryMap.get("IT"));
	}

}
