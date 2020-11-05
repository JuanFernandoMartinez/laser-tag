package model;

public class Node {
	
	//Attributes
	private String mirror;
	private int col;
	private boolean visible;
	//links
	private Node up;
	private Node down;
	private Node left;
	private Node right;
	
	/**
	 * Creates a Node with a column number and a mirror value 
	 * @param mirror String which refer to Mirror value '/' or '\'
	 * @param col Integer with the column number
	 */
	public Node(String mirror, int col) {
		this.mirror = mirror;
		this.col = col;
		this.visible = false;
	}

	public String getMirror() {
		return mirror;
	}

	public void setMirror(String mirror) {
		this.mirror = mirror;
	}

	public Node getUp() {
		return up;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public Node getDown() {
		return down;
	}

	public void setDown(Node down) {
		this.down = down;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isVisible() {
		return visible;
	}

	
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	
	
}
