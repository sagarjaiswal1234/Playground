package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {

	// Helper for creation and usage.Not required for actual processing
	Set<Vertex> listOfAllVertices = new HashSet<>();
	Set<Edge> listOfAllEdges = new HashSet<>();

	Map<Vertex, LinkedList<Edge>> map = new HashMap<Vertex, LinkedList<Edge>>();

	@Override
	public String toString() {
		return "Graph [map=" + map + "]";
	}

	public Map<Vertex, LinkedList<Edge>> getMap() {
		return map;
	}

	public void setMap(Map<Vertex, LinkedList<Edge>> map) {
		this.map = map;
	}

	public boolean addVertex(Vertex vertex) {

		if (map.get(vertex) != null) {
			return false;
		}

		map.put(vertex, new LinkedList<Edge>());
		listOfAllVertices.add(vertex);
		return true;

	}

	public boolean addEdge(Edge edge) {

		Vertex vertex1 = edge.getVertices()[0];
		Vertex vertex2 = edge.getVertices()[1];

		if (vertex1 != null && map.get(vertex1) == null) {

			LinkedList<Edge> edgesFromVertex = new LinkedList<Edge>();
			edgesFromVertex.addFirst(edge);
			map.put(vertex1, edgesFromVertex);

		} else if (!map.get(vertex1).contains(edge)) {
			map.get(vertex1).addFirst(edge);
		}

		if (vertex2 != null && map.get(vertex2) == null) {

			LinkedList<Edge> edgesFromVertex = new LinkedList<Edge>();
			edgesFromVertex.addFirst(edge);
			map.put(vertex2, edgesFromVertex);

			listOfAllEdges.add(edge);
			listOfAllVertices.add(vertex2);
		} else if (!map.get(vertex2).contains(edge)) {
			map.get(vertex2).addFirst(edge);
		}

		listOfAllVertices.add(vertex1);
		listOfAllVertices.add(vertex2);
		listOfAllEdges.add(edge);

		return true;

	}

	public boolean removeVertex(Vertex vertex) {

		LinkedList<Edge> listOfConnctedEdges = map.get(vertex);

		for (Edge edge : listOfConnctedEdges) {
			removeEdge(edge);
		}
		map.remove(vertex);
		listOfAllVertices.remove(vertex);
		return true;

	}

	public boolean removeEdge(Edge edge) {
		// Assuming we are removing only connection between vertices
		Vertex vertex1 = edge.getVertices()[0];
		Vertex vertex2 = edge.getVertices()[1];

		map.get(vertex1).remove(edge);
		map.get(vertex2).remove(edge);
		listOfAllEdges.remove(edge);
		return true;
	}

	public Vertex getRandonVertex() {
		// TODO Auto-generated method stub

		Object[] keys = map.keySet().toArray();
		Vertex root = (Vertex) keys[0];

		return root;
	}

	
	public Set<Vertex> getListOfAllVertices() {
		return listOfAllVertices;
	}

	public void setListOfAllVertices(Set<Vertex> listOfAllVertices) {
		this.listOfAllVertices = listOfAllVertices;
	}

	public Set<Edge> getListOfAllEdges() {
		return listOfAllEdges;
	}

	public void setListOfAllEdges(Set<Edge> listOfAllEdges) {
		this.listOfAllEdges = listOfAllEdges;
	}

	
	
	
}
