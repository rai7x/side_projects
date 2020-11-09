import javax.swing.ImageIcon;

public class Queen extends Piece {

	public Queen(Colour colour, ImageIcon icon) {
		super(colour, icon);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'Q' : 'q');
		return symbol;
	}
}
