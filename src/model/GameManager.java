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
	
	public void setManager(int x, int y, int k,String name) {
		nick = name;
		manager = new NodeManager(x, y, k);
		manager.createBoard();
	}
	
	public NodeManager getManager() {
		return manager;
	}
	
	public String shoot(char x, int y) throws InvalidCoordinatesException {
		
		
			return manager.getNodesSE('A'-x+1, y);
		
	}
	
	public String shoot(char x, int y, int dir) throws InvalidCoordinatesException {
		if (manager.isBorder(x-'A'+1,y)) {
			return manager.getNodesSE(x-'A'+1, y, dir);
		}else {
			return "La casilla especificada no es una esquina";
		}
	}
	
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
	
	public String MirrorsRemain() {
		int mrs = manager.getQuantity()-manager.getRest();
		
		return nick+": "+mrs+" espejos restantes";
	}
	
	public String getScore() {
		String str = "Su puntaje final es: "+manager.getScore();
		return str;
	}
	
	public boolean isBorder(int x, int y) {
		return manager.isBorder(x, y);
	}
	
	
	public String listPlayers() {
		return players.listPlayers();
	}
	
	
	
}
