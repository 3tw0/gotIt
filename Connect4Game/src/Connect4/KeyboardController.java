package Connect4;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


// work in progress

public class KeyboardController implements KeyListener {

	private Board board;

	public KeyboardController(Board board) {
		this.board = board;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int position = 1;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (position < 1) {
					position -= 1;
				}
				break;
	
			case KeyEvent.VK_RIGHT:
				if (position < 7) {
					position += 1;
				}
				break;
			
			case KeyEvent.VK_ENTER:
				

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
