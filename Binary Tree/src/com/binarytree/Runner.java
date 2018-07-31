package com.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.event.PopupMenuListener;
import com.stack.*;

public class Runner {

	static int generateTree_unprocessedArray = 0;
	static Node convertBSTToDLL_prev = null;
	static Node convertBSTToDLL_head = null;

	public static void main(String args[]) throws CloneNotSupportedException {
		ArrayList<Integer> result = new ArrayList<>();
		Node root = new Node(5, null, null);
		createBinaryTree(root);

		Node head = null;
		//head=convertBSTToDLL(root, head);
		//head=convertBSTToDLLWithoutGlobalVariarble(root, head);
		//head=convertBSTToCLL(root);

		
		int [] input= {1,2,3,4,5};
		Node root1=convertArrayToBST(input,0,input.length-1);
		
		
		
		

	}

	private static Node convertArrayToBST(int[] input, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Node convertBSTToCLL(Node root) {
		// TODO Auto-generated method stub
		if (root==null) {
			return null;
		}
		
		Node leftCircularList=convertBSTToCLL(root.getLeft());
		Node rightCircularList=convertBSTToCLL(root.getRight());
		
		//making root as circular list
		root.setRight(root);
		root.setLeft(root);
		
		Node head=concatenateCLL(leftCircularList,root);
		head=concatenateCLL(head,rightCircularList);
		return head;
	}

	private static Node concatenateCLL(Node leftCircularList, Node rightCircularList) {
		// TODO Auto-generated method stub
		
		if (leftCircularList==null) {
			return rightCircularList;
		}
		if (rightCircularList==null) {
			return leftCircularList;
		}
		
		
		Node leftLast=leftCircularList.getLeft();
		Node rightLast=rightCircularList.getLeft();
		
		leftLast.setRight(rightCircularList);
		rightCircularList.setLeft(leftLast);
		
		leftCircularList.setLeft(rightLast);
		rightLast.setRight(leftCircularList);
		
		return leftCircularList;
	}

	private static Node convertBSTToDLLWithoutGlobalVariarble(Node root, Node head) {
		// reverse inorderrecursive RDL
		if (root == null) {
			return head;
		}
		head=convertBSTToDLLWithoutGlobalVariarble(root.getRight(), head);
		head=processNodeforConversionv2(root, head);
		head=convertBSTToDLLWithoutGlobalVariarble(root.getLeft(), head);
		return head;

	}

	private static Node processNodeforConversionv2(Node root, Node head) {
		// TODO Auto-generated method stub
		root.setRight(head);

		if (head != null) {
			head.setLeft(root);
		}

		head = root;
		return head;

	}

	private static Node convertBSTToDLL(Node root, Node head) {
		// Same as inorder recursive excudingProcess
		if (root == null) {
			return head;
		}
		head=convertBSTToDLL(root.getLeft(),head);
		head=processNodeforConversion(root, head);
		head=convertBSTToDLL(root.getRight(),head);

		return head;
	}

	private static Node processNodeforConversion(Node root, Node head) {
		// TODO Auto-generated method stub

		if (convertBSTToDLL_prev == null) {
			head = root;
		} else {
			convertBSTToDLL_prev.setRight(root);
			root.setLeft(convertBSTToDLL_prev);
		}
		convertBSTToDLL_prev=root;
		return head;

	}

	private static boolean isBST(Node root) {
		// TODO Auto-generated method stub
		boolean isBST = isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

		return isBST;
	}

	private static boolean isBSTUtil(Node root, int minValue, int maxValue) {
		// TODO Auto-generated method stub
		if (root == null) {
			return true;
		}

		if (root.getData() < minValue || root.getData() > maxValue) {
			return false;
		}

		return isBSTUtil(root.getLeft(), minValue, root.getData())
				&& isBSTUtil(root.getRight(), root.getData(), maxValue);

	}

	private static void buildExpressionTree(String infix) {
		// TODO Auto-generated method stub
		// String infix="(A+B*C)/D";
		String postFixData = com.stack.Runner.inFixToPostFix(infix).replaceAll(",", "");
		StringBuffer postFix = new StringBuffer(postFixData);
		Stack<NodeString> stack = new Stack<>();
		NodeString root = null;
		for (int i = 0; i < postFix.length(); i++) {

			if (!isOperator(postFix.charAt(i))) {
				NodeString operandNode = new NodeString();
				operandNode.setData("" + postFix.charAt(i));
				operandNode.setLeft(null);
				operandNode.setRight(null);
				stack.push(operandNode);
			} else {
				NodeString operand2 = stack.pop();
				NodeString operand1 = stack.pop();
				NodeString opertorNode = new NodeString();
				opertorNode.setData("" + postFix.charAt(i));
				opertorNode.setLeft(operand1);
				opertorNode.setRight(operand2);
				stack.push(opertorNode);
				root = opertorNode;

			}

		}

		System.out.println(root.getData());

	}

	private static boolean isOperator(Character c) {
		// TODO Auto-generated method stub

		String operators = "*" + "/" + "+" + "-";
		if (operators.indexOf(c) != -1) {
			return true;
		}
		return false;
	}

	private static void createBSiblinginaryTree(SiblingNode siblingNode) {
		// TODO Auto-generated method stub
		addSiblingNode(siblingNode, 3);
		addSiblingNode(siblingNode, 7);
		addSiblingNode(siblingNode, 2);
		addSiblingNode(siblingNode, 4);
		addSiblingNode(siblingNode, 1);
		addSiblingNode(siblingNode, 8);
		addSiblingNode(siblingNode, 6);
		addSiblingNode(siblingNode, 11);
		addSiblingNode(siblingNode, 9);
	}

	private static void addSiblingNode(SiblingNode root, int data) {

		SiblingNode current = root;

		if (data < current.getData()) {

			if (current.getLeft() == null) {
				current.setLeft(new SiblingNode(data, null, null));
				// System.out.println("left " + data);
			} else {
				addSiblingNode(root.getLeft(), data);
			}

		} else {

			if (current.getRight() == null) {
				current.setRight(new SiblingNode(data, null, null));
				// System.out.println("right" + data);
			} else {
				addSiblingNode(root.getRight(), data);
			}

		}

	}

	private static void populateNextSiblingNode(SiblingNode root) {
		// TODO Auto-generated method stub
		if (root == null) {
			return;
		}

		Queue<SiblingNode> currentLevel = new LinkedList<>();
		Queue<SiblingNode> nextLevel = new LinkedList<>();
		currentLevel.add(root);

		while (!currentLevel.isEmpty()) {

			SiblingNode current = currentLevel.poll();
			if (current.getLeft() != null) {
				nextLevel.add(current.getLeft());
			}
			if (current.getRight() != null) {
				nextLevel.add(current.getRight());
			}
			current.setNextSibling(currentLevel.peek());

			if (currentLevel.isEmpty()) {
				Queue<SiblingNode> temp = currentLevel;
				currentLevel = nextLevel;
				nextLevel = temp;
			}

		}

	}

	private static Node generateTree(char[] nodes) {
		/*
		 * Additional condition.Each node has either 2 child or no child
		 */
		// 0 represents internal node,1 represents leaf
		// Node newNode;
		if (nodes == null) {
			return null;
		}
		if (generateTree_unprocessedArray == nodes.length) {
			Node leafNode = new Node();
			leafNode.setData(1);
			return leafNode;
		}

		if (nodes[generateTree_unprocessedArray] == 'L') {
			Node leafNode = new Node();
			leafNode.setData(1);
			return leafNode;
		} else {
			Node internalNode = new Node();
			internalNode.setData(0);
			generateTree_unprocessedArray++;
			internalNode.setLeft(generateTree(nodes));
			generateTree_unprocessedArray++;
			internalNode.setRight(generateTree(nodes));
			return internalNode;

		}

	}

	private static ArrayList<Node> genearteAllTreesPossible(ArrayList<Node> listONodes) {
		// TO DO after BST

		return null;

	}

	private static void verticalSum(Node root) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int currentColumnIndex = 0;
		verticalSumUtility(root, map, currentColumnIndex);
		System.out.println(map);
	}

