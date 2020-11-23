public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Board b = new Board();
		BoardPanel bp = new BoardPanel(b);
		b.buildBoard(bp);
		b.setup();
		BoardHolder bh = new BoardHolder(bp);
		System.out.print(b);
		
//		System.out.println();
		
//		b.performMove(new Move( b.board[0][0], b.board[0][1]));
//		System.out.print(b);
		
		
	}
}
