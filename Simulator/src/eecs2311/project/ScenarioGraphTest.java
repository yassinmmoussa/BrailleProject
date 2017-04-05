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
	private static ScenarioNode root, node, t1, t2;
	private static String test;

	@Before
	public void setUp() throws Exception {
		// test file for testing the creation of
		test = System.getProperty("user.dir") + File.separator + "Scenarios" + File.separator;

		root = new ScenarioNode("Root", "2 3");
		node = new ScenarioNode("Text-To-Speech", "testing TTS system");
		
		try {
			notExists = new ScenarioGraph(new File(test + "notexist.txt"));
			fail("File is not supposed to exist for this test");
		} catch (FileNotFoundException e) {
			assertNull(notExists);
		}
		
//		root = new ScenarioNode("Root", "2 3");
		x = new ScenarioGraph(root);
//		x.setCurrent(root);
//		x.addOneToCurrent(new ScenarioNode("Text-To-Speech","this is a text"));
//		node = root.onlyChild;
//		x.setCurrent(node);
//		x.addOneToCurrent(new ScenarioNode("Play Audio","sound.wav"));
//		node = node.onlyChild;
//		x.setCurrent(root);
		
		
		try {
			exists = new ScenarioGraph(new File(test + "test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("File needs to exist for this test");
		}		
		
	}

	@Test
	public void TestScenarioGraph(){
		
		// tests the constructor ScenarioGraph(ScenarioNode node)
		assertEquals(root, x.root);

		// tests the constructor ScenarioGraph(File file)		
		// for existing file
		
		

	}

	@Test
	public void TestGetScenario() {
		String scenario = exists.getScenario();
		System.out.println(scenario);
		
	}
}
