package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Runner {

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph = createGraph();
		
		System.out.println(graph.getListOfAllEdges());
		System.out.println(graph.getListOfAllVertices());
		
		ArrayList<Edge> edges=new ArrayList<>(graph.getListOfAllEdges());
		ArrayList<Vertex> vertices=new ArrayList<>(graph.getListOfAllVertices());
		
		graph.addVertex(new Vertex(new Employee()));
		graph.addEdge(new Edge(vertices.get(1),vertices.get(2) ));
		graph.addEdge(new Edge(vertices.get(1),vertices.get(3) ));
		graph.addEdge(new Edge(vertices.get(1),vertices.get(4) ));
		
		System.out.println(graph.getListOfAllEdges().size());
		System.out.println(graph.getListOfAllVertices().size());
		
		
		Vertex v = graph.getRandonVertex();
		bfs(graph, v);
		System.out.println();
	}

	private static void bfs(Graph graph, Vertex startVetex) {
		// TODO Auto-generated method stub

		Queue<Vertex> neighbours = new LinkedList<Vertex>();
		//while adding to queue since there would be addition of connected sibling unlike tree
		startVetex.setVisited(true);
		neighbours.add(startVetex);

		while (!neighbours.isEmpty()) {

			Vertex vertex = neighbours.remove();	
			process(vertex);
			List<Edge> listOfEdges = graph.getMap().get(vertex);

			for (Edge edge : listOfEdges) {
				if (edge.getFirstVertex().equals(vertex)) {
					if (!(edge.getSecondVertex().isVisited())) {
						edge.getSecondVertex().setVisited(true);
						neighbours.add(edge.getSecondVertex());
					}

				} else {
					if (!edge.getFirstVertex().isVisited()) {
						edge.getFirstVertex().setVisited(true);
						neighbours.add(edge.getFirstVertex());
					}

				}
			}

		}

	}

	

	private static void process(Vertex vertex) {
		// TODO Auto-generated method stub
		System.out.println("Vertex visited "+vertex.getData());
		
	}

	private static Graph createGraph() {
		// TODO Auto-generated method stub

		Graph graph = new Graph();

		Vertex vertex1 = new Vertex(new Employee());
		Vertex vertex2 = new Vertex(new Employee());
		Vertex vertex3 = new Vertex(new Employee());
		Vertex vertex4 = new Vertex(new Employee());
		Vertex vertex5 = new Vertex(new Employee());

		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
		graph.addVertex(vertex4);
		graph.addVertex(vertex5);

		Edge edge1 = new Edge(vertex1, vertex2);
		Edge edge2 = new Edge(vertex1, vertex3);
		Edge edge3 = new Edge(vertex2, vertex3);
		Edge edge4 = new Edge(vertex3, vertex4);
		Edge edge5 = new Edge(vertex3, vertex5);

		graph.addEdge(edge1);
		graph.addEdge(edge2);
		graph.addEdge(edge3);
		graph.addEdge(edge4);
		graph.addEdge(edge5);

		return graph;
	}
}
