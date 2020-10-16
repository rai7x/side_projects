
public class Knight extends Piece {

	public Knight(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'N' : 'n');
		return symbol;
	}
}
