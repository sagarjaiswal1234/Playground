package com.ctci;

public class OneEditAway {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s1 = "pale";
		String s2 = "palds";
		boolean is = isOneEditAwayApproach1(s1, s2);
		boolean is2 = isOneEditAwayApproach2(s1, s2);

		System.out.println(is);
		System.out.println(is2);
	}

	private static boolean isOneEditAwayApproach2(String longer, String shortOrEqual) {
		// TODO Auto-generated method stub

		if (Math.abs(longer.length() - shortOrEqual.length()) > 1) {
			return false;
		}

		if (longer.length() < shortOrEqual.length()) {
			String temp = longer;
			longer = shortOrEqual;
			shortOrEqual = temp;
		}

		boolean foundDifference = false;
		int longerIndex = 0;
		int shorterIndex = 0;

		while (shorterIndex < shortOrEqual.length()) {

			if (shortOrEqual.charAt(shorterIndex) != longer.charAt(longerIndex)) {

				if (foundDifference) {
					return false;
				}
				foundDifference = true;

				if (shortOrEqual.length() == longer.length()) {
					shorterIndex++;//Meaning unmatched is replacedOnce
				}
				
			}else {
				shorterIndex++;
			}
			
			longerIndex++;
			
		}

		return true;

	}

	private static boolean isOneEditAwayApproach1(String s1, String s2) {
		// TODO Auto-generated method stub

		if (s1.length() == s2.length()) {
			return isOneReplaceAway(s1, s2);
		} else if (s1.length() + 1 == s2.length()) {// s1 is smaller

			// start navigating from longer
			return isOneRemoveAway(s2, s1);
		} else if (s1.length() - 1 == s2.length()) {
			return isOneRemoveAway(s1, s2);
		} else {
			// diff is greater than 1
			return false;
		}

	}

	private static boolean isOneRemoveAway(String longer, String shorter) {

		boolean ignoredOneCharacter = false;

		int longerIndex = 0;
		int shorterIndex = 0;

		while (longerIndex < longer.length() ) {

			if (longer.charAt(longerIndex) != shorter.charAt(shorterIndex)) {

				if (ignoredOneCharacter) {
					return false;
				}
				ignoredOneCharacter = true;
				longerIndex++;
			}else {
			//shorter index moves only when matched
				shorterIndex++;
				longerIndex++;
			}

			

		}

		return true;
		// TODO Auto-generated method stub

	}

	private static boolean isOneReplaceAway(String s1, String s2) {
		// TODO Auto-generated method stub

		boolean foundDiffOnce = false;
		int index = 0;
		while (index < s1.length()) {

			if (s1.charAt(index) != s2.charAt(index)) {

				// This is only exit if more than one diff is found
				if (foundDiffOnce) {
					return false;
				}
				foundDiffOnce = true;
			}
			index++;
		}

		return true;
	}

}
