package com.demo.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.demo.poker.model.Board;
import com.demo.poker.model.Hand;
import com.demo.poker.model.Showdown;

public class App {

	public static void main(String[] args) {
		if (args[0].length() != 10) {
			throw new IllegalArgumentException("Board length must be 10 character!");
		}

		Board board = new Board(args[0]);

		List<Showdown> showdowns = new ArrayList<Showdown>();
		for (int i = 1; i < args.length; i++) {
			if (args[i].length() != 4) {
				throw new IllegalArgumentException("Hand length must be 4 character!");
			}
			showdowns.add(new Showdown(new Hand(args[i]), board));
		}

		Collections.sort(showdowns);
		StringBuilder printer = new StringBuilder();
		printer.append(board.getCards()).append(" ");
		printer.append(showdowns.get(0).getHand().getCards());
		for (int i = 1; i < showdowns.size(); i++) {

			if (showdowns.get(i).compareTo(showdowns.get(i - 1)) == 0) {
				printer.append("=");
			} else {
				printer.append(" ");
			}
			printer.append(showdowns.get(i).getHand().getCards());
		}
		System.out.println(printer.toString());
	}

}
