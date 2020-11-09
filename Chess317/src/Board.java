import java.awt.Color;

import javax.swing.ImageIcon;

public class Board {
	Square[][] board = new Square[8][8];
	
	BoardFrame myFrame = new BoardFrame(this);
	
	public Board() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				int sum = row+col;
				if (sum%2==0) {
					SquareButton sbutton = new SquareButton();
					myFrame.add(sbutton);
					sbutton.setBackground(Color.LIGHT_GRAY);
					//if the sum of row and col is even, then it's a white square
					board[row][col] = new Square(row, col, Colour.WHITE, sbutton);			
				} else {
					SquareButton sbutton = new SquareButton();
					myFrame.add(sbutton);
					sbutton.setBackground(Color.DARK_GRAY);
					//if the sum of row and col is odd, then it's a black square
					board[row][col] = new Square(row, col, Colour.BLACK, sbutton);
				}
			}
		}
		myFrame.setSize(600,600);  
		myFrame.setVisible(true);
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
			board[6][col].myPiece = new Pawn(Colour.WHITE, new ImageIcon("whitePawn.png"));
		}
		//place white pieces
		board[7][0].myPiece = new Rook(Colour.WHITE, new ImageIcon("whiteRook.png"));
		board[7][1].myPiece = new Knight(Colour.WHITE, new ImageIcon("whiteKnight.png"));
		board[7][2].myPiece = new Bishop(Colour.WHITE, new ImageIcon("whiteBishop.png"));
		board[7][3].myPiece = new Queen(Colour.WHITE, new ImageIcon("whiteQueen.png"));
		board[7][4].myPiece = new King(Colour.WHITE, new ImageIcon("whiteKing.png"));
		board[7][5].myPiece = new Bishop(Colour.WHITE, new ImageIcon("whiteBishop.png"));
		board[7][6].myPiece = new Knight(Colour.WHITE, new ImageIcon("whiteKnight.png"));
		board[7][7].myPiece = new Rook(Colour.WHITE, new ImageIcon("whiteRook.png"));
		//place black pawns
		for(int col = 0; col < 8; col++) {
			board[1][col].myPiece = new Pawn(Colour.BLACK, new ImageIcon("blackPawn.png"));
		}
		//place black pieces
		board[0][0].myPiece = new Rook(Colour.BLACK, new ImageIcon("blackRook.png"));
		board[0][1].myPiece = new Knight(Colour.BLACK, new ImageIcon("blackKnight.png"));
		board[0][2].myPiece = new Bishop(Colour.BLACK, new ImageIcon("blackBishop.png"));
		board[0][3].myPiece = new Queen(Colour.BLACK, new ImageIcon("blackQueen.png"));
		board[0][4].myPiece = new King(Colour.BLACK, new ImageIcon("blackKing.png"));
		board[0][5].myPiece = new Bishop(Colour.BLACK, new ImageIcon("blackBishop.png"));
		board[0][6].myPiece = new Knight(Colour.BLACK, new ImageIcon("blackKnight.png"));
		board[0][7].myPiece = new Rook(Colour.BLACK, new ImageIcon("blackRook.png"));
		updateDisplay();
	}
	
	//moves a piece from starting square to endings square
	public void performMove(Move m) {
		//set the destination square to contain the moving piece
		board[m.end.x][m.end.y].myPiece = board[m.start.x][m.start.y].myPiece;
		//set the starting square to contain nothing
		board[m.start.x][m.start.y].myPiece = null;
		updateDisplay();
	}
	
	//update every square's icon based on what kind of piece it has
	public void updateDisplay() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				if (board[row][col].myPiece!=null) {
//					board[row][col].myPiece.icon.getImage().flush();
					board[row][col].my_sb.setIcon(board[row][col].myPiece.icon);
				} else {
					board[row][col].my_sb.setIcon(null);
				}
			}
		}
	}
}


