
public class King extends Piece {

	public King(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'K' : 'k');
		return symbol;
	}
}
