import javax.swing.ImageIcon;

enum Colour {WHITE, BLACK}

public abstract class Piece {
	
	public Piece(Colour colour, ImageIcon icon) {
		this.colour = colour;
		this.icon = icon;
	}
	
	Colour colour;
	boolean hasMoved = false;
	ImageIcon icon;
	public abstract char mySymbol(); //returns the symbol for each piece
}
