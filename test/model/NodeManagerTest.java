package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeManagerTest {
	private NodeManager manager;
	
	private void setup1() {
		manager = new NodeManager();
		
	}
	
	@Test
	void testAddList() {
		setup1();
		manager.addList(2, 2);
		
		assertNotEquals(null, manager.getFirst());
		assertNotEquals(null, manager.getFirst().getNext());
		assertNotEquals(null, manager.getFirst().getNode());
		assertNotEquals(null, manager.getFirst().getNode().getRight());
		assertNotEquals(null, manager.getFirst().getNext().getNode());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getRight());
	}
	
	@Test
	void testCreateBoard() {
		setup1();
		manager.createBoard(2, 2, 2);
		assertNotEquals(null, manager.getFirst());
		assertNotEquals(null, manager.getFirst().getNext());
		assertNotEquals(null, manager.getFirst().getNode().getDown());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getUp());
		assertNotEquals(null, manager.getFirst().getNext().getNode().getRight());
		
	}
	
	@Test
	void testSearchNode() {
		setup1();
		manager.createBoard(3, 3, 2);
		Node aux = manager.searchNode(2, 2);
		assertNotEquals(null, aux);
		assertEquals(2,aux.getCol());
	}
	
	@Test
	void testRunLeft() {
		setup1();
		manager.createBoard(3, 2, 0);
		Node aux =  manager.runLeft(manager.searchNode(1, 1));
		assertEquals(3, aux.getCol());
	}
	
	@Test
	void testRunRight() {
		setup1();
		manager.createBoard(3, 2, 0);
		Node aux = manager.runRight(manager.searchNode(3, 1));
		assertNotEquals(null, aux);
		assertEquals(1, aux.getCol());
	}

}
