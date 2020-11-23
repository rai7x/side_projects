import javax.swing.ImageIcon;
import java.util.ArrayList;

enum Colour {WHITE, BLACK}

public abstract class Piece {
	
	Colour colour;
	int range;
	boolean hasMoved = false;
	ImageIcon icon;
	ArrayList<Direction> directions = new ArrayList<Direction>();
	public abstract char mySymbol(); //returns the symbol for each piece
	
	public Piece(Colour colour, ImageIcon icon) {
		this.colour = colour;
		this.icon = icon;
	}
	
}

enum Direction {N, E, S, W, NE, SE, NW, SW, NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW, NP, SP, KSC, QSC};