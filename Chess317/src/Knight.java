import javax.swing.ImageIcon;

public class Knight extends Piece {

	public Knight(Colour colour, ImageIcon icon) {
		super(colour, icon);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'N' : 'n');
		return symbol;
	}
}
