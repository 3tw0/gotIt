package Connect4;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Board Class. Provides the board and all methods necessary for playing connect
 * 4
 * 
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
	 * 
	 * @param player the player whos placing
	 * @param col    the column where he wants to playce
	 * @param input  the string to input
	 */
	public void setPlayerPos(Player player, int col, String input) {

		// recursion wenn col außerhalb des bereichs ist -> links / rechts
		if (col < 1 || col > 7) {
			System.out.println("Select a valid Column to place!");
			Scanner scan = new Scanner(System.in);
			int newInt = scan.nextInt();
			setPlayerPos(player, newInt, input);
			return;
		}

		// recursion bei invalider eingabe -> oben
		if (colCount[col - 1] == 6 && col >= 1 && col <=7) {
			System.out.println("Select a valid Column to place!");
			Scanner scan = new Scanner(System.in);
			int newInt = scan.nextInt();
			setPlayerPos(player, newInt, input);
			return;
		}
		// placing
		if (colCount[col - 1] < 6 && col >= 1 && col <=7) {
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
	 * 
	 * @param x "row"
	 * @param y "column"
	 * @return string at given location
	 */
	public String getPos(int row, int col) {
		return storage[row][col];
	}

	/**
	 * Get array row length.
	 * 
	 * @return row length
	 */
	public int getBoardRowLength() {
		return storage.length;
	}

	/**
	 * get array column length.
	 * 
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
	 * 
	 * @param player1 player1
	 * @param player2 player2
	 * @return the player who won, else null
	 */
	public Player winCheck(Player player1, Player player2) {

		// absolut geistiger code aber funktioniert
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
	 * 
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

				if (storage[i][j] == " - ") {
					winPlayer1 = 0;
					winPlayer2 = 0;
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
	 * 
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

				if (storage[j][i] == " - ") {
					winPlayer1 = 0;
					winPlayer2 = 0;
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
	 * 
	 * @param player1 player1
	 * @param player2 player2
	 * @return player with a winning diagonal, else null
	 */
	private Player diagUp(Player player1, Player player2) {

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;

		// not working
		for(int row = 0; row < 1; row++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			int a = 0;
			for(int col = 3; col < 6; col++) {
				for(int diag = 0; diag < ((col * (-1)) - 1); diag--) {
					if (storage[row + a][col + diag] == symbol1) {
						winPlayer1 += 1;
						winPlayer2 = 0;
					}

					if (storage[row + a][col + diag] == symbol2) {
						winPlayer1 = 0;
						winPlayer2 += 1;
					}

					if (storage[row + a][col + diag] == " - ") {
						winPlayer1 = 0;
						winPlayer2 = 0;
					}

					if (winPlayer1 == 4) {
						return player1;
					}

					if (winPlayer2 == 4) {
						return player2;
					}
					
					a++;
				}
			}
		}
		return null;
	}

	/**
	 * diagonal down checker.
	 * 
	 * @param player1 player1
	 * @param player2 player2
	 * @return player with a winning diagonal, else null
	 */
	private Player diagDown(Player player1, Player player2) {

		// ja ich weis das das sehr ekliger code ist aber es funktioniert :D

		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();

		int winPlayer1;
		int winPlayer2;
		int uselessCount;

		// case row[0] col[2] / row[0] col[3]
		for (int col = 2; col < 4; col++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			for (int diagDown = 0; diagDown < 7 - col; diagDown++) {
				if (storage[0 + diagDown][col + diagDown] == symbol1) {
					winPlayer1 += 1;
					winPlayer2 = 0;
				}

				if (storage[0 + diagDown][col + diagDown] == symbol2) {
					winPlayer1 = 0;
					winPlayer2 += 1;
				}

				if (storage[0 + diagDown][col + diagDown] == " - ") {
					winPlayer1 = 0;
					winPlayer2 = 0;
				}

				if (winPlayer1 == 4) {
					return player1;
				}

				if (winPlayer2 == 4) {
					return player2;
				}
			}
		}

		// special case row[0] col[0]
		for (int row = 0; row < 1; row++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			for (int diagDown = 0; diagDown < 6 - row; diagDown++) {
				if (storage[row + diagDown][0 + diagDown] == symbol1) {
					winPlayer1 += 1;
					winPlayer2 = 0;
				}

				if (storage[row + diagDown][0 + diagDown] == symbol2) {
					winPlayer1 = 0;
					winPlayer2 += 1;
				}

				if (storage[row + diagDown][0 + diagDown] == " - ") {
					winPlayer1 = 0;
					winPlayer2 = 0;
				}

				if (winPlayer1 == 4) {
					return player1;
				}

				if (winPlayer2 == 4) {
					return player2;
				}
			}
		}

		// case row[0] col[1] / row[1] col[0] / row[2] col[0]
		for (int row = 0; row < 3; row++) {
			winPlayer1 = 0;
			winPlayer2 = 0;
			for (int diagDown = 0; diagDown < 6 - row; diagDown++) {

				if (row == 0) {
					uselessCount = 1;
				} else {
					uselessCount = 0;
				}

				if (storage[row + diagDown][uselessCount + diagDown] == symbol1) {
					winPlayer1 += 1;
					winPlayer2 = 0;
				}

				if (storage[row + diagDown][uselessCount + diagDown] == symbol2) {
					winPlayer1 = 0;
					winPlayer2 += 1;
				}

				if (storage[row + diagDown][uselessCount + diagDown] == " - ") {
					winPlayer1 = 0;
					winPlayer2 = 0;
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

}
