package Connect4;

import java.util.HashSet;

/**
 * Board Class.
 * Provides the board and all methods necessary for playing connect 4
 * @author elianeiselt
 *
 */
public class Board {

	/** input storage. */
	private String[][] storage;
	/** damit man nach oben net zu viele hat. */
	private int[] colCount = { 0, 0, 0, 0, 0, 0, 0 };
	/** ka hat cds gemacht. */
	private final HashSet<View> views = new HashSet<>();

///////////////////////////
// Constructor
	
	public Board() {
		this(6, 7);
	}

	public Board(int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException("Illegal size");
		}

		storage = new String[width][height];
	}

///////////////////////////
// Getter / Setter

	/**
	 * fills @storage with - / behandeln als wäre es leer.
	 */
	public void setEmpty() {
		for (int i = 0; i < storage.length; i++) {
			for (int j = 0; j < storage[i].length; j++) {
				storage[i][j] = " - ";
			}
		}
	}

	/**
	 * adds a player stone to @storage and updates view.
	 * @param player the player whos placing
	 * @param col the column where he wants to playce
	 * @param input the string to input
	 */
	public void setPlayerPos(Player player, int col, String input) {

		if (col < 1 || col > 7) {
			return;
		}

		if (colCount[col - 1] < 6) {
			for (int row = 5; row >= 0; row--) {
				if (storage[row][col - 1].equals(" - ")) {
					storage[row][col - 1] = player.getSymbol();
					colCount[col - 1] += 1;
					updateViews();
					return;
				}
			}
		}

	}

	/**
	 * Getts the value in @storage at the given position.
	 * @param x "row"
	 * @param y	"column"
	 * @return string at given location
	 */
	public String getPos(int row, int col) {
		return storage[row][col];
	}
	
	/**
	 * Get array row length.
	 * @return row length
	 */
	public int getBoardRowLength() {
		return storage.length;
	}
	
	/**
	 * get array column length.
	 * @return column length
	 */
	public int getBoardColLength() {
		return storage[0].length;
	}

///////////////////////////
// View

	// ka komische cds kram kann ich erklären aber kb zu schreiben
	
	public void registerView(View view) {
		views.add(view);
		view.update(this);
	}

	private void updateViews() {
		for (View view : views) {
			view.update(this);
		}
	}

///////////////////////////
// Win Check

	// TODO

	/**
	 * Win check
	 * @param player1 player1
	 * @param player2 player2
	 * @return the player who won, else null
	 */		
	public Player winCheck(Player player1, Player player2) {

		// absolut geistiger code  aber funktioniert
		if (rowCheck(player1, player2) == player1) {
			return player1;
		}
		
		if (rowCheck(player1, player2) == player2) {
			return player2;
		}

		if (colCheck(player1, player2) == player1) {
			return player1;
		}

		if (colCheck(player1, player2) == player2) {
			return player2;
		}

		

		if (diagUp(player1, player2) == player1) {
			return player1;
		}

		if (diagUp(player1, player2) == player2) {
			return player2;
		}

		if (diagDown(player1, player2) == player1) {
			return player1;
		}

		if (diagDown(player1, player2) == player2) {
			return player2;
		}

		return null;

	}

	/**
	 * row check.
	 * @param player1 player1 
	 * @param player2 playe2
	 * @return player with a winning row, else null
	 */
	private Player rowCheck(Player player1, Player player2) {

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;

		// row check
		for (int i = 0; i < storage.length; i++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			for (int j = 0; j < storage[i].length; j++) {

				if (storage[i][j] == symbol1) {
					winPlayer1 += 1;
					winPlayer2 = 0;
				}

				if (storage[i][j] == symbol2) {
					winPlayer1 = 0;
					winPlayer2 += 1;
				}

				if (winPlayer1 == 4) {
					return player1;
				}

				if (winPlayer2 == 4) {
					return player2;
				}
			}
		}
		return null;
	}

	/**
	 * col Check.
	 * @param player1 player1
	 * @param player2 player 2
	 * @return player with a winning column, else null
	 */
	private Player colCheck(Player player1, Player player2) {

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;

		for (int i = 0; i < 7; i++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			for (int j = 0; j < 6; j++) {

				if (storage[j][i] == symbol1) {
					winPlayer1 += 1;
					winPlayer2 = 0;
				}

				if (storage[j][i] == symbol2) {
					winPlayer1 = 0;
					winPlayer2 += 1;
				}

				if (winPlayer1 == 4) {
					return player1;
				}

				if (winPlayer2 == 4) {
					return player2;
				}
			}
		}
		return null;
	}

	/**
	 * diagonal up checker.
	 * @param player1 player1
	 * @param player2 player2
	 * @return player with a winning diagonal, else null
	 */
	private Player diagUp(Player player1, Player player2) {

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;
		
		// TODO
		
		return null;
	}

	/**
	 * diagonal down checker.
	 * @param player1 player1
	 * @param player2 player2
	 * @return player with a winning diagonal, else null
	 */
	private Player diagDown(Player player1, Player player2) {

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;
		
		for (int row = 0; row < 3; row++) {
			for(int col = 0; col < 4; col++) {
				winPlayer1 = 0;
				winPlayer2 = 0;
				for (int diagonal = 0; diagonal < 6 - diagonal; diagonal++) { // in einer der beiden zeilen ist der fehler
					if (storage[col + diagonal][row + diagonal] == symbol1) { // 
						winPlayer1 += 1;
						winPlayer2 = 0;
					}
					
					if (storage[col + diagonal][row + diagonal] == symbol2) {
						winPlayer1 = 0;
						winPlayer2 += 1;
					}
					
					if (winPlayer1 == 4) {
						return player1;
					}

					if (winPlayer2 == 4) {
						return player2;
					}
				}
			}
		}
		
		return null;
	}


}
