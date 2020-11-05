package model;

public class Player {
	private String nickname;
	private double score;
	private boolean visited;
	
	private Player parent;
	private Player left;
	private Player right;
	
	public Player(String nick) {
		visited = !true;
		nickname = nick;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean value) {
		visited = value;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Player getParent() {
		return parent;
	}

	public void setParent(Player parent) {
		this.parent = parent;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}
	
	

}
