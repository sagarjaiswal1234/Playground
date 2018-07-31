package com.linkedlist;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String [] array=new String("1,2,3,4,5,6,7,8,9,10,11,12,13,14").split(",");
		//array=new String("1,2,8,7").split(",");
		
		Node head=LinkedList.createLinkedListi(array);
		LinkedList.printLinkedList(head);
		Node orig=head;
		while (!head.getData().equalsIgnoreCase("9")) {
			head=head.getNextRef();
		}
		head.setNextRef(null);
		LinkedList.printLinkedList(orig);
		
	}

	
	
}
