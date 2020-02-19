package Connect4;

import java.awt.Color;

import acm.program.GraphicsProgram;

public class Connect4 extends GraphicsProgram {

	public void run() {

		Board board = new Board(6, 7);
		board.setEmpty();

		Player player1 = new Player(readLine("Name: "), Color.RED);
		player1.setSymbol(readLine("Choose a Letter: "));
		Player player2 = new Player(readLine("Name: "), Color.YELLOW);
		player2.setSymbol(readLine("Choose a Letter: "));
				
		ConsoleView cView = new ConsoleView();
		board.registerView(cView);

		int a = 0;
		boolean win = false;
		Player player;

		while (!win) {
			if (a % 2 == 0) {
				player = player1;
				board.setPlayerPos(player, readInt(), player.getSymbol());
				
				if (board.winCheck(player)) {
					win = true;
				}
			}

			if (a % 2 == 1) {
				player = player2;
				board.setPlayerPos(player, readInt(), player.getSymbol());
				
				if (board.winCheck(player)) {
					win = true;
				}
			}

			// abbruchbedingung
			if (a == 42) {
				break;
			}

			a++;
		}

	}

	public static void main(String[] args) {
		new Connect4().start();
	}

}
