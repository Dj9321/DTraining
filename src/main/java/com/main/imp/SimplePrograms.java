package com.main.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.main.classes.Animal;
import com.main.classes.Dog;

public class SimplePrograms {

	public static void main(String[] args) {
		SimplePrograms s = new SimplePrograms();
		String reverse = s.reverseOfString("DHJ");
		System.out.println(reverse);
		s.forLoop();
		s.inheritanceExamples();
		s.stringExample();
		s.printDuplicateCharactersInString("Dheeeraj");
		s.readAndPrintAFile();
	}

	public void forLoop() {
		for (int i = 0; i < 10; i++) {
			if (i == 8) {
				// returning without completing the loop. Nothing will run after this.
//				return;
			}
			System.out.println("I value is " + i);
		}

		// Enhanced for loop
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int a : arr) {
			System.out.print(a + " ");
		}
		// infinite loop
//		for (int j = 0; j > -1; j++) {
//			System.out.println(j);
//		}
	}

	public String reverseOfString(final String input) {
		// recursive termination
		if (input.length() <= 1) {
			return input;
		}
		// Separating first character and remaining characters
		char firstChar = input.charAt(0);
		String remaining = input.substring(1);
		// recursive descent
		return reverseOfString(remaining) + firstChar;
	}

	public void inheritanceExamples() {
		// if we have same method eat() in Animal & Dog but reference of a is Animal,
		// then Dog eat() will be called and not Animal
		Animal a = new Dog();
		a.eat();
//		a.super.eat(); // Not allowed
		Animal an = new Animal();
		an.eat();
	}

	public void stringExample() {
		System.out.println("============== String Examples ================");
		String a = "abcdef a";
		String b = a.replace("a", "z");
		System.out.println(b);
	}

	public void printDuplicateCharactersInString(String a) {
		System.out.println("============== Program: Duplicater characters ================");
		// put characters in Map > key: character, value: Default 1, next time get the
		// value and increase 1 (count)
		Map<Character, Integer> charCountMap = new HashMap<>();
		// Count characters
		for (char c : a.toCharArray()) {
			// gets the value and adds 1
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}

		// Print characters
		for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
		}
	}

	public void readAndPrintAFile() {
//		File f = new File("abc");
		// 1. BufferedReader. try with resources: Automatically closes the resources (No
		// need of reader.close() below)
		// By default it will check file in the home folder (not where this .java file
		// is there)
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/MovieList.json"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 2. Using Scanner
		try (Scanner scanner = new Scanner(new File("src/main/resources/MovieList.json"))) {
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 3. Using Java NIO (New Input/Output) Advanced
		// Path, Paths, Files are from NIO
		// Files createDirectory(), createLink(), createSymbolicLink(), createFile(),
		// createTempFile(), copy()
		Path path = Paths.get("src/main/resources/MovieList.json");
		try {
			List<String> lines = Files.readAllLines(path);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
