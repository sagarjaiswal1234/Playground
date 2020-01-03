package com.string;

import java.util.ArrayList;
import java.util.HashMap;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	private static void wildCardMatch(String data, String wildCardPattern) {
		// TODO Auto-generated method stub
		char[] text = data.toCharArray();
		char[] pattern = wildCardPattern.toCharArray();

		// Ignoring consecustive ** condition

		// length+1 since we are going to match "" string as well
		// default intialized to False
		boolean T[][] = new boolean[text.length + 1][pattern.length + 1];

		// since "" matches with ""
		T[0][0] = true;

		// since "" could match with *
		if (pattern[0] == '*') {
			T[0][1] = true;
		}

		// remaining row and column for "" char is already false
		// patternIndex==1 if start then it will be ignored since we are considering
		// entire matrix is intialized with false
		// but in above ladder we have overwriiten value with true
		// we are starting from 1 since our actual pattern and text start with 1 index
		// and 0 is for ""
		for (int textIndex = 1; textIndex < T.length; textIndex++) {

			for (int patternIndex = 1; patternIndex < T[0].length; patternIndex++) {

				char patternChar = pattern[patternIndex - 1];
				char textChar = text[textIndex - 1];

				if (patternChar == '?' || patternChar == textChar) {
					// ignore current char from pattern and text
					T[textIndex][patternIndex] = T[textIndex - 1][patternIndex - 1];

				} else if (patternChar == '*') {
					boolean left = T[textIndex][patternIndex - 1];
					boolean up = T[textIndex - 1][patternIndex];
					T[textIndex][patternIndex] = left || up;
				}

			}

		}

		System.out.println("text " + data + " matches " + wildCardPattern + " " + T[text.length][pattern.length]);
	}

	private static void findPatternIn2DArray(char[][] text, String pattern) {
		// TODO Auto-generated method stub

		/*
		 * char text1 [][]= { {'a','b','c'}, {'b','c','d'}, {'h','i','j'} };
		 */

		for (int i = 0; i < text.length; i++) {

			for (int j = 0; j < text[i].length; j++) {

				System.out.println(i + " " + j);
				boolean found = searchPattern(text, pattern, i, j);
				if (found == true) {
					System.out.println("found pattern at index " + i + j);
					return;
				}

			}

		}
		System.out.println("Pattern not found");

	}

	private static boolean searchPattern(char[][] text, String pattern, int seedx, int seedy) {
		// TODO Auto-generated method stub

		int seedX = seedx;
		int seedY = seedy;
		if (pattern.charAt(0) != text[seedX][seedY]) {
			return false;
		}

		int[] x = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] y = { 0, 1, 1, 1, 0, -1, -1, -1 };

		for (int direction = 0; direction < y.length; direction++) {

			int currentX = seedX + x[direction];
			int currentY = seedY + y[direction];
			int patternIndex = 0;
			for (patternIndex = 1; patternIndex < pattern.length(); patternIndex++) {

				if (currentX < 0 || currentX > text.length - 1 || currentY < 0 || currentY > text[0].length - 1) {
					break;
				}

				if (text[currentX][currentY] != pattern.charAt(patternIndex)) {
					break;
				}

				currentX = currentX + x[direction];
				currentY = currentY + x[direction];

			}

			if (patternIndex == pattern.length()) {
				return true;
			}

		}

		return false;
	}

	private static int minimumWindowSubstring(String text, String pattern) {
		// TODO Auto-generated method stub

		int lengthMatched = 0;
		int startOfWindow = 0;
		int minimumWindowLength = 100;

		if (pattern.length() > text.length()) {
			return -1;
		}
		HashMap<Character, Integer> matchedCharMap = new HashMap<>();
		HashMap<Character, Integer> patternMap = new HashMap<>();

		for (int i = 0; i < pattern.length(); i++) {
			Character patternChar = pattern.charAt(i);
			int countOfChar = patternMap.getOrDefault(patternChar, 0);
			countOfChar++;
			patternMap.put(patternChar, countOfChar);
		}

		for (int i = 0; i < text.length(); i++) {

			Character textChar = text.charAt(i);

			if (patternMap.containsKey(textChar)) {

				int countOfChar = matchedCharMap.getOrDefault(textChar, 0);
				countOfChar++;
				matchedCharMap.put(textChar, countOfChar);

				if (countOfChar <= patternMap.getOrDefault(textChar, -1)) {
					lengthMatched++;
				}

				if (lengthMatched == pattern.length()) {// shifting startWindow

					Character charAtIndex = text.charAt(startOfWindow);

					while (patternMap.containsKey(charAtIndex)
							&& matchedCharMap.get(charAtIndex).intValue() > patternMap.get(charAtIndex)) {
						decrementCountInMap(matchedCharMap, charAtIndex);
						startOfWindow++;

						// remove chars not present in pattern
						charAtIndex = text.charAt(startOfWindow);
						while (startOfWindow < i && patternMap.get(text.charAt(startOfWindow)) == null) {
							startOfWindow++;
						}
					}

					int effeciveLength = i - startOfWindow + 1;
					if (effeciveLength < minimumWindowLength) {
						minimumWindowLength = effeciveLength;
						if (effeciveLength == pattern.length()) {
							return effeciveLength;
						}
					}

				}

			}

		}

		return minimumWindowLength;
	}

	private static void decrementCountInMap(HashMap<Character, Integer> matchedCharMap, Character key) {
		// TODO Auto-generated method stub
		Integer count = matchedCharMap.get(key);
		if (count == null) {
			return;
		}
		matchedCharMap.put(key, --count);
	}

	private static void removeAdjascentPairs(String t) {
		// TODO Auto-generated method stub
		char[] text = t.toCharArray();
		StringBuffer sb = new StringBuffer(t);

		int prev = 0;
		for (int current = 1; current < text.length;) {

			while (prev >= 0 && current < text.length && text[prev] == text[current]) {
				sb.delete(prev, current);
				prev--;
				current++;
			}
			prev++;
			current++;
		}
		System.out.println(sb);

	}

		
	private static void printCombinations(String text) {
		// TODO Auto-generated method stub
		combine(text, new StringBuffer(""), 0);

	}

	private static void combine(String text, StringBuffer fixedOutput, int startOfRemainigCombination) {
		// TODO Auto-generated method stub
		// Issue when text has repeated charter
		for (int i = startOfRemainigCombination; i < text.length(); i++) {
			fixedOutput = fixedOutput.append(text.charAt(i));
			System.out.println(fixedOutput);
			combine(text, fixedOutput, i + 1);
			fixedOutput.deleteCharAt(fixedOutput.length() - 1);

		}

	}

	private static void printAllPermuations(String text) {
		// TODO Auto-generated method stub
		permute(text.toCharArray(), 0, text.length() - 1);

	}

	private static void permute(char[] text, int startIndex, int endIndex) {
		// TODO Auto-generated method stub

		if (startIndex == endIndex) {
			System.out.println(new String(text));
			return;
		}

		for (int i = startIndex; i <= endIndex; i++) {

			swap(text, startIndex, i);
			permute(text, startIndex + 1, endIndex);
			swap(text, startIndex, i);
		}

	}

	private static void swap(char[] text, int i, int j) {
		// TODO Auto-generated method stub
		char temp = text[i];
		text[i] = text[j];
		text[j] = temp;

	}

	private static ArrayList<Integer> kmp(String inputText, String patternToBeSearched) {

		ArrayList<Integer> result = new ArrayList<>();
		char[] text = inputText.toCharArray();
		char[] pattern = patternToBeSearched.toCharArray();
		int[] prefixTable = getPrefixTable(patternToBeSearched);

		int patternIterator = 0;
		for (int textIterator = 0; textIterator < text.length; /* no update statement */) {

			if (text[textIterator] == pattern[patternIterator]) {
				textIterator++;
				patternIterator++;
				if (patternIterator == pattern.length) {
					result.add(textIterator - patternIterator);// since textiterator has already moved 1 extra //abab
																// p=ab
					patternIterator = prefixTable[patternIterator - 1];// aaaaaa p=aaa
				}
			} else {

				if (patternIterator == 0) {
					textIterator++;
					// patternIterator continues to be 0
				} else {
					patternIterator = prefixTable[patternIterator - 1];// patternIterator will be at index from where we
																		// need to begin matching with current
																		// text[textIIterator]
				}

			}

		}

		return result;
	}

	private static void printarray(int[] prefixTable) {
		// TODO Auto-generated method stub

		System.out.print("[");
		for (int i = 0; i < prefixTable.length; i++) {
			System.out.print(prefixTable[i] + ",");
		}
		System.out.println("]");

	}

	private static int[] getPrefixTable(String inputPattern) {
		// TODO Auto-generated method stub

		int[] prefixTable = new int[inputPattern.length()];

		char[] pattern = inputPattern.toCharArray();// both pointing to same since for creating prefixTable we compare
													// pattern with itself
		char[] text = pattern;

		prefixTable[0] = 0;// since no proper suffix for single character

		int patternIterator = 0;// number of chars matched
		for (int textIterator = 1; textIterator < text.length; /* no update statement */) {

			if (text[textIterator] == pattern[patternIterator]) {

				prefixTable[textIterator] = patternIterator + 1;// plus 1 since 1 more character has matched
				patternIterator++;
				textIterator++;
			} else {

				if (patternIterator == 0) {
					prefixTable[textIterator] = 0;
					textIterator++;
				} else {

					patternIterator = prefixTable[patternIterator - 1];// example ababc p=abe
					// we are not moving textiterator
				}

			}

		}

		return prefixTable;
	}

	private static ArrayList<String> getSuffixes(String substring) {
		// TODO Auto-generated method stub
		ArrayList<String> suffixs = new ArrayList<String>();

		for (int i = substring.length() - 1; i > 0; i--) {
			suffixs.add(substring.substring(i));
		}

		return suffixs;
	}

	private static ArrayList<String> getPrefixes(String substring) {
		// TODO Auto-generated method stub
		ArrayList<String> prefixs = new ArrayList<String>();

		for (int i = 0; i < substring.length(); i++) {
			prefixs.add(substring.substring(0, i));
		}

		return prefixs;
	}

	private static ArrayList<Integer> rabinKarp(String inputText, String patternToBeSearched) {
		// assuming we will use only 10 characters

		ArrayList<Integer> indexWherePatterMatched = new ArrayList<>();

		char[] elementsPossible = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' };
		int base = 10;// since 10 elemnts.In equation directly using 10 for simplicity
		int patternLength = patternToBeSearched.length();

		char[] pattern = patternToBeSearched.toCharArray();
		int patternHashValue = getHashValue(pattern, base);

		char[] textPattern = inputText.substring(0, patternLength).toCharArray();
		int rollingHashValue = getHashValue(textPattern, base);

		char[] text = inputText.toCharArray();

		for (int textIndex = patternLength - 1; textIndex < text.length; textIndex++) {

			if (patternHashValue == rollingHashValue) {

				// check all elemnets if found add to result
				int patternIndex = patternLength - 1;
				int textIndexDecrementer = textIndex;

				while (patternIndex >= 0) {
					if (pattern[patternIndex] != text[textIndexDecrementer--]) {
						break;
					}
					patternIndex--;
				}
				if (patternIndex < 0) {
					indexWherePatterMatched.add(textIndexDecrementer + 1);
				}

			}

			if (textIndex + 1 >= text.length) {
				break;
			}
			rollingHashValue = (int) ((10 * (rollingHashValue
					- ((Math.pow(10, patternLength - 1)) * (text[textIndex - (patternLength - 1)]))))
					+ text[textIndex + 1]);

		}

		return indexWherePatterMatched;
	}

	private static int getHashValue(char[] pattern, int base) {
		// TODO Auto-generated method stub
		int hahValue = 0;
		int j = pattern.length - 1;
		for (int i = 0; i < pattern.length; i++) {
			System.out.println((int) pattern[i]);
			hahValue = hahValue + (int) (pattern[i] * (Math.pow(10, j)));
			j--;
		}
		return hahValue;
	}

}
