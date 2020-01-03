package com.hashtable;

public class Node {

	Integer key;
	Integer data;
	Node nextRef;
	
	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Node(int key, Integer value)
	{
		this.key=key;
		this.data=value;
	}
	
	public Integer getData() {
		return data;
	}
	public void setData(Integer data) {
		this.data = data;
	}
	public Node getNextRef() {
		return nextRef;
	}
	public void setNextRef(Node nextRef) {
		this.nextRef = nextRef;
	}
	
	
}
