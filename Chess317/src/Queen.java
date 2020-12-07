import javax.swing.ImageIcon;

public class Queen extends Piece {

	public Queen(Colour colour, ImageIcon icon) {
		super(colour, icon);
		range = 8;
		directions.add(Direction.N);
		directions.add(Direction.E);
		directions.add(Direction.S);
		directions.add(Direction.W);
		directions.add(Direction.NE);
		directions.add(Direction.SE);
		directions.add(Direction.SW);
		directions.add(Direction.NW);
		value = 9;
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'Q' : 'q');
		return symbol;
	}
}
