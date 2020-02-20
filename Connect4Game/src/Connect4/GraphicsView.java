package Connect4;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class GraphicsView extends GCompound implements View {

	Player player1;
	Player player2;

	static final int OFFSET = 75;
	/** size. */
	static final int SQUARE_SIZE = 100;

	private static final int DESCRIPTION_OFFSET = 20;

	public GraphicsView(Board board, Player player1, Player player2) {

		this.player1 = player1;
		this.player2 = player2;

		int x = OFFSET;
		int y = OFFSET;

		for (int i = 0; i < board.getBoardColLength(); i++) {
			for (int j = 0; j < board.getBoardRowLength(); j++) {
				x = OFFSET + (i * SQUARE_SIZE);
				y = OFFSET + (j * SQUARE_SIZE);

				GRect rect = new GRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
				rect.setFillColor(Color.BLUE);
				rect.setFilled(true);
				add(rect);

				GOval oval = new GOval(x + 8, y + 8, SQUARE_SIZE - 16, SQUARE_SIZE - 16);
				oval.setFillColor(Color.WHITE);
				oval.setFilled(true);
				add(oval);

				// werden bei jedem durchlauf geprintet
				// aus for schleife raus
				GLabel labelPlayer1 = new GLabel(player1.getName());
				labelPlayer1.setColor(player1.getColor());
				labelPlayer1.setLocation(OFFSET + (SQUARE_SIZE / 4), OFFSET + (SQUARE_SIZE * 6) + DESCRIPTION_OFFSET);
				add(labelPlayer1);

				GLabel labelPlayer2 = new GLabel(player2.getName());
				labelPlayer2.setColor(player2.getColor());
				labelPlayer2.setLocation(OFFSET + (SQUARE_SIZE / 4) + (SQUARE_SIZE * 6),
						OFFSET + (SQUARE_SIZE * 6) + DESCRIPTION_OFFSET);
				add(labelPlayer2);
			}

		}
	}

	@Override
	public void update(Board board) {

		int x = OFFSET;
		int y = OFFSET;

		for (int i = 0; i < board.getBoardColLength(); i++) {
			for (int j = 0; j < board.getBoardRowLength(); j++) {
				x = OFFSET + (i * SQUARE_SIZE);
				y = OFFSET + (j * SQUARE_SIZE);

				if (board.getPos(j, i).equals(player1.getSymbol())) {
					GOval oval = new GOval(x + 8, y + 8, SQUARE_SIZE - 16, SQUARE_SIZE - 16);
					oval.setFillColor(player1.getColor());
					oval.setFilled(true);
					add(oval);
				}
				
				if (board.getPos(j, i).equals(player2.getSymbol())) {
					GOval oval = new GOval(x + 8, y + 8, SQUARE_SIZE - 16, SQUARE_SIZE - 16);
					oval.setFillColor(player2.getColor());
					oval.setFilled(true);
					add(oval);
				}
			}
		}
	}
}
