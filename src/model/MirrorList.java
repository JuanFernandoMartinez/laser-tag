package model;

public class MirrorList {
	private MirrorRandom first;
	
	
	//links
	private MirrorList next;
	private MirrorList prev;
	
	public MirrorList() {
		
	}
	
	
	public void addMirror(MirrorRandom n) {
		if (first == null) {
			first = n;
		}else {
			addMirror(first, n);
		}
	}
	
	private void addMirror(MirrorRandom r, MirrorRandom n) {
		if (r.getNext() == null) {
			r.setNext(n);
			n.setPrev(r);
		}else {
			addMirror(r.getNext(), n);
		}
	}
	
	public MirrorRandom searchMirror(int col) {
		if (first.getCol() == col) {
			return first;
		}else {
			return searchMirror(first, col);
		}
	}
	
	private MirrorRandom searchMirror(MirrorRandom r, int col) {
		if (r.getCol() == col) {
			return r;
			
		}else if (r.getNext() == null) {
			return null;
		}else {
			return searchMirror(r.getNext(), col);
		}
	}


	public MirrorRandom getFirst() {
		return first;
	}


	public void setFirst(MirrorRandom first) {
		this.first = first;
	}


	public MirrorList getNext() {
		return next;
	}


	public void setNext(MirrorList next) {
		this.next = next;
	}


	public MirrorList getPrev() {
		return prev;
	}


	public void setPrev(MirrorList prev) {
		this.prev = prev;
	}
	
	
}
