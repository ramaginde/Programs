package com.rama.game.snakesandladders;
/**
 * This class is used to display the moves when a 
 * player comes on the ladder square.
 * @author rginde
 *
 */
public final class Ladder extends SquareRole {
	private int move;

	public Ladder(Square s, int t) {
		super(s);
		move = t;
	}

	@Override
	public Square findPosition() {
		System.out.println(" ladder from " + (square.getPosition() + 1)
				+ " to " + (destination().getPosition() + 1));
		return destination().findPosition();
	}

	private Square destination() {
		return square.findSquare(move);
	}
}