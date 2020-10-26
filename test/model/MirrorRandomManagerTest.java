package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MirrorRandomManagerTest {
	private MirrorRandomManager manager;
	
	private void setup1() {
		NodeManager mana = new NodeManager();
		mana.createBoard(3, 3, 3);
		manager = new MirrorRandomManager(mana,3,3,3);
	}
	
	@Test
	void testGenerateX() {
		setup1();
		int x = manager.generateX(3);
		boolean correct = true;
		
		if (x< 1 || x> 3) {
			correct = false;
		}
		
		assertEquals(true, correct);
	}
	
	@Test
	void testGenerateY() {
		setup1();
		int y = manager.generateY(3);
		boolean correct = true;
		
		if (y< 1 || y> 3) {
			correct = false;
		}
		
		assertEquals(true, correct);
	}
	
	@Test
	void testGenerateMirror() {
		setup1();
		String mirror = manager.generateMirror();
		
		boolean correct = false;
		
		if (mirror.equals("/") || mirror.equals("\\")) {
			correct = true;
		}
		
		assertEquals(true, correct);
	}

}
