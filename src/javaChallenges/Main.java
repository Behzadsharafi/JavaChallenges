package javaChallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	public static int[] arrayDiff(int[] a, int[] b) {
		return IntStream.of(a).filter(x -> IntStream.of(b).noneMatch(y -> y == x)).toArray();
	}

	public static void main(String[] args) {

		System.out.println(Arrays.toString(arrayDiff(new int[] { 1, 2 }, new int[] { 1 })));
		System.out.println(Arrays.toString(arrayDiff(new int[] { 1, 2 }, new int[] { 1 })));

	}
}

//int number = 0;

// i=0
//-----------------
//number=2

//i=1
//-----------------
//temp=2
//number=2-2=0
//number= 0 + 2*100= 200

//i=2
//-----------------
//nothing

//i=3
//-----------------
//number = 200+60 = 260

//i=4
//-----------------
