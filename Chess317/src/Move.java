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
	
	public Move(Square start, Square end) {
		this.start = start;
		this.end = end;
	}

	public boolean equals(Move other)
	{
		boolean result;
		result = ((start==other.start)&&(end==other.end));
		return result;
	}
}

