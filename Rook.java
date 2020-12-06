import javax.swing.ImageIcon;

public class Rook extends Piece {

	
	
	public Rook(Colour colour, ImageIcon icon) {
		super(colour, icon);
		range = 8;
		directions.add(Direction.N);
		directions.add(Direction.E);
		directions.add(Direction.S);
		directions.add(Direction.W);
	}

	@Override
	public char mySymbol() {
		char symbol;
		symbol = (colour==Colour.WHITE ? 'R' : 'r');
		return symbol;
	}
}
