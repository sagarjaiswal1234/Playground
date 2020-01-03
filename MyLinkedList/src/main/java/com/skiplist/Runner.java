package com.skiplist;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SkipList skplst=new SkipList();
		skplst.addElement(5);
		skplst.addElement(6);
		skplst.addElement(9);
		skplst.addElement(7);
		skplst.addElement(2);
		
		skplst.printSkipList();
		
	}

}
