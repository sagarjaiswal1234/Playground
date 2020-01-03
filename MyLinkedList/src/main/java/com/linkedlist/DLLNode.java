package com.linkedlist;

public class DLLNode {

	String data;
	DLLNode nextRef;
	DLLNode prevRef;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public DLLNode getNextRef() {
		return nextRef;
	}
	public void setNextRef(DLLNode nextRef) {
		this.nextRef = nextRef;
	}
	public DLLNode getPrevRef() {
		return prevRef;
	}
	public void setPrevRef(DLLNode prevRef) {
		this.prevRef = prevRef;
	}
	
	
}
