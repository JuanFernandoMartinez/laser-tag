package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeListTest {
	private NodeList list;
	private void setup1() {
		list = new NodeList(4);
	}
	@Test
	void testAddNodes() {
		setup1();
		list.addNodes(3);
		assertNotEquals(null, list.getNode());
		assertNotEquals(null, list.getNode().getRight());
		
	}
	
	@Test
	void testSearchNode() {
		setup1();
		list.addNodes(3);
		Node aux = list.searchNode(1);
		assertNotEquals(null, aux);
		
		aux = list.searchNode(2);
		assertNotEquals(null, aux);
		
		aux = list.searchNode(3);
		assertNotEquals(null, aux);
	}
	
	@Test 
	void testConnectNodes() {
		setup1();
		NodeList aux = new NodeList(5);
		list.addNodes(3);
		aux.addNodes(3);
		list.connectNodes(aux);
		
		assertNotEquals(null, list.getNode().getDown());
		assertNotEquals(null, list.searchNode(2).getDown());
		assertNotEquals(null, list.searchNode(3).getDown());
		
		
	}

}
