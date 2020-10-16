
public class Queen extends Piece {

	public Queen(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'Q' : 'q');
		return symbol;
	}
}
