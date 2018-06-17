package com.heap;


public class StackUsingHeap {

	PriorityHeap heap;
	
	
	public StackUsingHeap(int maxSize) {
		heap=new PriorityHeap(maxSize);
		// TODO Auto-generated constructor stub
	}
	
	public void push(Employee emp) {
		PriorityObject obj=new PriorityObject(emp);
		heap.insert(obj);
		
	}

	public Employee pop() {

		if (heap.getCount()!=0) {
			
			return heap.removeMax().getEmp();	
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		if (heap.getCount()<=0) {
			return true;
		} else {
			return false;
		}
	}
	
	public Employee peek() {
		if (heap.getCount()<=0) {
			return null;//"Stack is empty";
		} else {
			return heap.getMax().getEmp();
		}
	}
}
