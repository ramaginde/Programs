/**
 * 
 */
package com.rama.game.test;

import com.rama.game.snakesandladders.Game;

import junit.framework.TestCase;

/**
 * @author rginde
 *
 */
public class GameTest extends TestCase {

	public int noOfplayers = 0;
	public int nunOfsquares = 0;
	public int [][] snakesArray = {{17,7},{87,24},{62,19},{64,60},{95,75},{99,78},{93,73}};
	public int [][] laddersArray = { {4,14},{9,31},{28,84},{40,59},{63,81},{71,91},{20,38} };
	public String playersArray [] = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	protected void setUp() throws Exception {
		noOfplayers = 2;
		nunOfsquares = 120;
		playersArray = new String[noOfplayers];
		playersArray[0]= "Bob";
		playersArray[1]= "Alice";
	}

	/**
	 * Test method for {@link com.rama.game.snakesandladders.Game#play()}.
	 */
	public void testPlay() {
		Game snakesandladdersGame = new Game(playersArray,nunOfsquares,snakesArray,laddersArray);
		snakesandladdersGame.play();
	}

}
