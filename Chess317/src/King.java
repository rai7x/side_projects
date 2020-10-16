
public class King extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'K' : 'k');
		return symbol;
	}
}
