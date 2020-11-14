public class Square {
	int row, col;
	Colour colour;
	Piece myPiece;
	SquareButton mySB;
	
	public Square(int row, int col, Colour colour, SquareButton sb) {
		this.row = row;
		this.col = col;
		this.colour = colour;
		myPiece = null;
		mySB = sb;
	}
	
	public void setPiece(Piece p) {
		myPiece = p;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (other==null) return false;
		Square s = (Square) other; 
		boolean result;
		result = ((row==s.row)&&(col==s.col));
		return result;
	}
	

}

