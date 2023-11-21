package javaChallenges;

import java.util.Arrays;

public class Main {

	public static int findIt(int[] arr) {
		return Arrays.stream(arr).reduce(0, (x, y) -> x ^ y);
	}

	public static void main(String[] args) {
		// Example
		int[] integerArray = { 1, 2, 2, 3, 3, 3, 4, 3, 3, 3, 2, 2, 1 };
		System.out.println(findIt(integerArray));
	}

}
