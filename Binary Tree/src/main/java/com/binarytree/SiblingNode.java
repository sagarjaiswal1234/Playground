package com.binarytree;



public class SiblingNode extends Node{//for avoiding creation of tree

	SiblingNode nextSibling;
	
	public SiblingNode getNextSibling() {
		return nextSibling;
	}

	public void setNextSibling(SiblingNode nextSibling) {
		this.nextSibling = nextSibling;
	}

	public SiblingNode(Integer data) {
		this.data=data;
	}
	
	public SiblingNode(Integer data, SiblingNode left, SiblingNode right) {
		this.data=data;
		this.left=left;
		this.right=right;
	}
	
	public SiblingNode() {
		super();
		// TODO Auto-generated constructor stub
	}


	Integer data;
	SiblingNode left;
	SiblingNode right;
	
	

	
	public SiblingNode getLeft() {
		return left;
	}

	public void setLeft(SiblingNode left) {
		this.left = left;
	}

	public SiblingNode getRight() {
		return right;
	}

	public void setRight(SiblingNode right) {
		this.right = right;
	}

	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getData().toString();
	}
}
