package model;

public class NodeManager {
	private NodeList first;
	
	public NodeManager() {
		first = null;
	}
	
	public void addList(int n, int m) {
		if (first == null) {
			first = new NodeList(1);
			first.addNodes(m);
		}
		addList(first,n-1,m);
	}
	
	private void addList(NodeList list,int n, int m) {
		list.setNext(new NodeList(list.getRow()+1));
		list.getNext().setPrev(list);
		list.getNext().addNodes(m);
		if (n < 1) {
			addList(list.getNext(),n-1,m);
		}
	}
	
	public void connectLists() {
		connectLists(first);
	}
	
	private void connectLists(NodeList n) {
		if (n.getNext() != null) {
			n.connectNodes(n.getNext());
			connectLists(n.getNext());
		}
	}
}
