package com.stack;

import java.util.ArrayList;

public class Runner {

	public static void main(String[] args) {

		Integer maxArea;
		Integer[] histogramInput= {10,20,20,10,5,2};
		maxArea=calculateMaxAreaOfHistogram(histogramInput);
		System.out.println(maxArea);
	}

	private static Integer calculateMaxAreaOfHistogram(Integer[] histogramInput) {
		Integer maxArea=0;
		Integer tempMaxArea=0;
		Stack unfinishedHistomgramPositionStck=new Stack();
		Stack unfinishedHistogramHeightStck=new Stack();
		
		for (int i = 0; i < histogramInput.length; i++) {
			
			if (unfinishedHistomgramPositionStck.isEmpty()) {
				unfinishedHistomgramPositionStck.push(new Integer(i).toString());
				unfinishedHistogramHeightStck.push(histogramInput[i].toString());
			} else if (histogramInput[i].intValue() > new Integer(unfinishedHistogramHeightStck.peek())) {
				unfinishedHistomgramPositionStck.push(new Integer(i).toString());
				unfinishedHistogramHeightStck.push(histogramInput[i].toString());
			}else if (histogramInput[i].intValue() == new Integer(unfinishedHistogramHeightStck.peek())) {
				//ignore histograminput
			}else {
				
				while (!unfinishedHistomgramPositionStck.isEmpty() && histogramInput[i]<new Integer(unfinishedHistogramHeightStck.peek())) {
					Integer height=new Integer(unfinishedHistogramHeightStck.pop());
					Integer startOfWidth=new Integer(unfinishedHistomgramPositionStck.pop());
					tempMaxArea=height*(i-startOfWidth);
					System.out.println(height+" "+tempMaxArea);
					if (maxArea<tempMaxArea) {
						maxArea=tempMaxArea;
					}
					
					
					 if (!unfinishedHistomgramPositionStck.isEmpty() && histogramInput[i].intValue() > new Integer(unfinishedHistogramHeightStck.peek())) {
							unfinishedHistomgramPositionStck.push(new Integer(i).toString());
							unfinishedHistogramHeightStck.push(histogramInput[i].toString());
					}else {
					//ignore if they are equal with data already present	
					}
				}
			}
		}
		while (!unfinishedHistomgramPositionStck.isEmpty()) {
			
			Integer height=new Integer(unfinishedHistogramHeightStck.pop());
			Integer startOfWidth=new Integer(unfinishedHistomgramPositionStck.pop());
			tempMaxArea=height*(histogramInput.length-startOfWidth);
			System.out.println("End of stack");
			System.out.println(height+" "+tempMaxArea);
			if (maxArea<tempMaxArea) {
				maxArea=tempMaxArea;
			}
			
		}
		
		
		return maxArea;
	}

	private static Integer[] findSpansOfElements(Integer[] stockInput) {
		// TODO Auto-generated method stub
		Integer[] spans=new Integer[10];
		Stack stackOfIndexWhereConditionFails=new Stack();
		Integer indexOfLastHigherValue=-1;
		for (int i = 0; i < stockInput.length; i++) {
			
			while (!stackOfIndexWhereConditionFails.isEmpty() 
					&& stockInput[i]>stockInput[ new Integer(stackOfIndexWhereConditionFails.peek())	]) {
				stackOfIndexWhereConditionFails.pop();
			}	
			
			if (stackOfIndexWhereConditionFails.isEmpty()) {
				indexOfLastHigherValue=-1;
			}else {
				indexOfLastHigherValue=new Integer(stackOfIndexWhereConditionFails.peek());
			}
			spans[i]=i-indexOfLastHigherValue;
			stackOfIndexWhereConditionFails.push(new Integer(i).toString());
			
		}
		
		return spans;
	}

	private static String postFixEvaluation(String postfix) {
		
		
		/*String postfix = inFixToPostFix("(A+B*e)*C-D*E");
		postfix=inFixToPostFix("1+6/2");
		String result = postFixEvaluation(postfix);
		System.out.println(result);*/
		String[] postFixElement = postfix.split(",");
		System.out.println(postFixElement[0]);
		Stack stack = new Stack();
		for (int i = 0; i < postFixElement.length; i++) {

			if (isOperator(postFixElement[i])) {
				Integer operand2 = new Integer(stack.pop());
				Integer operand1 = new Integer(stack.pop());
				Integer result = performOperation(operand1, operand2, postFixElement[i]);
				stack.push(result.toString());
			} else {
				stack.push(postFixElement[i]);
			}

		}
		return stack.pop();
	}

