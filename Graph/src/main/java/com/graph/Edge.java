package com.graph;

import java.util.Arrays;

public class Edge {

	// vertex[0] for start of edge for directed Graph
	Vertex[] vertices = new Vertex[2];
	int weight = 1;

	public Vertex getFirstVertex() {
		return vertices[0];
	}
	
	public Vertex getSecondVertex() {
		return vertices[1];
	}
	
	public Edge(Vertex vertex1, Vertex vertex2) {
		super();
		vertices[0] = vertex1;
		vertices[1] = vertex2;
	}

	public Edge(Vertex vertex1, Vertex vertex2, int weight) {
		super();
		vertices[0] = vertex1;
		vertices[1] = vertex2;
		this.weight = weight;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [vertices=" + Arrays.toString(vertices) + ", weight=" + weight + "]";
	}

	
	
	
}
