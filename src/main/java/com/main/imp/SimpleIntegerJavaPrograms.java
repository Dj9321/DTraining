package com.main.imp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleIntegerJavaPrograms {
	// System.out.println("============== ================");

	public static void main(String[] args) {
		SimpleIntegerJavaPrograms intPrograms = new SimpleIntegerJavaPrograms();
		int[] iA = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[][] b = intPrograms.spiralFill(3, 3, iA);
		System.out.println(Arrays.deepToString(b));
		intPrograms.checkI();
		intPrograms.printDuplicateIntegers();

	}

	public int[][] spiralFill(int n, int m, int[] arr) {
		// if output array is less than input > Return null
		if (n * m < arr.length) {
			System.out.println("Array size is less than the input value");
//			System.exit(0);
			return null;
		}
		int[][] res = new int[n][m];
		// Filling the array with values -1
		for (int[] row : res) {
			Arrays.fill(row, -1);
		}
		System.out.println(Arrays.deepToString(res));

		int[] dr = { 0, 1, 0, -1 }; // rows: right, down, left, up
		int[] dc = { 1, 0, -1, 0 }; // cols: right, down, left, up
		int dir = 0, r = 0, c = 0, idx = 0;

		while (idx < arr.length) {
			// here we are setting the value > first will be 00
			res[r][c] = arr[idx++];
			// going to the next element 01, 02, 03 then nc = m(3) > dir is reset
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			if (nr < 0 || nr == n || nc < 0 || nc == m || res[nr][nc] != -1) {
				dir = (dir + 1) % 4;
				nr = r + dr[dir]; // 13, 23, 33
				nc = c + dc[dir];
			}
			r = nr;
			c = nc;
		}
		return res;
	}

	public int[][] spiralFillAnotherWay(int n, int m, int[] input1DArr) {
		// if output array is less than input > Return null
		if (n * m < input1DArr.length) {
			System.out.println("Array size is less than the input value");
//			System.exit(0);
			return null;
		}
		int[][] res = new int[n][m];
		for (int[] row : res) {
			Arrays.fill(row, -1);
		}
		int[] dr = { 0, 1, 0, -1 }; // rows: right, down, left, up
		int[] dc = { 1, 0, -1, 0 }; // cols: right, down, left, up
		int dir = 0, r = 0, c = 0, idx = 0;

		while (idx < input1DArr.length) {
			res[r][c] = input1DArr[idx++];
			int nr = r + dr[dir], nc = c + dc[dir];
			if (nr < 0 || nr == n || nc < 0 || nc == m || res[nr][nc] != -1) {
				dir = (dir + 1) % 4;
				nr = r + dr[dir];
				nc = c + dc[dir];
				dir++;
			}
			r = nr;
			c = nc;
		}
		return res;
	}

	// pick one of 4 predefined numbers
	public void checkI() {
		int[] dr = { 0, 1, 0, -1 }; // rows: right, down, left, up
		int[] dc = { 1, 0, -1, 0 }; // cols: right, down, left, up
		int nr = 0, nc = 0, r = 0, c = 0, dir = 0, i = 0;
		while (i < 20) {
			dir = (dir) % 4;
			nr = r + dr[dir];
			nc = c + dc[dir];
			System.out.println("nr: " + nr);
			System.out.println("nc: " + nc);
			dir++;
			i++;
		}
	}

	public void printDuplicateIntegers() {
		System.out.println("============== Program: Print Duplicate Integers ================");

		// 1. Using Map
		int[] numbers = { 1, 2, 3, 4, 5, 5, 6 };
		Map<Integer, Integer> map = new HashMap<>();

		for (int i : numbers) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}

		System.out.println(map);

		// 2. Using Streams > using Set and filter (in stream) but fails for more than 2
		// duplicates
		List<Integer> numberList = Arrays.asList(5, 3, 4, 1, 3, 3, 7, 2, 9, 9, 4, 9);

		Set<Integer> numberSet = new HashSet<>();
		Set<Integer> duplicateNumbersSet = new HashSet<>();
		// add returns false if element already in set
		duplicateNumbersSet = numberList.stream().filter(n -> !(numberSet.add(n))).collect(Collectors.toSet());
//				.forEach(System.out::println); // print duplicates
		System.out.println("Duplicate numbers: " + duplicateNumbersSet);
//		for (int j : numberList) {
//			System.out.println(numberSet1.add(j));
//		}
		System.out.println(numberSet);
	}

}
