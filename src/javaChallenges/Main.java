package javaChallenges;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

	public static long digPow(int n, int p) {
		String intString = String.valueOf(n);
		long sum = 0;
		for (int i = 0; i < intString.length(); i++) {
			sum += Math.pow(Character.getNumericValue(intString.charAt(i)), p + i);
		}

		return (sum % n == 0) ? sum / n : -1;
	}

	public static void main(String[] args) {

		System.out.println(digPow(46288, 3));

	}

}
