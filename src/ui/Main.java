package ui;

import exception.InvalidCoordinatesException;
import model.NodeManager;

public class Main {
	public static void main(String[] args) {
		NodeManager manager = new NodeManager(2, 2, 2);
		manager.createBoard();
		
		manager.searchNode(1, 1).setVisible(true);
		manager.searchNode(1, 2).setVisible(true);
		manager.searchNode(2, 1).setVisible(true);
		manager.searchNode(2, 2).setVisible(true);
		System.out.println(manager.getNodesString());
		
	}
}
