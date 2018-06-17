package com.heap;

public class Heap {

	public int[] elements;
	public boolean heapType = MAX_HEAP;
	private int count;

	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setElements(int[] elements) {
		this.elements = elements;
	}

	public Heap(int size) {
		this.elements = new int[size];
		count = 0;
		LAST_INDEX=count-1;
	}
	
	public Heap(int[] elements) {
		this.elements = elements;
		count = elements.length;
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

		if (left != -1 && elements[startOfPerolateDown] < elements[left]) {
			maxElementIndex = left;
		}

		if (right != -1 && elements[maxElementIndex] < elements[right]) {
			maxElementIndex = right;
		}

		if (maxElementIndex != startOfPerolateDown) {
			swap(startOfPerolateDown, maxElementIndex);
			percolateDown(maxElementIndex);// since maxElementIndex is swapped and points
			// to latest element whose subtree needs heapfication

		}

		
	}

	public void swap(int index1, int index2) {
		int tempData = elements[index1];
		elements[index1] = elements[index2];
		elements[index2] = tempData;

	}

	public void percolateUpRecursive(int startOfPercolateUp) {
		// To Do check the usecase
		int parentIndex = getParent(startOfPercolateUp);

		if (parentIndex != -1 && elements[startOfPercolateUp] > elements[parentIndex]) {
			swap(startOfPercolateUp, parentIndex);
			percolateUpRecursive(parentIndex);
		}

	}

	public void percolateupIterative(int startOfPercolateUp) {
		int currentPosition = startOfPercolateUp;
		int parentIndex = getParent(startOfPercolateUp);

		while (parentIndex != -1 && elements[currentPosition] > elements[parentIndex]) {
			swap(startOfPercolateUp, parentIndex);
			currentPosition = parentIndex;
			parentIndex = getParent(currentPosition);
		}

	}

	public void insert(int element) {

		if (elements.length == count) {
			System.out.println("Size exceeded");
			resizeHeap();
		}
		elements[count] = element;
		count++;
		percolateUpRecursive(count-1);
		
	}

	private void resizeHeap() {
		// TODO Auto-generated method stub
		int[] resizedArray = new int[elements.length * 2];

		for (int i = 0; i < elements.length; i++) {
			resizedArray[i] = elements[i];
		}
		elements = resizedArray;
		
	}

	public int[] getElements() {
		return elements;
	}

	public void delete(int dataToBeDeleted) {
		int indexOfElementtoBeDeleted = findPositionDataToBeDeleted(dataToBeDeleted, 0);

		if (indexOfElementtoBeDeleted < 0) {
			return;// throw error data not found
		}
		swap(indexOfElementtoBeDeleted, count - 1);
		count--;
		percolateDown(indexOfElementtoBeDeleted);

		/*
		 * slower algorithm elements[index]=Integer.MAX_VALUE;//for maxHeapify
		 * percolateUpRecursive(index); swap(0, count-1); count--; percolateDown(0);
		 */

	}
	
	

	private int findPositionDataToBeDeleted(int dataToBeDeleted, int startOfSearch) {

		if (startOfSearch >= count || startOfSearch < 0) {
			return NOT_FOUND;
		}

		if (dataToBeDeleted == elements[startOfSearch]) {
			return startOfSearch;
		} else if (dataToBeDeleted < elements[startOfSearch]) {// for Max Heap
			int foundInLeftSubtree = findPositionDataToBeDeleted(dataToBeDeleted, getLeftChild(startOfSearch));
			int foundInRightSubtree = findPositionDataToBeDeleted(dataToBeDeleted, getRightChild(startOfSearch));

			if (foundInLeftSubtree != NOT_FOUND) {
				return foundInLeftSubtree;
			}
			if (foundInRightSubtree != NOT_FOUND) {
				return foundInRightSubtree;
			}

		}

		return NOT_FOUND;
	}

	
	public int getMinFromMaxHeap() {
		// TODO Auto-generated method stub
		int min=Integer.MAX_VALUE;
		for (int i = (count-1)/2; i < count; i++) {
			if (elements[i]< min) {
				min=elements[i];
			}
		}
		return min;
	}
	
	
	public static boolean MAX_HEAP = true;
	public static boolean MIN_HEAP = false;
	public static int NOT_FOUND = -1;
	public static int LAST_INDEX;



	public int removeMax() {
		// TODO Auto-generated method stub
		int max=elements[0];
		swap(0, count-1);
		count--;
		percolateDown(0);
		return max;
	}
	
	public int getMax() {
		// TODO Auto-generated method stub
		return elements[0];
	}



	

}
