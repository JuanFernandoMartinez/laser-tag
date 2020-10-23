package model;

public class NodeList {
	//Attributes
	private int row;
	
	//Links 
	private Node node;
	private NodeList next;
	private NodeList prev;
	
	public NodeList(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return row;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public NodeList getNext() {
		return next;
	}

	public void setNext(NodeList next) {
		this.next = next;
	}
	
	
	
	public NodeList getPrev() {
		return prev;
	}

	public void setPrev(NodeList prev) {
		this.prev = prev;
	}

	public void addNodes(int n) {
		if (node == null) {
			node = new Node("",'A');
			
		}
		addNodes(node,n-1);
	}
	
	private void addNodes(Node n1, int n) {
		if (n == 1) {
			n1.setRight(new Node("",n1.getCol()+1));
		}else {
			n1.setRight(new Node("",n1.getCol()+1));
			addNodes(n1.getRight(),n-1);
		}
	}
	
	public void connectNodes(NodeList down) {
		node.setDown(down.getNode());
		down.getNode().setUp(node);
		connectNodes(node.getRight(), down.getNode().getRight());
	}
	
	private void connectNodes(Node u, Node d) {
		u.setDown(d);
		d.setUp(u);
		if (u.getRight() != null) {
			connectNodes(u.getRight(), d.getRight());
		}
		
		
	}
	
}
