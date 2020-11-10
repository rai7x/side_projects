public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();
		b.buildBoard(new BoardFrame(b));
		b.setup();
		System.out.print(b);
		
//		System.out.println();
		
//		b.performMove(new Move( b.board[0][0], b.board[0][1]));
//		System.out.print(b);
		
		
	}
}
