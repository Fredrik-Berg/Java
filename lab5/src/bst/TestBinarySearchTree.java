package bst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchTree {
	private BinarySearchTree<Integer> bst1;
	private BinarySearchTree<Integer> bst2;
	
	@Before
	public void setUp() throws Exception{
		bst1 = new BinarySearchTree<>();
		bst2 = new BinarySearchTree<>();
		bst1.add(3);
		bst1.add(5);
		bst1.add(1);
		bst1.add(2);
		bst1.add(4);
		bst1.add(0);
	}
	
	@After
	public final void tearDown() throws Exception{
			bst1 = null;
		
	}
	
	
	@Test
	public void testHeight(){
		bst1.height();
		assertEquals("Höjden på trädet blir: ", + bst1.height(), 3, bst1.height());
	}
	
	@Test
	public void testEmptyHeight(){
		bst2.height();
		assertEquals("Höjden på det tomma trädet blir: ", + bst2.height(), 0, bst2.height());
	}
	
	@Test
	public void testSize(){
		bst1.size();
		assertEquals("Storleken på trädet blir: ", + bst1.size(), 6, bst1.size());
	}
	
	@Test
	public void testEmptySize(){
		bst2.size();
		assertEquals("Storleken på det tomma trädet blir: ", +bst2.size(), 0, bst2.size());
	}
	
	@Test
	public void testAdd(){
		bst2.add(7);
		assertEquals("Noden lades till", 1, bst2.size());
	}
	
	@Test
	public void testDoublettAdd(){
		bst2.add(2);
		bst2.add(2);
		assertEquals("Det lades bara till en nod", 1, bst2.size());
	}
}
