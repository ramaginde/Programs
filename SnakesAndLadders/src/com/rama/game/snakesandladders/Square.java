package com.rama.game.snakesandladders;
/**
 * This class will determine the square details
 * whether it is a snake square or ladder square
 * last square etc.
 * @author rginde
 *
 */
public class Square {
	private SnakeLadderBoard board = null;
	private Player player = null;
	private int position;
	private SquareRole squareRole = null;

	public Square(int pos, SnakeLadderBoard b) {
		position = pos;
		board = b;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public int getPosition() {
		return position;
	}

	public void setSquareRole(SquareRole sr) {
		squareRole = sr;
	}

	public boolean isOccupied() {
		return squareRole.isOccupied();
	}

	public boolean isLastSquare() {
		return squareRole.isLastSquare();
	}

	public Square moveAndLand(int moves) {
		return squareRole.moveAndLand(moves);
	}

	public Square findPosition() {
		return squareRole.findPosition();
	}

	public void enter(Player p) {
		squareRole.enter(p);
	}

	public void leave(Player p) {
		squareRole.leave(p);
	}

	public Square findSquare(int shift) {
		return board.findSquare(position + shift);
	}

	public Square findFirstSquare() {
		return board.firstSquare();
	}

	public Square findLastSquare() {
		return board.lastSquare();
	}
}