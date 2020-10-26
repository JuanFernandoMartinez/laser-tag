package model;

import exception.InvalidCoordinatesException;

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
	
	public void createBoard(int n, int m, int k) {
		addList(m, n);
		connectLists();
		MirrorRandomManager manager = new MirrorRandomManager(this, n, m, k);
		manager.fillMatrix();
	}
	
	
	
	public Node searchNode(int x, int y) {
		if (first.getRow() == y) {
			Node aux = first.searchNode(x);
			if (aux != null) {
				return aux;
			}else {
				return searchNode(first,x,y);
			}
		}else {
			return null;
		}
		
	}
	

	
	private Node searchNode(NodeList n, int x,int y) {
		if (n.getNext() != null) {
			if (n.getNext().getRow() == y) {
				Node aux = n.getNext().searchNode(x);
				if (aux != null) {
					return aux;
				}else {
					return null;
				}
			}
		}
		return null;
	}
	/**
	 * Run the game initially 
	 * @param x column
	 * @param y row
	 * @param dir direction 0 for horizontal 1 for vertical
	 * @return final Node
	 */
	public Node runAttempt(int x, int y, int dir) throws InvalidCoordinatesException {
		Node aux = searchNode(x, y);
		if (aux == null) {
			throw new InvalidCoordinatesException();
		}
		switch (dir) {
		case 0:
			if (aux.getLeft() == null) {
				return runRight(aux);
			}else {
				return runLeft(aux);
			}
		case 1: 
			if (aux.getUp() == null) {
				return runDown(aux);
			}else {
				return runUp(aux);
			}
		}
		
		return null;
	}
	
	public Node runAttempt(int x, int y) throws InvalidCoordinatesException {
		Node aux = searchNode(x, y);
		if (aux == null) {
			throw new InvalidCoordinatesException();
		}else {
			if (aux.getLeft() == null) {
				return runRight(aux);
			}else if (aux.getRight() == null) {
				return runLeft(aux);
			}else if (aux.getUp() == null) {
				return runDown(aux);
			}else if (aux.getDown() == null) {
				return runUp(aux);
			}
		}
		
		return null;
	}
	
	public Node runLeft(Node nd) {
		
		String mr = nd.getMirror();
		
		switch(mr) {
		case "": 
			if (nd.getLeft() == null) {
				return nd;
			}else {
				return runLeft(nd.getLeft());
			}
				
				
		case "/":
			if (nd.getDown() == null) {
				return nd;
			}else {
				return runDown(nd.getDown());
			}
				
		case "\\":
			if (nd.getUp() == null) {
					return nd;
			}else {
				return runUp(nd.getUp());
			}
		}
		
		return null;
		
	}
	
	
	public Node runDown(Node nd){
		String mr = nd.getMirror();
		
		switch (mr) {
		case "/":
			if (nd.getLeft() == null ) {
				return nd;
			}else {
				return runLeft(nd);
			}
			
		case "\\":
			if (nd.getRight() == null) {
				return nd;
			}else {
				runRight(nd.getRight());
			}
			
		case "":
			if (nd.getDown() == null) {
				return nd;
			}else {
				return runDown(nd.getDown());
			}
		}
		return null;
	}
	
	public Node runUp(Node nd) {
		String mr = nd.getMirror();
		
		switch (mr) {
		case "/":
			if (nd.getRight() == null) {
				return nd;
			}else {
				return runRight(nd.getRight());
			}
			
		case "\\":
			if (nd.getLeft() == null) {
				return nd;
			}else {
				runLeft(nd.getLeft());
			}
			
		case "":
			if (nd.getUp() == null) {
				return nd;
			}else {
				return runUp(nd.getUp());
			}
		}
		
		return null;
	}
	
	public Node runRight(Node nd) {
		String mr = nd.getMirror();
		
		switch (mr) {
		case "/":
			if (nd.getUp() == null) {
				return nd;
			}else {
				return runUp(nd.getUp());
			}
		case "\\":
			if (nd.getDown() == null) {
				return nd;
			}else {
				return runDown(nd.getDown());
			}
		}
		
		return null;
	}

	public NodeList getFirst() {
		return first;
	}

	public void setFirst(NodeList first) {
		this.first = first;
	}
	
	
	
}