	private static void verticalSumUtility(Node root, HashMap<Integer, Integer> map, Integer currentColumnIndex) {
		// TODO Auto-generated method stub

		if (root == null) {
			return;
		}
		int currentNodeData = root.getData();
		if (map.containsKey(currentColumnIndex)) {
			map.put(currentColumnIndex, (map.get(currentColumnIndex)) + currentNodeData);
		} else {
			map.put(currentColumnIndex, currentNodeData);
		}
		verticalSumUtility(root.getLeft(), map, currentColumnIndex - 1);
		verticalSumUtility(root.getRight(), map, currentColumnIndex + 1);

	}

	private static void zigzagTreeTraversal(Node root) {

		Stack<Node> currentLevel = new Stack<>();
		Stack<Node> nextLevel = new Stack<>();
		currentLevel.push(root);
		boolean toggle = false;// leftToWrite

		while (!currentLevel.isEmpty()) {
			Node current = currentLevel.pop();
			processNode(current);
			if (toggle) {

				if (current.getLeft() != null) {
					nextLevel.push(current.getLeft());
				}

				if (current.getRight() != null) {
					nextLevel.push(current.getRight());
				}

			} else {

				if (current.getRight() != null) {
					nextLevel.push(current.getRight());
				}
				if (current.getLeft() != null) {
					nextLevel.push(current.getLeft());
				}
			}

			if (currentLevel.isEmpty()) {
				Stack<Node> emptyStack = currentLevel;
				currentLevel = nextLevel;
				nextLevel = emptyStack;
				toggle = !toggle;
			}

		}

	}

