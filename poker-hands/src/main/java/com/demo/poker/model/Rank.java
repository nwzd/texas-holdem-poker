package com.demo.poker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Rank {

	private static final int INCREMENT = 15;
	private Showdown showdown;

	public Rank(Showdown showdown) {
		this.showdown = showdown;
	}

	public Integer rankStraight() {
		Integer rank = 0;
		Map<String, List<Integer>> straight = showdown.getStraight();
		if (straight.size() == 1 && straight.values().iterator().next().size() == 5) {
			List<Integer> next = straight.values().iterator().next();
			if (Collections.min(next) == 2 && Collections.max(next) == 14) {
				rank = 5 + 4 * INCREMENT;
			}
			rank = Collections.max(next) + 5 * INCREMENT;
		}
		// System.out.println("rankStraight: " + rank);
		return rank;
	}

	public Integer rankFlush() {
		Integer rank = 0;
		Map<String, List<Integer>> flush = showdown.getFlush();
		if (flush.size() == 1 && flush.values().iterator().next().size() == 5) {
			rank = Collections.max(flush.values().iterator().next()) + 6 * INCREMENT;
		}
		// System.out.println("rankFlush: " + rank);
		return rank;
	}

	public Integer rankFourOfAKind() {
		Integer rank = 0;
		Map<Integer, List<String>> ofAKind = showdown.getOfAKind();
		if (ofAKind.size() == 1 && ofAKind.values().iterator().next().size() == 4) {
			rank = Collections.max(ofAKind.keySet()) + 8 * INCREMENT;
		}
		// System.out.println("rankFourOfAKind: " + rank);
		return rank;
	}

	public Integer rankThreeOfAKind() {
		Integer rank = 0;
		Map<Integer, List<String>> ofAKind = showdown.getOfAKind();
		if (ofAKind.size() == 1 && ofAKind.values().iterator().next().size() == 3) {
			rank = Collections.max(ofAKind.keySet()) + 4 * INCREMENT;
		}
		// System.out.println("rankThreeOfAKind: " + rank);
		return rank;

	}

	public Integer rankFullHouse() {
		Integer rank = 0;
		boolean check3 = false;
		boolean check2 = false;
		Map<Integer, List<String>> ofAKind = showdown.getOfAKind();
		if (ofAKind.size() > 1) {
			for (Entry<Integer, List<String>> entry : ofAKind.entrySet()) {
				if (entry.getValue().size() == 3) {
					check3 = true;
				}
				if (entry.getValue().size() == 2) {
					check2 = true;
				}
			}
		}

		if (check3 && check2) {
			rank = Collections.max(ofAKind.keySet()) + 7 * INCREMENT;
		}

		// System.out.println("rankFullHouse: " + rank);
		return rank;
	}

	public Integer rankTwoPairs() {
		Integer rank = 0;
		boolean check2 = false;
		boolean checkOthers = false;
		Map<Integer, List<String>> ofAKind = showdown.getOfAKind();
		if (ofAKind.size() > 1) {
			for (Entry<Integer, List<String>> entry : ofAKind.entrySet()) {
				if (entry.getValue().size() >= 2) {
					check2 = true;
				}
				if (entry.getValue().size() < 3) {
					checkOthers = true;
				}
			}
		}

		if (check2 && checkOthers) {
			rank = Collections.max(ofAKind.keySet()) + 3 * INCREMENT;
		}

		// System.out.println("rankTwoPairs: " + rank);
		return rank;
	}

	public Integer rankPair() {
		Integer rank = 0;
		Map<Integer, List<String>> ofAKind = showdown.getOfAKind();
		if (ofAKind.size() == 1 && ofAKind.values().iterator().next().size() == 2) {
			rank = Collections.max(ofAKind.keySet()) + 2 * INCREMENT;
		}
		// System.out.println("rankPair: " + rank);
		return rank;
	}

	public Integer rankHighCard() {
		Integer rank = 0;
		Map<Integer, List<String>> cards = showdown.getCards();
		if (cards.size() == 7) {
			rank = Collections.max(cards.keySet());
		}
		// System.out.println("rankHighCard: " + rank);
		return rank;
	}

	public Integer rankStraightFlush() {
		Integer rank = 0;
		Map<String, List<Integer>> straight = showdown.getStraight();
		if (straight.size() == 1 && straight.values().iterator().next().size() == 5 && showdown.getFlush().size() == 1
				&& showdown.getFlush().values().iterator().next().size() == 5) {
			List<Integer> next = straight.values().iterator().next();
			if (Collections.min(next) == 2 && Collections.max(next) == 14) {
				rank = 5 + 9 * INCREMENT;
			}
			rank = Collections.max(straight.values().iterator().next()) + 9 * INCREMENT;
		}
//		 System.out.println("rankStraightFlush: " + rank);
		return rank;
	}

	public Integer getRank() {
		ArrayList<Integer> rankList = new ArrayList<Integer>();
		rankList.add(rankFlush());
		rankList.add(rankFourOfAKind());
		rankList.add(rankFullHouse());
		rankList.add(rankHighCard());
		rankList.add(rankPair());
		rankList.add(rankStraight());
		rankList.add(rankStraightFlush());
		rankList.add(rankThreeOfAKind());
		rankList.add(rankTwoPairs());
		return Collections.max(rankList);
	}

}
