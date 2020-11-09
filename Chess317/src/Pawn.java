import javax.swing.ImageIcon;

public class Pawn extends Piece {

	public Pawn(Colour colour, ImageIcon icon) {
		super(colour, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'P' : 'p');
		return symbol;
	}
}
