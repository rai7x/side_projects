enum Colour {WHITE, BLACK}

public abstract class Piece {
	
	public Piece(Colour colour) {
		this.colour = colour;
	}
	
	Colour colour;
	boolean hasMoved = false;
	public abstract char mySymbol(); //returns the symbol for each piece
}
