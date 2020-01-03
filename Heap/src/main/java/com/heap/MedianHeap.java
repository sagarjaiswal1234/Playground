package com.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MedianHeap {

	public int[] elements;
	private int count;
	Heap maxHeap;
	ArrayList<Integer> minHeap;
	
	
	public MedianHeap(int[] sortedElements) {
	
		this.elements=sortedElements;
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i]);
		}
		System.out.println();
		int[] smallHalf=null;
		int[] bigHalf=null;
		if (elements.length%2==0) {
			smallHalf=getSubsetArray(elements, 0, sortedElements.length/2);
			maxHeap=new Heap(smallHalf);
		}else {
			 smallHalf=getSubsetArray(elements, 0, (sortedElements.length/2)+1);
			maxHeap=new Heap(smallHalf);
		}
		minHeap=new ArrayList<>();
		bigHalf=getSubsetArray(elements, smallHalf.length, elements.length);
		for (int i = 0; i < bigHalf.length; i++) {
			minHeap.add(bigHalf[i]);
		}
		System.out.println(minHeap);
	}

	
	private static int[] getSubsetArray(int[] billionInput, int start, int end) {
		// TODO Auto-generated method stub
		int[] subset=new int[end];//since array satrts from 0
		int subsetIndexStart=0;
		for (int i = start; i < end; i++) {
			subset[subsetIndexStart]=billionInput[i];
			subsetIndexStart++;
		}		
		return subset;
	}

	public int getMedian() {
		// TODO Auto-generated method stub
		if (maxHeap.getCount()==minHeap.size()) {
			int max=maxHeap.getMax();
			int med=minHeap.get(0);
			return (max+med)/2;
		}else {
			return maxHeap.getMax();
		}
		
	}


	public int removeMedian() {
		int median=-1;
		// TODO Auto-generated method stub
		if (maxHeap.getCount()==minHeap.size()) {
			median=maxHeap.removeMax();
			maxHeap.insert(minHeap.remove(0));
		}else {
			median=maxHeap.removeMax();
		}
		
		return median;
	}


	public void insert(int newElement) {
		
		if (newElement<maxHeap.getMax()) {
			maxHeap.insert(newElement);
		}else {
			addInMinHeap(newElement);
		}
		
		if (minHeap.size()>maxHeap.getCount()) {
			balanceHeaps();
		}
	}


	private void balanceHeaps() {
		// TODO Auto-generated method stub
		maxHeap.insert(minHeap.get(0));
		
	}


	private void addInMinHeap(int newElement) {
		// TODO Auto-generated method stub
		int index=0;
		for (int i : minHeap) {
			if (i>newElement) {
				break;
			}
			index++;
		}
		minHeap.add(index, newElement);
	}


	public void delete(int i) {
		// TODO Auto-generated method stub
		removeMedian();
		
	}


	public void getAllElements() {
		// TODO Auto-generated method stub
		elements=maxHeap.getElements();
		
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i]);
		}
		System.out.println();
		System.out.println(minHeap);
	}
	
}
