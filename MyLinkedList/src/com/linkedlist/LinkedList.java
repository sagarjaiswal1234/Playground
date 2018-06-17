package com.linkedlist;

import java.util.ArrayList;
import java.util.Iterator;

public class LinkedList {

	static int countFromLast = 0;
	static int interval = 0;

	public static void main(String[] args) {

		// List with 10 nodes
		String[] array = new String("1,2,3,4,5,6,7,8,9,10,11,12,13,14").split(",");
		array = new String("1,2,8,7,5,6,0,99,87,66,44,11,0,90").split(",");
		array=new String("2,9,1,0,3,2,1").split(",");
		
		
	}

	private static Node partitionByK(Node head1,Integer value) {
		
		Node kprev=null;
		Node knext=null;
		Node temp=null;
		Node kNode=head1;
		Node kPrevHead=null;
		Node kNextHead=null;
		
		while (kNode!=null) {
			
			temp=kNode.getNextRef();//removing elemnt
			kNode.setNextRef(null);
			if (nodeValueLessThan(kNode,value)) {
		
				if (kprev==null) {
					kprev=kNode;
					kPrevHead=kNode;
				}else {
					kprev.setNextRef(kNode);
					kprev=kNode;
				}
				
			} else {

				if (knext==null) {
					kNextHead=kNode;
					knext=kNode;
				}else {
					knext.setNextRef(kNode);
					knext=kNode;
				}
			}
			kNode=temp;
			
		}
		
		kprev.setNextRef(kNextHead);
		return kPrevHead;
		
	}

	private static boolean nodeValueLessThan(Node kNode, Integer value) {
		
		if (new Integer(kNode.getData())<value) {
			return true;
		}else {
			return false;
		}
		
	}

	private static Node additionWithoutReversing(Node head1, Node head2) {
		
		Node list1=head1;
		Node list2=head2;
		Node result=null;
		int length1=0;
		int lengeth2=0;
		Node newHead=null;
		
		ArrayList<Object> array=new ArrayList<Object>();
		Integer previousCarry=0;
		Node lowerOrder=null;
		Integer newCarry;
		
		while (list1!=null) {
			list1=list1.getNextRef();
			length1++;
		}
		while (list2!=null) {
			list2=list2.getNextRef();
			lengeth2++;
		}
		list1=head1;
		list2=head2;
		int diff=Math.abs(length1-lengeth2);
		
		if (length1==lengeth2) {
			
			array=addTwoNodes(list1,list2);
			lowerOrder=(Node)array.get(0);
			previousCarry=(Integer)array.get(1);
			if (previousCarry!=0) {
				result=new Node();
				result.setData(previousCarry.toString());
				result.setNextRef(lowerOrder);
			}else {
				result=lowerOrder;
			}
			newHead=result;
			
			 
		} else if (length1>lengeth2) {
			
			for (int i = 1; i < diff; i++) {
				list1=list1.getNextRef();
			}
			
			array=addTwoNodes(list1.getNextRef(),list2);
			lowerOrder=(Node)array.get(0);
			previousCarry=(Integer)array.get(1);
			
			newCarry=propagateCarry(head1,list1,previousCarry);
			
			if (newCarry!=0) {
				Node carryNode=new Node();
				carryNode.setData(newCarry.toString());
				carryNode.setNextRef(head1);
				head1=carryNode;
			}
			newHead=head1;
			
		}else {
			
			for (int i = 1; i < diff; i++) {
				list2=list2.getNextRef();
			}
			
			array=addTwoNodes(list2.getNextRef(),list1);
			lowerOrder=(Node)array.get(0);
			previousCarry=(Integer)array.get(1);
			
			newCarry=propagateCarry(head2,list2,previousCarry);
			
			if (newCarry!=0) {
				Node carryNode=new Node();
				carryNode.setData(newCarry.toString());
				carryNode.setNextRef(head2);
				head2=carryNode;
			}
			
			newHead=head2;
			
		}

		
		
		
		return newHead;
	}

	private static Integer propagateCarry(Node head1, Node list1, Integer previousCarry) {
		// TODO Auto-generated method stub
		Integer newCarry=0;
		if (head1==list1) {
			Integer addition=new Integer(head1.getData())+previousCarry;
			head1.setData(new Integer(addition%10).toString());
			return addition/10;
		}
		newCarry=propagateCarry(head1.getNextRef(), list1, previousCarry);
		
		if (newCarry!=0) {
			Integer addition=new Integer(head1.getData())+newCarry;
			head1.setData(new Integer(addition%10).toString());
			return addition/10;
		} else {
			return 0;
		}
		
		
	}

