package ui;

import java.util.Scanner;

import exception.InvalidCoordinatesException;
import model.GameManager;

public class Menu {
	private Scanner sc;
	private GameManager manager;
	
	public Menu() {
		manager = new GameManager();
		sc = new Scanner(System.in);
	}
	
	/**
	 * starts the application
	 */
	public void start() {
		boolean finished = false;
		menu(finished);
	}
	
	/**
	 * controls the menu
	 * @param f boolean which verify if the application is still running
	 */
	private void menu(boolean f) {
		if (f) {
			
		}else {
			System.out.println(showOptions());
			boolean aux = manageOptions();
			menu(aux);
			
		}
	}
	
	/**
	 * gets the menu options
	 * @return String with the menu options
	 */
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
	
	/**
	 * manage all menu options
	 * @return true if the game should finished otherwise returns false
	 */
	private boolean manageOptions() {
		int choice = Integer.parseInt(sc.nextLine());
		
		switch (choice) {
		case 1: play(); break;
		case 2: showScores(); break;
		case 3: return true;
		
		}
		return false;
	}
	
	/**
	 * controls the game mechanics
	 */
	private void play(){
		if (getPlayerInfo()) {
			System.out.println(manager.MirrorsRemain());
			System.out.println(manager.getManager().getNodesString());
			play(false);
		}
		
	}
	
	/**
	 * controls the game flow
	 * @param win boolean which represent the game win or lose status 
	 */
	private void play(boolean win) {
		if (win) {
			System.out.println("Felicidades usted ha ganado "+manager.getScore());
			
		}else {
			shot();
			play(manager.getManager().isWon());
				
			}
		}
	
	
	/**
	 * gets the player's information
	 * @return String with the players information
	 */
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
		System.out.println("========================");
		System.out.println("         Scores         ");
		System.out.println("========================");
		System.out.println(manager.listPlayers());
		
	}
	
	/**
	 * controls shoots and shots in the game 
	 */
	public void shot() {
		String str = sc.nextLine();
		if (str.charAt(0) != 'L') {
			if ((str.charAt(str.length()-1)-'H' != 0) && (str.charAt(str.length()-1)-'V'!= 0)) {
				
				char x = str.charAt(str.length()-1);
				str = str.replace(x+"","");
				int y = Integer.parseInt(str);
				
				try {
					System.out.println(manager.shoot(x, y));
				} catch (InvalidCoordinatesException e) {
					e.printStackTrace();
				}
			}else {
				if (str.charAt(str.length()-1) == 'H') {
					 char x = str.charAt(str.length()-2);
					 str = str.replace(x, '/');
					 str.replace("/", "");
					 str.replace("H", "");
					 int y = Integer.parseInt(str);
					 try {
						 System.out.println(manager.shoot(x, y, 0));
					 }catch (InvalidCoordinatesException e) {
						 e.printStackTrace();
					 }
				}else if (str.charAt(str.length()-1) == 'V') {
					char x = str.charAt(str.length()-2);
					str = str.replace(x, '/');
					str = str.replace("/", "");
					str = str.replace("V", "");
					
					int y = Integer.parseInt(str);
					
					try {
					manager.shoot(x, y, 1);	
					}catch (InvalidCoordinatesException e) {
						e.printStackTrace();
					}
				}
			}
		}else {
			if (str.charAt(str.length()-1) == 'L') {
				str = str.replace(str.charAt(str.length()-1)+"","");
				str = str.replace(str.charAt(0)+"", "");
				char x = str.charAt(str.length()-1);
				str = str.replace(x+"", "");
				int y = Integer.parseInt(str);
				System.out.println(manager.shot(x, y, "\\"));
				} else {
					char x = str.charAt(str.length()-2);
					str = str.replaceAll('L'+"", "");
					str = str.replaceAll('R'+"", "");
					str = str.replace(x+"", "");
					int y = Integer.parseInt(str);
					System.out.println(manager.shot(x, y, "/"));
				}
			}
	}
}
