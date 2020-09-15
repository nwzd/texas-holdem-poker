package com.demo.poker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.demo.poker.constant.Royal;

public class Showdown implements Comparable<Showdown> {
	private static final String REGEX_SDCH = "(?<=[sdch])";
	private static final String REGEX_DIGIT_CHAR = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
	private Hand hand;
	private Board board;
	private Map<Integer, List<String>> ofAKind;
	private Map<String, List<Integer>> flush;
	private Map<String, List<Integer>> straight;
	private Map<Integer, List<String>> cards;

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Showdown(Hand hand, Board board) {
		this.hand = hand;
		this.board = board;
		String allCards = this.hand.getCards().concat(board.getCards()).replace(Royal.A.name(), "14")
				.replace(Royal.K.name(), "13").replace(Royal.Q.name(), "12").replace(Royal.J.name(), "11")
				.replace(Royal.T.name(), "10");
		init(allCards.split(REGEX_SDCH));
	}

	private void init(String[] array) {
		Map<Integer, List<String>> valueMap = new HashMap<Integer, List<String>>();
		Map<String, List<Integer>> suitMap = new HashMap<String, List<Integer>>();
		for (int i = 0; i < array.length; i++) {
			String[] split = array[i].split(REGEX_DIGIT_CHAR);

			Integer value = Integer.valueOf(split[0]);
			String suit = split[1];
			if (valueMap.get(value) == null) {
				valueMap.put(value, new ArrayList<String>(Arrays.asList(suit)));
			} else {
				valueMap.get(value).add(suit);
			}
			if (suitMap.get(suit) == null) {
				suitMap.put(suit, new ArrayList<Integer>(Arrays.asList(value)));
			} else {
				suitMap.get(suit).add(value);
			}
		}
		for (Entry<String, List<Integer>> entry : suitMap.entrySet()) {
			Collections.sort(entry.getValue());
		}
		// System.out.println("valueMap: " + valueMap);
		// System.out.println("suitMap: " + suitMap);

		cards = valueMap;
		ofAKind = getOfAKind(valueMap);
		flush = getFlush(suitMap);
		straight = getStraight(valueMap);

	}

	private Map<Integer, List<String>> getOfAKind(Map<Integer, List<String>> valueMap) {
		Map<Integer, List<String>> ofAKind = new HashMap<Integer, List<String>>();
		for (Entry<Integer, List<String>> entry : valueMap.entrySet()) {
			if (entry.getValue().size() > 1) {
				ofAKind.put(entry.getKey(), entry.getValue());
			}
		}
		// System.out.println("ofAKind: " + ofAKind);
		return ofAKind;
	}

	private Map<String, List<Integer>> getStraight(Map<Integer, List<String>> valueMap) {
		Map<String, List<Integer>> straight = new HashMap<String, List<Integer>>();
		ArrayList<Integer> keySet = new ArrayList<>(valueMap.keySet());
		Collections.sort(keySet, Collections.reverseOrder());
		int counter = 1;
		for (int i = 1; i < keySet.size(); i++) {
			if (keySet.get(i) == (keySet.get(i - 1) - 1)) {
				counter++;
				if (counter == 5) {
					straight.put("o", keySet.subList(i - 4, i + 1));
					break;
				}
			} else {
				counter = 1;
			}

		}

		// System.out.println("straight: " + straight);
		return straight;
	}

	private Map<String, List<Integer>> getFlush(Map<String, List<Integer>> suitMap) {
		Map<String, List<Integer>> flush = new HashMap<String, List<Integer>>();
		for (Entry<String, List<Integer>> entry : suitMap.entrySet()) {
			if (entry.getValue().size() >= 5) {
				flush.put(entry.getKey(),
						entry.getValue().subList(entry.getValue().size() - 5, entry.getValue().size()));
			}
		}
		// System.out.println("flush: " + flush);
		return flush;
	}

	public Map<Integer, List<String>> getOfAKind() {
		return ofAKind;
	}

	public Map<String, List<Integer>> getFlush() {
		return flush;
	}

	public Map<String, List<Integer>> getStraight() {
		return straight;
	}

	public Map<Integer, List<String>> getCards() {
		return cards;
	}

	@Override
	public int compareTo(Showdown o) {
		return (new Rank(this).getRank()) - (new Rank(o).getRank());
	}

}
