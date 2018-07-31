package com.binarytree;

public class NodeString implements Cloneable{

	String data;
	NodeString left;
	NodeString right;

	
	public NodeString(String data) {
		super();
		this.data = data;
		
	}
	
	public NodeString(String data, NodeString left, NodeString right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		NodeString cloned=(NodeString)super.clone();
		if (left!=null) {
			cloned.setLeft((NodeString)left.clone());
		}else {
			cloned.setLeft(null);
		}
		if (right!=null) {
			cloned.setRight((NodeString)right.clone());
		} else {
			cloned.setRight(null);
		}
		return cloned;
	}
	public NodeString() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public NodeString getLeft() {
		return left;
	}
	public void setLeft(NodeString left) {
		this.left = left;
	}
	public NodeString getRight() {
		return right;
	}
	public void setRight(NodeString right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getData().toString();
	}
	
}
