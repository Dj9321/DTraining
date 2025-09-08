package com.main.imp;

import java.util.Arrays;

public class SimpleIntegerJavaPrograms {

	public static void main(String[] args) {
		SimpleIntegerJavaPrograms intPrograms = new SimpleIntegerJavaPrograms();
		int[] iA = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[][] b = intPrograms.spiralFill(3, 3, iA);
		System.out.println(Arrays.deepToString(b));
		intPrograms.checkI();

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

}
