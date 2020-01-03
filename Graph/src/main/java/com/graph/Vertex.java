package com.graph;

public class Vertex {

	Employee data;
	boolean isVisited=false;
	

	public Employee getData() {
		return data;
	}


	public void setData(Employee data) {
		this.data = data;
	}


	public boolean isVisited() {
		return isVisited;
	}


	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}


	public Vertex(Employee e) {
		data=e;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		Vertex inVertexObject=(Vertex)obj;
		
		boolean equal=inVertexObject.getData().equals(data);
		
		return equal;
	}

	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return data.getEmpId();
	}


	
	@Override
	public String toString() {
		return "Vertex [data=" + data + ", isVisited=" + isVisited + "]";
	}


	
	

}
