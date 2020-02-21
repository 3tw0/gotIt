package Connect4;

import java.awt.Color;
import acm.program.GraphicsProgram;

/**
 * Connect 4 Game.
 * @author elianeiselt
 *
 */
public class Connect4 extends GraphicsProgram {

	public void run() {

		
		setSize(900,800);
		setBackground(Color.LIGHT_GRAY);
		setTitle("Connect 4 Game by Elian Eiselt and Malte Möheken");
		
		Board board = new Board(6, 7);
		board.setEmpty();

		Player player1 = new Player(readLine("Name: "), Color.RED);
		player1.setSymbol(readLine("Choose a Letter: "));
		Player player2 = new Player(readLine("Name: "), Color.YELLOW);
		player2.setSymbol(readLine("Choose a Letter: "));

		ConsoleView cView = new ConsoleView();
		board.registerView(cView);
		
		GraphicsView gView = new GraphicsView(board, player1, player2);
		add(gView);
		board.registerView(gView);
		
		//LighthouseView lView = new LighthouseView(board, player1, player2);
		//board.registerView(lView);

		int a = 0;
		boolean win = false;

		// game loop
		while (!win) {

			// abwechselndes spielen
			if (a % 2 == 0) {
				board.setPlayerPos(player1, readInt(), player1.getSymbol());
			}

			if (a % 2 == 1) {
				board.setPlayerPos(player2, readInt(), player2.getSymbol());
			}

			// win checks
			if (board.winCheck(player1, player2) == player1) {
				println(player1.getName() + " hat gewonnen!!!");
				win = true;
			}

			if (board.winCheck(player1, player2) == player2) {
				println(player2.getName() + " hat gewonnen!!!");
				win = true;
			}

			a++;
			
			// abbruchbedingung
			if (a == 42) {
				return;
			}
		}
		
		if (!win) {
			System.out.println("Unentschieden!");
		}
		
		System.out.println();
		System.out.println("Thanks for playing <3");
		
		//starting a new game
		String again = readLine("If you want to play again type 'Yes': ");
		if (again.toLowerCase().equals("yes")) {
			new Connect4().start();
		}
		// lighthouse display
		if (again.toLowerCase().equals("no")) {
			//display.close();
		}

	}

	public static void main(String[] args) {
		new Connect4().start();
	}

}
