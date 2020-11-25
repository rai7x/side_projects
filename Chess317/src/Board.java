import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.ImageIcon;

public class Board{
	Square[][] board = new Square[8][8];
	Square selectedSquare = null; //if the board has a square selected, this will not be null
	Stack<Move> moveStack = new Stack<Move>();
	Colour activeColour = Colour.WHITE;
	Game myGame;
	
	public void buildBoard(BoardPanel panel) {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				int sum = row+col;
				SquareButton sbutton = new SquareButton();
				panel.add(sbutton);
				if (sum%2==0) {
					sbutton.setBackground(Color.LIGHT_GRAY);
					sbutton.defaultColor = Color.LIGHT_GRAY;
					//if the sum of row and col is even, then it's a white square
					board[row][col] = new Square(row, col, Colour.WHITE, sbutton);
					
				} else {
					sbutton.setBackground(Color.DARK_GRAY);
					sbutton.defaultColor = Color.DARK_GRAY;
					//if the sum of row and col is odd, then it's a black square
					board[row][col] = new Square(row, col, Colour.BLACK, sbutton);
				}
				sbutton.setSquare(board[row][col]);
				sbutton.setBoard(this);
			}
		}  
//		frame.setVisible(true);
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
	
	//setup the board by placing the pieces on the board in starting position
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
		myGame = new Game(this); //adding the pieces to new Game's white/black Lists
	}
	
	//moves a piece from starting square to endings square
	public void performMove(Move m) {
		ArrayList<Square> activeList = (activeColour == Colour.WHITE) ? myGame.whiteList : myGame.blackList;
		ArrayList<Square> oppositeList = (activeColour == Colour.WHITE) ? myGame.blackList : myGame.whiteList;
		//if the destination square already has a piece, store it into capturedPiece
		if (m.end.myPiece != null) {
			m.capturedPiece = m.end.myPiece;
			oppositeList.remove(m.end);
		}
		//set the destination square to contain the moving piece
		m.end.myPiece = m.start.myPiece;
		m.end.myPiece.hasMoved = true;
		//set the starting square to contain nothing
		m.start.myPiece = null;
		System.out.println(m.moveType);
		if (m.moveType == Direction.KSC) {
			//if move type is castling, move the rook too
			performMove(new Move(board[m.end.row][m.end.col+1], board[m.end.row][m.end.col-1]));
			System.out.println("Castling");
		}
		if (m.moveType == Direction.QSC) {
			//if move type is castling, move the rook too
			performMove(new Move(board[m.end.row][m.end.col-2], board[m.end.row][m.end.col+1]));
			System.out.println("Castling");
		}
		updateDisplayAt(m.start);
		updateDisplayAt(m.end);
		moveStack.push(m);
		
		activeList.add(m.end);
		activeList.remove(m.start);
		
		displayLists();
	}
	
	//given a move object, undo the move
	public void undoMove(Move m) {
		ArrayList<Square> activeList = (activeColour == Colour.WHITE) ? myGame.whiteList : myGame.blackList;
		ArrayList<Square> oppositeList = (activeColour == Colour.WHITE) ? myGame.blackList : myGame.whiteList;
		//reverse starting square
		m.start.myPiece = m.end.myPiece;
		//reverse end square
		m.end.myPiece = m.capturedPiece;
		if ((m.moveType == Direction.KSC) || (m.moveType == Direction.QSC)) {
			m.start.myPiece.hasMoved = false;
			Move poppedMove = moveStack.pop();
			poppedMove.end.myPiece.hasMoved = false;
			undoMove(poppedMove);
		}
		updateDisplayAt(m.start);
		updateDisplayAt(m.end);
		
		//it's the turn AFTER the move has been played, i.e. activeColour != the move m's active colour
		oppositeList.add(m.start);
		oppositeList.remove(m.end);
		
		//if this move captured an enemy piece
		if (m.capturedPiece != null) {
			activeList.add(m.end);
		}
		
		displayLists();
	}
	
	//update a single square's icon based on what kind of piece it has
	public void updateDisplayAt(Square s) {
		if (s.myPiece!=null) {
			s.mySB.setIcon(s.myPiece.icon);
		} else {
			s.mySB.setIcon(null);
		}
	}
	
	//update the entire board visually
	public void updateDisplay() {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				updateDisplayAt(board[row][col]);
			}
		}
	}

	private void displayLists() {
		System.out.println("White List");
		myGame.printList(myGame.whiteList);
		System.out.println("Black List");
		myGame.printList(myGame.blackList);
	}
	
}


