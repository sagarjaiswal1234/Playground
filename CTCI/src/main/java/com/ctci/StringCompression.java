package com.ctci;

public class StringCompression {

	public static void main(String args []) {
		String input="aabbb";
		String compressed=compressString(input);
		System.out.println(input);
		System.out.println(compressed);
		System.out.println("done");
	} 

	private static String compressString(String input) {
		// TODO Auto-generated method stub

		if (input.length()<1) {
			return input;
		}
		
		StringBuilder compressed=new StringBuilder();
		//compressed.append(input.charAt(0));
		int inputIndex=1;
		char previousChar=input.charAt(0);
		int previousCharCounter=1;
		
		while (inputIndex<input.length()) {
		
			char inputChar=input.charAt(inputIndex);
			
			if (inputChar==previousChar) {
				previousCharCounter++;
			}else {
				compressed.append(String.valueOf((char)previousChar)+previousCharCounter);
				previousChar=inputChar;
				previousCharCounter=1;
			}
			inputIndex++;
				
		}
		
		compressed.append(String.valueOf((char)previousChar)+previousCharCounter);
		
		if (compressed.length()==input.length()) {
			return input;
		}
		
		return compressed.toString();
	}
	
}
