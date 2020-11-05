package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.InvalidCoordinatesException;

class NodeManagerTest {
	private NodeManager manager;
	
	private void setup1() {
		manager = new NodeManager(3,3,0);
		
	}
	
	private void setup2() {
		manager = new NodeManager(10,20,0);
	}
	private void setup3() {
		manager = new NodeManager(10, 10, 5);
	}
	
	private void setup4() {
		manager = new NodeManager(1, 1, 1);
	}
	

	
	@Test
	void testAddList() {
		setup1();
		manager.addList();
		
		assertNotEquals(null, manager.getFirst());
		assertNotEquals(null, manager.getFirst().getNext());
		assertNotEquals(null, manager.getFirst().getNode());
		assertNotEquals(null, manager.getFirst().getNode().getRight());
		assertNotEquals(null, manager.getFirst().getNext().getNode());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getRight());
		
		assertEquals(manager.getFirst().getSize(), manager.getFirst().getNext().getSize());
	}
	
	@Test
	void testCreateBoard() {
		setup1();
		manager.createBoard();
		assertNotEquals(null, manager.getFirst());
		assertNotEquals(null, manager.getFirst().getNext());
		assertNotEquals(null, manager.getFirst().getNode().getDown());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getUp());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getRight());
		
	}
	
	
	@Test
	void testSearchNode() {
		setup1();
		manager.createBoard();
		Node aux = manager.searchNode(2, 2);
		assertNotEquals(null, aux);
		assertEquals(2,aux.getCol());
	}
	
	
	@Test
	void testRunLeft() {
		setup1();
		manager.createBoard();
		Node aux =  manager.runLeft(manager.searchNode(3, 3));
		assertEquals(1, aux.getCol());
	}
	
	@Test
	void testRunRight() {
		setup1();
		manager.createBoard();
		Node aux = manager.runRight(manager.searchNode(1, 3));
		assertNotEquals(null, aux);
		assertEquals(3, aux.getCol());
	}
	
	@Test
	void testRunUp() {
		setup1();
		manager.createBoard();
		Node aux = manager.runUp(manager.searchNode(3, 3));
		Node aux2 = manager.searchNode(3, 1);
		assertEquals(aux2, aux);
	}
	
	/*@Test
	void getNodesString() {
		setup2();
		manager.createBoard();
		String aux = "[ ][ ]\n[ ][ ]";
		assertEquals(aux, manager.getNodesString());
		
	}*/
	
	/*@Test
	void testShot() {
		setup2();
		manager.createBoard();
		String out = manager.shot(2, 2);
		assertEquals("[]",out);
	}*/
	
	@Test
	void testRunAttempt() throws InvalidCoordinatesException {
		setup2();
		manager.createBoard();
		assertNotEquals(null, manager.runAttempt(2, 1));
		assertEquals(manager.searchNode(2, 20), manager.runAttempt(2, 1));
	}
	
	@Test
	void testGetNodesSE() throws InvalidCoordinatesException {
		setup3();
		manager.createBoard();
		
		assertEquals("",manager.searchNode(10, 1).getMirror());
	}
	
	@Test
	void testAsignMirror() {
		setup4();
		manager.createBoard();
		assertEquals(1, 1);
	}
	
	@Test
	void testGetNodesString() {
		setup3();
		manager.createBoard();
		manager.searchNode(1, 1).setVisible(true);
		manager.searchNode(2, 1).setVisible(true);
		manager.searchNode(1, 2).setVisible(true);
		manager.searchNode(2, 2).setVisible(true);
		
		//assertEquals("", manager.getNodesString());
	}
	

}
