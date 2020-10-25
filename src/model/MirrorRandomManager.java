package model;

public class MirrorRandomManager {
	
	private int col;
	private int row;
	private int quantity;
	
	private MirrorList first;
	
	public MirrorRandomManager(int n, int m, int k) {
		this.col = n;
		this.row = m;
		this.quantity = k;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public MirrorList getFirst() {
		return first;
	}

	public void setFirst(MirrorList first) {
		this.first = first;
	}
	
	public void generateRows(int r) {
		if (first == null) {
			first = new MirrorList();
		}else {
			generateRows(first, r-1);
		}
	}
	
	private void generateRows(MirrorList n, int r) {
		n.setNext(new MirrorList());
		n.getNext().setPrev(n);
		
		if (r>1) {
			generateRows(n.getNext(), r-1);
		}
	}
	
	
}
