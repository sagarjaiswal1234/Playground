package com.heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int[] elements= {6,17,13,1,4,5,2};
		int[] elements= {1,2,3,4,5,6,7};
		

		//Heap heap=new Heap(elements);
		
		Heap h=minMaxHeap(elements);
		
	}

	
	private static Heap minMaxHeap(int[] elements) {
		// TODO Auto-generated method stub
		MedianHeap med=new MedianHeap(elements);
		med.getAllElements();
		System.out.println("Median "+med.getMedian());
		System.out.println(med.removeMedian());
		System.out.println("Median "+med.getMedian());
		med.insert(90);
		System.out.println("Median "+med.getMedian());
		med.delete(90);
		System.out.println("Median "+med.getMedian());
		med.getAllElements();
		return null;
	}


	private static void maxkElements() {
		// TODO Auto-generated method stub
		
		int[] billionInput=new int[1000];
		for (int i = 0; i < billionInput.length; i++) {
			billionInput[i]=i;
		}
		billionInput[1]=19999;
		billionInput[90]=90000;
		
		int k=10;
		int[] inputthatCanBeHandled=new int[100];
		int[] maxElements=new int[k];
		inputthatCanBeHandled=getSubsetArray(billionInput,0,100);
		
		Heap h1=new Heap(inputthatCanBeHandled);
		
		maxElements=extractMaxElements(h1,k);
		h1=null;
		
		int start=100;
		while (start<billionInput.length) {
			int end=start+90;
			inputthatCanBeHandled=getSubsetArray(billionInput, start, end);
			
			for (int i = 0; i < maxElements.length; i++) {//maxElements.length=10
				inputthatCanBeHandled[90+i]=maxElements[i];
			}
			
			Heap heap=new Heap(inputthatCanBeHandled);
			maxElements=extractMaxElements(heap, k);
			
			start=start+90;
		}
		
		for (int i = 0; i < maxElements.length; i++) {
			System.out.println(maxElements[i]);
		}
		
	}


	private static int[] extractMaxElements(Heap h1, int k) {
		// TODO Auto-generated method stub
		int[] maxElements=new int[k];
		for (int i = 0; i < maxElements.length; i++) {
			maxElements[i]=h1.removeMax();
		}
		
		return maxElements;
	}


	private static int[] getSubsetArray(int[] billionInput, int start, int end) {
		// TODO Auto-generated method stub
		int[] subset=new int[100];
		int subsetIndexStart=0;
		for (int i = start; i < end; i++) {
			subset[subsetIndexStart]=billionInput[i];
			subsetIndexStart++;
		}		
		return subset;
	}


	private static ArrayList<Integer> getAllElementsGreaterThan_k(Heap heap, int k) {
		// TODO Auto-generated method stub
		ArrayList<Integer> listOfElemnentsLessThank=new ArrayList<>();
		int[] elements=heap.getElements();
		System.out.println();
		for (int i = 0; i < elements.length; i++) {
			System.out.print(elements[i]);
		}
		Stack<Integer> stack=new Stack<>();
		stack.push(0);
		
				
		
		if (elements[0]>k) {
			listOfElemnentsLessThank.add(elements[0]);
		}else {
			return null;
		}
		while (!stack.isEmpty()) {
			int currentindex=stack.pop();
			int leftIndex=heap.getLeftChild(currentindex);
			int rightIndex=heap.getRightChild(currentindex);
			
			if (rightIndex!=-1 && elements[rightIndex]> k ) {
				listOfElemnentsLessThank.add(elements[rightIndex]);
				stack.push(rightIndex);
			}
			if (leftIndex!=-1 && elements[leftIndex]>k) {
				listOfElemnentsLessThank.add(elements[leftIndex]);
				stack.push(leftIndex);
			}
				
		}
		
		
		
		return listOfElemnentsLessThank;
	}


	private static void heapsort(Heap heap) {
		// TODO Auto-generated method stub
		int count=heap.getCount();
				
		for (int i = 0; i < count; i++) {
			heap.removeMax();
			heap.percolateDown(0);
		}
		int [] elements=heap.getElements();
		
		for (int i = 0; i < count; i++) {
			System.out.print(elements[i]);
		}
	}


	public static void levelOrderTraversal(int[] elements,Heap heap) {

		Queue<Integer> queueOfIndex = new LinkedList<Integer>();
		queueOfIndex.add(0);

		while (!queueOfIndex.isEmpty()) {
			Integer currentIndex = queueOfIndex.poll();
			processNode(currentIndex,elements);
			if (heap.getLeftChild(currentIndex)!=-1) {
				queueOfIndex.add(heap.getLeftChild(currentIndex));
			}
			if (heap.getRightChild(currentIndex)!=-1) {
				queueOfIndex.add(heap.getRightChild(currentIndex));
			}

		}

	}


	private static void processNode(Integer currentIndex, int[] elements) {
		// TODO Auto-generated method stub
		System.out.print(elements[currentIndex]);
	}
	
}
