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
		if (manager.IsCorner(manager.searchNode('A'-x+1, y))){
			return "Para las esquinas debe especificar la direccion (H horizontal, V vertical)";
		}else {
			return manager.getNodesSE('A'-x+1, y);
		}
	}
	
	public boolean shot(String attempt) {
		return true;
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
	
	
	
	
	
	
}
