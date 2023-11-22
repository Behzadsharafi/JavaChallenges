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

//	Your task is to construct a building which will be a pile of n cubes. The cube at the bottom will have a volume of 
//	n^3, the cube above will have volume of (n-1)^3 and so on until the top which will have a volume of (1)^3.
//	You are given the total volume m of the building. Being given m can you find the number n of cubes you will have to build?
//  The parameter of the function findNb (find_nb, find-nb, findNb, ...) will be an integer m and you
//	have to return the integer n such as n^3 + (n-1)^3 + (n-2)^3 + .and..and + 1^3 = m  if such a n exists or -1 if there is no such n.
//	Examples:
//	findNb(1071225) --> 45
//
//	findNb(91716553919377) --> -1

	public static long findNb(long m) {
		long sum = 0;
		long n = 0;

		while (sum < m) {
			sum += Math.pow(++n, 3);

		}

		return (sum == m) ? n : -1;

	}

//	You might know some pretty large perfect squares. But what about the NEXT one?
//
//	Complete the findNextSquare method that finds the next integral perfect square after the one passed as a parameter. Recall that an integral perfect square is an integer n such that sqrt(n) is also an integer.
//
//	If the parameter is itself not a perfect square then -1 should be returned. You may assume the parameter is non-negative.
//
//	Examples:(Input --> Output)
//
//	121 --> 144
//	625 --> 676
//	114 --> -1 since 114 is not a perfect square

	public static long findNextSquare(long sq) {
		long root = (long) Math.sqrt(sq);
		return root * root == sq ? (root + 1) * (root + 1) : -1;
	}

	public static long findNextSquare2(long sq) {
		return Math.sqrt(sq) % 1 != 0 ? -1 : (long) Math.pow(Math.sqrt(sq) + 1, 2);
	}

//	Find the missing letter
//	Write a method that takes an array of consecutive (increasing) letters as input and that returns the missing letter in the array.
//
//	You will always get an valid array. And it will be always exactly one letter be missing. The length of the array will always be at least 2.
//	The array will always contain letters in only one case.
//
//	Example:
//
//	['a','b','c','d','f'] -> 'e'
//	['O','Q','R','S'] -> 'P'
//	(Use the English alphabet with 26 letters!)

	public static char findMissingLetter(char[] array) {
		boolean stop = false;
		int i;
		for (i = 1; i < array.length && !stop; i++) {
			if (array[i] - array[i - 1] != 1)
				stop = true;
		}
		return (char) (array[i - 1] - 1);
	}

	public static char findMissingLetter2(char[] array) {
		char expectableLetter = array[0];
		for (char letter : array) {
			if (letter != expectableLetter)
				break;
			expectableLetter++;
		}
		return expectableLetter;
	}

//	Given an array of integers, find the one that appears an odd number of times.
//
//	There will always be only one integer that appears an odd number of times.
//
//	Examples
//	[7] should return 7, because it occurs 1 time (which is odd).
//	[0] should return 0, because it occurs 1 time (which is odd).
//	[1,1,2] should return 2, because it occurs 1 time (which is odd).
//	[0,1,0,1,0] should return 0, because it occurs 3 times (which is odd).
//	[1,2,2,3,3,3,4,3,3,3,2,2,1] should return 4, because it appears 1 time (which is odd).

	public static int findIt(int[] A) {
		int xor = 0;
		for (int i = 0; i < A.length; i++) {
			xor ^= A[i];
		}
		return xor;
	}

	public static int findIt2(int[] arr) {
		return Arrays.stream(arr).reduce(0, (x, y) -> x ^ y);
	}

//	You are given an array (which will have a length of at least 3, but could be very large) containing integers. 
//	The array is either entirely comprised of odd integers or entirely comprised of even integers except 
//	for a single integer N. Write a method that takes the array as an argument and returns this "outlier" N.
//
//	Examples
//	[2, 4, 0, 100, 4, 11, 2602, 36] -->  11 (the only odd number)
//
//	[160, 3, 1719, 19, 11, 13, -21] --> 160 (the only even number)

	public static int findOutlier(int[] array) {

		int sum = (Arrays.stream(array).limit(3).map(i -> Math.abs(i) % 2).sum());
		int mod = (sum == 0 || sum == 1) ? 1 : 0;
		return Arrays.stream(array).parallel().filter(n -> Math.abs(n) % 2 == mod).findFirst().getAsInt();
	}
	
	public static int findOutlier2(int[] array) {

		int evenCount = 0;
		int oddCount = 0;
		for (int i = 0; i < 3; i++) {
			if (array[i] % 2 == 0) {
				evenCount++;
			} else {
				oddCount++;
			}
		}

		boolean isOutlierEven = evenCount > oddCount;
		for (int num : array) {
			if (isOutlierEven && num % 2 != 0) {
				return num;
			} else if (!isOutlierEven && num % 2 == 0) {
				return num;
			}
		}

		throw new IllegalArgumentException("No outlier found.");
	}

}
