package com.ctci;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PrimitiveIterator.OfInt;

public class UniqueCharsInString {

	public static void main(String[] args) {
		String input = "abcdea";

		boolean usingHashTable = usingHashTable(input);
		System.out.println(usingHashTable);

		boolean usingSorting = usingSorting(input);
		System.out.println(usingSorting);

		boolean usingStream = usingStream(input);
		System.out.println(usingStream);

		boolean usingBooleanArray = usingBooleanArrayApproach1(input);
		System.out.println(usingBooleanArray);

		boolean usingBooleanArray2 = usingBooleanArrayApproach2(input);
		System.out.println(usingBooleanArray2);
		
		
	}

	private static boolean usingBooleanArrayApproach2(String input) {
		boolean[] uniqueChars = new boolean[128];

		// Approach2
		for (int i = 0; i < input.length(); i++) {
			char asciiValue=input.charAt(i);
			if (uniqueChars[asciiValue]==true) {
				return false;
			}
			uniqueChars[asciiValue]=true;
		}

		return true;
	}

	private static boolean usingBooleanArrayApproach1(String input) {
		// TODO Auto-generated method stub
		boolean[] uniqueChars = new boolean[128];

		// Approach1
		OfInt iterator = input.chars().iterator();

		while (iterator.hasNext()) {
			int asciiValue = iterator.nextInt();

			if (uniqueChars[asciiValue] == true) {
				return false;
			}
			uniqueChars[asciiValue] = true;
		}

		return true;
	}

	private static boolean usingStream(String input) {
		// TODO Auto-generated method stub

		long count = input.chars().distinct().count();

		if (input.length() != count) {
			return false;
		} else {
			return true;
		}

	}

	private static boolean usingSorting(String input) {
		char[] charArray = input.toCharArray();
		Arrays.sort(charArray);

		for (int i = 0; i < charArray.length - 1; i++) {
			if (charArray[i] == charArray[i + 1]) {
				return false;
			}
		}
		return true;

	}

	private static boolean usingHashTable(String input) {
		// TODO Auto-generated method stub

		HashSet<Character> hashSet = new HashSet<>();

		for (int i = 0; i < input.length(); i++) {
			if (hashSet.contains(input.charAt(i))) {
				return false;
			}
			hashSet.add(input.charAt(i));
		}
		return true;
	}

}
