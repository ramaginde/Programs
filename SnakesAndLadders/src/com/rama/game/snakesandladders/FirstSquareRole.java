package com.rama.game.snakesandladders;

import java.util.ArrayList;
/**
 * This class will be used during the game to determine 
 * the moves of the players
 * @author rginde
 *
 */
public final class FirstSquareRole extends SquareRole {
	private ArrayList<Player> players = new ArrayList<Player>();

	public FirstSquareRole(Square s) {
		super(s);
	}

	@Override
	public boolean isFirstSquare() {
		return true;
	}

	@Override
	public void enter(Player player) {
		players.add(player);
		player.setSquare(square);
	}

	@Override
	public void leave(Player player) {
		players.remove(player);
	}

	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}
}