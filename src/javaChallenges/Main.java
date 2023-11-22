package javaChallenges;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

	public static String longestConsec(String[] strarr, int k) {
		   int n = strarr.length;

	        // Check for valid input
	        if (n == 0 || k > n || k <= 0) {
	            return "";
	        }

	        String longestString = "";

	        for (int i = 0; i <= n - k; i++) {
	            StringBuilder currentString = new StringBuilder();
	            for (int j = i; j < i + k; j++) {
	                currentString.append(strarr[j]);
	            }

	            if (currentString.length() > longestString.length()) {
	                longestString = currentString.toString();
	            }
	        }

	        return longestString;
	}

	public static void main(String[] args) {
		// Example
		String[] example1 = { "tree", "foling", "trashy", "blue", "abcdef", "uvwxyz" };
		System.out.println(longestConsec(example1, 0));
	}

}

//return Arrays.stream(strarr).skip(1).limit(2).collect(Collectors.joining());