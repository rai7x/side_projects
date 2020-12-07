import java.awt.Color;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

	public Pawn(Colour colour, ImageIcon icon) {
		super(colour, icon);
		if (colour==Colour.WHITE) {
			directions.add(Direction.NP);
		} else {
			directions.add(Direction.SP);
		}
		range = 1;
		value = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'P' : 'p');
		return symbol;
	}
}
