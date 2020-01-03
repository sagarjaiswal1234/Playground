package com.heap;

public class PriorityObject {

	public static int defaultpriority=0;//only for stack implementation
	
	int priority;
	Employee emp;
	
	public PriorityObject(Employee e) {
		this.priority=defaultpriority++;
		this.emp=e;
	}

	public static int getDefaultpriority() {
		return defaultpriority;
	}

	public static void setDefaultpriority(int defaultpriority) {
		PriorityObject.defaultpriority = defaultpriority;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
}
