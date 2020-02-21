package Connect4;

import java.io.IOException;

// User Etwo
// Pw ARagorn17

public class LighthouseView implements View {
	
	public LighthouseView(Board board, Player player1, Player player2) {
		
		LighthouseDisplay display = null;

		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
		    display.setUsername("Etwo");
		    display.setToken("API-TOK_EcgV-B8Co-C9WE-I56Q-C1Oo");
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}

		// Send data to the display
		try {
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.
			byte[] data = new byte[14 * 28 * 3];
			
			// Fill array
			
			display.sendImage(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void update(Board board) {
		// TODO Auto-generated method stub
		
	}

}
