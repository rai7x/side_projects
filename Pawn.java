
public class Pawn extends Piece {

	public Pawn(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'P' : 'p');
		return symbol;
	}
}