	private static Node findLeastCommonAncestorBST(Node root, int data1, int data2) {
		// <TO DO>
		// assuming data1 and data2 are present
		// data1<node<data2
		if (data1 > data2) {
			int temp = data1;
			data1 = data2;
			data2 = temp;
		}

		Node lca = null;

		if (root.getData() == null) {
			return null;
		} else if (root.getData() == data1 || root.getData() == data2) {
			return root;
		} else if (root.getData() < data1) {
			return findLeastCommonAncestorBST(root.getRight(), data1, data2);
		} else if (root.getData() > data2) {
			return findLeastCommonAncestorBST(root.getLeft(), data1, data2);
		}

		return root;
	}

	private static ArrayList<Integer> getAncestors(Node root, int data) {
		// TODO Auto-generated method stub

		ArrayList<Integer> listOfAncestors = null;

		if (root == null) {
			return null;
		}

		if (root.getData() == data) {
			listOfAncestors = new ArrayList<Integer>();
			listOfAncestors.add(root.getData());
			return listOfAncestors;
		} else if ((listOfAncestors = getAncestors(root.getLeft(), data)) != null) {
			listOfAncestors.add(root.getData());
			return listOfAncestors;
		} else if ((listOfAncestors = getAncestors(root.getRight(), data)) != null) {
			listOfAncestors.add(root.getData());
			return listOfAncestors;
		}
		return listOfAncestors;

	}

	private static boolean printAncestors(Node root, int data) {
		// TODO Auto-generated method stub

		if (root == null) {
			return false;
		}

		if (root.getData() == data || printAncestors(root.getLeft(), data) || printAncestors(root.getRight(), data)) {
			System.out.print(root.getData());
			return true;
		}
		return false;
	}

	private static Node constructTreeUsingInorderPreOrder(int[] inorder, int[] preOder) {
		// TODO Auto-generated method stub

		// TO DO check if inOrder and pre/postorder is invalid

		int preStart = 0;
		int preEnd = preOder.length - 1;
		int inStart = 0;
		int inEnd = inorder.length - 1;
		Node root = null;
		if (inorder.length == 0) {
			root = null;
		} else if (inorder.length == 1) {
			root = new Node(inorder[0], null, null);
		} else {
			root = constructTreeUsingInorderPreOrderUtility(inorder, inStart, inEnd, preOder, preStart, preEnd);
		}
		return root;
	}

	private static Node constructTreeUsingInorderPreOrderUtility(int[] inorder, int inStart, int inEnd, int[] preOder,
			int preStart, int preEnd) {

		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}

