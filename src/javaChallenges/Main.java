package javaChallenges;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

	public static long findNb(long m) {
		long sum = 0;
		long n = 0;

		while (sum < m) {
			sum += Math.pow(++n, 3);
			
		}

		return (sum == m) ? n : -1;

	}

	public static void main(String[] args) {

		System.out.println(findNb(91716553919377l));

	}

}
