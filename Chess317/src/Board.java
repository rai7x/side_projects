
public class Board {
	Square[][] board = new Square[8][8];
	
	public Board() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				int sum = row+col;
				if (sum%2==0) {
					//if the sum of row and col is even, then it's a white square
					board[row][col] = new Square(row, col, Colour.WHITE);
				} else {
					//if the sum of row and col is odd, then it's a black square
					board[row][col] = new Square(row, col, Colour.BLACK);
				}
			}
		}
	}
	
	//returns a string representation of a Board object
	public String toString() {
		String board_rep = "";
		
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				//if there is no piece on this square
				if (board[row][col].myPiece==null) {
					if (board[row][col].colour==Colour.WHITE) {
						board_rep += " ";
					} else if (board[row][col].colour==Colour.BLACK) {
						board_rep += "_";
					}
				}
				//if there is a piece on this square
				else {
					
				}
			}
			board_rep += "\n";
		}
		return board_rep;
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		System.out.print(b);
	}
}


