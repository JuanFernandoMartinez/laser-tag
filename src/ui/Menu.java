package ui;

import java.util.Scanner;

import model.GameManager;

public class Menu {
	private Scanner sc;
	private GameManager manager;
	
	public Menu() {
		manager = new GameManager();
		sc = new Scanner(System.in);
	}
	
	public void start() {
		boolean finished = false;
		menu(finished);
	}
	
	private void menu(boolean f) {
		if (f) {
			
		}else {
			System.out.println(showOptions());
			boolean aux = manageOptions();
			menu(aux);
			
		}
	}
	
	private String showOptions() {
		String options;
		options = "=============================\n";
		options +="           Welcome\n";
		options +="=============================\n";
		options +="1. Jugar\n";
		options +="2. Ver tablero de posiciones\n";
		options +="3. Salir\n";
		
		return options;
	}
	
	private boolean manageOptions() {
		int choice = Integer.parseInt(sc.nextLine());
		
		switch (choice) {
		case 1: play(); break;
		case 2: showScores(); break;
		case 3: return true;
		
		}
		return false;
	}
	
	private void play(){
		if (getPlayerInfo()) {
			System.out.println(manager.MirrorsRemain());
			System.out.println(manager.getManager().getNodesString());
			play(false);
		}
		
	}
	
	private void play(boolean win) {
		if (win) {
			System.out.println("Felicidades usted ha ganado "+manager.getScore());
		}else {
			String str = sc.nextLine();
			if (str.length() >= 2) {
				manager.shoot(str.charAt(0), )
			}
		}
	}
	
	private boolean getPlayerInfo() {
		System.out.println("Escriba separando con espacios su nickname, #columnas, #filas y #espejos");
		String line = sc.nextLine();
		String prt[] = line.split(" ");
		if (Integer.parseInt(prt[3])>(Integer.parseInt(prt[1])*Integer.parseInt(prt[2])) || Integer.parseInt(prt[1])> 26)  {
			System.out.println("No pueden haber mas espejos que casillas, ni mas de 26 columnas");
			return false;
		}else {
			manager.setManager(Integer.parseInt(prt[1]),Integer.parseInt(prt[2]), Integer.parseInt(prt[3]),prt[0]);
			return true;
			
		}
				
	}
	
	private void showScores() {
		
	}
}
