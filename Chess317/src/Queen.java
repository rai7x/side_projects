
public class Queen extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'Q' : 'q');
		return symbol;
	}
}
