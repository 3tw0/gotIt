package Connect4;

import java.awt.Color;

/**
 * Player Class.
 * Stores name, symbol, color of every player object and provides getter/setter
 * @author elianeiselt
 *
 */
public class Player {
	
	private String name;
	private String symbol;
	private Color color;

/////////////////
// Constructor
	
	public Player () {
		this("PlayerUnknown", Color.PINK);
	}
	
	public Player (String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
//////////////////
// Getter / Setter
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSymbol(String symbol) {		
		if (symbol.length() > 1) {
			throw new IllegalArgumentException("Symbol musst be single char");
		}
		
		this.symbol = " " + symbol.toUpperCase() + " ";
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	

}
