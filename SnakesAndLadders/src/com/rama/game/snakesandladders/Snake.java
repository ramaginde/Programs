package com.rama.game.snakesandladders;
/**
 * This class is used to display the moves when a 
 * player comes on the snake square.
 * @author rginde
 *
 */
public final class Snake extends SquareRole {
	private int move;

	public Snake(Square s, int t) {
		super(s);
		move = t;

	}

	@Override
	public Square findPosition() {
		System.out.println(" snake from " + (square.getPosition() + 1) + " to "
				+ (destination().getPosition() + 1));
		return destination().findPosition();
	}

	private Square destination() {
		return square.findSquare(move);
	}
}