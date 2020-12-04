//class Posn {
//	int x;
//	int y;
//	
//	public Posn(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//
//	public boolean equals(Posn other)
//	{
//		boolean result;
//		result = ((x==other.x) && (y==other.y));
//		return result;
//	}
//}

public class Move {
	Square start;
	Square end;
	Direction moveType;
	Piece capturedPiece;
	Colour movedColour;
	boolean isPromotion;
	
	public Move(Square start, Square end, Colour movedColour) {
		this.start = start;
		this.end = end;
		this.movedColour = movedColour;
		isPromotion = false;
	}

	@Override
	public boolean equals(Object other)
	{
		if (other==null) return false;
		Move m = (Move) other; 
		boolean result;
		result = ((start.equals(m.start))&&(end.equals(m.end)));
		return result;
	}
	
	public String toString() {
		return "Starting Square: (" + start.row + ", " + start.col + ") End Square: (" + end.row + ", " + end.col + ")";
	}
}