	private static Integer performOperation(Integer operand1, Integer operand2, String operation) {
		// TODO Auto-generated method stub
		Integer result=0;
		switch (operation) {
		case "+":
			result=operand1+operand2;
			break;
		case "-":
			result=operand1-operand2;
			break;
		case "*":
			result=operand1*operand2;
			break;
		case "/":
			result=operand1/operand2;
			break;

		default:
			break;
		}

		return result;
	}

	public static String inFixToPostFix(String infix) {
		String postfix = "";
		Stack stack = new Stack();

		for (int i = 0; i < infix.length(); i++) {
			Character c = infix.charAt(i);

			if (c.toString().equals("(")) {
				stack.push(c.toString());
			} else if (c.toString().equals(")")) {
				String dataFromStack = stack.pop();
				System.out.println("POP" + stack);
				while ((!dataFromStack.equals("(")) && (!stack.isEmpty())) {
					postfix = postfix + "," + dataFromStack;
					dataFromStack = stack.pop();
				}
				stack.pop();// removing (
			} else if (!isOperator(c)) {
				postfix = postfix + "," + c;
			} else {

				if (!stack.peek().equals("(") && isPrecedenceGreaterWeightedApproach(c, stack.peek())) {
					stack.push(c.toString());
				} else {

					while ((!stack.isEmpty()) && !stack.peek().equals("(")
							&& (!isPrecedenceGreaterWeightedApproach(c, stack.peek()))) {
						String data = stack.pop();
						postfix = postfix + "," + data;
					}
					System.out.println("isoperator");
					stack.push(c.toString());
				}

			}

		}

		while (!stack.isEmpty()) {
			postfix = postfix + "," + stack.pop();
		}

		return postfix.substring(1);
	}

	private static boolean isPrecedenceGreaterWeightedApproach(Character current, String stacktop) {
		ArrayList<String> array = new ArrayList<String>();

		int weightCurrent = getWeight(current.toString());
		int weightStackTop = getWeight(stacktop);

		if (weightCurrent > weightStackTop) {
			return true;
		} else if (weightCurrent == weightStackTop) {
			return false;// so that we pop
		} else {
			return false;
		}

	}

	private static int getWeight(String operator) {
		// TODO Auto-generated method stub
		int weight = 0;
		switch (operator) {
		case "+":
		case "-":
			weight = 1;
			break;
		case "*":
		case "/":
			weight = 2;
			break;
		case "(":
			weight = 3;
			break;
		default:
			break;
		}

		return weight;
	}

	private static boolean isPrecedenceGreater(Character current, String stack) {
		ArrayList<String> array = new ArrayList<String>();

		array.add("+");// fails when more than 2 operators have same precedence
		array.add("-");
		array.add(null);
		array.add("*");
		array.add("/");
		array.add(null);
		array.add("(");

		int indexCurrent = array.indexOf(current.toString());
		int indexOfStack = array.indexOf(stack);

		if (indexCurrent - indexOfStack > 1) {
			return true;
		} else {
			return false;
		}

	}

	private static boolean isOperator(Character c) {
		// TODO Auto-generated method stub

		String operators = "*" + "/" + "+" + "-";
		if (operators.indexOf(c) != -1) {
			return true;
		}
		return false;
	}

	private static boolean isOperator(String c) {
		// TODO Auto-generated method stub

		String operators = "*" + "/" + "+" + "-";
		if (operators.indexOf(c) != -1) {
			return true;
		}
		return false;
	}

	private static void stackOperations() {
		Stack stack = new Stack();

		for (Integer i = 0; i < 5; i++) {
			stack.push(i.toString());
		}
		System.out.println(stack);
		System.out.println("peek" + stack.peek());
		System.out.println("pop" + stack.pop());

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		System.out.println(stack.pop());
		System.out.println(stack);
	}

}
