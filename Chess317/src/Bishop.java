
public class Bishop extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'B' : 'b');
		return symbol;
	}

}
