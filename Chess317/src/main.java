public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();
		b.setup();
		System.out.print(b);
		
//		System.out.println();
		
		b.performMove(new Move( new Posn(0, 0), new Posn(0, 1)));
//		System.out.print(b);
		
		
	}
}
