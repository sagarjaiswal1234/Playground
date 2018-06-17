package com.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Wiki {

	static ArrayList<HyperLink> visited=new ArrayList<HyperLink>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HyperLink firstPage=new HyperLink("firstpage");
	//	firstPage.setListOfLinksInCurrentPage(fetchLinksInCurrentPage());
	
		
		
		HyperLink a=new HyperLink("a");
		a.addLink(new HyperLink("a1"));
		a.addLink(new HyperLink("a2"));
		HyperLink b=new HyperLink("b");
		b.addLink(new HyperLink("b1"));
		b.addLink(new HyperLink("b2"));
		b.addLink(new HyperLink("b3"));
		b.addLink(new HyperLink("b4"));
		a.addLink(b);
		b.addLink(a);
		a.addLink(new HyperLink("a3"));
		a.addLink(new HyperLink("a4"));
		a.addLink(new HyperLink("a5"));
		a.addLink(new HyperLink("a6"));
		
		firstPage.addLink(a);
		
		
		metchodToFetchAllLinks(firstPage);
		System.out.println("dffdfddf");
		int i=1;
		for (HyperLink aa : visited) {
			System.out.println(" "+visited+i+++" "+aa.getData());
		}
		
	}

	private static void metchodToFetchAllLinks(HyperLink firstPage) {
		ArrayList<HyperLink> listOfHyperlinksInCurrentPage=null;
		Queue <HyperLink> queue= new LinkedList<HyperLink>();
		
		queue.add(firstPage);
		
		while (!queue.isEmpty()) {
			HyperLink removed=queue.remove();
			
			listOfHyperlinksInCurrentPage=fetchLinksInCurrentPage(removed);
			
			//update count in visited node list if found in forward link
			for (HyperLink hyperLink : listOfHyperlinksInCurrentPage) {
				if (visited.contains(hyperLink)) {
					int index=visited.indexOf(hyperLink);
					visited.get(index).setCountOfVisited(visited.get(index).getCountOfVisited()+1);
				}
			}
			listOfHyperlinksInCurrentPage.removeAll(visited);
			queue.addAll(listOfHyperlinksInCurrentPage);
			System.out.println("removed "+removed);
			removed.setCountOfVisited(removed.getCountOfVisited()+1);
			visited.add(removed);
		}
		
	}

	private static ArrayList<HyperLink> fetchLinksInCurrentPage(HyperLink hyperLink) {

		return hyperLink.getListOfLinksInCurrentPage();
		
	}

}
