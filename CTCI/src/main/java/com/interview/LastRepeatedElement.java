package com.interview;

import java.util.HashMap;

public class LastRepeatedElement {

	public static void main(String[] args) {
		int[] input= {};
		
		int result=lastRepeatedElement(input);
		System.out.println(result);
		
	}

	private static int lastRepeatedElement(int[] input) {
		
		HashMap<Integer, Integer> frequencyMap=new HashMap<>();
		
		for (int i = 0; i < input.length; i++) {
			frequencyMap.put(input[i], frequencyMap.getOrDefault(input[i], 0)+1);
		}
		
		for (int i = input.length-1; i >=0; i--) {
			if (frequencyMap.get(input[i])>1) {
				return input[i];
			}
		}
		
		return -1;
		
		
	}
	
}
