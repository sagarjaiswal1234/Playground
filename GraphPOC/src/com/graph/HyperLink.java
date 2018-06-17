package com.graph;

import java.util.ArrayList;

public class HyperLink {

	ArrayList<HyperLink> listOfLinksInCurrentPage=new ArrayList<HyperLink>();
	int countOfVisited=0;
	static String data="Satart";
	
	public static String getData() {
		return data;
	}

	public static void setData(String data) {
		HyperLink.data = data;
	}

	public HyperLink(String data) {
		
		this.data=data;
	}
	
	public ArrayList<HyperLink> getListOfLinksInCurrentPage() {
		return listOfLinksInCurrentPage;
	}
	public void setListOfLinksInCurrentPage(ArrayList<HyperLink> listOfLinksInCurrentPage) {
		this.listOfLinksInCurrentPage = listOfLinksInCurrentPage;
	}
	public int getCountOfVisited() {
		return countOfVisited;
	}
	public void setCountOfVisited(int countOfVisited) {
		this.countOfVisited = countOfVisited;
	}
	
	public void addLink(HyperLink h) {
		listOfLinksInCurrentPage.add(h);
	}
	
	
}
