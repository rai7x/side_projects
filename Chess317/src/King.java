import javax.swing.ImageIcon;

public class King extends Piece {

	public King(Colour colour, ImageIcon icon) {
		super(colour, icon);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'K' : 'k');
		return symbol;
	}
}
