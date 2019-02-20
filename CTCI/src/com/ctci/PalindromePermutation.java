package com.ctci;

import java.util.Collection;
import java.util.HashMap;

public class PalindromePermutation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String input="AAABB";
		boolean is=checkIfPalindromePermutationV1(input);
		 is=checkIfPalindromePermutationV2(input);
		 is=checkIfPalindromePermutationV3(input);
				
		System.out.println(is);
		
	}

	private static boolean checkIfPalindromePermutationV3(String input) {
		// Bulb :When you find that character you flip its current state
		//using bit vector
		
		return false;
	}

	private static boolean checkIfPalindromePermutationV2(String input) {
		// TODO Auto-generated method stub
		HashMap<Character, Integer> frequencyMap=new HashMap<>();
		int countOfOddFrequency=0;
		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);
			int count=frequencyMap.getOrDefault(charAt, 0)+1;
			if (count%2!=0) {
				countOfOddFrequency++;
			}else {
				countOfOddFrequency--;
			}
			frequencyMap.put(charAt, count);
		}
		
		if (countOfOddFrequency!=1) {
			return false;
		}
		return true;
	}

	private static boolean checkIfPalindromePermutationV1(String input) {
		
		HashMap<Character, Integer> map=new HashMap<>();
		
		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);
			map.put(charAt, map.getOrDefault(charAt, 0)+1);
		}
		
		Collection<Integer> values = map.values();
		
		/*boolean foundOneOdd=false;
		for (Integer count : values) {
			
			if (count%2!=0 && foundOneOdd) {
				return false;
			}else if (count%2!=0) {
				foundOneOdd=true;
			}
			
		}
		
		return true;*/
		
		long count = values.stream()
				.filter(i->i%2!=0)
				.count();
		
		if (count>1) {
			return false;
		}else {
			return true;
		}
		
	}

}
