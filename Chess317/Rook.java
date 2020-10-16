public class Rook extends Piece {

	public Rook(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'R' : 'r');
		return symbol;
	}
}
