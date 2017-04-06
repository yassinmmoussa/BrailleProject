package eecs2311.project;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CORBA.Current;

import com.mxgraph.model.mxCell;

public class ScenarioGraphTest {
	private static ScenarioGraph x, notExists, exists;
	private static ScenarioNode root, node, node2, t1, t2;
	private static String test;

	@Before
	public void setUp() throws Exception {
		// test file for testing the creation of
		test = System.getProperty("user.dir") + File.separator + "Scenarios" + File.separator;

		root = new ScenarioNode("Root", "2 3");
		node = new ScenarioNode("Text-To-Speech", "testing TTS system");
		node2 = new ScenarioNode("Display String", "testing display");
		t1 = new ScenarioNode("Pause", "1");
		t2 = new ScenarioNode("Text-To-Speech", "testing TTS system");

		try {
			notExists = new ScenarioGraph(new File(test + "notexist.txt"));
			fail("File is not supposed to exist for this test");
		} catch (FileNotFoundException e) {
			assertNull(notExists);
		}

		// root = new ScenarioNode("Root", "2 3");
		x = new ScenarioGraph(root);
		// x.setCurrent(root);
		// x.addOneToCurrent(new ScenarioNode("Text-To-Speech","this is a
		// text"));
		// node = root.onlyChild;
		// x.setCurrent(node);
		// x.addOneToCurrent(new ScenarioNode("Play Audio","sound.wav"));
		// node = node.onlyChild;
		// x.setCurrent(root);

		try {
			exists = new ScenarioGraph(new File(test + "test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("File needs to exist for this test");
		}

	}

	@Test
	public void TestScenarioGraph() {

		// tests the constructor ScenarioGraph(ScenarioNode node)
		assertEquals(root, x.root);

		// tests the constructor ScenarioGraph(File file)
		// for existing file

	}

	/*
	 * Tests the getScenario() method
	 */
	@Test
	public void TestGetScenario() {

		// first append everything in our scenario file to a string
		String fileScenario = "";
		try {
			Scanner file = new Scanner(new File(test + "test.txt"));
			fileScenario = file.nextLine();
			while (file.hasNextLine()) {
				// System.out.println(fileScenario);
				fileScenario = fileScenario + file.nextLine();
			}
			file.close();

		} catch (FileNotFoundException e) {
			fail("file should exist");
		}
		// System.out.println(fileScenario);

		// Then get the string created by getScenario() method for the same file
		String getScenario = "";
		Scanner read = new Scanner(exists.getScenario());
		getScenario = read.nextLine();
		while (read.hasNextLine()) {
			getScenario = getScenario + read.nextLine();
		}
		// System.out.println(getScenario);
		read.close();

		// compare the strings to see if they are the same
		assertEquals(getScenario, fileScenario);

		/*
		 * This is sufficient because we simply need to check if the string is
		 * the same as our scenario file.
		 */
	}

	/*
	 * Tests the addTwoToCurrent() method
	 */
	@Test
	public void TestAddTwoToCurrent() {
		// when current node has no children
		ScenarioGraph noCh = new ScenarioGraph(root);
		noCh.setCurrent(root);
		noCh.addTwoToCurrent(t1, t2);

		noCh.setCurrent(root.leftChild);
		assertEquals(t1, noCh.current);
		noCh.setCurrent(root.rightChild);
		assertEquals(t2, noCh.current);

		// when currentnode has one child
		ScenarioGraph oneCh = new ScenarioGraph(root);
		oneCh.setCurrent(root);
		oneCh.addOneToCurrent(node);
		oneCh.addTwoToCurrent(t1, t2);

		oneCh.setCurrent(root.leftChild);
		assertEquals(t1, oneCh.current);
		oneCh.setCurrent(root.rightChild);
		assertEquals(t2, oneCh.current);
		oneCh.setCurrent(root.leftChild.onlyChild);
		assertEquals(node, oneCh.current);
		oneCh.setCurrent(root.rightChild.onlyChild);
		assertEquals(node, oneCh.current);

		// when current node has two child
		ScenarioGraph twoCh = new ScenarioGraph(root);
		twoCh.setCurrent(root);
		twoCh.addTwoToCurrent(t1, t2);
		twoCh.addTwoToCurrent(node, node2);

		twoCh.setCurrent(root.leftChild.onlyChild);
		assertEquals(t1, twoCh.current);
		twoCh.setCurrent(root.rightChild.onlyChild);
		assertEquals(t2, twoCh.current);
		twoCh.setCurrent(root.leftChild);
		assertEquals(node, twoCh.current);
		twoCh.setCurrent(root.rightChild);
		assertEquals(node2, twoCh.current);

		/*
		 * This is sufficient because all cases are covered in the method
		 */

	}

	/*
	 * Tests the addTwoToCurrent() method
	 */
	@Test
	public void TestAddOneToCurrent() {
		// when current node has no children
		ScenarioGraph noCh = new ScenarioGraph(root);
		noCh.setCurrent(root);
		noCh.addOneToCurrent(node);

		noCh.setCurrent(root.onlyChild);
		assertEquals(node, noCh.current);

		// when current node has one child
		ScenarioGraph oneCh = new ScenarioGraph(root);
		oneCh.setCurrent(root);
		oneCh.addOneToCurrent(node);
		oneCh.addOneToCurrent(t1);

		oneCh.setCurrent(root.onlyChild);
		assertEquals(t1, oneCh.current);
		oneCh.setCurrent(root.onlyChild.onlyChild);
		assertEquals(node, oneCh.current);

		// when current node has two child
		ScenarioGraph twoCh = new ScenarioGraph(root);
		twoCh.setCurrent(root);
		twoCh.addTwoToCurrent(t1, t2);
		twoCh.addOneToCurrent(node);

		twoCh.setCurrent(root.onlyChild);
		assertEquals(node, twoCh.current);
		twoCh.setCurrent(root.onlyChild.leftChild);
		assertEquals(t1, twoCh.current);
		twoCh.setCurrent(root.onlyChild.rightChild);
		assertEquals(t2, twoCh.current);

		/*
		 * This is sufficient because all cases are covered in the method
		 */

	}

	/*
	 * Tests getGraph() method
	 */
	@Test
	public void testGetGraph() {
		// when current node has two child
		ScenarioGraph twoCh = new ScenarioGraph(root);
		twoCh.setCurrent(root);
		twoCh.addTwoToCurrent(t1, t2);
		twoCh.addTwoToCurrent(node, node2);

		twoCh.setCurrent(root.leftChild.onlyChild);
		assertEquals(t1, twoCh.current);
		twoCh.setCurrent(root.rightChild.onlyChild);
		assertEquals(t2, twoCh.current);
		twoCh.setCurrent(root.leftChild);
		assertEquals(node, twoCh.current);
		twoCh.setCurrent(root.rightChild);
		assertEquals(node2, twoCh.current);
		
		//test method now
		twoCh.getGraph();
	}
}
