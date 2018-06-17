package com.binarytree;
import java.util.Stack;

import com.linkedlist.DLLNode;

public class Runner {

	
	public static void main(String args[]) {
		
		Node root=new Node(5, null, null);
		createBinaryTree(root);
		DLLNode DllHead=convertBinaryToDLL(root);
	
	}


	private static DLLNode convertBinaryToDLL(Node root) {
		Node current=root;
		DLLNode head=new DLLNode();
		Stack<Node> stack=new Stack<Node>();
		
		while (current.getLeft()!=null) {
			stack.push(root);
			root=root.getLeft();
		}
		head.setData(current.getData().toString());
		while (current.getRight()!=null) {
			stack.push(root);
			root=root.getRight();
		}
		return head;
		
		
	}


	private static void createBinaryTree(Node root) {
		
		addNode(root, 3);
		addNode(root, 7);
		addNode(root, 2);
		addNode(root, 4);
		addNode(root, 1);
		addNode(root, 8);
		addNode(root, 6);
		addNode(root, 11);
		addNode(root, 9);
	}

	private static void addNode(Node root, int data) {
		
		Node current=root;
		
		if (data<current.getData()) {
			
			if (current.getLeft()==null) {
				current.setLeft(new Node(data, null, null));
				System.out.println("left "+data);
			}else {
				addNode(root.getLeft(), data);
			}
			
		}else {
			
			if (current.getRight()==null) {
				current.setRight(new Node(data, null, null));
				System.out.println("right"+data);
			} else {
				addNode(root.getRight(), data);
			}
			
		}
		
	}
	
}
