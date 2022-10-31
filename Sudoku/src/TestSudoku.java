package sudoku;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSudoku {
	private Sudoku sod1;
	private Sudoku sod2;
	private Sudoku sod3;
	private Sudoku sod4;
	private Sudoku sod5;
	

	@Before
	public void setUp() throws Exception {
		sod1 = new Sudoku();
		sod2 = new Sudoku();
		sod3 = new Sudoku();
		sod4 = new Sudoku();
		sod5 = new Sudoku();
		sod2.setValue(0, 2, 8);
  		sod2.setValue(0, 5, 9);
  		sod2.setValue(0, 7, 6);
  		sod2.setValue(0, 8, 2);
  		sod2.setValue(1, 8, 5);
  		sod2.setValue(2, 0, 1);
  		sod2.setValue(2, 2, 2);
  		sod2.setValue(2, 3, 5);
  		sod2.setValue(3, 3, 2);
  		sod2.setValue(3, 4, 1);
  		sod2.setValue(3, 7, 9);
  		sod2.setValue(4, 1, 5);
  		sod2.setValue(4, 1, 5);
  		sod2.setValue(4, 6, 6);
  		sod2.setValue(5, 0, 6);
  		sod2.setValue(5, 7, 2);
  		sod2.setValue(5, 8, 8);
  		sod2.setValue(6, 0, 4);
  		sod2.setValue(6, 1, 1);
  		sod2.setValue(6, 3, 6);
  		sod2.setValue(6, 5, 8);
  		sod2.setValue(7, 0, 8);
  		sod2.setValue(7, 1, 6);
  		sod2.setValue(7, 4, 3);
  		sod2.setValue(7, 6, 1);
  		sod2.setValue(8, 6, 4);
		sod3.setValue(0, 0, 1);
		sod3.setValue(0, 1, 1);
		sod4.setValue(0, 0, 1);
		sod4.setValue(1, 0, 1);
		sod5.setValue(0, 0, 1);
		sod5.setValue(1, 2, 1);
	}

	@After
	public void tearDown() throws Exception {
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				sod1.setValue(i, j, 0);
				sod2.setValue(i, j, 0);
				sod3.setValue(i, j, 0);
				sod4.setValue(i, j, 0);
				sod5.setValue(i, j, 0);
			}
		}
	}

	@Test
	public void testGetValue() {
		sod1.setValue(0, 0, 1);
		assertEquals("Värdena lika", + sod1.getValue(0, 0), 1, sod1.getValue(0, 0));
	}

	@Test
	public void testSetValue() {
		sod1.setValue(0, 0, 1);
		assertEquals("Värdena är lika", 1, sod1.getValue(0, 0));
	}
	
	@Test
	public void testSolveEmpty() {
		assertTrue("Matrisen är lösningsbar", sod1.solve());
	}
	
	@Test
	public void testSolveFig1() {
		assertTrue("Matrisen i Fig.1 är lösningsbar", sod2.solve());
		
	}
	
	@Test
	public void testSolveDoubleRow() {
		assertEquals("Detta är inte lösningsbart", false, sod4.solve());
		
	}
	
	@Test
	public void testSolveDoubleCol() {
		assertEquals("Detta är inte lösningsbart", false, sod3.solve());
		
	}
	
	@Test
	public void testSolveDoubleBox() {
		assertEquals("Detta är inte lösningsbart", false, sod5.solve());
		
	}

}
