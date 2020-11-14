import javax.swing.ImageIcon;
import java.util.ArrayList;

enum Colour {WHITE, BLACK}

public abstract class Piece {
	
	public Piece(Colour colour, ImageIcon icon) {
		this.colour = colour;
		this.icon = icon;
	}
	
	Colour colour;
	boolean hasMoved = false;
	ImageIcon icon;
	ArrayList<Direction> directions = new ArrayList<Direction>();
	public abstract char mySymbol(); //returns the symbol for each piece
}

enum Direction {N, E, S, W, NE, SE, NW, SW, NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW};