package model;

public class MirrorRandomManager {
	private int col;
	private int row;
	private int quantity;
	
	private NodeManager manager;
	
	public MirrorRandomManager(NodeManager manager, int x, int y, int k) {
		this.col = x;
		this.row = y;
		this.quantity = k;
		this.manager = manager;
	}
	
	
	public void fillMatrix() {
		int x = generateX(col);
		int y = generateY(row);
		String mr = generateMirror();
		
		Node aux = manager.searchNode(x, y);
		if (aux != null) {
			if (aux.getMirror().equals("")) {
				aux.setMirror(mr);
				quantity--;
			}else {
				fillMatrix();
			}
		}else {
			fillMatrix();
		}
		
		if (quantity >= 1) {
			fillMatrix();
		}	
	}
	
	public int generateX(int max) {
		int x = (int)Math.random()*(max+1);
		
		return x;
	}
	
	public int generateY(int max) {
		int y = (int)Math.random()*(max+1);
		return y;
	}
	
	public String generateMirror() {
		double i = Math.random();
		
		if (i < 0.5) {
			return ""; //return "/";
		}else {
			return ""; // return "\";
		}
	}
	
}
