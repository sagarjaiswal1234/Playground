package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = { 9, 3, 4, 2, 57, 7, -6 ,9,9,9};
		int[] input2 = { 0, 80, 20, 91,22};
		
		
		String [] array=new String("1,2,99,55,-8,5,6,7,8,78,10,11,2,13,14").split(",");
		//array=new String("1,2,8,7").split(",");
		
		Node head=createLinkedListi(array);	
		Node sortedHead=mergeSortLinkedList(head);
		
		
		
	}
	
	
	private static Node mergeSortLinkedList(Node head) {
		// TODO Auto-generated method stub
		Node sortedList=null;
		Node list1=null;
		Node list2=null;
		
		if (head==null || head.getNextRef()==null) {
			return head;
		}
		
		list1=head;
		list2=head.getNextRef();
		Node list1Head=list1;
		Node list2Head=list2;
		head=head.getNextRef();
		//split into 2 halves
		while (head!=null) {			
			list1.setNextRef(head.getNextRef());
			list1=list1.getNextRef();
			head=head.getNextRef();	
			if (head!=null) {
				list2.setNextRef(head.getNextRef());
				list2=list2.getNextRef();
				head=head.getNextRef();
			}
		}
		
		if (list1!=null) {
			list1.setNextRef(null);
		}
		if (list2!=null) {
			list2.setNextRef(null);
		}
		
		Node sortedList1=mergeSortLinkedList(list1Head);
		Node sortedList2=mergeSortLinkedList(list2Head);
		
		sortedList=mergeLinkedList(sortedList1,sortedList2);
		
		return sortedList;
	}


	private static Node mergeLinkedList(Node list1Head, Node list2Head) {
		// TODO Auto-generated method stub
		Node sortedListHead=null;
		if (list1Head==null && list2Head==null) {
			return null;
		}else if (list1Head==null) {
			return list2Head;
		}else if (list2Head==null) {
			return list1Head;
		}
		
		if (compare(list1Head.getData(),list2Head.getData())<=0) {
			sortedListHead=list1Head;
			list1Head=list1Head.getNextRef();
		}else {
			sortedListHead=list2Head;
			list2Head=list2Head.getNextRef();
		}
		Node sortedListCursor=sortedListHead;
		
		while (list1Head!=null && list2Head!=null) {
			
			if (compare(list1Head.getData(), list2Head.getData())<=0) {
				sortedListCursor.setNextRef(list1Head);
				list1Head=list1Head.getNextRef();
				sortedListCursor=sortedListCursor.getNextRef();
			} else {
				sortedListCursor.setNextRef(list2Head);
				list2Head=list2Head.getNextRef();
				sortedListCursor=sortedListCursor.getNextRef();
			}
			
		}
		
		if (list1Head!=null) {
			sortedListCursor.setNextRef(list1Head);
		} else {
			sortedListCursor.setNextRef(list2Head);
		}
		
		return sortedListHead;
	}


	private static int compare(String data1, String data2) {
		// TODO Auto-generated method stub
		if (new Integer(data1).intValue()<=new Integer(data2)) {
			return -1;
		} else {
			return 1;
		}
	}


	public static Node createLinkedListi(String[] data) {
		Node head = new Node();
		head.setData(data[0]);
		head.setNextRef(null);
		Node current = head;
		Node newNode;

		for (int i = 1; i < data.length; i++) {
			newNode = new Node();
			newNode.setData(data[i]);
			newNode.setNextRef(null);

			current.setNextRef(newNode);
			current = newNode;
		}

		return head;
	}

	private static void bucketsort(int[] input, int range) {
		// TODO Auto-generated method stub
		ArrayList<Integer>[] buckets=new ArrayList[range+1];
		
		for (int i = 0; i < buckets.length; i++) {
			buckets[i]=new ArrayList<Integer>();
		}
		
		int[] output=new int[input.length];
		for (int i = 0; i < input.length; i++) {
			int data=input[i];
			int bucketIndex=getbucket(data);
			buckets[bucketIndex].add(data);
		}
		
		for (int i = 0; i < buckets.length; i++) {
			Collections.sort(buckets[i]);
		}
		
		int j=0;
		for (int i = 0; i < buckets.length; i++) {
			for (int listData : buckets[i]) {
				output[j]=listData;
				j++;
			}
		}
		
		copyArray(output, input);
	}

	private static int getbucket(int data) {
		
		return data/10;
	}

	private static void countingSort(int[] input, int range) {
		// TODO Auto-generated method stub
		int[] frequencyCounter=new int[range+1];
		int[] output=new int[input.length];
		
		for (int i = 0; i < input.length; i++) {
			
			frequencyCounter[input[i]]++;
		}
		
		int[] elementsLessOrEqualIndexn=frequencyCounter;
		
		for (int i = 1; i < elementsLessOrEqualIndexn.length; i++) {
			elementsLessOrEqualIndexn[i]=elementsLessOrEqualIndexn[i-1]+elementsLessOrEqualIndexn[i];
		}
		
		for (int i = input.length-1; i >=0; i--) {
			
			int elementToBeInserted=input[i];
			int postitionOfInsertion=elementsLessOrEqualIndexn[elementToBeInserted];//value
			output[postitionOfInsertion-1]=elementToBeInserted;//since we are using frequency and index starts from 0
			
			elementsLessOrEqualIndexn[elementToBeInserted]--;
		}
		copyArray(output,input);
	}

	private static void copyArray(int[] source, int[] target) {
		// TODO Auto-generated method stub
		for (int i = 0; i < source.length; i++) {
			target[i]=source[i];
		}
		
	}

	private static void quicksort(int[] input, int startIndex, int endIndex) {
		
		if (startIndex<endIndex) {
			
			int pivotIndex=partition(input,startIndex,endIndex);
			quicksort(input, startIndex, pivotIndex-1);
			quicksort(input, pivotIndex+1, endIndex);
		}
		
		
	}

	private static int partition(int[] input, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		int currentPivotIndex=getPivot(input,startIndex,endIndex);
		swap(input, endIndex, currentPivotIndex);
		
		
		int endOflessOrequalIndex=startIndex-1;
		int pivotValue=input[endIndex];
		
		for (int i = startIndex; i <= endIndex-1; i++) {
			
			if (input[i]<=pivotValue) {
				endOflessOrequalIndex++;
				swap(input, endOflessOrequalIndex, i);
			}
			
		}
		
		endOflessOrequalIndex++;
		swap(input, endOflessOrequalIndex, endIndex);
		
		return endOflessOrequalIndex;
	}

	private static int getPivot(int[] input, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		return endIndex;
	}

	private static void mergesort(int[] input, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		int mid=(startIndex+endIndex)/2;
		
		if (startIndex<endIndex) {
			mergesort(input, startIndex, mid);
			mergesort(input, mid+1, endIndex);
			merge(input,startIndex,mid,endIndex);		
		}
		
		
	}

	private static void merge(int[] input, int startIndex, int mid, int endIndex) {
		// TODO Auto-generated method stub
		
		int[] sublist1=getsubsetArray(input,startIndex,mid);
		int[] sublist2=getsubsetArray(input,mid+1,endIndex);
		int listIndex1 = 0,listindex2=0;
		
		while (listIndex1<sublist1.length
				&& listindex2<sublist2.length) {
			
			if (sublist1[listIndex1]<=sublist2[listindex2]) {
				input[startIndex]=sublist1[listIndex1];
				listIndex1++;
			}else if (sublist2[listindex2]<sublist1[listIndex1]) {
				input[startIndex]=sublist2[listindex2];
				listindex2++;
			}
			
			startIndex++;
		}
		
		while (listIndex1<sublist1.length) {
			input[startIndex]=sublist1[listIndex1];
			listIndex1++;
			startIndex++;
		}
		
		while (listindex2<sublist2.length) {
			input[startIndex]=sublist2[listindex2];
			listindex2++;
			startIndex++;
		}
		
	
	}

	private static int[] getsubsetArray(int[] input, int startIndex, int endIndex) {
		// TODO Auto-generated method stub
		int[] subset=new int[endIndex+1-startIndex];
		for (int i = 0; i < subset.length; i++) {
			subset[i]=input[startIndex+i];
		}
		
		return subset;
	}

	private static void shellsort(int[] input) {
		int[] gaps = { 4, 2, 1 };
		int gap = 4;

		for (int i = 0; i < gaps.length; i++) {
			gap = gaps[i];
			int sortedEnd = gap-1;
			
			for (int j = gap; j < input.length; j++) {

				if (input[j] < input[sortedEnd]) {

					
					int startOfBackwardNavigation=j;//87654321   start=4 end =8
					int endOfBackwardNavigation=startOfBackwardNavigation-gap;
					int holedata = input[startOfBackwardNavigation];
					
					while (endOfBackwardNavigation >= 0 
							&& holedata < input[endOfBackwardNavigation]) {
						input[startOfBackwardNavigation] = input[endOfBackwardNavigation];
						startOfBackwardNavigation = endOfBackwardNavigation;
						endOfBackwardNavigation=endOfBackwardNavigation-gap;
					}

					input[startOfBackwardNavigation]=holedata;
				}

				sortedEnd = sortedEnd + 1;
			}
			System.out.println("After pass "+i);
			printArray(input);
			
		}
		
		printArray(input);

	}

	private static void insertionsort(int[] input) {

		int sortedEnd = 0;// assuming element1 is sorted

		for (int i = 1; i < input.length; i++) {

			if (input[i] < input[sortedEnd]) {

				// shift until proper postion is found
				int holedata = input[i];
				int backwardNavigation = i - 1;
				while (backwardNavigation >= 0 && holedata < input[backwardNavigation]) {
					input[backwardNavigation + 1] = input[backwardNavigation];
					backwardNavigation--;
				}

				// found index for insertion
				input[backwardNavigation + 1] = holedata;
			}

			sortedEnd++;// whether swap happened or not
		}

		printArray(input);

	}

	private static void selectionsort(int[] input) {
		// TODO Auto-generated method stub

		for (int i = 0; i < input.length; i++) {

			int minIndex = i;
			for (int j = i; j < input.length; j++) {

				if (input[minIndex] > input[j]) {
					minIndex = j;
				}
				swap(input, minIndex, i);

			}

		}

		printArray(input);
	}

	private static void bubblesort(int[] input) {
		// TODO Auto-generated method stub

		for (int i = 0; i < input.length; i++) {

			for (int j = 0; j < input.length - i - 1; j++) {

				if (input[j] > input[j + 1]) {
					swap(input, j, j + 1);
				}
			}
		}

		printArray(input);

	}

	private static void printArray(int[] input) {
		// TODO Auto-generated method stub
		for (int i = 0; i < input.length; i++) {
			System.out.print(input[i]+" ");
		}
		System.out.println();
	}

	private static void swap(int[] input, int i, int j) {
		// TODO Auto-generated method stub
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

}
