package javaChallenges;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

//	You are given an array(list) strarr of strings and an integer k. Your task is to return the first longest string consisting of k consecutive strings taken in the array.
//
//	Examples:
//	strarr = ["tree", "foling", "trashy", "blue", "abcdef", "uvwxyz"], k = 2
//
//	Concatenate the consecutive strings of strarr by 2, we get:
//
//	treefoling   (length 10)  concatenation of strarr[0] and strarr[1]
//	folingtrashy ("      12)  concatenation of strarr[1] and strarr[2]
//	trashyblue   ("      10)  concatenation of strarr[2] and strarr[3]
//	blueabcdef   ("      10)  concatenation of strarr[3] and strarr[4]
//	abcdefuvwxyz ("      12)  concatenation of strarr[4] and strarr[5]
//
//	Two strings are the longest: "folingtrashy" and "abcdefuvwxyz".
//	The first that came is "folingtrashy" so 
//	longest_consec(strarr, 2) should return "folingtrashy".
//
//	In the same way:
//	longest_consec(["zone", "abigail", "theta", "form", "libe", "zas", "theta", "abigail"], 2) --> "abigailtheta"
//	n being the length of the string array, if n = 0 or k > n or k <= 0 return "" (return Nothing in Elm, "nothing" in Erlang).
//
//	Note
//	consecutive strings : follow one after another without an interruption

	public static String longestConsec(String[] strarr, int k) {
		if (strarr.length == 0 || k > strarr.length || k <= 0)
			return "";

		String longestStr = "";
		for (int index = 0; index < strarr.length - k + 1; index++) {
			StringBuilder sb = new StringBuilder();
			for (int i = index; i < index + k; i++) {
				sb.append(strarr[i]);
			}
			if (sb.toString().length() > longestStr.length()) {
				longestStr = sb.toString();
			}
		}
		return longestStr;
	}

	public static String longestConsec2(String[] strarr, int k) {
		if (k <= 0) {
			return "";
		}

		return IntStream.rangeClosed(0, strarr.length - k)
				.mapToObj(i -> Arrays.stream(strarr, i, i + k).collect(Collectors.joining()))
				.max(Comparator.comparingInt(String::length)).orElse("");
	}

//	  Description
//	  We need a function that can transform a string 
//	  into a number. What ways of achieving this do you know?
//
//	  Note: Don't worry, all inputs will be strings, and every 
//	  string is a perfectly valid representation of an integral number.
//
//	  Examples
//	  "1234" --> 1234
//	  "605"  --> 605
//	  "1405" --> 1405
//	  "-7" --> -7

	public static int stringToNumber(String str) {
		return Integer.parseInt(str);
	}

	public static int stringToNumber2(String str) {
		return Integer.valueOf(str);
	}

//	Consider an array/list of sheep where some sheep may be missing from their place.
//	We need a function that counts the number of sheep present in the array (true means present).
//
//	For example,
//
//	[true,  true,  true,  false,
//	  true,  true,  true,  true ,
//	  true,  false, true,  false,
//	  true,  false, false, true ,
//	  true,  true,  true,  true ,
//	  false, false, true,  true]
//	The correct answer would be 17.
//
//	Hint: Don't forget to check for bad values like null/undefined

	public int countSheeps(Boolean[] arrayOfSheeps) {
		int counter = 0;
		for (Boolean present : arrayOfSheeps) {
			if (present != null && present) {
				counter += 1;
			}
		}
		return counter;
	}

	public int countSheeps2(Boolean[] arrayOfSheeps) {
		return Collections.frequency(Arrays.asList(arrayOfSheeps), true);
	}

	public int countSheeps3(Boolean[] arrayOfSheeps) {
		// TODO May the force be with you
		return Arrays.stream(arrayOfSheeps).filter(x -> x != null).filter(x -> x == true).toArray().length;
	}

//	Your task is to make a function that can take any non-negative integer as an argument and return 
//	it with its digits in descending order. Essentially, rearrange the digits to create the highest possible number.
//
//	Examples:
//	Input: 42145 Output: 54421
//
//	Input: 145263 Output: 654321
//
//	Input: 123456789 Output: 987654321

	public static int sortDesc(final int num) {
		String[] array = String.valueOf(num).split("");
		Arrays.sort(array, Collections.reverseOrder());
		return Integer.valueOf(String.join("", array));
	}

