package com.string;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text="";
		String pattern="";
		int[] indices=rabinKarp(text,pattern);
	}

	private static int[] rabinKarp(String text, String pattern) {
		//assuming we will use only 10 characters

		char[] elementsPossible= {'a','b','c','d','e','f','g','h'};
		int base=10;
		
		char[] pattern1=pattern.toCharArray();
		int hashValue=0;
		int j=pattern1.length-1;
		
		for (int i = 0; i < pattern1.length; i++) {
			hashValue=hashValue+(int) (pattern1[i]*(Math.pow(base,j)));
			j--;
		}
		
		
		char[] text1=text.toCharArray();
		
		
		
		return null;
	}

}
