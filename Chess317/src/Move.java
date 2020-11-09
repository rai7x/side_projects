class Posn {
	int x;
	int y;
	
	public Posn(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Posn other)
	{
		boolean result;
		result = ((x==other.x) && (y==other.y));
		return result;
	}
}

public class Move {
	Posn start;
	Posn end;
	
	public Move(Posn start, Posn end) {
		this.start = start;
		this.end = end;
	}

	public boolean equals(Move other)
	{
		boolean result;
		result = ((start==other.start)&&(end==other.end));
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Posn p = new Posn(0, 0);
		Posn q = new Posn(0, 3);
		Move m1 = new Move(p, q);
		Move m2 = new Move(p, p);
		System.out.println(m1.equals(m2));
	}
}

