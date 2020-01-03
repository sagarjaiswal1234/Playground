package com.ctci;

import java.lang.reflect.Array;
import java.util.Arrays;

public class URLify {

	public static void main(String[] args) {
		
		
		char[] input = "Sagar is programmer  ".toCharArray();
		
		urlify(input);
		System.out.println(Arrays.asList(input));
		
	}

	private static void urlify(char[] input) {
		// TODO Auto-generated method stub
		int countOfSpaces=0;
		for (int i = 0; i < input.length; i++) {
			if (input[i]==' ') {
				countOfSpaces++;
			}
		}
		System.out.println(countOfSpaces);
		int lengthOfOututString=(countOfSpaces*2)+input.length;
		System.out.println(input.length);
		System.out.println(lengthOfOututString);
		char[] result=new char[lengthOfOututString];
		System.out.println(result.length);
		
		for (int i = input.length-1,j=result.length-1; i >=0; i--,j--) {
			System.out.println(input[i]);
			if (input[i]==' ') {
				
				result[j]='0';
				result[--j]='2';
				result[--j]='%';
			}else {
				result[j]=input[i];
			}
			
			
		}
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		System.out.println(Arrays.asList(new String(result)));
	}

}
