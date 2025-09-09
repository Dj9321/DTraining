package com.main.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.main.classes.Animal;
import com.main.classes.Dog;

/**
 * While writing programs: 1. Understand the output we want. Check how we can
 * put that output > In a list or List of Lists or Map of lists etc
 */
public class SimplePrograms {

	public static void main(String[] args) throws IOException {
		SimplePrograms s = new SimplePrograms();
		String reverse = s.reverseOfString("DHJ");
		System.out.println(reverse);
		s.forLoop();
		s.inheritanceExamples();
		s.stringExample();
		s.printDuplicateCharactersInString("Dheeeraj");
		s.readAndPrintAFile();
		s.stringFormatting();
		s.lastValueinAlphabets();
		s.maxOccurrenceinCharacterArray();
		s.convertStringToNumber();
//		s.maxPrefixInStringArray();
		String[] words = { "flower", "flower", "flow", "flee", "flowers" };
		String longestPrefix = findLongestCommonPrefix(words, 0, words.length - 1);
		System.out.println("Longest Common Prefix: " + longestPrefix);
		s.anagramsExample();
		s.printReverseOfEachWord("Hello Java Programming Language");
		s.reverseLettersOfAWord("Hello Java Programming Langauge");
		int d = s.tryExample();
		System.out.println(d);
		s.parseHTML();
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

	// Get last value or ending value in alphabets. For finding max by ASCII value
	public void lastValueinAlphabets() {
		System.out.println("============== lastValueinAlphabets ================");
		char[] arr = { 'A', 'E', 'I', 'O', 'U' };
		char max = arr[0];
		for (char c : arr) {
			if (c > max) {
				max = c;
			}
		}
		System.out.println("Maximum character is: " + max);
	}

	// Get count of max alphabets in array
	public void maxOccurrenceinCharacterArray() {
		System.out.println("============== maxOccurrenceinCharacterArray ================");
		char[] arr = { 'a', 'b', 'c', 'a', 'a', 'b' };
		int[] freq = new int[256]; // Assuming ASCII input. Covers all ASCII characters
		for (char c : arr) {
			freq[c]++;
			System.out.println(freq[c]);
		}
		int maxCount = 0;
		char maxChar = ' ';
		for (char c : arr) {
			if (freq[c] > maxCount) {
				maxCount = freq[c];
				maxChar = c;
			}
		}
		System.out.println("Maximum occurring character: " + maxChar + " (Count: " + maxCount + ")");
	}

	public void convertStringToNumber() {
		System.out.println("============== convertStringToNumber ================");
		String raw = "1233983543587325318";
		int[] num = new int[raw.length()];

		for (int i = 0; i < raw.length(); i++) {
			// - '0'
			num[i] = raw.charAt(i) - '0';
//            num[i] = raw.charAt(i) // save ASCII value
			System.out.println(num[i]);
		}

//        for (int i : num) {
//            System.out.println(i);
//        }
	}

	// get max prefix in a set of strings
	public void maxPrefixInStringArray() {
		System.out.println("============== maxPrefixInStringArray ================");
		String[] sArray = { "flower", "flowers", "flower" };
		int prefixCount = 0;
		int smallestWordLength = sArray[0].length();
//		for (int i = 0; i < sArray.length; i++) {
//			if (sArray[i].length() <= smallestWordLength) {
//				smallestWordLength = sArray[i].length();
//			}
//		}
//			int counter = sArray[i].length();
//			String word = sArray[i];
		// 0 letter with words
//		for (int k = 0; k < smallestWordLength; k++) {
//			char f = sArray[k].charAt(k);
//			for (int j = 0; j < sArray.length;) {
//				if (sArray[k].charAt(j) == sArray[j].charAt(j)) {
//					prefixCount++;
//				}
//			}
//
//		}
		System.out.println("P: " + prefixCount);
	}

	public static String findCommonPrefix(String str1, String str2) {
		StringBuilder result = new StringBuilder();
		int length1 = str1.length(), length2 = str2.length();
		for (int i = 0; i < length1 && i < length2; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				break;
			}
			result.append(str1.charAt(i));
		}
		return result.toString();
	}

	public static String findLongestCommonPrefix(String[] strings, int start, int end) {
		if (start == end) {
			return strings[start];
		}
		if (end > start) {
			int mid = start + (end - start) / 2;
			String prefix1 = findLongestCommonPrefix(strings, start, mid);
			String prefix2 = findLongestCommonPrefix(strings, mid + 1, end);
			return findCommonPrefix(prefix1, prefix2);
		}
		return null;
	}

