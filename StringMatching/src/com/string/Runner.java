package com.string;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "abcabcccabcjklj";
		String pattern = "abc";
		ArrayList<Integer> indices = rabinKarp(text, pattern);
		System.out.println(indices);

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
				int patternIndex = patternLength-1;
				int textIndexDecrementer=textIndex;
				
				while (patternIndex >=0) {
					if (pattern[patternIndex] != text[textIndexDecrementer--]) {
						break;
					}
					patternIndex--;
				}
				if (patternIndex <0) {
					indexWherePatterMatched.add(textIndexDecrementer+1);
				}

			}

			if (textIndex + 1 >= text.length) {
				break;
			}
			rollingHashValue = (int) ( (10 * (rollingHashValue - ( (Math.pow(10, patternLength - 1)) * (text[textIndex - (patternLength-1)]) )))+ text[textIndex + 1]);

		}

		return indexWherePatterMatched;
	}

	private static int getHashValue(char[] pattern, int base) {
		// TODO Auto-generated method stub
		int hahValue = 0;
		int j = pattern.length - 1;
		for (int i = 0; i < pattern.length; i++) {
			System.out.println((int)pattern[i]);
			hahValue = hahValue + (int) (pattern[i] * (Math.pow(10, j)));
			j--;
		}
		return hahValue;
	}

}
