package model;

public class MirrorRandom {
	private int col;
	private int row;
	private String mirror;
	
	private MirrorRandom next;
	private MirrorRandom prev;
	
	public MirrorRandom(int n, int m) {
		this.col = (int)Math.random()*(n+1);
		this.row = (int)Math.random()*(m+1);
		double aux = Math.random();
		if (aux < 0.5) {
			this.mirror = "/";
		}else {
			// this "other bar" must be change by / using escape java chars
			this.mirror = "other bar";
		}	
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getMirror() {
		return mirror;
	}

	public void setMirror(String mirror) {
		this.mirror = mirror;
	}

	public MirrorRandom getNext() {
		return next;
	}

	public void setNext(MirrorRandom next) {
		this.next = next;
	}

	public MirrorRandom getPrev() {
		return prev;
	}

	public void setPrev(MirrorRandom prev) {
		this.prev = prev;
	}
	
	
}
