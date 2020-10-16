public class Rook extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'R' : 'r');
		return symbol;
	}
}
