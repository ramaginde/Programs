package com.rama.game.snakesandladders;

import java.util.ArrayList;
/**
 * This class is used to create the snakes and ladders
 * platform with squares arranged for the players to play
 * @author rginde
 *
 */
public final class SnakeLadderBoard {
	private ArrayList<Square> squares = new ArrayList<Square>();
	
	public SnakeLadderBoard(int numSquares, int[][] ladders, int[][] snakes) {
		createSquares(numSquares);
		setupLadders(ladders);
		setupSnakes(snakes);
	}

	private void createSquares(int numSquares) {
		System.out.println(" There are " + numSquares + " squares ");
		for (int position = 0; position < numSquares; position++) {
			Square square = new Square(position, this);
			squares.add(square);
			square.setSquareRole(new RegularSquareRole(square));
		}
		firstSquare().setSquareRole(new FirstSquareRole(firstSquare()));
		lastSquare().setSquareRole(new LastSquareRole(lastSquare()));
	}

	public Square firstSquare() {
		return squares.get(0);
	}

	public Square lastSquare() {
		return squares.get(squares.size() - 1);
	}

	public Square findSquare(int position) {
		return squares.get(position);
	}

	private int numberOfSquares() {
		return squares.size();
	}

	private void setupSnakes(int[][] snakes) {
		for (int i = 0; i < snakes.length; i++) {
			int fromPosition = snakes[i][0] - 1;
			int toPosition = snakes[i][1] - 1;
			int transport = toPosition - fromPosition;

			System.out.println(" snake from " + (fromPosition + 1) + " to "
					+ (toPosition + 1));
			Square snakeSquare = squares.get(fromPosition);
			snakeSquare.setSquareRole(new Snake(snakeSquare, transport));
		}
	}

	private void setupLadders(int[][] ladders) {
		for (int i = 0; i < ladders.length; i++) {

			int fromPosition = ladders[i][0] - 1;
			int toPosition = ladders[i][1] - 1;
			int transport = toPosition - fromPosition;
			System.out.println(" ladder from " + (fromPosition + 1) + " to "
					+ (toPosition + 1));

			Square ladderSquare = squares.get(fromPosition);
			ladderSquare.setSquareRole(new Ladder(ladderSquare, transport));
		}
	}
}