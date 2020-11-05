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
	
	public NodeManager(int n, int m, int k) {
		col = n;
		row = m;
		quantity = k;
		first = null;
	}
	
	public void addList() {
		if (first == null && row > 0) {
			first = new NodeList(1);
			first.addNodes(col);
		}
		if (row-1>0) {
			addList(first,col,row-1);
		}
		
	}
	
	private void addList(NodeList list,int n, int m) {
		list.setNext(new NodeList(list.getRow()+1));
		list.getNext().setPrev(list);
		list.getNext().addNodes(n);
		if (m > 1) {
			addList(list.getNext(),n,m-1);
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
	
	public void createBoard() {
		addList();
		connectLists();
		asignMirror();
		
	}
	
	public int randX(int max) {
		if (max == 1) {
			return 1;
		}else {
			int X = (int)(Math.random()*max+1);
			return X;
		}
	}
	
	public int randY(int max) {
		if (max == 1) {
			return 1;
		}else {
			int y = (int)(Math.random()*max+1);
			return y;
		}
	}
	
	public String randMirror() {
		double mr = Math.random();
		if (mr>0.5) {
			return "/";
		}else {
			return "\\";
		}
	}
	
	
	
	public void asignMirror() {
	if (quantity == 0) {
		
	}else {
		asignMirror(quantity);
	}
	}
	
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
	
	/*public boolean IsCorner(Node node) {
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
	}*/
	
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
	
	public String getNodesString() {
		String str = "";
		if (first == null) {
			return str;
		}else {
			return getNodesString(str, first);
		}
	}
	
	private String getNodesString(String str, NodeList nd) {
		String aux = str+"\n"+nd.getNodesString();
		if (nd.getNext() == null) {
			return aux;
		}else {
			return getNodesString(aux, nd.getNext());
		}
	}
	
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
	
	private String getNodesSE(String str, NodeList nd, Node one, Node two) {
		String aux = str+"\n"+nd.getNodesSE(one,two);
		if (nd.getNext() == null) {
			return aux;
		}else {
			return getNodesSE(aux, nd.getNext(),one,two);
		}
	}
	
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
	
	private String shot(String str, NodeList n, Node a,String dir) {
		String aux = str+n.shot(a,dir)+"\n";
		
		if (n.getNext() == null) {
			return aux;
		}else {
			
			return  shot(aux,n.getNext(),a,dir);
		}
	}
	
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
