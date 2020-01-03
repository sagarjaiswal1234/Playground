package com.graph;

public class Employee {

	static int counter = 1;
	private Integer empId;
	private String empName;

	public Employee() {
		// TODO Auto-generated constructor stub
		empId=counter++;
		
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Employee emp2 = (Employee) obj;

		return this.getEmpId().intValue() == emp2.getEmpId();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return empId;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Employee.counter = counter;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
