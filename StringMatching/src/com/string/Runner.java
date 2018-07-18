package com.string;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "abc";
		printAllPermuations(text);
		printCombinations(text);
	
		
	}

	private static void printCombinations(String text) {
		// TODO Auto-generated method stub
		
		
	}

	private static void printAllPermuations(String text) {
		// TODO Auto-generated method stub
		permute(text.toCharArray(),0,text.length()-1);
		
	}

	private static void permute(char[] text , int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		
		if (startIndex==endIndex) {
			System.out.println(new String(text) );
			return;
		}
			
		for (int i = startIndex; i <= endIndex; i++) {
			
			swap(text ,startIndex,i);
			permute(text , startIndex+1, endIndex);
			swap(text ,startIndex,i);
		}
		
		
	}

	private static void swap(char[] text, int i, int j) {
		// TODO Auto-generated method stub
		char temp=text[i];
		text[i]=text[j];
		text[j]=temp;
		
	}

	private static ArrayList<Integer> kmp(String inputText, String patternToBeSearched) {
		
		ArrayList<Integer> result=new ArrayList<>();
		char[] text = inputText.toCharArray();
		char[] pattern = patternToBeSearched.toCharArray();
		int[] prefixTable = getPrefixTable(patternToBeSearched);
		
		
		int patternIterator=0;
		for (int textIterator = 0; textIterator < text.length; /* no update statement */) {

			if (text[textIterator]==pattern[patternIterator]) {
				textIterator++;
				patternIterator++;
				if (patternIterator==pattern.length) {
					result.add(textIterator-patternIterator);//since textiterator has already moved 1 extra //abab p=ab
					patternIterator=prefixTable[patternIterator-1];//aaaaaa p=aaa
				}
			}else {
				
				if (patternIterator==0) {
					textIterator++;
					//patternIterator continues to be 0
				}else {
					patternIterator=prefixTable[patternIterator-1];//patternIterator will be at index from where we need to begin matching with current text[textIIterator]
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
