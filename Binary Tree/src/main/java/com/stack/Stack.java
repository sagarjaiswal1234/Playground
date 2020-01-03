package com.stack;

public class Stack {

	Node top=null;

	public void push(String data) {
			Node temp = top;
			top = new Node();
			top.setData(data);
			top.setNextRef(temp);
		
	}

	public String pop() {

		if (!isEmpty()) {
			Node temp = top;
			top = top.getNextRef();
			return temp.getData();	
		} else {
			return "Stack is empty";
		}
	}

	public boolean isEmpty() {
		if (top == null) {
			return true;
		} else {
			return false;
		}
	}
	
	public String peek() {
		if (isEmpty()) {
			return "Stack is empty";
		} else {
			return top.getData();
		}
	}

	public String toString() {
		Node current=top;
		String output="[";
		while (current!=null) {
			output=output+current.getData()+",";
			current=current.getNextRef();
		}
		
		if (output.contains(",")) {
			output=output.substring(0, output.length()-1);
				
		}
		
		output=output+"]";
		
		return output;
	}
	
	
}
