package model;

import exception.InvalidCoordinatesException;


public class GameManager {
	private NodeManager manager;
	private PlayersManager players;
	private String nick;
	
	public GameManager() {
		manager = null;
		players = new PlayersManager();
	}
	
	/**
	 * create the matrix manager with the given information  and save the current player name 
	 * @param x number of columns
	 * @param y number of rows
	 * @param k number of mirrors
	 * @param name player's name
	 */
	public void setManager(int x, int y, int k,String name) {
		nick = name;
		manager = new NodeManager(x, y, k);
		manager.createBoard();
	}
	
	public NodeManager getManager() {
		return manager;
	}
	
	/**
	 * simulates a laser shoot
	 * @param x column
	 * @param y row
	 * @return String with the simulation
	 * @throws InvalidCoordinatesException
	 */
	public String shoot(char x, int y) throws InvalidCoordinatesException {
			return manager.getNodesSE('A'-x+1, y);
	}
	
	/**
	 * simulates a laser shoot
	 * @param x column
	 * @param y row
	 * @param dir laser direction
	 * @return String with the simulation
	 * @throws InvalidCoordinatesException
	 */
	public String shoot(char x, int y, int dir) throws InvalidCoordinatesException {
		if (manager.isBorder(x-'A'+1,y)) {
			return manager.getNodesSE(x-'A'+1, y, dir);
		}else {
			return "La casilla especificada no es una esquina";
		}
	}
	
	/**
	 * tries to find a mirror
	 * @param x possible column
	 * @param y possible row
	 * @param dir possible mirror position
	 * @return String with the simulation
	 */
	public String shot(int x, int y, String dir) {
		String str = manager.shot(x, y, dir);
		if (manager.isWon()) {
			String aux = "";
			aux+= "============================\n";
			aux+= "         Has ganado\n";
			aux+= "============================\n";
			aux+= "Tu puntaje es: "+manager.getScore();
			Player player = new Player(nick);
			player.setScore(manager.getScore());
			players.addNode(player);
			
			return aux;
		}else {
			return str;
		}
		
	}
	
	/**
	 * gets the mirror remain
	 * @return player's nickname followed by mirrors remain 
	 */
	public String MirrorsRemain() {
		int mrs = manager.getQuantity()-manager.getRest();
		
		return nick+": "+mrs+" espejos restantes";
	}
	
	/**
	 * gets the current score
	 * @return score message
	 */
	public String getScore() {
		String str = "Su puntaje final es: "+manager.getScore();
		return str;
	}
	
	/**
	 * verify if a node is located on a border
	 * @param x column
	 * @param y row
	 * @return true if is on border otherwise returns false
	 */
	public boolean isBorder(int x, int y) {
		return manager.isBorder(x, y);
	}
	
	/**
	 * list all players on the BST
	 * @return String with the players list
	 */
	public String listPlayers() {
		return players.listPlayers();
	}
	
	
	
}
