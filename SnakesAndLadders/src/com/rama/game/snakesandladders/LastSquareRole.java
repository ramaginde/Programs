package com.rama.game.snakesandladders;
/**
 * This class will determine if the player has come 
 * to the end square and to decide if the game is over.
 * @author rginde
 *
 */
public final class LastSquareRole extends SquareRole {
	public LastSquareRole(Square s) {
		super(s);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
}