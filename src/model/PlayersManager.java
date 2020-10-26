package model;

public class PlayersManager {
	private Player root;
	
	public PlayersManager() {
		
	}
	
	
	public Player getRoot() {
		return root;
	}
	
	public void setRoot(Player root) {
		this.root = root;
	}
	
	public void addPlayer(String nick) {
		Player player = new Player(nick);
		if (root == null) {
			root = player;
		}else {
			addPlayer(root, nick);
		}
	}
	
	private void addPlayer(Player n, String nick) {
		if (n.getNickname().compareTo(nick) == 1) {
			if (n.getLeft() == null) {
				n.setLeft(new Player(nick));
			}else {
				addPlayer(n.getLeft(),nick);
			}
		}else if (n.getNickname().compareTo(nick) == -1) {
			if (n.getRight() == null) {
				n.setRight(new Player(nick));
			}else {
				addPlayer(n.getRight(), nick);
			}
		}
	}
	
	
	public Player searchPlayer(String nick) {
		if (root == null) {
			return null;
		}else {
			return searchPlayer(root, nick);
		}
	}
	
	private Player searchPlayer(Player n, String nick) {
		if (n.getNickname().equals(nick)) {
			return n;
		}else{
			if (n.getNickname().compareTo(nick) == 1 && n.getLeft() != null) {
				return searchPlayer(n.getLeft(),nick);
			}else if (n.getNickname().compareTo(nick) == -1 && n.getRight() != null) {
				return searchPlayer(n.getRight(),nick);
			}else {
				return null;
			}
		}
	}
}
