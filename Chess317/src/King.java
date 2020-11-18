import javax.swing.ImageIcon;

public class King extends Piece {

	public King(Colour colour, ImageIcon icon) {
		super(colour, icon);
		range = 1;
		directions.add(Direction.N);
		directions.add(Direction.E);
		directions.add(Direction.S);
		directions.add(Direction.W);
		directions.add(Direction.NE);
		directions.add(Direction.SE);
		directions.add(Direction.SW);
		directions.add(Direction.NW);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'K' : 'k');
		return symbol;
	}
}
