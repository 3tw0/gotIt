package Connect4;

import java.util.HashSet;

public class Board {

	/** input storage. */

	private String[][] storage;
	private int[] colCount = {0,0,0,0,0,0,0};

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

	public boolean winCheck(Player player) {
		if (rowCheck() || colCheck() || diagUp() || diagDown()) {
			return true;
		}
		return false;
	}

	private boolean colCheck() {
		return false;
	}

	private boolean rowCheck() {
		return false;
	}

	private boolean diagUp() {
		return false;
	}

	private boolean diagDown() {
		return false;
	}

}
