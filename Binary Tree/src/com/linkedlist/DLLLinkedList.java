package com.linkedlist;

public class DLLLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String [] array=getStringArray();
		String data="6,6,6,6,7";
		String [] array=data.split(",");
		
		DLLNode head=createDLList(array);
		System.out.println("Before");
		printDLLIst(head);
		head=deleteMatchedValue(head,"6");
		System.out.println("After");
		printDLLIst(head);
		
	}

	private static DLLNode deleteMatchedValue(DLLNode head, String string) {
		
		
		
		while (head!=null && head.getData().equals(string)) {
			
			if (head.getNextRef()==null) {
				head=null;
				return null;
			}
				head.getNextRef().setPrevRef(null);
				head=head.getNextRef();
		}
		
		
		
		DLLNode prev=null;
		DLLNode next=head.getNextRef();
		DLLNode current=head;
		
		
		
		while (current!=null) {
			prev=current.getPrevRef();
			next=current.getNextRef();
			
			if (current.getData().equals(string)) {
				prev.setNextRef(next);
				if (next!=null) {
					next.setPrevRef(prev);	
				}
				
			}
			current=next;
		}
		
		return head;
	}

	private static void printDLLIst(DLLNode head) {
		DLLNode currentNode=head;
		while (currentNode!=null) {
			System.out.print(currentNode.getData());
			currentNode=currentNode.getNextRef();
		}
		
	}

	public static DLLNode createDLList(String[] array) {
		
		
		DLLNode head=new DLLNode();
		head.setData(array[0]);
		head.setPrevRef(null);
		DLLNode current=head;
		
		for (int i = 1; i < array.length; i++) {
			
			DLLNode newNode=new DLLNode();
			newNode.setData(array[i]);
			newNode.setPrevRef(current);
		
			current.setNextRef(newNode);
			current=current.getNextRef();
		}
		
		return head;
		
	}

	private static String[] getStringArray() {
String array[]=new String[10];
		
		for (int i = 0; i < 10; i++) {
			array[i]=new String(""+i);
		}
		
		return array;
	}

}
