package com.main.classes;

import java.util.List;
import java.util.stream.Stream;

public class JavaStreamExamples {

	public static void main(String[] args) {
		JavaStreamExamples examples = new JavaStreamExamples();
		examples.filterExample();
	}

	private void filterExample() {
		List<String> stringList = List.of("Arjun", "Avinash", "Apple", "Orange", "Abc", "Akshay");
		Stream<String> stringStream = stringList.parallelStream();
		stringStream.filter(a -> a.toUpperCase().startsWith("A")).forEach(System.out::println);

	}
}
