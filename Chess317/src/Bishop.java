import javax.swing.ImageIcon;

public class Bishop extends Piece {

	public Bishop(Colour colour, ImageIcon icon) {
		super(colour, icon);
		range = 8;
		directions.add(Direction.NE);
		directions.add(Direction.SE);
		directions.add(Direction.SW);
		directions.add(Direction.NW);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'B' : 'b');
		return symbol;
	}

}
