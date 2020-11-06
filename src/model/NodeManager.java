package model;



import exception.InvalidCoordinatesException;

public class NodeManager {
	private int col;
	private int row;
	private int quantity;
	private int rest;
	private double  score;
	private int attempts;
	
	private NodeList first;
	
	/**
	 * creates a Node Manager Object
	 * @param n integer with the quantity of columns 
	 * @param m integer with the quantity of rows
	 * @param k integer with the quantity of mirrors
	 */
	public NodeManager(int n, int m, int k) {
		col = n;
		row = m;
		quantity = k;
		first = null;
	}
	
	/**
	 * creates lists with their nodes
	 */
	public void addList() {
		if (first == null && row > 0) {
			first = new NodeList(1);
			first.addNodes(col);
		}
		if (row-1>0) {
			addList(first,col,row-1);
		}
		
	}
	
	
	/**
	 * creates the lists with their nodes in a recursive way
	 * @param list
	 * @param n
	 * @param m
	 */
	private void addList(NodeList list,int n, int m) {
		list.setNext(new NodeList(list.getRow()+1));
		list.getNext().setPrev(list);
		list.getNext().addNodes(n);
		if (m > 1) {
			addList(list.getNext(),n,m-1);
		}
	}
	
	/**
	 * connect the lists with themselves 
	 */
	public void connectLists() {
		connectLists(first);
	}
	
	/**
	 * connects current list with the next list
	 * @param n current NodeList
	 */
	private void connectLists(NodeList n) {
		if (n.getNext() != null) {
			n.connectNodes(n.getNext());
			connectLists(n.getNext());
		
		}
	}
	
	/**
	 * create the game matrix with the columns, rows and mirrors
	 */
	public void createBoard() {
		addList();
		connectLists();
		asignMirror();
		
	}
	
	/**
	 * generates a random integer between 1 and max
	 * @param max maximum value
	 * @return integer with the random final value
	 */
	public int randX(int max) {
		if (max == 1) {
			return 1;
		}else {
			int X = (int)(Math.random()*max+1);
			return X;
		}
	}
	
	/**
	 * generates a random integer between 1 and max
	 * @param max maximum value
	 * @return integer with the random final value
	 */
	public int randY(int max) {
		if (max == 1) {
			return 1;
		}else {
			int y = (int)(Math.random()*max+1);
			return y;
		}
	}
	
	/**
	 * generates randomly a String between / and \ 
	 * @return randomly String 
	 */
	public String randMirror() {
		double mr = Math.random();
		if (mr>0.5) {
			return "/";
		}else {
			return "\\";
		}
	}
	
	
	
	/**
	 * assigns mirrors randomly to the matrix
	 */
	public void asignMirror() {
	if (quantity == 0) {
		
	}else {
		asignMirror(quantity);
	}
	}
	
	
	/**
	 * assigns mirrors randomly
	 * @param k mirrors quantity
	 */
	private void asignMirror(int k) {
			int x = randX(col);
			int y = randY(row);
			String mirror = randMirror();
			Node aux = searchNode(x, y);
			if (aux.getMirror().equals("")) {
				aux.setMirror(mirror);
				
			}else {
				asignMirror(k);
			}
			if (k-1 > 0) {
				asignMirror(k-1);
			}
			
			
		
		}
	
	
	
	/**
	 * search a specific Node with its coordinates  
	 * @param x Column
	 * @param y row
	 * @return Node if it's fund otherwise returns null
	 */
	public Node searchNode(int x, int y) {
		if (first.getRow() == y) {
			Node aux = first.searchNode(x);
			if (aux != null) {
				return aux;
			}else {
				return null;
			}
		}else {
			return searchNode(first,x,y);
		}
		
	}
	

