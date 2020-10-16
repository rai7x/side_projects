
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
					board_rep += board[row][col].myPiece.mySymbol();
				}
			}
			board_rep += "\n";
		}
		return board_rep;
	}
	
	//setup the board
	public void setup() {
		//place white pawns
		for(int col = 0; col < 8; col++) {
			board[6][col].myPiece = new Pawn(Colour.WHITE);
		}
		//place white pieces
		board[7][0].myPiece = new Rook(Colour.WHITE);
		board[7][1].myPiece = new Knight(Colour.WHITE);
		board[7][2].myPiece = new Bishop(Colour.WHITE);
		board[7][3].myPiece = new Queen(Colour.WHITE);
		board[7][4].myPiece = new King(Colour.WHITE);
		board[7][5].myPiece = new Bishop(Colour.WHITE);
		board[7][6].myPiece = new Knight(Colour.WHITE);
		board[7][7].myPiece = new Rook(Colour.WHITE);
		//place black pawns
		for(int col = 0; col < 8; col++) {
			board[1][col].myPiece = new Pawn(Colour.BLACK);
		}
		//place black pieces
		board[0][0].myPiece = new Rook(Colour.BLACK);
		board[0][1].myPiece = new Knight(Colour.BLACK);
		board[0][2].myPiece = new Bishop(Colour.BLACK);
		board[0][3].myPiece = new Queen(Colour.BLACK);
		board[0][4].myPiece = new King(Colour.BLACK);
		board[0][5].myPiece = new Bishop(Colour.BLACK);
		board[0][6].myPiece = new Knight(Colour.BLACK);
		board[0][7].myPiece = new Rook(Colour.BLACK);
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		b.setup();
		System.out.print(b);
	}
}