//	A Narcissistic Number (or Armstrong Number) is a positive number which is the sum of its
//	own digits, each raised to the power of the number of digits in a given base. In this Kata,
//	we will restrict ourselves to decimal (base 10).
//
//	For example, take 153 (3 digits), which is narcissistic:
//
//	    1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153
//	and 1652 (4 digits), which isn't:
//
//	    1^4 + 6^4 + 5^4 + 2^4 = 1 + 1296 + 625 + 16 = 1938
//	The Challenge:
//
//	Your code must return true or false (not 'true' and 'false') depending upon whether
//			the given number is a Narcissistic number in base 10.
//
//	This may be True and False in your language, e.g. PHP.
//
//	Error checking for text strings or other invalid inputs is not required, only valid
//	positive non-zero integers will be passed into the function.

	public static boolean isNarcissistic(int number) {
		String strNumber = String.valueOf(number);
		char[] digits = strNumber.toCharArray();
		int sum = 0;
		for (char digit : digits)
			sum += Math.pow(Integer.parseInt(String.valueOf(digit)), digits.length);
		return sum == number ? true : false;
	}

	public static boolean isNarcissistic2(int number) {
		int length = String.valueOf(number).length();
		return number == Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::parseInt)
				.mapToDouble(m -> Math.pow(m, length)).sum();
	}

//	Task
//	Given an integral number, determine if it's a square number:

//	Examples
//	-1  =>  false
//	 0  =>  true
//	 3  =>  false
//	 4  =>  true
//	25  =>  true
//	26  =>  false

	public static boolean isSquare(int n) {
		return Math.sqrt(n) % 1 == 0;
	}

	public static boolean isSquare2(int n) {
		long s = Math.round(Math.sqrt(n));
		return s * s == n;
	}

//	Given a string of words, you need to find the highest scoring word.
//
//	Each letter of a word scores points according to its position in the alphabet: a = 1, b = 2, c = 3 etc.
//
//	For example, the score of abad is 8 (1 + 2 + 1 + 4).
//
//	You need to return the highest scoring word as a string.
//
//	If two words score the same, return the word that appears earliest in the original string.
//
//	All letters will be lowercase and all inputs will be valid.

	public static String high(String s) {
		String winner = "";
		int highScore = 0;

		for (String word : s.split(" ")) {
			int score = 0;
			for (char c : word.toCharArray()) {
				score += c - 'a' + 1;
			}
			if (score > highScore) {
				winner = word;
				highScore = score;
			}
		}

		return winner;
	}

	public static String high2(String s) {
		return Arrays.stream(s.split(" ")).max(Comparator.comparingInt(a -> a.chars().map(i -> i - 96).sum())).get();
	}

//	Welcome. In this kata, you are asked to square
//	every digit of a number and concatenate them.
//
//	For example, if we run 9119 through the function,
//	811181 will come out, because 92 is 81 and 12 is 1. (81-1-1-81)
//
//	Example #2: An input of 765 will/should return 
//	493625 because 72 is 49, 62 is 36, and 52 is 25. (49-36-25)
//
//	Note: The function accepts an integer and returns an integer.

	public static int squareDigits(int n) {
		return Integer.parseInt(String.valueOf(n).chars().map(Character::getNumericValue).map(i -> i * i)
				.mapToObj(String::valueOf).collect(Collectors.joining("")));
	}

	public static int squareDigits2(int n) {

		String result = "";
		for (char c : String.valueOf(n).toCharArray()) {
			result += (int) Math.pow(Character.getNumericValue(c), 2);
//			result += (int) Math.pow(Character.digit(c, 10), 2); This is the same

		}
		return Integer.parseInt(result);
	}

//	Given an array (arr) as an argument complete the function countSmileys
//	that should return the total number of smiling faces.
//
//	Rules for a smiling face:
//
//	Each smiley face must contain a valid pair of eyes. Eyes can be marked as : or ;
//	A smiley face can have a nose but it does not have to. Valid characters for a nose are - or ~
//	Every smiling face must have a smiling mouth that should be marked with either ) or D
//	No additional characters are allowed except for those mentioned.
//
//	Valid smiley face examples: :) :D ;-D :~)
//	Invalid smiley faces: ;( :> :} :]
//
//	Example
//	countSmileys([':)', ';(', ';}', ':-D']);       // should return 2;
//	countSmileys([';D', ':-(', ':-)', ';~)']);     // should return 3;
//	countSmileys([';]', ':[', ';*', ':$', ';-D']); // should return 1;
//	Note
//	In case of an empty array return 0. You will not be tested with invalid input
//	(input will always be an array). Order of the face (eyes, nose, mouth) elements will always be the same.

	public static int countSmileys(List<String> arr) {
		return (int) arr.stream().filter(x -> x.matches("[:;][-~]?[)D]")).count();
	}
	
	
