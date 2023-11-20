package javaChallenges;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

	public static char findMissingLetter(char[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i + 1] - array[i] > 1) {
				return (char) (array[i] + 1);
			}
		}
		  throw new IllegalArgumentException("error");
	}

	public static void main(String[] args) {
		char[] example1 = { 'a', 'b', 'c', 'd', 'f' };
	     char[] example2 = {'O', 'Q', 'R', 'S'};

		System.out.println(findMissingLetter(example2));

	}

}
