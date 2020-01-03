package com.avl;

public class Node {

	Integer data;
	Node left;
	Node right;
	Integer height;
	
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Node(Integer data) {
		super();
		this.data = data;
		
	}
	
	public Node(Integer data, Node left, Node right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Node() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getData().toString();
	}
	
}
