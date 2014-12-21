package com.rama.game.snakesandladders;
/**
 * This class will be used to get the 
 * players' position
 * @author rginde
 *
 */
public final class Player {
	private Square square = null;
	private String name;

	public Square getSquare() {
		return square;
	}

	public void setSquare(Square square) {
		this.square = square;
	}

	public int position() {
		return square.getPosition();
	}

	public String getName() {
		return name;
	}

	public Player(String strname) {
		name = strname;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean wins() {
		return square.isLastSquare();
	}

	public void moveForward(int moves) {
		square.leave(this);
		square = square.moveAndLand(moves);
		square.enter(this);
	}
}