package com.hashtable;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashTable hashtble = new HashTable();

		hashtble.put(1, 12);
		hashtble.put(3, 33);
		hashtble.put(12, 33);
		hashtble.put(15, 90);
		hashtble.put(16, 78);
		hashtble.put(90, 78);
		System.out.println(hashtble.get(3));
		System.out.println(hashtble);
		System.out.println(hashtble.getSize());
		int[] a = {};
		int[] b = {};
		
	}

	private static boolean checkTwoArrayHaveSameNumbers(int[] a, int[] b) {
		// TODO Auto-generated method stub
		HashTable h1 = new HashTable();
		for (int i = 0; i < a.length; i++) {
			Integer key = a[i];
			Integer data = h1.get(key);
			if (data == null) {
				h1.put(key, 1);
			} else {
				h1.put(key, data++);
			}
		}

		for (int i = 0; i < b.length; i++) {
			Integer bkey = b[i];
			Integer data = h1.get(bkey);

			if (data == null) {
				return false;
			} else if (data == 1) {
				h1.delete(bkey);
			} else {
				h1.put(bkey, --data);
			}

		}

		if (h1.getCountOfElements() == 0) {
			return true;
		}
		return false;
	}

}
