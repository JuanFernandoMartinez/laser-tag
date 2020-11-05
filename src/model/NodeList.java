package model;

public class NodeList {
	//Attributes
	private int row;
	private int size;
	
	//Links 
	private Node node;
	private NodeList next;
	private NodeList prev;
	
	//getters and setters
	
	public NodeList(int row) {
		this.row = row;
		this.size = 0;
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

	
	//methods
	
	
	/**
	 * Adds N nodes to the nodeList
	 * @param n integer that represents the number of nodes in the nodelist
	 */
	public void addNodes(int n) {
		if (node == null) {
			node = new Node("",1);
			size++;
			
		}
		addNodes(node,n-1);
	}
	
	/**
	 * Adds nodes in a recursive way
	 * @param n1 current Node
	 * @param n new Node
	 */
	private void addNodes(Node n1, int n) {
		if (n < 1) {
			
		}else if (n == 1) {
			n1.setRight(new Node("",n1.getCol()+1));
			n1.getRight().setLeft(n1);
			size++;
		}else {
			
			n1.setRight(new Node("",n1.getCol()+1));
			n1.getRight().setLeft(n1);
			size++;
			addNodes(n1.getRight(),n-1);
		}
	}
	
	
	/**
	 * Connects two nodelists between themselves 
	 * @param down Another nodeList placed under main list
	 */
	public void connectNodes(NodeList down) {
		node.setDown(down.getNode());
		down.getNode().setUp(node);
		connectNodes(node.getRight(), down.getNode().getRight());
	}
	
	
	/**
	 * Connects two nodelists in a recursive way
	 * @param u current Node
	 * @param d next down Node
	 */
	private void connectNodes(Node u, Node d) {
		u.setDown(d);
		d.setUp(u);
		if (u.getRight() != null) {
			connectNodes(u.getRight(), d.getRight());
		}
		
		
	}
	
	/**
	 * search a node in a nodelist by column value
	 * @param col integer with the node of the column
	 * @return the Node asked or null if this is not found
	 */
	public Node searchNode(int col) {
		if (node.getCol()== col) {
			return node;
		}else {
			return searchNode(node,col);
		}
		
		
	}
	
	/**
	 * search a node in a recursive way by column number
	 * @param n current Node
	 * @param col column of the asked Node
	 * @return Node asked if this is found otherwise returns null
	 */
	private Node searchNode(Node n, int col) {
		if (n.getRight() != null) {
			if (n.getRight().getCol() == col) {
				return n.getRight();
			}else {
				return searchNode(n.getRight(), col);
			}
		}else {
			return null;
		}
	}
	
	public String getNodesString() {
		String str = "";
		if (node == null) {
			return str;
		}else {
			return getNodesString(str,node);
		}
	}
	
	private String getNodesString(String str, Node n) {
		String aux;
		if (n.getMirror().equals("/") && n.isVisible()) {
			aux = "[/]";
		}else if (n.getMirror().contentEquals("\\") && n.isVisible()) {
			aux = "[\\]";
		}else {
			aux = "[ ]";
		}
		String chain = str+aux;
		if (n.getRight() == null) {
			return chain;
		}else {
			return getNodesString(chain, n.getRight());
		}
	}
	
	public String getNodesSE(Node a, Node b) {
		String aux = "";
		if (node == null) {
			return aux;
		}else {
			return getNodesSE(aux , node,a,b);
		}
	}
	
	private String getNodesSE(String str ,Node n,Node a, Node b) {
		String aux = "";
		if (n == a) {
			if (a == b) {
				aux = "[M]";
			}else {
				aux = "["+"S"+"]";
			}
			
		}else if (n == b) {
			aux = "["+"E"+"]";
		}else {
			if (n.isVisible()) {
				aux = "["+n.getMirror()+"]";
			}else {
				aux = "[ ]";
			}
		}
		
		if (n.getRight() == null) {
			return str+aux;
		}else {
			return getNodesSE(str+aux,n.getRight(),a,b);
		}
		
	}
	
	public String shot (Node n) {
		String str = "";
		
		if (node == null) {
			return str;
		}else {
			return shot(str,node,n);
		}
	}
	
	private String shot(String str,Node r, Node n) {
		String aux = "";
		if (r == n) {
			if (r.getMirror().equals("/")||r.getMirror().equals("\\")) {
				aux = "["+r.getMirror()+"]";
				r.setVisible(true);
			}else {
				aux = "[X]";
			}
		}else {
			aux = "[ ]";
		}
		
		
		if (r.getRight() == null) {
			return str+aux;
		}else {
			return shot(str+aux,r.getRight(),n);
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	
	
	
}
