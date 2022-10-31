package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue q1;
	private FifoQueue q2;
	private FifoQueue qe1;
	private FifoQueue qe2;
	
	@Before
	
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
		qe1 = new FifoQueue<Integer>();
		qe2 = new FifoQueue<Integer>();
		
		for(int i = 1; i <= 5; i++){
			q1.offer(i);
			q2.offer(i + 5);
		}
	}
	
	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
		qe1 = null;
		qe2 = null;
	}

	@Test
	public void testAppendTwoEmpty() {
		qe1.append(qe2);
		assertEquals("Två tomma ger inte tom", 0, qe1.size());
		assertEquals("", null, qe1.poll());
		assertEquals("", null, qe2.poll());
	}
	
	@Test
	public void testAppendFulltoEmpty() {
		qe1.append(q1);
		assertEquals("Den tomma listan får storleken", + qe1.size(), 5, qe1.size());
		for( int i = 1; i <= 5; i++){
			assertEquals("Genomgång av listan", i, qe1.poll());
		}
		assertEquals("", null, qe1.poll());
		assertEquals("", null, q1.poll());
	}
	
	@Test
	public void testAppendEmptytoFull() {
		q1.append(qe1);
		assertEquals("Den fulla listan får storleken " + q1.size(), 5, q1.size());
		for (int i = 1; i <= 5; i++){
			assertEquals("Genomgång av listan", i, q1.poll());	
		}
		assertEquals("Listan ska vara tom", null, q1.poll());
		assertEquals("Listan ska vara tom", null, qe1.poll());
	}
	
	@Test
	public void testAppendFullToFull() {
		q1.append(q2);
		assertEquals("Den sammanlagda listan får storleken " + q1.size(), 10, q1.size());
		for (int i = 1; i <= 10; i++){
			assertEquals("Genomgång av Listan", i, q1.poll());
		}
		assertEquals("Listan ska vara tom", null, q1.poll());
		assertEquals("Listan ska vara tom", null, q2.poll());
	}
	
	@Test
	(expected = IllegalArgumentException.class)
	public void testAppendEquals() {
	//	try {
			q1.append(q1);
		//	fail("Should raise IllegalArgumentException");
	//	}catch(IllegalArgumentException e){
	//	}
	}

}
