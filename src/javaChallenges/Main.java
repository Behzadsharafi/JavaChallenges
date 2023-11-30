package javaChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static int countSmileys(List<String> arr) {
		return (int) arr.stream().filter(x -> x.matches("[:;][-~]?[)D]")).count();
	}

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add(":)");
		a.add(":D");
		a.add(":-}");
		a.add(":-()");

		System.out.println(countSmileys(a));
	}
}
