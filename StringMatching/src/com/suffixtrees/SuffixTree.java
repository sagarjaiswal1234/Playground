package com.suffixtrees;

import java.util.ArrayList;
import java.util.Arrays;

public class SuffixTree {

	static String[] suffixes;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	String text="babca";
	String pattern="ba";
	
	createSuffixes(text);
	sortSuffixes();
	boolean found=isPresent(pattern);
	System.out.println(found);
	
	ArrayList<Integer> occurences=getPostionOfOccurence(suffixes,pattern);
		
	}


	private static ArrayList<Integer> getPostionOfOccurence(String[] subsetSuffixes,String pattern) {
		// TODO Auto-generated method stub
		ArrayList<Integer> matched=new ArrayList<>();
		
		int insertionPoint=Arrays.binarySearch(suffixes, pattern);
		
		if (insertionPoint>=0) {//suffix exactly matches the pattern
			matched.add(insertionPoint);
		}	
		
		
		
		int startOfSubset=(-insertionPoint)-1;
		
		if (subsetSuffixes.length<=1) {
			return matched;
		}
		
		if (insertionPoint<0 && suffixes[startOfSubset].startsWith(pattern)) {
			matched.add(startOfSubset);
		}
		
		String[] subsetSuffix=getSubsetArray(suffixes,startOfSubset+1);
		
		ArrayList<Integer> sublist=getPostionOfOccurence(subsetSuffix, pattern);
		matched.addAll(sublist);
		
		return matched;
	}


	private static String[] getSubsetArray(String[] suffixes2, int startOfSubset) {

		String subset[]=new String[suffixes2.length-startOfSubset];
		
		for (int i = 0; i < subset.length; i++) {
			subset[i]=suffixes2[startOfSubset+i];
		}
		
		return subset;
	}


	private static boolean isPresent(String pattern) {
		// TODO Auto-generated method stub
		
		int insertionPoint=Arrays.binarySearch(suffixes, pattern);
		
		if (insertionPoint>=0) {//suffix exactly matches the pattern
			return true;
		}
		
		//if negative means String not present
		//insertion point gives point where the searched element could be inserted
		insertionPoint=(-insertionPoint)-1;//-1 since index starts from 0 for positive
		
		//start searching after insertion point
		if (suffixes[insertionPoint].startsWith(pattern)) {
			return true;
		}	
		
		
		return false;
	}


	private static void sortSuffixes() {
		// TODO Auto-generated method stub
		Arrays.sort(suffixes);
	}


	private static void createSuffixes(String text) {
		// TODO Auto-generated method stub
		suffixes=new String[text.length()];//
		
		for (int i = text.length()-1; i>=0; i--) {
			suffixes[i]=text.substring(i);
		}
	
	}

	
	
	
	
}
