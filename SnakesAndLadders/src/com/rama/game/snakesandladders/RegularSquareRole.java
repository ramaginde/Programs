package com.rama.game.snakesandladders;
/**
 * This class determines what to do if the square is 
 * not a part of any snake (mouth or tail) or ladder
 * (foot pr top)
 * @author rginde
 *
 */
public final class RegularSquareRole extends SquareRole {
	public RegularSquareRole(Square s) {
		super(s);
	}
}