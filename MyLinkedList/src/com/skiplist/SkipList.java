package com.skiplist;

import java.util.Random;

public class SkipList {

	Node head;
	Node tail;
	int maxLevel = 0;
	Random random=new Random();

	public SkipList() {
		// TODO Auto-generated constructor stub

		head = new Node();
		head.setData(Integer.MIN_VALUE);

		tail = new Node();
		tail.setData(Integer.MAX_VALUE);

		head.setNext(tail);
		tail.setPrev(head);

	}

	public boolean addElement(Integer data) {

		Node nodePostionForAddition = findNodePostion(data);
		Node nodeRefOfLowerLevel = null;
		nodeRefOfLowerLevel = addNodefromGivenNode(data, nodePostionForAddition);
		
		boolean tossIsHead = random.nextDouble()<0.5;
		System.out.println("First toss for"+data+tossIsHead);
		int nodeLevel = 0;
		while (tossIsHead) {
			
			nodeLevel++;
			// introduce a new blank level
			if (nodeLevel > maxLevel) {				
				addBlankLevel();
				maxLevel++;
			}

			while (nodePostionForAddition.getUp()==null) {
				nodePostionForAddition=nodePostionForAddition.getPrev();
			}
			nodePostionForAddition=nodePostionForAddition.getUp();
			
			Node addedNode=addNodefromGivenNode(data, nodePostionForAddition);
			addedNode.setDown(nodeRefOfLowerLevel);
			nodeRefOfLowerLevel.setUp(addedNode);
			
			nodeRefOfLowerLevel=addedNode;
			// toss now
			tossIsHead = random.nextDouble()<0.5;
			System.out.println("Next toss for"+data+tossIsHead);
			
		}

		return true;
	}

	private void addBlankLevel() {
		// TODO Auto-generated method stub
		Node newHead = new Node();
		newHead.setData(Integer.MIN_VALUE);

		Node newTail = new Node();
		newTail.setData(Integer.MAX_VALUE);

		newHead.setNext(newTail);
		newTail.setPrev(newHead);

		newHead.setDown(head);
		newTail.setDown(tail);
		head.setUp(newHead);
		tail.setUp(newTail);
		
		head = newHead;
		tail = newTail;

	}

	private boolean tossTheCoin() {
		// TODO Auto-generated method stub

		if (Math.random() < 0.5) {
			return false;
		} else {
			return true;
		}

	}

	private Node addNodefromGivenNode(Integer data, Node nodePostionForAddition) {

		Node newNode = new Node();
		newNode.setData(data);
		newNode.setNext(nodePostionForAddition.getNext());
		nodePostionForAddition.getNext().setPrev(newNode);
		nodePostionForAddition.setNext(newNode);
		newNode.setPrev(nodePostionForAddition);

		return newNode;
	}

	private Node findNodePostion(Integer data) {
		Node current = head;
		while (true) {

			System.out.println("before"+(data>current.getNext().getData()));
			
			while (current.getNext()!=null && current.getNext().getData()<data) {
				System.out.println(data<current.getNext().getData());
				current = current.getNext();
			}

			if (current.getDown() != null) {
				current = current.getDown();
			} else {
				break;
			}
		}

		System.out.println("CURRENT NODE IN FIND POSTION: "+current.getData());
		return current;
	}

	public void printSkipList() {
		
		int level=maxLevel;
		Node current=head;
		Node levelHead=head;
		while (level>-1) {
			
			while (current.getNext()!=null) {
				System.out.print(current.getData()+" ");
				current=current.getNext();
			}
			System.out.println("");
			current=levelHead.getDown();
			levelHead=levelHead.getDown();
			level--;
		}
		
		
	}
	
	
	
	
}