		// preOrder so 1st element is the root
		Integer rootData = preOder[preStart];
		Node current = new Node();
		current.setData(rootData);
		// System.out.println(current);
		// find index in inOrder
		int indexOfRootInINORDER = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == rootData) {
				indexOfRootInINORDER = i;
				break;
			}
		}

		int leftSubCount = indexOfRootInINORDER - inStart;
		int rightSubCount = inEnd - indexOfRootInINORDER;

		current.setLeft(constructTreeUsingInorderPreOrderUtility(inorder, inStart, indexOfRootInINORDER - 1, preOder,
				preStart + 1, preStart + leftSubCount));
		// indexOfRootInINORDER-1-->since we want to eliminate the root,
		// preStart+leftSubCount --> RootLEFTRight so

		current.setRight(constructTreeUsingInorderPreOrderUtility(inorder, indexOfRootInINORDER + 1, inEnd, preOder,
				preEnd - rightSubCount + 1, preEnd));
		// indexOfRootInINORDER+1 -->since we want to eliminate the root
		// preEnd-rightSubCount+1 --> RootLEFTRight so

		return current;
	}

	private static Node mirrorOfTree(Node root) {
		// TODO Auto-generated method stub
		// just like clone
		if (root == null) {
			return null;
		}

		Node current = new Node();
		current.setData(root.getData());
		current.setLeft(mirrorOfTree(root.getRight()));
		current.setRight(mirrorOfTree(root.getLeft()));

		return current;
	}

	private static Boolean hasPathSum(Node root, int sum) {
		// TODO Auto-generated method stub
		if (root == null || sum < 0) {
			return false;// necessary since we will call || operator in recursion
		}

		if (sum == root.getData()) {
			return true;
		} else {
			return (hasPathSum(root.getLeft(), sum - root.getData())
					|| hasPathSum(root.getRight(), sum - root.getData()));
		}

	}

	private static void rootToLeafPaths(Node root) {

		if (root == null) {
			return;
		}
		Integer[] list = new Integer[20];
		Integer length = 0;// a.add(root);//important since you don't want to copy array again and again
		printRootToLeafPaths(root, list, length);
	}

	private static void printRootToLeafPaths(Node root, Integer[] list, int length) {

		list[length] = root.getData();
		if (root.getLeft() == null && root.getRight() == null) {
			printArrayTillLength(list, length);
		}

		if (root.getLeft() != null) {
			printRootToLeafPaths(root.getLeft(), list, length + 1);
		}
		if (root.getRight() != null) {
			printRootToLeafPaths(root.getRight(), list, length + 1);
		}
	}

	private static void printArrayTillLength(Integer[] list, int length) {

		for (int i = 0; i <= length; i++) {
			System.out.print(list[i]);
		}
		System.out.println("");

	}

	private static Integer widthOfTree(Node root) {
		// no of max nodes in a level,level order traversal
		if (root == null) {
			return 0;
		}
		Integer maxNodesInLevel = 0;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		Integer numberOfNodesInLevel = 0;
		while (!queue.isEmpty()) {

			numberOfNodesInLevel = queue.size();
			if (numberOfNodesInLevel > maxNodesInLevel) {
				maxNodesInLevel = numberOfNodesInLevel;
			}

			while (numberOfNodesInLevel > 0) {
				Node current = queue.poll();

				if (current.getLeft() != null) {
					queue.add(current.getLeft());
				}
				if (current.getRight() != null) {
					queue.add(current.getRight());
				}
				numberOfNodesInLevel--;
			}
		}

		return maxNodesInLevel;
	}

	private static Integer diameterOfTree(Node root) {

		if (root == null) {
			return 0;
		}
		// assuming diamter passes through root
		Integer leftHeight = heightOfTreeRecursive(root.getLeft());
		Integer rightHeight = heightOfTreeRecursive(root.getRight());

		// assuming root does not lie in diameter
		Integer leftDiameter = diameterOfTree(root.getLeft());
		Integer rightDiameter = diameterOfTree(root.getRight());

		Integer maxDiameter = Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
		return maxDiameter;
	}

	private static Boolean structurallyIdentical(Node root1, Node root2) {
		// TODO Auto-generated method stub
		if (root1 == null && root2 == null) {
			return true;
		} else if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) {
			return false;
		} else {

			return ((structurallyIdentical(root1.getLeft(), root2.getLeft()))
					&& (structurallyIdentical(root1.getRight(), root2.getRight())));
		}
	}

	private static Integer numberOfLeaves(Node root) {
		Integer noOfLeaves = 0;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		Node current;

		while (true) {
			// current=queue.peek();
			if (queue.isEmpty()) {// || (current.getLeft()==null && current.getRight()==null)) {
				return noOfLeaves;
			}
			// noOfLeaves++;
			Integer numberOfnodesInLevel = queue.size();

			while (numberOfnodesInLevel > 0) {
				current = queue.poll();
				if (current.getLeft() == null && current.getRight() == null) {
					noOfLeaves++;
				}
				if (current.getLeft() != null) {
					queue.add(current.getLeft());
				}
				if (current.getRight() != null) {
					queue.add(current.getRight());
				}
				numberOfnodesInLevel--;
			}

		}
	}

	private static Node deleteNodeInTreeRecursive(Node root, int dataToBeDeleted) {
		Node current = root;
		if (root == null) {
			return null;
		}

		if (dataToBeDeleted < current.getData()) {
			current.setLeft(deleteNodeInTreeRecursive(current.getLeft(), dataToBeDeleted));
		} else if (dataToBeDeleted > current.getData()) {
			current.setRight(deleteNodeInTreeRecursive(current.getRight(), dataToBeDeleted));
		} else {

			if (current.getLeft() == null && current.getRight() == null) {// case1:leaf node
				return null;
			} else if (current.getLeft() == null) {// case2 :single child
				return current.getRight();
			} else if (current.getRight() == null) {// case2 :single child
				return current.getLeft();
			} else { // case 3

				// minValue since the node which has minValue is either case2 or case1
				Integer minValue = findMinValueInTreee(current.getRight());// could be also max value in left tree and
																			// delete from left tree
				current.setData(minValue);
				current.setRight(deleteNodeInTreeRecursive(current.getRight(), minValue));//

			}

		}
		return current;

	}

	private static Integer findMinValueInTreee(Node root) {
		// TODO Auto-generated method stub

		while (root.getLeft() != null) {
			root = root.getLeft();
		}

		return root.getData();
	}

	private static Integer minDepthTree(Node root) {
		// TODO Auto-generated method stub
		Integer mindepth = 0;

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		Node current;

		while (true) {
			// current=queue.peek();
			if (queue.isEmpty()) {// || (current.getLeft()==null && current.getRight()==null)) {
				return mindepth;
			}
			mindepth++;
			Integer numberOfnodesInLevel = queue.size();

			while (numberOfnodesInLevel > 0) {
				current = queue.poll();
				if (current.getLeft() == null && current.getRight() == null) {
					return mindepth;
				}
				if (current.getLeft() != null) {
					queue.add(current.getLeft());
				}
				if (current.getRight() != null) {
					queue.add(current.getRight());
				}
				numberOfnodesInLevel--;
			}

		}

	}

	private static Integer heightOfTreelevelOrder(Node root) {
		// TODO Auto-generated method stub
		Integer height = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		Node current;
		Integer numberOfNodesInLevel = 0;

		while (true) {

			numberOfNodesInLevel = queue.size();
			if (numberOfNodesInLevel == 0) {// level below last level
				return height;
			}
			height++;

			while (numberOfNodesInLevel > 0) {
				current = queue.peek();// for fetchhing clindren of current node
				queue.poll();
				if (current.getLeft() != null) {
					queue.add(current.getLeft());// adding nodes for next level
				}
				if (current.getRight() != null) {
					queue.add(current.getRight());// adding nodes for next level
				}
				numberOfNodesInLevel--;
			}
		}

	}

	private static Integer heightOfTreeRecursive(Node root) {

		Integer heightOfLeftSubtree = 0;
		Integer heightOfRightSubtree = 0;
		if (root == null) {
			return 0;
		}
		heightOfLeftSubtree = heightOfTreeRecursive(root.getLeft());
		heightOfRightSubtree = heightOfTreeRecursive(root.getRight());

		if (heightOfLeftSubtree > heightOfRightSubtree) {
			return heightOfLeftSubtree + 1;
		} else {
			return heightOfRightSubtree + 1;
		}

	}

	private static Integer heightOfTreePostOrder(Node root) {
		// The point where stack has maximum elements is max height since it is kind
		// ofDFS
		Stack<Node> stack = new Stack<>();
		Integer height = 0;
		Node current = root;
		stack.push(current);
		Node prev = null;

		while (!stack.isEmpty()) {
			current = stack.peek();

			if (prev == null || prev.getLeft() == current || prev.getRight() == current) {

				if (current.getLeft() != null) {
					stack.push(current.getLeft());
				} else if (current.getRight() != null) {
					stack.push(current.getRight());
				}

			} else if (current.getLeft() == prev) {

				if (current.getRight() != null) {
					stack.push(current.getRight());
				}
			} else {
				stack.pop();
			}
			prev = current;
			System.out.println(stack.size());
			if (stack.size() > height) {
				height = stack.size();
			}

		}

		return height;
	}

	private static void levelOrderTraversal(Node root) {

		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node current = queue.remove();
			processNode(current);
			if (current.getLeft() != null) {
				queue.add(current.getLeft());
			}
			if (current.getRight() != null) {
				queue.add(current.getRight());
			}

		}

	}

	private static void postOrderIterative(Node root) {
		Node current = null;
		Node prev = null;
		Stack<Node> stack = new Stack<>();
		stack.push(root);

		while (!stack.isEmpty()) {
			current = stack.peek();

			if (prev == null || prev.getLeft() == current || prev.getRight() == current) {

				if (current.getLeft() != null) {
					stack.push(current.getLeft());
				} else if (current.getRight() != null) {
					stack.push(current.getRight());
				}

			} else if (current.getLeft() == prev) {

				if (current.getRight() != null) {
					stack.push(current.getRight());
				}

			} else {
				processNode(current);
				stack.pop();
			}
			prev = current;

		}

		/*
		 * if (current.getLeft()!=null && current.getLeft()!=prev ||
		 * current.getRight()!=prev) {//navigating down stack.push(current);
		 * prev=current; current=current.getLeft(); }else if (current.getRight()!=null
		 * && current.getRight()!=prev) {//navigating down prev=current;
		 * stack.push(current); current=current.getRight(); }else if
		 * (current.getLeft()!=null && current.getLeft()==prev) {//coming from left side
		 * 
		 * if (current.getRight()!=null) { prev=current; current=current.getRight();
		 * }else { current=stack.pop(); processNode(current); prev=current;
		 * current=stack.peek(); } } else if (current.getRight()!=null &&
		 * current.getRight()==prev) {//coming from right side current=stack.pop();
		 * prev=current; processNode(current); current=current.getRight(); }else {
		 * //leaf node processNode(current); prev=current; current=stack.pop();
		 * 
		 * }
		 */

	}

	private static void inOrderIterative(Node root) {
		Stack<Node> stack = new Stack<>();
		Node current = root;
		boolean done = false;

		while (true) {// !done

			if (current != null) {
				stack.push(current);
				current = current.getLeft();
			} else {

				if (stack.isEmpty()) {// also implies current is null
					// done=true;
					break;
				}
				current = stack.pop();
				processNode(current);
				current = current.getRight();
			}
		}

	}

	private static void preOrderIterative(Node root) {
		Stack<Node> stack = new Stack<>();
		Node current;
		stack.push(root);

		while (!stack.isEmpty()) {
			current = stack.pop();
			processNode(current);

			// Note we have irst pushed right since LIFO
			if (current.getRight() != null) {
				stack.push(current.getRight());
			}

			if (current.getLeft() != null) {
				stack.push(current.getLeft());
			}
		}

	}

	private static void postOrderRecursive(Node root) {
		if (root == null) {
			return;
		}
		postOrderRecursive(root.getLeft());
		postOrderRecursive(root.getRight());
		processNode(root);
	}

	private static void inOrderRecursive(Node root) {
		if (root == null) {
			return;
		}
		inOrderRecursive(root.getLeft());
		processNode(root);
		inOrderRecursive(root.getRight());
	}

	private static void preOrderRecursive(Node root) {
		if (root == null) {
			return;
		}
		processNode(root);
		preOrderRecursive(root.getLeft());
		preOrderRecursive(root.getRight());
	}

	private static void processNode(Object root) {
		// TODO Auto-generated method stub
		if (root instanceof Node) {
			System.out.print(((Node) root).getData());

		}

		/*
		 * nextSibling
		 */
		if (root instanceof SiblingNode) {
			SiblingNode siblingNode = (SiblingNode) root;
			if (siblingNode.getNextSibling() != null) {
				System.out.println(siblingNode.getData() + "has next sibling" + siblingNode.getNextSibling().getData());
			}

		}
	}

	private static void createBinaryTree(Node root) {

		addNode(root, 3);
		addNode(root, 7);
		addNode(root, 2);
		addNode(root, 4);
		addNode(root, 1);
		addNode(root, 8);
		addNode(root, 6);
		addNode(root, 11);
		addNode(root, 9);
	}

	private static void addNode(Node root, int data) {

		Node current = root;

		if (data < current.getData()) {

			if (current.getLeft() == null) {
				current.setLeft(new Node(data, null, null));
				// System.out.println("left " + data);
			} else {
				addNode(root.getLeft(), data);
			}

		} else {

			if (current.getRight() == null) {
				current.setRight(new Node(data, null, null));
				// System.out.println("right" + data);
			} else {
				addNode(root.getRight(), data);
			}

		}

	}

}
