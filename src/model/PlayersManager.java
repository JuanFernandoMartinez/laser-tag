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
	
	/**
	 * adds a player in the BST
	 * @param n new Player
	 */
	public void addNode(Player n) {
		if (root == null) {
			root = n;
		}else {
			addNode(root,n);
		}
		
	}
	
	/**
	 * adds a player in the BTS
	 * @param r root player
	 * @param n new player
	 */
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
	
	/**
	 * search a player in the BTS
	 * @param score player's score
	 * @return the player if it's found otherwise returns null
	 */
	public Player searchPlayer(double score) {
		if (root.getScore() == score) {
			return root;
		}else {
			return searchPlayer(root,score);
		}
	}
	
	/**
	 * search a player by score
	 * @param r current root player
	 * @param score players score
	 * @return the player if it's found otherwise return null
	 */
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
	
	/**
	 * list all players on the BTS
	 * @return String with the list of players
	 */
	public String listPlayers() {
		String str = "";
		if (root == null) {
			return str;
		}else {
			return visitPlayer(root, str);
		}
	}
	
	/**
	 * visit all players on the BST saving their information
	 * @param r current root player
	 * @param str current String
	 * @return
	 */
	private String visitPlayer(Player r, String str) {
		String aux = "";
		if (r.getRight() != null && !(r.getRight().isVisited())) {
			aux += visitPlayer(r.getRight(), str);
			aux += "Nickname: "+r.getNickname()+" Score: "+r.getScore()+"\n";
			r.setVisited(true);
			if (r.getLeft() != null && !(r.getLeft().isVisited())) {
				aux += visitPlayer(r.getLeft(), aux);
				return aux;
			}else {
				return aux;
			}
		}else {
			aux += "NickName: "+r.getNickname()+" Score: "+r.getScore()+"\n";
			r.setVisited(true);
			if (r.getLeft() != null && !(r.getLeft().isVisited())) {
				aux += visitPlayer(r.getLeft(), aux);
				return aux;
			}else {
				return aux;
			}
			
		}
		
		
	}
	
	
	
	
}
