import javax.swing.ImageIcon;

public class Rook extends Piece {

	public Rook(Colour colour, ImageIcon icon) {
		super(colour, icon);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'R' : 'r');
		return symbol;
	}
}
