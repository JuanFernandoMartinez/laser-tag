package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeListTest {
	private NodeList list;
	
	private void setup1() {
		list = new NodeList(4);
	}
	
	private void setup2() {
		list = new NodeList(2);
	}
	
	@Test
	void testAddNodes() {
		setup1();
		list.addNodes(3);
		assertNotEquals(null, list.getNode());
		assertNotEquals(null, list.getNode().getRight());
		assertNotEquals(null, list.getNode().getRight().getRight());
		Node aux = list.getNode().getRight().getRight();
		assertEquals(null, aux.getRight());
		assertEquals(list.getNode(), list.getNode().getRight().getLeft());
		
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
		
		aux = list.searchNode(1);
		assertEquals(list.getNode(), aux);
		
		aux = list.searchNode(3);
		assertEquals(3, aux.getCol());
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
		
		assertEquals(aux.searchNode(3), list.searchNode(3).getDown());
		assertEquals(list.searchNode(3),aux.searchNode(3).getUp());
		
		
	}
	
	@Test
	void testGetNodesString() {
		setup2();
		list.addNodes(2);
		String aux = "[ ][ ]";
		String str = list.getNodesString();
		assertEquals(aux, str);
		
		setup2();
		list.addNodes(4);
		aux =  "[ ][ ][ ][ ]";
		str = list.getNodesString();
		assertEquals(aux, list.getNodesString());
	}

}
