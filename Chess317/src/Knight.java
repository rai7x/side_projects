
public class Knight extends Piece {

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'K' : 'K');
		return symbol;
	}
}
