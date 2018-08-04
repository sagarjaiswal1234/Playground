package com.avl;

import com.binarytree.*;

public class Runner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node root = createAVLTree();
		int lowerLimit;
		int upperLimit;
		Node root2=removeNodesOutOfRange(root, 12, 20);
		
		inOrderRecursive(root2);
		
		
	}

	private static Node removeNodesOutOfRange(Node root, int lowerLimit, int upperLimit) {
		// TODO Auto-generated method stub
		
		if (root == null) {
			return null;
		}

		if (root.getData() < lowerLimit) {
			root.setLeft(null);
			root=root.getRight();
			root=removeNodesOutOfRange(root, lowerLimit, upperLimit);
			
		} else if (root.getData() > upperLimit) {
			root.setRight(null);
			root=root.getLeft();
			root=removeNodesOutOfRange(root, lowerLimit, upperLimit);

		} else {
			// lowerlimit<=data<==upperlimit
			Node left=removeNodesOutOfRange(root.getLeft(), lowerLimit, upperLimit);
			Node right=removeNodesOutOfRange(root.getRight(), lowerLimit, upperLimit);
			root.setLeft(left);
			root.setRight(right);
		}
		
		return root;
	}

	private static Node removeLeaves(Node root) {
		// TODO Auto-generated method stub
		if (root==null) {
			return null;
		}else if (root.getLeft()==null && root.getRight()==null) {
			return null;
		}else {
			Node left=removeLeaves(root.getLeft());
			Node right=removeLeaves(root.getRight());
			root.setLeft(left);
			root.setRight(right);
			return root;
		}
	}

	private static Node removeHalfNodes(Node root) {
		// TODO Auto-generated method stub
		if (root==null) {
			return null;
		}else if (root.getLeft()!=null && root.getRight()==null) {
			return null;
		} else if (root.getLeft()==null && root.getRight()!=null) {
			return null;
		}else {
			Node left=removeHalfNodes(root.getLeft());
			Node right=removeHalfNodes(root.getRight());
			root.setLeft(left);
			root.setRight(right);
			return root;
		}
	
	}

	private static int countOfNodesInRange(Node root, int lowerLimit, int upperLimit) {

		// PostOrder
		if (root == null) {
			return 0;
		}

		if (root.getData() < lowerLimit) {
			return countOfNodesInRange(root.getRight(), lowerLimit, upperLimit);
		} else if (root.getData() > upperLimit) {
			return countOfNodesInRange(root.getLeft(), lowerLimit, upperLimit);
		} else {
			// lowerlimit<=data<==upperlimit

			return (countOfNodesInRange(root.getLeft(), lowerLimit, upperLimit)
					+ countOfNodesInRange(root.getRight(), lowerLimit, upperLimit) + 1);
		}

	}

	private static boolean isAVL(Node root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return true;
		}

		return (isAVL(root.getLeft()) && isAVL(root.getRight())
				&& (Math.abs(getMaxHeightLR(root.getLeft()) - getMaxHeightLR(root.getRight()))) <= 1);

		// return false;
	}

	private static void inOrderRecursive(Node root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return;
		}
		inOrderRecursive(root.getLeft());
		System.out.print(root.getData() + ",");
		inOrderRecursive(root.getRight());
	}

	private static Node createAVLTree() {

		Node updatedRoot = new Node(1, null, null);
		updatedRoot.setHeight(0);

		for (int i = 0; i < 10; i++) {
			updatedRoot = insert(updatedRoot, i);
		}

		System.out.println(updatedRoot.getData());

		return updatedRoot;
		// TODO Auto-generated method stub

	}

	private static Node insert(Node root, int data) {
		// TODO Auto-generated method stub
		if (root == null) {
			Node newNode = new Node(data, null, null);
			newNode.setHeight(0);
			return newNode;
		} else if (data < root.getData()) {
			Node childNode = insert(root.getLeft(), data);
			root.setLeft(childNode);

			if (getHeightDiffOfLR(root) == 2) {

				// if leftChild is leftHeavy after addition .i.e child is added to the root left
				// left
				// diagonal
				if (data < root.getLeft().getData()) {
					root = SingleRotateLeft(root);
				} else {
					root = DoubleRotateLR(root);// left child is right heavy
				}
			}
		} else if (data > root.getData()) {
			Node childNode = insert(root.getRight(), data);
			root.setRight(childNode);

			if (getHeightDiffOfLR(root) == 2) {

				// right diagonal
				if (data > root.getRight().getData()) {
					root = SingleRotateRight(root);
				} else {
					root = DoubleRotateRL(root);// left child is right heavy
				}
			}

		}

		root.setHeight(getMaxHeightLR(root) + 1);

		return root;
	}

	private static int getMaxHeightLR(Node root) {
		// TODO Auto-generated method stub

		if (root == null) {
			return -1;
		}

		Node left = root.getLeft();
		Node right = root.getRight();

		if (left == null && right == null) {
			return 0;
		} else if (left == null) {
			return Math.abs(right.getHeight());
		} else if (right == null) {
			return Math.abs(left.getHeight());
		} else {
			return Math.max(left.getHeight(), right.getHeight());
		}

	}

	private static Node DoubleRotateRL(Node root) {
		// TODO Auto-generated method stub

		Node updatedChildChild = SingleRotateLeft(root.getRight());
		root.setRight(updatedChildChild);

		return SingleRotateRight(root);

	}

	private static Node SingleRotateRight(Node root) {
		// TODO Auto-generated method stub
		Node newRoot = root.getRight();
		root.setRight(newRoot.getLeft());
		newRoot.setLeft(root);

		// +1 for self
		newRoot.setHeight(getMaxHeightLR(newRoot) + 1);
		root.setHeight(getMaxHeightLR(root) + 1);

		return newRoot;
	}

	private static Node DoubleRotateLR(Node root) {
		// left child has additional node in the right

		// make if left diagonal
		Node updatedChildChild = SingleRotateRight(root.getLeft());
		root.setLeft(updatedChildChild);
		return SingleRotateLeft(root);

	}

	private static Node SingleRotateLeft(Node root) {
		// TODO Auto-generated method stub
		// left child is left heavy;
		// left child has additional node in the left

		Node newRoot = root.getLeft();
		root.setLeft(newRoot.getRight());
		newRoot.setRight(root);

		newRoot.setHeight(getMaxHeightLR(newRoot) + 1);
		root.setHeight(getMaxHeightLR(root) + 1);

		return newRoot;
	}

	private static int getHeightDiffOfLR(Node root) {
		// TODO Auto-generated method stub

		Node left = root.getLeft();
		Node right = root.getRight();

		if (left == null && right == null) {
			return 0;
		} else if (left == null) {
			return Math.abs(right.getHeight());
		} else if (right == null) {
			return Math.abs(left.getHeight());
		} else {
			return Math.abs(left.getHeight() - right.getHeight().intValue());
		}

	}

}