	private static ArrayList<Object> addTwoNodes(Node list1, Node list2) {
		// TODO Auto-generated method stub
		ArrayList<Object> array=new ArrayList<Object>();
		ArrayList<Object> previousArray=new ArrayList<Object>();
		
		Integer previousCarry=0;
		Node lowerOrder=null;
		Integer newCarry;
		
		if (list1==null && list2==null) {
			return null;
		}
		
		previousArray=addTwoNodes(list1.getNextRef(), list2.getNextRef());
		if (previousArray!=null) {
			lowerOrder=(Node)previousArray.get(0);
			previousCarry=(Integer) previousArray.get(1);	
		}
		
		Integer addition=new Integer(list1.getData())+new Integer(list2.getData())+previousCarry;
		list1.setData(new Integer(addition%10).toString());
		list1.setNextRef(lowerOrder);
		newCarry=addition/10;
		array.add(0, list1);
		array.add(1,newCarry);
		
		return array;
		
		
	}

	private static Node addreversedLinkedist(Node head1, Node head2) {
		
		Integer carry=0;
		Node list1=head1;
		Node list2=head2;
		Node prevList2=null;
		
		while (list1!=null && list2!=null) {
			Integer addition=new Integer(list1.getData())+new Integer(list2.getData())+carry;
			list2.setData(new Integer(addition%10).toString());
			carry=new Integer(addition/10);
			
			list1=list1.getNextRef();
			prevList2=list2;
			list2=list2.getNextRef();
			
		}
		
		if (list1==null && list2==null && carry!=0) {
			Node carryNode=new Node();
			carryNode.setData(carry.toString());
			prevList2.setNextRef(carryNode);
		} else if (list1==null) {
			
			addIntegerToDecimalList(list2,carry);
			
		} else {
			addIntegerToDecimalList(list1,carry);
			prevList2.setNextRef(list1);
		}
		
		return head2;
	}

	private static void addIntegerToDecimalList(Node list2, Integer carry) {
		// TODO Auto-generated method stub
		while (carry!=0 ) {
			Integer addition=new Integer(list2.getData())+carry;
			list2.setData(new Integer(addition%10).toString());
			carry=new Integer(addition/10);
			
			if (list2.getNextRef()==null && carry!=0) {//lastNode
				Node carryNode=new Node();
				carryNode.setData(carry.toString());
				list2.setNextRef(carryNode);
				break;
			}
			
			list2=list2.getNextRef();
		}
	}

	private static Node rotateByk(Node head, int k) {
		//12345 k=2 45123
		
		Node oldHead=head;
		Node newHead=oldHead;
		Node ptr1=head;
		Node ptr2=head;
		
		if (head==null || head.getNextRef()==null) {
			return head;
		}
		
		
		for (int j = 0; j < k; j++) {//so that we have ptr1 1 place before note j=0
			ptr2=ptr2.getNextRef();
			if (ptr2==null) {
				ptr2=head;
			}
		}
		
		if (ptr1==ptr2) {
			return head;
		}
		
		while ( ptr2.getNextRef()!=null) {
			ptr1=ptr1.getNextRef();
			ptr2=ptr2.getNextRef();
		}
		newHead=ptr1.getNextRef();
		ptr1.setNextRef(null);//end of list
		
		ptr2.setNextRef(oldHead);
	
		return newHead;
	}

	private static Node insertionsort(Node head) {

		Node sortedHead = head;
		Node sortedTail = head;
		Node unsorted = head.getNextRef();

		while (unsorted != null) {

			// compare with last element in sortedList
			if (new Integer(sortedTail.getData()).intValue() < new Integer(unsorted.getData()).intValue()) {
				unsorted = unsorted.getNextRef();
				sortedTail = sortedTail.getNextRef();
			} else {

				// check from beginning
				// remove unsorted 1st element

				Node removeRef = unsorted;
				unsorted = unsorted.getNextRef();
				removeRef.setNextRef(null);
				sortedTail.setNextRef(null);// so that element is added on the sorted part only

				Node newHead=addElementTosortedList(sortedHead, removeRef);
				sortedHead=newHead;
				sortedTail.setNextRef(unsorted);
				printLinkedList(sortedHead);
			}

		}

		return sortedHead;
	}

