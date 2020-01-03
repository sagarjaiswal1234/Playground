package com.heap;

import java.util.LinkedList;
import java.util.Queue;

public class PriorityHeap {

	public  PriorityObject [] priorityObjects;
	public boolean heapType = MAX_HEAP;
	private int count;

	

	public PriorityObject[] getPriorityObjects() {
		return priorityObjects;
	}

	public void setPriorityObjects(PriorityObject[] priorityObjects) {
		this.priorityObjects = priorityObjects;
	}

	public boolean isHeapType() {
		return heapType;
	}

	public void setHeapType(boolean heapType) {
		this.heapType = heapType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public PriorityHeap(int size) {
		this.priorityObjects = new PriorityObject[size];
		count = 0;
		LAST_INDEX=count-1;
	}
	
	public PriorityHeap(PriorityObject [] priorityObject) {
		this.priorityObjects = priorityObject;
		count = priorityObjects.length;
		LAST_INDEX=count-1;
		buildBinaryheap();
	}

	public void buildBinaryheap() {

		for (int i = (count - 1) / 2; i >= 0; i--) {
			percolateDown(i);
		}

	}

	public int getParent(int childIndex) {
		if (childIndex < 0 || childIndex >= count) {
			return -1;
		}
		return (childIndex - 1) / 2;
	}

	public int getLeftChild(int parentIndex) {

		int childIndex = parentIndex * 2 + 1;
		if (childIndex < 0 || childIndex >= count) {
			return -1;
		}
		return childIndex;
	}

	public int getRightChild(int parentIndex) {
		int childIndex = parentIndex * 2 + 2;
		if (childIndex < 0 || childIndex >= count) {
			return -1;
		}
		return childIndex;
	}

	public void percolateDown(int startOfPerolateDown) {
		int maxElementIndex = startOfPerolateDown;
		int left = getLeftChild(startOfPerolateDown);
		int right = getRightChild(startOfPerolateDown);

		if (left != -1 && priorityObjects[startOfPerolateDown].getPriority() < priorityObjects[left].getPriority()) {
			maxElementIndex = left;
		}

		if (right != -1 && priorityObjects[maxElementIndex].getPriority() < priorityObjects[right].getPriority()) {
			maxElementIndex = right;
		}

		if (maxElementIndex != startOfPerolateDown) {
			swap(startOfPerolateDown, maxElementIndex);
			percolateDown(maxElementIndex);// since maxElementIndex is swapped and points
			// to latest element whose subtree needs heapfication

		}

		
	}

	public void swap(int index1, int index2) {
		PriorityObject tempData = priorityObjects[index1];
		priorityObjects[index1] = priorityObjects[index2];
		priorityObjects[index2] = tempData;

	}

	public void percolateUpRecursive(int startOfPercolateUp) {
		// To Do check the usecase
		int parentIndex = getParent(startOfPercolateUp);

		if (parentIndex != -1 && priorityObjects[startOfPercolateUp].getPriority() > priorityObjects[parentIndex].getPriority()) {
			swap(startOfPercolateUp, parentIndex);
			percolateUpRecursive(parentIndex);
		}

	}

	public void percolateupIterative(int startOfPercolateUp) {
		int currentPosition = startOfPercolateUp;
		int parentIndex = getParent(startOfPercolateUp);

		while (parentIndex != -1 && priorityObjects[currentPosition].getPriority() > priorityObjects[parentIndex].getPriority()) {
			swap(startOfPercolateUp, parentIndex);
			currentPosition = parentIndex;
			parentIndex = getParent(currentPosition);
		}

	}

	public void insert(PriorityObject priorityObject) {

		if (priorityObjects.length == count) {
			resizeHeap();
		}
		priorityObjects[count] = priorityObject;
		count++;
		percolateUpRecursive(count-1);
		
	}

	private void resizeHeap() {
		// TODO Auto-generated method stub
		PriorityObject[] resizedArray = new PriorityObject[priorityObjects.length * 2];

		for (int i = 0; i < priorityObjects.length; i++) {
			resizedArray[i] = priorityObjects[i];
		}
		priorityObjects = resizedArray;
		
	}

	public PriorityObject[] getpriorityObjects() {
		return priorityObjects;
	}

	public void delete(Employee dataToBeDeleted) {
		int indexOfElementtoBeDeleted = findPositionDataToBeDeleted(dataToBeDeleted, 0);

		if (indexOfElementtoBeDeleted < 0) {
			return;// throw error data not found
		}
		swap(indexOfElementtoBeDeleted, count - 1);
		count--;
		percolateDown(indexOfElementtoBeDeleted);

		/*
		 * slower algorithm priorityObjects[index]=Integer.MAX_VALUE;//for maxHeapify
		 * percolateUpRecursive(index); swap(0, count-1); count--; percolateDown(0);
		 */

	}
	
	

	private int findPositionDataToBeDeleted(Employee dataToBeDeleted, int startOfSearch) {

		if (startOfSearch >= count || startOfSearch < 0) {
			return NOT_FOUND;
		}

		Queue<Integer> queueOfIndex = new LinkedList<Integer>();
		queueOfIndex.add(startOfSearch);

		while (!queueOfIndex.isEmpty()) {
			Integer currentIndex = queueOfIndex.poll();
			if (priorityObjects[currentIndex].getEmp().getName()
					.equalsIgnoreCase(
					dataToBeDeleted.getName())) {
				return currentIndex;
				
			}
			if (this.getLeftChild(currentIndex)!=-1) {
				queueOfIndex.add(this.getLeftChild(currentIndex));
			}
			if (this.getRightChild(currentIndex)!=-1) {
				queueOfIndex.add(this.getRightChild(currentIndex));
			}

		}

		return NOT_FOUND;
	}

	
	


	
	
	public static boolean MAX_HEAP = true;
	public static boolean MIN_HEAP = false;
	public static int NOT_FOUND = -1;
	public static int LAST_INDEX;



	public PriorityObject removeMax() {
		// TODO Auto-generated method stub
		PriorityObject max=priorityObjects[0];
		swap(0, count-1);
		count--;
		return max;
		
	}

	public PriorityObject getMax() {
		// TODO Auto-generated method stub
		return priorityObjects[0];
	}
	
	
}
