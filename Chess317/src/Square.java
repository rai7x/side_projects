public class Square {
	private int row, col;
	Colour colour;
	Piece myPiece;
	
	public Square(int row, int col, Colour colour) {
		this.row = row;
		this.col = col;
		this.colour = colour;
		myPiece = null;
	}
	
	public void setPiece(Piece p) {
		myPiece = p;
	}
}