	private static Node addElementTosortedList(Node head, Node nodeToBeAdded) {

		Node prev = null;
		Node sortedHead=head;
		Node current = head;
		while (current != null) {
			if (new Integer(current.getData()).intValue() > new Integer(nodeToBeAdded.getData()).intValue()) {// add<current
				break;
			}
			prev = current;
			current = current.getNextRef();
		}

		nodeToBeAdded.setNextRef(current);
		if (prev != null) {
			prev.setNextRef(nodeToBeAdded);	
		}else {
			sortedHead=nodeToBeAdded;
		}

		printLinkedList(sortedHead);
		return sortedHead;
	}

	private static Node reOrderFirstLastCouple(Node head) {
		Node current = head;
		Node slwptr = head;
		Node fstptr = head;

		while (fstptr != null && fstptr.getNextRef() != null) {
			slwptr = slwptr.getNextRef();
			fstptr = fstptr.getNextRef().getNextRef();
		}
		Node reversed = reverseLL(slwptr);
		System.out.println("reversed ");
		printLinkedList(reversed);
		head = concatToggled(head, reversed);

		return head;
	}

	private static Node concatToggled(Node list11, Node list22) {
		Node head = list11;
		Node result = list11;
		Node list1 = list11;
		Node list2 = list22;

		if (list1 == list2) {
			return list1;
		}

		boolean toggle = true;
		while (list1 != null && list2 != null) {

			if (toggle) {
				Node temp = list1.getNextRef();
				result.setNextRef(list2);
				list1 = temp;
				result = list2;
				list2 = list2.getNextRef();
				toggle = !toggle;
			} else {
				Node temp = list2.getNextRef();
				result.setNextRef(list1);
				list2 = temp;
				result = list1;
				list1 = list1.getNextRef();
				toggle = !toggle;
			}

		}
		if (list1 == null) {
			result.setNextRef(list2);
		} else {
			result.setNextRef(list2);
		}
		return head;
	}

	private static Node findModularNode(Node head, int k) {
		// 19 length 19%3=1
		int length = 0;
		Node modular = null;
		while (head != null) {
			if (length % k == 0) {
				modular = head;
			}
			length++;
			head = head.getNextRef();
		}

		return modular;
	}

	private static Node reversedInBlocksRecursive(Node head, int k) {
		// TODO Auto-generated method stub
		Node current = head;
		Node prev = null;
		for (int j = 0; j < k && current != null; j++) {
			Node temp = current.getNextRef();
			current.setNextRef(prev);
			prev = current;
			current = temp;
		}

		if (current != null) {
			head.setNextRef(reversedInBlocksRecursive(current, k));
		}
		head = prev;

		return head;
	}

	private static Node reverseInBlocks(Node head, int k) {

		Node prev = null;

		Node current = head;
		Node prevTail = null;
		Node newTail = null;

		while (current != null) {

			prev = null;
			newTail = current;
			for (int i = 0; i < k && current != null; i++) {
				Node temp = current.getNextRef();
				current.setNextRef(prev);
				prev = current;
				current = temp;
			}

			if (prevTail == null) {
				head = prev;
			} else {
				prevTail.setNextRef(prev);
			}
			prevTail = newTail;
		}

		return head;

	}

	private static Node exchangeAdjascentElemntsInLL(Node head) {

		Node first = head;
		Node second = first.getNextRef();
		Node oldSecond = first;
		Node newHead = null;
		while (first != null && first.getNextRef() != null) {

			second = first.getNextRef();

			// swap
			Node third = second.getNextRef();
			second.setNextRef(first);
			first.setNextRef(third);
			if (head != first) {
				oldSecond.setNextRef(second);// old second connects to newFirst
			} else {
				newHead = second;
			}
			oldSecond = first;
			first = third;
			printLinkedList(second);

		}
		System.out.println(head.getData());
		return newHead;
	}

