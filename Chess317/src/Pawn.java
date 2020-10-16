
public class Pawn extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'P' : 'p');
		return symbol;
	}
}
