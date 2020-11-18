import java.awt.Color;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

	public Pawn(Colour colour, ImageIcon icon) {
		super(colour, icon);
		if (colour==Colour.WHITE) {
			directions.add(Direction.N);
		} else {
			directions.add(Direction.S);
		}
		range = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'P' : 'p');
		return symbol;
	}
}