	private static boolean checkifListIsPalindrome(Node head) {

		Node slwptr = head;
		Node fstptr = head;
		Node startOfFirstList = head;
		Node startOfSecondList = null;

		while (fstptr != null && fstptr.getNextRef() != null) {
			slwptr = slwptr.getNextRef();
			fstptr = fstptr.getNextRef().getNextRef();
		}

		// if odd start 1234 if if even 12345 skip elemnt 3
		if (fstptr != null) {
			startOfSecondList = slwptr.getNextRef();
		}
		startOfSecondList = slwptr;

		startOfSecondList = reverseLL(startOfSecondList);
		printLinkedList(startOfSecondList);
		while (startOfSecondList != null && startOfFirstList.getData().equals(startOfSecondList.getData())) {
			startOfFirstList = startOfFirstList.getNextRef();
			startOfSecondList = startOfSecondList.getNextRef();
		}

		if (startOfSecondList == null) {
			return true;
		}

		return false;
	}

	private static Node reverseLinkedListInPairs(Node head, int window) {
		if (head == null) {
			return null;
		}
		int counter = 1;
		Node start = head;
		Node slwptr = head;
		Node fstptr = head;

		// starting point for fstptr
		while (fstptr != null && counter < window) {
			fstptr = fstptr.getNextRef();
			counter++;
		}
		if (counter < window) {
			return start;
		}

		counter = 1;
		int j = 0;
		while (fstptr != null) {

			while (counter <= window - 1) {
				String fstData = fstptr.getData();
				fstptr.setData(slwptr.getData());
				slwptr.setData(fstData);
				slwptr = slwptr.getNextRef();
				fstptr = fstptr.getNextRef();
				counter++;
			}

			for (int i = 1; i <= window - 1 && fstptr != null; i++) {
				slwptr = slwptr.getNextRef();
				fstptr = fstptr.getNextRef();
			}
			if (fstptr == null) {
				return start;
			}

			counter = 1;
		}

		return start;
	}

	private static Node reverseLL(Node head) {
		// TODO Auto-generated method stub

		if (head == null) {
			return null;
		} else {
			Node prev = null;

			Node current = head;
			do {
				Node temp = current.getNextRef();

				current.setNextRef(prev);

				prev = current;
				current = temp;
			} while (current != null);

			return prev;
		}

	}

	private static Node reverseLL_recursion(Node head) {
		Node current = head;
		if (current == null) {
			return null;
		} else if (current.getNextRef() == null) {
			return current;
		} else {

			Node newHead = reverseLL_recursion(current.getNextRef());
			current.getNextRef().setNextRef(current);
			current.setNextRef(null);
			return newHead;

		}

	}

	private static ArrayList<String> findLastNElements(Node current, int i) {

		ArrayList<String> output = new ArrayList<String>();
		if (current == null) {
			return output;
		} else {

			output.addAll(findLastNElements(current.getNextRef(), i));
			countFromLast++;
			if (countFromLast <= i) {
				output.add(current.getData());
				return output;
			} else {
				return output;
			}
		}
	}

	private static String findLastNthElemnet(Node current, int i) {
		// TODO Auto-generated method stub
		String output = "";
		if (current == null) {
			return null;
		} else {

			output = findLastNthElemnet(current.getNextRef(), i);
			countFromLast++;
			if (countFromLast == i) {
				return current.getData();
			} else {
				return output;
			}
		}

		// return output;
	}

	private static void insertElementAtPostion(Node head, int pos, String data) {

		int i = 1;

		// while (head!=null) {
		//
		//// if (i<=pos) {
		//// head=head.getNextRef();
		//// i++;
		//// continue;
		//// } else {
		//// Node newNode=new Node();
		//// newNode.setData(data);
		//// newNode.setNextRef(head.getNextRef());
		//// head.setNextRef(newNode);
		//// break;
		//// }
		//
		// }

		for (int j = 1; j < pos - 1; j++) {
			head = head.getNextRef();
		}

		Node newNode = new Node();
		newNode.setData(data);
		newNode.setNextRef(head.getNextRef());
		head.setNextRef(newNode);

	}

	private static String[] getStringArray() {
		// TODO Auto-generated method stub
		String array[] = new String[10];

		for (int i = 0; i < 10; i++) {
			array[i] = new String("" + i);
		}

		return array;
	}

	@Override
	public String toString() {

		return super.toString();
	}

	public static void printLinkedList(Node head) {

		Node currentNode = head;
		while (currentNode != null) {
			System.out.print(currentNode.getData() + " ");
			currentNode = currentNode.getNextRef();
		}
		System.out.println("");
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

}
