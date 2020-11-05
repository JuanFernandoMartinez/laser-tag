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
			if (r.getRight() == null) {
				r.setRight(n);
				n.setParent(r);
			}else {
				addNode(r.getRight(),n);
			}
		}else if (r.getScore()>n.getScore()) {
			if (r.getLeft() == null) {
				r.setLeft(n);
				n.setParent(r);
			}else {
				addNode(r.getLeft(),n);
			}
		}
	}
	
	public Player searchPlayer(double score) {
		if (root.getScore() == score) {
			return root;
		}else {
			return searchPlayer(root,score);
		}
	}
	
	private Player searchPlayer(Player r, double score) {
		if (r.getScore()< score) {
			if (r.getRight() != null) {
				if (r.getRight().getScore() == score) {
					return r.getRight();
				}else {
					searchPlayer(r.getRight(), score);
				}
			}else {
				return null;
			}
		}else if (r.getScore()> score) {
			if (r.getLeft() != null) {
				if (r.getLeft().getScore() == score) {
					return r.getLeft();
				}else {
					searchPlayer(r.getLeft(), score);
				}
			}else {
				return null;
			}
		}
		return null;
	}
	
	
	public String listPlayers() {
		String str = "";
		if (root == null) {
			return str;
		}else {
			return null;
		}
	}
	
	
}
