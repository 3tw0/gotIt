package Connect4;

/**
 * Console View Class.
 * @author elianeiselt
 *
 */
public class ConsoleView implements View {

	@Override
	public void update(Board board) {
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(board.getPos(i,j));
			}
			System.out.println();
		}
		
	}

}
