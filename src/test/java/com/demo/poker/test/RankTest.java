package com.demo.poker.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.demo.poker.model.Board;
import com.demo.poker.model.Hand;
import com.demo.poker.model.Rank;
import com.demo.poker.model.Showdown;

public class RankTest {
	private Board board;
	@Before
	public void setUp() throws Exception {
		board = new Board("4cKs4h8s7s");
	}

	@Test
	public void testEquals() {
		Hand hand1 = new Hand("Ad4s");
		Hand hand2 = new Hand("Ac4d");
		Showdown showdown1 = new Showdown(hand1, board);
		Showdown showdown2 = new Showdown(hand2, board);
		Rank rank1 = new Rank(showdown1);
		Rank rank2 = new Rank(showdown2);
		assertTrue(rank1.getRank() == rank2.getRank());
	}
	
	@Test
	public void testGreater1() {
		Hand hand1 = new Hand("Ad4s");
		Hand hand2 = new Hand("5d6d");
		Showdown showdown1 = new Showdown(hand1, board);
		Showdown showdown2 = new Showdown(hand2, board);
		Rank rank1 = new Rank(showdown1);
		Rank rank2 = new Rank(showdown2);
		assertTrue(rank2.getRank() > rank1.getRank());
	}
	
	@Test
	public void testGreater2() {
		Hand hand1 = new Hand("5d6d");
		Hand hand2 = new Hand("As9s");
		Showdown showdown1 = new Showdown(hand1, board);
		Showdown showdown2 = new Showdown(hand2, board);
		Rank rank1 = new Rank(showdown1);
		Rank rank2 = new Rank(showdown2);
		assertTrue(rank2.getRank() > rank1.getRank());
	}
	
	@Test
	public void testGreater3() {
		Hand hand1 = new Hand("As9s");
		Hand hand2 = new Hand("KhKd");
		Showdown showdown1 = new Showdown(hand1, board);
		Showdown showdown2 = new Showdown(hand2, board);
		Rank rank1 = new Rank(showdown1);
		Rank rank2 = new Rank(showdown2);
		assertTrue(rank2.getRank() > rank1.getRank());
	}
	

}
