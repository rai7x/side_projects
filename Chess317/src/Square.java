public class Square {
	private int row, col;
	Colour colour;
	Piece myPiece;
	SquareButton my_sb;
	
	public Square(int row, int col, Colour colour, SquareButton sb) {
		this.row = row;
		this.col = col;
		this.colour = colour;
		myPiece = null;
		my_sb = sb;
	}
	
	public void setPiece(Piece p) {
		myPiece = p;
	}
}

