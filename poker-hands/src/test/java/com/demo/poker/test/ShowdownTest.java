package com.demo.poker.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.demo.poker.model.Board;
import com.demo.poker.model.Hand;
import com.demo.poker.model.Showdown;

public class ShowdownTest {
	private Showdown showdown;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testEmpty() {
		Board board = new Board("3s4h6d8cJs");
		Hand hand = new Hand("7sAh");
		showdown = new Showdown(hand, board);
		assertTrue(showdown.getFlush().isEmpty());
		assertTrue(showdown.getOfAKind().isEmpty());
		assertTrue(showdown.getStraight().isEmpty());
	}
	@Test
	public void testStraight() {
		Board board = new Board("3s4h2d7cJs");
		Hand hand = new Hand("5s6h");
		showdown = new Showdown(hand, board);
		assertTrue(showdown.getFlush().isEmpty());
		assertTrue(showdown.getOfAKind().isEmpty());
		assertTrue(showdown.getStraight().size() == 1);
	}
	
	@Test
	public void testFlush() {
		Board board = new Board("3s4s2s7cJs");
		Hand hand = new Hand("5s6s");
		showdown = new Showdown(hand, board);
		assertTrue(showdown.getFlush().size() == 1);
		assertTrue(showdown.getOfAKind().isEmpty());
		assertTrue(showdown.getStraight().size() == 1);
	}
	
	@Test
	public void testOfAKind() {
		Board board = new Board("4c4s2s7cJs");
		Hand hand = new Hand("4d6s");
		showdown = new Showdown(hand, board);
		assertTrue(showdown.getFlush().isEmpty());
		assertTrue(showdown.getOfAKind().size() == 1);
		assertTrue(showdown.getStraight().isEmpty());
	}
}
