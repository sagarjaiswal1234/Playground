package com.hashtable;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable hashtble=new HashTable();
		
		
		hashtble.put(1,12);
		hashtble.put(3, 33);
		hashtble.put(12, 33);
		hashtble.put(15, 90);
		hashtble.put(16, 78);
		hashtble.put(90, 78);
		System.out.println(hashtble.get(3));
		System.out.println(hashtble);
		System.out.println(hashtble.getSize());
	}

}
