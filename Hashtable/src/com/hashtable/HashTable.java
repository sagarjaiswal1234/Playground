package com.hashtable;

public class HashTable {

	private Node[] hashTable;
	private int size = 16;
	private double loadfactor = 0.75;
	private int countOfElements = 0;

	public HashTable() {
		super();
		hashTable = new Node[size];
	}

	public HashTable(int size) {
		hashTable = new Node[size];
		this.size = size;
	}

	public void put(Integer key, Integer value) {

		Integer hashindex = gethashedKey(key);
		Node list = hashTable[hashindex];
		Node newNode = new Node(key, value);

		if (list == null) {
			hashTable[hashindex] = newNode;
			countOfElements++;
			if ((countOfElements / (size * 1.0)) > loadfactor) {
				rehash();
			}

		} else {

			Node prev = null;
			while (list != null) {
				if (list.getKey() == key.intValue()) {// appears incorrect but take example of employee and equals
														// method
					list.setData(value);
					break;
				}
				prev = list;
				list = list.getNextRef();
			}

			if (list == null) {
				prev.setNextRef(newNode);
				countOfElements++;
				if ((countOfElements / (size * 1.0)) > loadfactor) {
					rehash();
				}
			}

		}
	}

	private void rehash() {
		// TODO Auto-generated method stub
		int newSize = size * 2;
		Node[] newHashtable = new Node[newSize];

		for (int i = 0; i < hashTable.length; i++) {
			Node oldList = hashTable[i];
			while (oldList != null) {
				Integer key = oldList.getKey();
				Integer value = oldList.getData();
				int hashIndex = gethashedKey(key, newSize);

				Node newList = newHashtable[hashIndex];
				Node newNode = new Node(key, value);
				if (newList == null) {
					newHashtable[hashIndex]=newNode;
				} else {

					Node prev = null;
					while (newList != null) {// adding to end
						prev = newList;
						newList = newList.getNextRef();
					}

					prev.setNextRef(newNode);

				}

				oldList = oldList.getNextRef();
			}

		}

		hashTable = newHashtable;
		size = newSize;

	}

	public String toString() {

		String output = "[";

		for (int i = 0; i < hashTable.length; i++) {
			Node list = hashTable[i];
			while (list != null) {
				Integer key = list.getKey();
				Integer value = list.getData();

				output = output + "{" + key + "," + value + "}";
				list = list.getNextRef();
			}

		}

		output = output + "]";
		return output;

	}

	public Integer get(Integer key) {
		Integer hashIndex = gethashedKey(key);
		Integer data = null;
		Node list = hashTable[hashIndex];
		if (list == null) {
			return null;
		}

		while (list != null) {
			if (list.getKey() == key.intValue()) {
				data = list.getData();
				break;
			}
			list = list.getNextRef();
		}
		return data;

	}

	private Integer gethashedKey(Integer key) {
		return key % size;
	}

	private Integer gethashedKey(Integer key, int size) {
		return key % size;
	}

	public boolean delete(Integer key) {

		boolean isFound = false;

		Integer hashindex = gethashedKey(key);
		Node list = hashTable[hashindex];

		if (list == null) {
			return isFound;
		}

		Node prev = null;
		while (list != null) {

			if (list.getKey() == key.intValue()) {// appears incorrect but take example of employee and equals
													// method
				if (prev == null) {
					hashTable[hashindex]=null;
				} else {
					prev.setNextRef(list.getNextRef());
				}
				countOfElements--;
				isFound = true;
				break;

			}
			prev = list;
			list = list.getNextRef();
		}
		return isFound;
	}

	public Node[] getHashTable() {
		return hashTable;
	}

	public void setHashTable(Node[] hashTable) {
		this.hashTable = hashTable;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getLoadfactor() {
		return loadfactor;
	}

	public void setLoadfactor(double loadfactor) {
		this.loadfactor = loadfactor;
	}

	public int getCountOfElements() {
		return countOfElements;
	}

	public void setCountOfElements(int countOfElements) {
		this.countOfElements = countOfElements;
	}

}
