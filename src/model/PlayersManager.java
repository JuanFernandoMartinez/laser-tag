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
	
	public void addNode(Player n) {
		if (root == null) {
			root = n;
		}else {
			addNode(root,n);
		}
		
	}
	
	private void addNode(Player r, Player n) {
		if (r.getScore() < n.getScore()) {
			if ()
		}
	}
	
	
}