	/**
	 * search a specific node recursively
	 * @param n current NodeList
	 * @param x column
	 * @param y row
	 * @return the Node asked if it's found otherwise returns null
	 */
	private Node searchNode(NodeList n, int x,int y) {
		if (n.getNext() != null) {
			if (n.getNext().getRow() == y) {
				Node aux = n.getNext().searchNode(x);
				if (aux != null) {
					return aux;
				}else {
					return null;
				}
			}else {
				return searchNode(n.getNext(),x,y);
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
	
	/**
	 * verifies if a node is located on a corner
	 * @param node Node asked
	 * @return true if it's a corner otherwise returns false
	 */
	public boolean IsCorner(Node node) {
		int count = 0;
		if (node.getLeft() == null) {
			count++;
		}
		if (node.getRight() == null) {
			count++;
		}
		if (node.getUp() == null) {
			count ++;
		}
		if (node.getDown() == null) {
			count++;
		}
		
		if (count>=2) {
			return true;
		}else {
			return false;
		}	
	}
	
	/**
	 * process a laser shot to find the end node
	 * @param x initial column
	 * @param y initial row
	 * @return End node
	 * @throws InvalidCoordinatesException if the initial node doesn't exist
	 */
	public Node runAttempt(int x, int y) throws InvalidCoordinatesException {
		Node aux = searchNode(x, y);
		if (aux.getLeft() == null) {
			return runRight(aux);
		} else if (aux.getRight() == null) {
			return runLeft(aux);
		} else if (aux.getUp() == null) {
			return runDown(aux);
		}else if (aux.getDown() == null) {
			return runUp(aux);
		}else {
			return null;
		}
	}
	
	/**
	 * sends the shoot to left
	 * @param nd current node
	 * @return the end node
	 */
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
	
	/**
	 * sends the shoot down
	 * @param nd current node
	 * @return end node
	 */
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
	
	/**
	 * sends the node up
	 * @param nd current node
	 * @return end node
	 */
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
	
	/**
	 * sends the shoot right
	 * @param nd current node
	 * @return end node
	 */
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
		case "":
			if (nd.getRight() == null) {
				return nd;
			}else {
				return runRight(nd.getRight());
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

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	/**
	 *parse the matrix to String 
	 * @return matrix in String
	 */
	public String getNodesString() {
		String str = "";
		if (first == null) {
			return str;
		}else {
			return getNodesString(str, first);
		}
	}
	
	/**
	 * parse the matrix to String 
	 * @param str current text
	 * @param nd current NodeList
	 * @return matrix in String
	 */
	private String getNodesString(String str, NodeList nd) {
		String aux = str+"\n"+nd.getNodesString();
		if (nd.getNext() == null) {
			return aux;
		}else {
			return getNodesString(aux, nd.getNext());
		}
	}
	
	/**
	 * Gets the shoot simulation in String
	 * @param x column
	 * @param y row
	 * @return matrix String with the Start and end point 
	 * @throws InvalidCoordinatesException
	 */
	public String getNodesSE(int x, int y) throws InvalidCoordinatesException {
		Node one = searchNode(x, y);
		Node two = runAttempt(x, y);
		String str = "";
		if (first == null) {
			return str;
		}else {
			return getNodesSE(str, first,one, two);
		}
	}
	
	
	/**
	 * gets the matrix string with the start and end points
	 * @param str current text String
	 * @param nd current NodeList
	 * @param one Start Node
	 * @param two end node
	 * @return String with the matrix string 
	 */
	private String getNodesSE(String str, NodeList nd, Node one, Node two) {
		String aux = str+"\n"+nd.getNodesSE(one,two);
		if (nd.getNext() == null) {
			return aux;
		}else {
			return getNodesSE(aux, nd.getNext(),one,two);
		}
	}
	
	/**
	 * gets the matrix String with the start and end points of in a corner
	 * @param x column
	 * @param y row
	 * @param dir String with direction vertical or horizontal 
	 * @return String with the matrix simulation
	 * @throws InvalidCoordinatesException
	 */
	public String getNodesSE(int x, int y, int dir) throws InvalidCoordinatesException {
		Node one = searchNode(x, y);
		Node two = runAttempt(x, y, dir);
		String str = "";
		if (first == null) {
			return str;
		}else {
			return getNodesSE(str,first,one,two);
		}
	}
	
	/**
	 * tries to identify a node with a mirror
	 * @param x possible column
	 * @param y possible row
	 * @param dir possible mirror position 
	 * @return String with the simulation
	 */
	public String shot(int x, int y,String dir) {
		String str = "";
		Node a = searchNode(x, y);
		if (first == null || a == null) {
			return str;
		}else {
			str =  shot(str, first, a,dir);
			if (!str.contains("X")) {
				rest++;
				
			}
			return str;
		}
		
	}
	
	/**
	 * tries to identify a node with a mirror
	 * @param str current String 
	 * @param n current NodeList
	 * @param a possible mirror location
	 * @param dir possible mirror position 
	 * @return
	 */
	private String shot(String str, NodeList n, Node a,String dir) {
		String aux = str+n.shot(a,dir)+"\n";
		
		if (n.getNext() == null) {
			return aux;
		}else {
			
			return  shot(aux,n.getNext(),a,dir);
		}
	}
	
	/**
	 * verify if the player has won
	 * @return true if the player won otherwise returns false
	 */
	public boolean isWon() {
		if (rest == quantity) {
			return true;
		}else {
			return false;
		}
	}

	public int getRest() {
		return rest;
	}

	public void setRest(int rest) {
		this.rest = rest;
	}
	
	public double  getScore() {
		score = (row*col)-attempts;
		return score;
		
	}
	
	/**
	 * verifies if a node is located on a border
	 * @param x column
	 * @param y row
	 * @return true if the node is on a border otherwise returns false
	 */
	public boolean isBorder(int x, int y) {
		int i = 0;
		Node aux = searchNode(x, y);
		if (aux.getDown() == null) {
			i++;
		}
		if (aux.getUp() == null) {
			i++;
		}
		if (aux.getLeft() == null) {
			i++;
		}
		if (aux.getRight() == null) {
			i++;
		}
		
		if (i >= 1) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