	public String reverseOfString(final String input) {
		System.out.println("============== reverseOfString ================");
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

	// Using Arrays.asList(), Arrays.sort(), map.computeIfAbsent()
	public void anagramsExample() {
		System.out.println("============== anagrams Example ================");
		// Arrays.asList()
		List<String> words = Arrays.asList("eat", "tea", "tan", "at", "nat", "bat");

		// Map from sorted word to list of words with those letters
		Map<String, List<String>> anagramMap = new HashMap<>();

		for (String word : words) {
			// Convert word to char array, sort it, and convert back to String
			char[] charArr = word.toCharArray();
			Arrays.sort(charArr);
			String sortedWord = new String(charArr);
			System.out.println(sortedWord);

			// Group words by the sorted sequence
			anagramMap.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
			System.out.println(anagramMap);
		}

		// Print groups of words that have the same letters
		for (List<String> group : anagramMap.values()) {
			if (group.size() > 0) {
				System.out.println(group);
			}
		}

		// SAME Using Streams:
		System.out.println("Same using Streams");
		Map<String, List<String>> grouped = words.stream().collect(Collectors.groupingBy(word -> {
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			return new String(chars);
		}));

		// Print only groups with more than one word (anagrams)
		grouped.values().stream().filter(group -> group.size() > 0).forEach(System.out::println);
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
		System.out.println("============== Read & Print a file ================");
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

	public void stringFormatting() {
		System.out.println("============== String format ================");
		// 1. String.format %s, %d
		String formatted = String.format("Name: %s, Age: %d", "Alice", 30);
		System.out.println(formatted);
	}

	// Uses String > split() StringBuilder > reverse()
	public void printReverseOfEachWord(String reverse) {
		System.out.println("============== Reverse of Each Word ================");
		String str = "Java Programming Language";
		String[] words = str.split(" ");
		StringBuilder result = new StringBuilder();

		for (String word : words) {
			StringBuilder reverseWord = new StringBuilder(word);
			result.append(reverseWord.reverse()).append(" ");
//			or use reverseLettersOfAWord() and use another for loop
		}

		// Trim to remove trailing space
		System.out.println(result.toString().trim());
	}

	public void reverseLettersOfAWord(String word) {
		System.out.println("============== String reverse letters of word ================");
		StringBuilder s = new StringBuilder();
		char[] letterArray = word.toCharArray();
		for (int i = letterArray.length - 1; i >= 0; i--) {
			s.append(letterArray[i]);
		}
		System.out.println(s);
	}

	public int tryExample() {
		System.out.println("============== Try Example ================");
		try {
			System.out.println("In Try block");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("In Finally block.. this will run just before returning the value");
			// if we write return here, it says finally block doesn't run normally. So the
			// return 1 in try block doesn't work as the value is already returned here.
//			return 2;
		}
		// we need to add return here or in finally even after adding in try
		return 0;
	}

	public void parseHTML() {
		System.out.println("============== HTML Parsing > takes bit time ================");
		// 1. Using Jsoup
		Document doc;
		try {
			// get call
			doc = Jsoup.connect("https://example.com").get();
			doc.select("p").forEach(System.out::println);

			// More precise
			String blogUrl = "https://spring.io/blog";
			Connection connection = Jsoup.connect(blogUrl);
			connection.userAgent("Mozilla");
			connection.timeout(5000);
			connection.cookie("cookiename", "val234");
			connection.cookie("cookiename", "val234");
			connection.referrer("http://google.com");
			connection.header("headersecurity", "xyz123");
			Document docCustomConn = connection.get();

			// Since the connection follows a fluent interface, you can chain these methods
			// before calling the desired HTTP method:
			Document docCustomConn1 = Jsoup.connect(blogUrl).userAgent("Mozilla").timeout(5000)
					.cookie("cookiename", "val234").cookie("anothercookie", "ilovejsoup").referrer("http://google.com")
					.header("headersecurity", "xyz123").get();

			Elements links = doc.select("a");
			Element pag = doc.getElementById("pagination_control");
			Elements desktopOnly = doc.getElementsByClass("desktopOnly");
//			Element firstArticle = articles.first();
			Element firstArticle = doc.select("article").first();
//			Element titleElement= firstArticle.select("h1 a").first();
			// creating and appending elements
//			Element link = new Element(Tag.valueOf("a"), "")
//					  .text("Checkout this amazing website!")
//					  .attr("href", "http://baeldung.com")
//					  .attr("target", "_blank");
//					firstArticle.appendChild(link);

			// 2. Using plain Java code ONLY
			String htmlString = "<html><head><title>Example Title</title></head><body><p>Hello</p></body></html>";
//			String htmlString = "<title>Example Title</title></head><body><p>Hello</p></body></html>";

			String startTag = "<title>";
			String endTag = "</title>";

			// adding title length as well. For end not needed.
			int startIndex = htmlString.indexOf(startTag) + startTag.length();
			int endIndex = htmlString.indexOf(endTag);
			System.out.println(startIndex);
			System.out.println(endIndex);

			// Ensures that there is something after <title> startIndex >= 7
//			(<title> length = 7)
			if (startIndex >= startTag.length() && endIndex > startIndex) {
				String title = htmlString.substring(startIndex, endIndex);
				System.out.println("Title: " + title);
			} else {
				System.out.println("Title tag not found");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
