package com.binarytree;

public class Node implements Cloneable{

	Integer data;
	Node left;
	Node right;

	
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
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Node cloned=(Node)super.clone();
		if (left!=null) {
			cloned.setLeft((Node)left.clone());
		}else {
			cloned.setLeft(null);
		}
		if (right!=null) {
			cloned.setRight((Node)right.clone());
		} else {
			cloned.setRight(null);
		}
		return cloned;
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