//	In this kata we want to convert a string into an integer.
//	The strings simply represent the numbers in words.
//
//	Examples:
//
//	"one" => 1
//	"twenty" => 20
//	"two hundred forty-six" => 246
//	"seven hundred eighty-three thousand nine hundred and nineteen" => 783919
//	Additional Notes:
//
//	The minimum number is "zero" (inclusively)
//	The maximum number, which must be supported is 1 million (inclusively)
//	The "and" in e.g. "one hundred and twenty-four" is optional, 
//	in some cases it's present and in others it's not
//	All tested numbers are valid, you don't need to validate them
	
	public static int parseInt(String numStr) {
        String[] numArray = numStr.split("[ |-]");
        int number = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("eleven", 11);
        map.put("twelve", 12);
        map.put("thirteen", 13);
        map.put("fourteen", 14);
        map.put("fifteen", 15);
        map.put("sixteen", 16);
        map.put("seventeen", 17);
        map.put("eighteen", 18);
        map.put("nineteen", 19);
        map.put("twenty", 20);
        map.put("thirty", 30);
        map.put("forty", 40);
        map.put("fifty", 50);
        map.put("sixty", 60);
        map.put("seventy", 70);
        map.put("eighty", 80);
        map.put("ninety", 90);
        map.put("hundred", 100);
        map.put("thousand", 1000);
        map.put("million", 1000000);
        
        for (int i = 0; i < numArray.length; i++) {
            for (String key : map.keySet()) {
                if (numArray[i].toLowerCase().equals(key)) {
                    if (map.get(key) == 100) {
                        int temp = number % 100;
                        number -= temp;
                        number += temp * (map.get(key));
                    }
                    else if (map.get(key) > 100)
                        number *= (map.get(key));
                    else
                        number += map.get(key);
                      break;
                }
            }
        }
        return number;
    }
	
//	Greed is a dice game played with five six-sided dice. Your mission, should 
//	you choose to accept it, is to score a throw according to these rules. 
//	You will always be given an array with five six-sided dice values.
//
//	 Three 1's => 1000 points
//	 Three 6's =>  600 points
//	 Three 5's =>  500 points
//	 Three 4's =>  400 points
//	 Three 3's =>  300 points
//	 Three 2's =>  200 points
//	 One   1   =>  100 points
//	 One   5   =>   50 point
//	A single die can only be counted once in each roll. For example, 
//	a given "5" can only count as part of a triplet (contributing to the 500 points)
//	or as a single 50 points, but not both in the same roll.
//
//	Example scoring
//
//	 Throw       Score
//	 ---------   ------------------
//	 5 1 3 4 1   250:  50 (for the 5) + 2 * 100 (for the 1s)
//	 1 1 1 3 1   1100: 1000 (for three 1s) + 100 (for the other 1)
//	 2 4 4 5 4   450:  400 (for three 4s) + 50 (for the 5)
//	In some languages, it is possible to mutate the input to the function.
//	This is something that you should never do. If you mutate the input,
//	you will not be able to pass all the tests.
	
	 public static int greedy(int[] dice) {
		  int n[]= new int[7];
		  for (int d:dice) n[d]++;
		  return n[1]/3*1000 + n[6]/3*600+ n[5]/3*500+ n[4]/3*400+n[3]/3*300+ n[2]/3*200+ n[1]%3*100+n[5]%3*50;

		  }
	 
	 
//	 Your goal in this kata is to implement a difference function, which 
//	 subtracts one list from another and returns the result.
//   It should remove all values from list a, which are present in list b keeping their order.
//   Kata.arrayDiff(new int[] {1, 2}, new int[] {1}) => new int[] {2}
//	 If a value is present in b, all of its occurrences must be removed from the other:
//   Kata.arrayDiff(new int[] {1, 2, 2, 2, 3}, new int[] {2}) => new int[] {1, 3}
	 
	 public static int[] arrayDiff1(int[] a, int[] b) {
		   List<Integer> listA = Arrays.stream(a).boxed().collect(Collectors.toList());
	        List<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toList());
	        listA.removeAll(listB);
	        return listA.stream().mapToInt(e -> e).toArray();
	    }
	 
	 public static int[] arrayDiff2(int[] a, int[] b) {
		    return IntStream.of(a).filter(x -> IntStream.of(b).noneMatch(y -> y == x)).toArray();
		  }
	 
	


}
