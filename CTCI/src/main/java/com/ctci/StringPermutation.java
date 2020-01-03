	package com.ctci;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class StringPermutation {

	public static void main(String[] args) {
		String input1="SAGARy";
		String input2="SAGARA";
		
		boolean isPermutation=checkPermuationUsingSorting(input1,input2);
		System.out.println(isPermutation);
		
		boolean isPermutation2=checkPermuationUsingHashTable(input1,input2);
		System.out.println(isPermutation2);
		
		
		boolean isPermutation3=checkPermuationUsingAsciiArray(input1,input2);
		System.out.println(isPermutation3);
		
	}

	private static boolean checkPermuationUsingAsciiArray(String input1, String input2) {
		// TODO Auto-generated method stub=
		int[] countArray=new int[128];
		
		
		if (input1.length()!=input2.length()) {
			return false;
		}
		
		for (int i = 0; i < input1.length(); i++) {
			
			countArray[input1.charAt(i)]++;
			
		}
		
		for (int i = 0; i < input2.length(); i++) {
			countArray[input2.charAt(i)]--;
			if (countArray[input2.charAt(i)]<0) {
				return false;
			}
			
		}
		
		return true;
	}

	private static boolean checkPermuationUsingHashTable(String input1, String input2) {
		// TODO Auto-generated method stub
		
		if (input1.length()!=input2.length()) {
			return false;
		}
		HashMap<Character, Integer> map=new HashMap<>();		
		
		char[] charArray = input1.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			map.put(charArray[i], map.getOrDefault(charArray[i], 0)+1);
		}
		
		for (int i = 0; i < input2.length(); i++) {
			
			
			if (map.get(input2.charAt(i))==null ) {
				return false;
			}
			
			map.put(input2.charAt(i), map.get(input2.charAt(i))-1);
			
			if (map.get(input2.charAt(i))<0) {
				return false;
			}
		}
		
		//No need to worry about subset since f length are not same we retiurn false
		
		return true;
	}

	private static boolean checkPermuationUsingSorting(String input1, String input2) {
		// TODO Auto-generated method stub
		
		if (input1.length()!=input2.length()) {
			return false;
		}
		
		char[] charArray = input1.toCharArray();
		Arrays.sort(charArray);
		
		
		char[] charArray2 = input2.toCharArray();
		Arrays.sort(charArray2);
		
		return new String(charArray).equals(new String(charArray2));
	}
	
	
}
