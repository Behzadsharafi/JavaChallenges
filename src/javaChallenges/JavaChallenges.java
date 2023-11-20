package javaChallenges;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaChallenges {

	// Write a function findNeedle() that takes an array full of junk but containing
	// one "needle"
	//
	// After your function finds the needle it should return a message (as a string)
	// that says:
	//
	// "found the needle at position " plus the index it found the needle, so:
	//
	// Example(Input --> Output)
	//
	// ["hay", "junk", "hay", "hay", "moreJunk", "needle", "randomJunk"] --> "found
	// the needle at position 5"
	public static String findNeedle(Object[] haystack) {
		return String.format("found the needle at position %d", Arrays.asList(haystack).indexOf("needle"));
	}

//	write a java function that it converts dash/underscore delimited words into camel casing. 
//	The first word within the output should be capitalized only if the original word was capitalized 
//	(known as Upper Camel Case, also often referred to as Pascal case). The next words should be always capitalized.
//
//	Examples
//	"the-stealth-warrior" gets converted to "theStealthWarrior"
//
//	"The_Stealth_Warrior" gets converted to "TheStealthWarrior"
//
//	"The_Stealth-Warrior" gets converted to "TheStealthWarrior"

	static String toCamelCase(String str) {

		String[] words = str.split("[-_]");
		return Arrays.stream(words, 1, words.length).map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
				.reduce(words[0], String::concat);
	}

	// second approach

	static String toCamelCase2(String s) {
		return Pattern.compile("[-|_](.)").matcher(s).replaceAll(r -> r.group(1).toUpperCase());
	}

//	Complete the function that accepts a string parameter, 
//	and reverses each word in the string. All spaces in the string should be retained.
	// input: This is an example! output: sihT si na !elpmaxe

	public static String reverseWords(final String original) {
		String[] array = original.split(" ");
		if (array.length == 0)
			return original;

		int i = 0;
		for (String string : array) {
			array[i] = new StringBuilder(string).reverse().toString();
			i++;
		}

		return String.join(" ", array);

	}

	// second approach
	public static String reverseWords2(final String original) {
		return original.trim().isEmpty() ? original
				: Stream.of(original.split(" ")).map(word -> new StringBuffer(word).reverse())
						.collect(Collectors.joining(" "));
	}

//	Write a function that takes an integer as input, and returns the number of
//	bits that are equal to one in the binary representation of that number. You can guarantee that input is non-negative.
//
//	Example: The binary representation of 1234 is 10011010010, so the function should return 5 in this case

	public static int countBits(int n) {

		return Integer.bitCount(n);
	}

	// second approach includes bitwise operations
	public static int countBits2(int n) {
		int ret = n % 2; // Initialize ret to the value of the least significant bit (LSB)
		while ((n /= 2) > 0)
			ret += n % 2; // Add the value of the current bit to ret
		return ret;
	}

	// third approach
	public static int countBits3(int n) {
		return (int) Integer.toBinaryString(n).chars().filter(c -> c == '1').count();
	}

	// 4th approach
	public static int countBits4(int n) {
		return Integer.toBinaryString(n).replaceAll("0", "").length();
	}

	// 5th approach includes bitwise operations
	public static int countBits5(int n) {
		int i = 0;

		for (int j = n; j > 0; j >>= 1) {
			i += j & 1;
		}

		return i;
	}

//	Some numbers have funny properties. For example:
//
//		89 --> 8¹ + 9² = 89 * 1
//
//		695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2
//
//		46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
//
//		Given a positive integer n written as abcd... (a, b, c, d... being digits) and a positive integer p
//
//		we want to find a positive integer k, if it exists, such that the sum of the digits of n taken to the successive powers of p is equal to k * n.
//		In other words:
//
//		Is there an integer k such as : (a ^ p + b ^ (p+1) + c ^(p+2) + d ^ (p+3) + ...) = n * k
//
//		If it is the case we will return k, if not return -1.
//
//		Note: n and p will always be given as strictly positive integers.

	public static long digPow(int n, int p) {
		String intString = String.valueOf(n);
		long sum = 0;
		for (int i = 0; i < intString.length(); i++)
			sum += Math.pow(Character.getNumericValue(intString.charAt(i)), p + i);
		return (sum % n == 0) ? sum / n : -1;
	}

	public static long digPow2(int n, int p) {
		int[] digits = String.valueOf(n).chars().map(Character::getNumericValue).toArray();
		int sum = IntStream.range(0, digits.length).map(i -> (int) Math.pow(digits[i], i + p)).sum();
		return sum % n == 0 ? sum / n : -1;
	}
}

