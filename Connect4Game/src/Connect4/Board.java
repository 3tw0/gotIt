package Connect4;

import java.util.HashSet;

public class Board {

	/** input storage. */

	private String[][] storage;
	private int[] colCount = { 0, 0, 0, 0, 0, 0, 0 };

	private final HashSet<View> views = new HashSet<>();

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

	public void setEmpty() {
		for (int i = 0; i < storage.length; i++) {
			for (int j = 0; j < storage[i].length; j++) {
				storage[i][j] = " - ";
			}
		}
	}

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

	public String getPos(int x, int y) {
		return storage[x][y];
	}

///////////////////////////
// View

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

	public Player winCheck(Player player1, Player player2) {
		
		if (rowCheck(player1, player2) == player1) {
			return player1;
		}
		
		if (colCheck(player1, player2) == player1) {
			return player1;
		}
		
		if (colCheck(player1, player2) == player2) {
			return player2;
		}
		
		if (colCheck(player1, player2) == player2) {
			return player2;
		}
		
		return null;
		
		
	}
	
	private Player rowCheck(Player player1, Player player2) {
		
		String symbol1 = player1.getSymbol();
		String symbol2 = player2.getSymbol();
		
		int winPlayer1;
		int winPlayer2;
		
		//row check
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

	

}
