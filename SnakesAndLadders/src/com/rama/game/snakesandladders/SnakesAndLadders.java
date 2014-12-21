package com.rama.game.snakesandladders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This is the main program that will be used to run the game logic. This class
 * allows user to input the number of players, the number of squares on the
 * board and validations to confirm that the value of top of ladder is greater
 * than the value of foot and the value of mouth of snake is greater than the
 * value of tail of snake.
 * 
 * @author rginde
 * 
 */
public final class SnakesAndLadders {
	public static String snakeErrorMsg = "Head of snake cannot be less than the tail";
	public static String ladderErrorMsg = "Foot of ladder cannot be greater than the top";
	public static String squareValueErrMsg = "The value of the square cannot be greater than the total number of squares in the game";

	public static void main(String[] args) {
		String noOfPlayers = null;
		String numSquares = null;
		System.out.print("Enter the number of players: \n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			noOfPlayers = br.readLine();
			if ("".equals(noOfPlayers) || Integer.parseInt(noOfPlayers) <= 0) {
				System.out.println("Invalid entry for number of players! \n");
				System.exit(0);
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read data! \n");
			System.exit(1);
		}
		String[] playerArray = new String[Integer.parseInt(noOfPlayers)];
		int playerCount = 0;
		while (playerCount < Integer.parseInt(noOfPlayers)) {
			System.out.print("\n Enter the name of player:  \n" + playerCount
					+ 1 + "\n");
			br = new BufferedReader(new InputStreamReader(System.in));
			try {
				playerArray[playerCount] = br.readLine();
				while ("".equals(playerArray[playerCount])) {
					System.out.println("Please enter a valid name!! \n");
					playerArray[playerCount] = br.readLine();
				}
				System.out.print("You entered the name ::::  "
						+ playerArray[playerCount]);
				playerCount++;
			} catch (IOException e) {
				System.out.println("IO error trying to read your name! \n");
			}
		}

		System.out.print("\nEnter the number of squares: \n");
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			numSquares = br.readLine();
			if ("".equals(numSquares) || Integer.parseInt(numSquares) < 100) {
				System.out
						.println("Invalid entry for number of squares (Squares have to be minimun 100)! \n");
				System.exit(0);
			}
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name! \n");
			System.exit(1);
		}
		// for the user first square is at position 1 but
		// internally is at 0
		// Creating snakes and ladder map for value comparison
		Map<String, String> snakeMap = new HashMap<String, String>();
		Map<String, String> ladderMap = new HashMap<String, String>();

		snakeMap = createMapFromPropertiesFile("snakes.properties", null);
		ladderMap = createMapFromPropertiesFile("ladder.properties", snakeMap);

		readAndValidateValuesFromProperties("snakes.properties", true,
				numSquares);
		readAndValidateValuesFromProperties("ladder.properties", false,
				numSquares);

		int[][] snakeArray = new int[snakeMap.size()][2];
		int count = 0;
		for (Map.Entry<String, String> entry : snakeMap.entrySet()) {
			snakeArray[count][0] = Integer.parseInt(entry.getKey());
			snakeArray[count][1] = Integer.parseInt(entry.getValue());
			count++;
		}
		int[][] ladderArray = new int[ladderMap.size()][2];
		count = 0;
		for (Map.Entry<String, String> entry : ladderMap.entrySet()) {
			ladderArray[count][0] = Integer.parseInt(entry.getKey().trim());
			ladderArray[count][1] = Integer.parseInt(entry.getValue().trim());
			count++;
		}
		Game game = new Game(playerArray, Integer.parseInt(numSquares),
				snakeArray, ladderArray);
		game.play();
	}

	private static void readAndValidateValuesFromProperties(String fileName,
			boolean isSnake, String numSquares) {
		Properties prop = new Properties();

		InputStream inputStream = SnakesAndLadders.class.getClassLoader()
				.getResourceAsStream(fileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + fileName
						+ "' not found in the classpath");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Enumeration enuKeys = prop.keys();
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = prop.getProperty(key);
			System.out.println(key + ": " + value);
			if (Integer.parseInt(key.trim()) > Integer.parseInt(numSquares
					.trim())
					|| Integer.parseInt(value.trim()) > Integer
							.parseInt(numSquares.trim())) {
				System.out.println(squareValueErrMsg);
				System.exit(0);
			}
			if (isSnake) {
				if (Integer.parseInt(key.trim()) < Integer.parseInt(value
						.trim())) {
					System.out.println(snakeErrorMsg);
					System.exit(0);
				}
			} else {
				if (Integer.parseInt(key.trim()) > Integer.parseInt(value
						.trim())) {
					System.out.println(ladderErrorMsg);
					System.exit(0);
				}
			}

		}
	}

	private static Map<String, String> createMapFromPropertiesFile(
			String fileName, Map<String, String> comparingMap) {
		Map<String, String> dataMap = new HashMap<String, String>();
		Properties prop = new Properties();

		InputStream inputStream = SnakesAndLadders.class.getClassLoader()
				.getResourceAsStream(fileName);
		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {

				throw new FileNotFoundException("property file '" + fileName
						+ "' not found in the classpath");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Enumeration enuKeys = prop.keys();
		while (enuKeys.hasMoreElements()) {
			String key = (String) enuKeys.nextElement();
			String value = prop.getProperty(key);
			if (!dataMap.containsValue(value)) {
				dataMap.put(key, value);
			}
		}
		if (comparingMap == null) {
			return dataMap;
		} else {
			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				if (comparingMap.containsKey(entry.getKey())
						|| comparingMap.containsValue(entry.getValue())
						|| comparingMap.containsKey(entry.getValue())
						|| comparingMap.containsValue(entry.getKey())) {
					System.out
							.println("Either head or tail of snake is coinciding with the top or foot of ladder");
					System.exit(0);
				}
			}
		}
		return dataMap;
	}
}