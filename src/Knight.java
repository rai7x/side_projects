import javax.swing.ImageIcon;

public class Knight extends Piece {

	public Knight(Colour colour, ImageIcon icon) {
		super(colour, icon);
		directions.add(Direction.NNE);
		directions.add(Direction.NEE);
		directions.add(Direction.SEE);
		directions.add(Direction.SSE);
		directions.add(Direction.SSW);
		directions.add(Direction.SWW);
		directions.add(Direction.NWW);
		directions.add(Direction.NNW);
		value = 3;
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'N' : 'n');
		return symbol;
	}
}