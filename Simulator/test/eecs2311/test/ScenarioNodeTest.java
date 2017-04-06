package eecs2311.project;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ScenarioNodeTest {

	//private static int cell, button;
	private static ScenarioNode root, node, t1, t2;
	private static String content, nodeType;
	private static Scanner read;

	@Before
	public void setUp() throws Exception {
		nodeType = "Text-To-Speech";
		content = "2 3";
		root = new ScenarioNode("Root", content);
		node = new ScenarioNode(nodeType, content);
		t1 = new ScenarioNode("Pause", "100");
		t2 = new ScenarioNode("Set Voice", "Kevin");
		read = new Scanner(content);
	}

	/*
	 * Test the constructor to see if everything is initialized properly We need
	 * to test when the node is a ROOT node and when it is not. Also tests the
	 * initializeMap() method Also tests getContent() method
	 */
	@Test
	public void TestScenarioNode() {

		// test for node that is not root

		assertNotNull(node.nodeType);
		assertNotNull(node.getContent());
		assertNull(node.onlyParent);
		assertNull(node.leftChild);
		assertNull(node.rightChild);
		assertNull(node.onlyChild);
		assertNull(node.twoParents.get(0));
		assertNull(node.twoParents.get(1));

		// test for root node content for root node should be null
		assertNull(root.content);
		assertEquals(2, root.cellNumber);
		assertEquals(3, root.buttonNumber);


		/*
		 * Iterate through the labelMap and see if the the keys and values are
		 * added. They should not be null and the labelMap should not be empty
		 * (in our case the size should be 15)
		 */
		for (Map.Entry<String, String> entry : node.labelMap.entrySet()) {
			assertNotNull(entry.getKey());
			assertNotNull(entry.getValue());
		}
		assertFalse(node.labelMap.isEmpty());
		assertEquals(15, node.labelMap.size());

		/*
		 * This is sufficient because we were able to test that everything in
		 * constructor correctly initialized. And for ROOT node, content is not
		 * initialized on purpose.
		 */

	}

	/*
	 * Test the toString() method
	 */
	@Test
	public void TestToString() {
		// test for a node that is not root
		assertEquals(content + System.getProperty("line.separator"), node.toString());

		// test for a root node
		assertEquals("Cells 2" + System.getProperty("line.separator") + "Button 3" + System.getProperty("line.separator"), root.toString());

		/*
		 * This is sufficient because all the other nodes represented by
		 * labelMap are similar to the Text-To-Speech node except the ROOT. The
		 * ROOT has null content and we should make sure that toString() method
		 * works for that as well
		 */
	}

	/*
	 * Tests setOnlyChild() method Also tests getOnlyChild() method
	 */
	@Test
	public void TestSetOnlyChild() {
		root.setOnlyChild(node);
		assertNotNull(root.getOnlyChild());
		assertEquals(node, root.onlyChild);

	}

	/*
	 * Test the setLeft() method
	 */
	@Test
	public void TestSetLeft() {
		// test when the method should return true
		root.setLeft(node);
		assertEquals(node, root.leftChild);

		// test when the method should return false
		root.setLeft(null);
		assertNull(root.leftChild);

		/*
		 * This is sufficient because we only need to test when the method
		 * returns true and when it returns false
		 */

	}

	/*
	 * Test the setRight() method
	 */
	@Test
	public void TestSetRight() {
		// test when the method should return true
		root.setRight(node);
		assertEquals(node, root.rightChild);

		// test when the method should return false
		root.setRight(null);
		assertNull(root.rightChild);

		/*
		 * This is sufficient because we only need to test when the method
		 * returns true and when it returns false
		 */

	}

	/*
	 * Test the setRight() method Also tests getOnly() method
	 */
	@Test
	public void TestSetParent() {
		node.setParent(root);
		assertNotNull(node.getOnlyParent());
		assertNull(node.twoParents.get(0));
		assertNull(node.twoParents.get(1));

		node.setParent(null);
		assertNull(node.onlyParent);

	}

	/*
	 * Test the hasOneChild() method
	 */
	@Test
	public void TestHasOneChild() {

		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = null;
		assertFalse(root.hasOneChild());

		// only case when the method should return true
		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = null;
		assertTrue(root.hasOneChild());

		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasOneChild());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasOneChild());

		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasOneChild());

		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasOneChild());

		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = t2;
		assertFalse(root.hasOneChild());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = t2;
		assertFalse(root.hasOneChild());

		/*
		 * This is sufficient because these are all possible cases in the
		 * hasOneChild() method
		 */

	}

	/*
	 * Test the hasNoChild() method
	 */
	@Test
	public void TestHasNoChild() {

		// only case when the method should return true
		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = null;
		assertTrue(root.hasNoChildren());

		// all other cases return false
		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = null;
		assertFalse(root.hasNoChildren());

		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasNoChildren());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasNoChildren());

		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasNoChildren());

		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasNoChildren());

		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = t2;
		assertFalse(root.hasNoChildren());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = t2;
		assertFalse(root.hasNoChildren());

		/*
		 * This is sufficient because these are all possible cases
		 */
	}

	/*
	 * Test the hasTwoChild() method
	 */
	@Test
	public void TestHasTwoChild() {

		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = null;
		assertFalse(root.hasTwoChildren());

		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = null;
		assertFalse(root.hasTwoChildren());

		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasTwoChildren());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = null;
		assertFalse(root.hasTwoChildren());

		root.onlyChild = null;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasTwoChildren());

		root.onlyChild = node;
		root.leftChild = null;
		root.rightChild = t2;
		assertFalse(root.hasTwoChildren());

		// only case when the method should return true
		root.onlyChild = null;
		root.leftChild = t1;
		root.rightChild = t2;
		assertTrue(root.hasTwoChildren());

		root.onlyChild = node;
		root.leftChild = t1;
		root.rightChild = t2;
		assertFalse(root.hasTwoChildren());

		/*
		 * This is sufficient because these are all possible cases
		 */
	}

	/*
	 * Test getTwoChildren() method
	 */
	@Test
	public void TestGetTwoChildren() {
		// tests when the node has no children, so should return null
		ArrayList<ScenarioNode> empty = node.getTwoChildren();
		assertNull(empty.get(0));
		assertNull(empty.get(1));

		// tests when node has two children
		node.setLeft(t1);
		node.setRight(t2);
		empty = node.getTwoChildren();
		assertNotNull(empty.get(0));
		assertNotNull(empty.get(1));
	}

	/*
	 * Test getTwoParents() method
	 * 
	 * @throws exception
	 * 
	 */
	@Test
	public void TestGetTwoParents() throws IllegalArgumentException {
		try {
			node.getTwoParents();
			fail("Exception should be thrown");
		} catch (IllegalArgumentException ex) {
			assertNull(node.twoParents.get(0));
			assertNull(node.twoParents.get(1));
		}

		node.twoParents.set(0, t1);
		node.twoParents.set(1, t2);
		node.getTwoParents();
		assertNotNull(node.twoParents.get(0));
		assertNotNull(node.twoParents.get(1));

		try {
			node.twoParents.set(0, t1);
			node.twoParents.set(1, null);
			node.getTwoParents();
			fail();
		} catch (IllegalArgumentException ex) {
			assertNotNull(node.twoParents.get(0));
			assertNull(node.twoParents.get(1));
		}

		try {
			node.twoParents.set(0, null);
			node.twoParents.set(1, t2);
			node.getTwoParents();
			fail();
		} catch (IllegalArgumentException ex) {
			assertNull(node.twoParents.get(0));
			assertNotNull(node.twoParents.get(1));
		}
	}

	/*
	 * Test setLeftParent() method
	 */
	@Test
	public void TestSetLeftParent() {
		assertNull(node.twoParents.get(0));
		node.setLeftParent(t1);
		assertNotNull(node.twoParents.get(0));

	}

	/*
	 * Test setLeftParent() method
	 */
	@Test
	public void TestSetRightParent() {
		assertNull(node.twoParents.get(1));
		node.setRightParent(t2);
		assertNotNull(node.twoParents.get(1));

	}

	/*
	 * Test switchNodes() method
	 */
	@Test
	public void TestSwitchNodes() {
		assertEquals("Pause", t1.nodeType);
		assertEquals("100", t1.getContent());
		assertEquals("Set Voice", t2.nodeType);
		assertEquals("Kevin", t2.getContent());

		t1.switchNodes(t2);
		assertEquals("Set Voice", t1.nodeType);
		assertEquals("Kevin", t1.getContent());
		assertEquals("Pause", t2.nodeType);
		assertEquals("100", t2.getContent());

	}

	/*
	 * Test hasOneParent() method
	 */
	@Test
	public void TestHasOneParent() {

		node.twoParents.set(0, null);
		node.twoParents.set(1, null);
		node.onlyParent = null;
		assertFalse(node.hasOneParent());

		// only case when method returns true
		node.twoParents.set(0, null);
		node.twoParents.set(1, null);
		node.onlyParent = root;
		assertTrue(node.hasOneParent());

		node.twoParents.set(0, null);
		node.twoParents.set(1, t2);
		node.onlyParent = null;
		assertFalse(node.hasOneParent());

		node.twoParents.set(0, null);
		node.twoParents.set(1, t2);
		node.onlyParent = root;
		assertFalse(node.hasOneParent());

		node.twoParents.set(0, t1);
		node.twoParents.set(1, null);
		node.onlyParent = null;
		assertFalse(node.hasOneParent());

		node.twoParents.set(0, t1);
		node.twoParents.set(1, null);
		node.onlyParent = root;
		assertFalse(node.hasOneParent());

		node.twoParents.set(0, t1);
		node.twoParents.set(1, t2);
		node.onlyParent = null;
		assertFalse(node.hasOneParent());

		node.twoParents.set(0, t1);
		node.twoParents.set(1, t2);
		node.onlyParent = root;
		assertFalse(node.hasOneParent());

		/*
		 * This is sufficient because these are all possible cases
		 */
	}

	/*
	 * Test setCellNumber() method
	 */
	@Test
	public void TestSetCellNumber() {
		try {
			//cell number is initialized as 2 in setup() method above
			root.setCellNumber(0);
			fail("Exception should be thrown");
		} catch (IndexOutOfBoundsException ex) {
			assertEquals(2, root.cellNumber); 
		}

		try {
			node.setCellNumber(2);
			fail("Exception should be thrown");
		} catch (IllegalStateException ex) {
			assertEquals(0, node.cellNumber);
		}

		root.setCellNumber(5);
		assertEquals(5, root.cellNumber);
	}
	
	/*
	 * Test setButtonNumber() method
	 */
	@Test
	public void TestSetButtonNumber() {
		try {
			//button number is initialized as 3 in setup() method above
			root.setButtonNumber(0);
			fail("Exception should be thrown");
		} catch (IndexOutOfBoundsException ex) {
			assertEquals(3, root.buttonNumber);	
		}

		try {
			node.setButtonNumber(2);
			fail("Exception should be thrown");
		} catch (IllegalStateException ex) {
			assertEquals(0, node.buttonNumber);
		}

		root.setButtonNumber(5);
		assertEquals(5, root.buttonNumber);
	}
}
