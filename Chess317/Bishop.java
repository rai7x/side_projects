
public class Bishop extends Piece {

	public Bishop(Colour colour) {
		super(colour);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'B' : 'b');
		return symbol;
	}

}
