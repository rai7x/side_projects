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
	Square whiteKingSquare, blackKingSquare; //these squares point to the white/black kings throughout the game
	
	//set default board square colours
	public static Color darkColour = Color.DARK_GRAY;
	public static Color lightColour = Color.LIGHT_GRAY;
	
	public void buildBoard(BoardPanel panel) {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				int sum = row+col;
				SquareButton sbutton = new SquareButton();
				panel.add(sbutton);
				if (sum%2==0) {
					sbutton.setBackground(lightColour);
					sbutton.defaultColor = lightColour;
					//if the sum of row and col is even, then it's a white square
					board[row][col] = new Square(row, col, Colour.WHITE, sbutton);
					
				} else {
					sbutton.setBackground(darkColour);
					sbutton.defaultColor = darkColour;
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
		whiteKingSquare = board[7][4];
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
		blackKingSquare = board[0][4];
		board[0][5].myPiece = new Bishop(Colour.BLACK, new ImageIcon("blackBishop.png"));
		board[0][6].myPiece = new Knight(Colour.BLACK, new ImageIcon("blackKnight.png"));
		board[0][7].myPiece = new Rook(Colour.BLACK, new ImageIcon("blackRook.png"));
		updateDisplay();
	}
	
	//moves a piece from starting square to endings square
	public void performMove(Move m, boolean isTest) {
		ArrayList<Square> activeList = (activeColour == Colour.WHITE) ? myGame.whiteList : myGame.blackList;
		ArrayList<Square> oppositeList = (activeColour == Colour.WHITE) ? myGame.blackList : myGame.whiteList;
		//if the destination square already has a piece, store it into capturedPiece
		if (m.end.myPiece != null) {
			m.capturedPiece = m.end.myPiece;
			if (!isTest) oppositeList.remove(m.end); //if we are capturing an enemy's piece
		}
		//set the destination square to contain the moving piece
		m.end.myPiece = m.start.myPiece;
		m.end.myPiece.moveCount++;
		//set the starting square to contain nothing
		m.start.myPiece = null;
//		System.out.println(m.moveType);
		if (m.moveType == Direction.KSC) {
			//if move type is castling, move the rook too
			performMove(new Move(board[m.end.row][m.end.col+1], board[m.end.row][m.end.col-1], board[m.end.row][m.end.col+1].myPiece.colour), isTest);
			System.out.println("Castling");
		}
		if (m.moveType == Direction.QSC) {
			//if move type is castling, move the rook too
			performMove(new Move(board[m.end.row][m.end.col-2], board[m.end.row][m.end.col+1], board[m.end.row][m.end.col-2].myPiece.colour), isTest);
			System.out.println("Castling");
		}
		//check for promotion
		if (m.end.myPiece.mySymbol()=='p') {
			if (m.end.row==7) {
				m.end.myPiece = new Queen(Colour.BLACK, new ImageIcon("blackQueen.png"));
				m.isPromotion = true;
			}
		}
		else if (m.end.myPiece.mySymbol()=='P') {
			if (m.end.row==0) {
				m.end.myPiece = new Queen(Colour.WHITE, new ImageIcon("whiteQueen.png"));
				m.isPromotion = true;
			}
		}
		updateDisplayAt(m.start);
		updateDisplayAt(m.end);
		moveStack.push(m);
		
		if(m.end.myPiece.mySymbol() == 'K') whiteKingSquare = m.end;
		else if(m.end.myPiece.mySymbol() == 'k') blackKingSquare = m.end;
		//if the move being passed in is not just a Checkmate Test
		if (!isTest) {
			activeList.add(m.end);
			activeList.remove(m.start);
		}
		
//		displayLists();
	}
	
	//moves a piece from starting square to endings square
	//perform a move FOR REAL
	public void performMove(Move m) {
		performMove(m, false);
	}
	
	//given a move object, undo the move
	public void undoMove(Move m, boolean isTest) {
		ArrayList<Square> activeList = (m.movedColour == Colour.WHITE) ? myGame.blackList : myGame.whiteList;
		ArrayList<Square> oppositeList = (m.movedColour == Colour.WHITE) ? myGame.whiteList : myGame.blackList;
		//reverse starting square
		m.start.myPiece = m.end.myPiece;
		//reverse end square
		m.end.myPiece = m.capturedPiece;
		//undo king-jump part of castling
		if ((m.moveType == Direction.KSC) || (m.moveType == Direction.QSC)) {
			m.start.myPiece.moveCount--;
			
			Move poppedMove = moveStack.pop();
			//undo rook
			undoMove(poppedMove);

		} else {
			m.start.myPiece.moveCount--;
		}
		//check for promotion
		if ((m.end.row==7) && (m.isPromotion)) {
			m.start.myPiece = new Pawn(Colour.BLACK, new ImageIcon("blackPawn.png"));
		}
		if ((m.end.row==0) && (m.isPromotion)) {
			m.start.myPiece = new Pawn(Colour.WHITE, new ImageIcon("whitePawn.png"));
		}
		updateDisplayAt(m.start);
		updateDisplayAt(m.end);
		
		//it's the turn AFTER the move has been played, i.e. activeColour != the move m's active colour
		if (!isTest) {
			oppositeList.add(m.start);
			oppositeList.remove(m.end);
		
		
			//if this move captured an enemy piece
			if (m.capturedPiece != null) {
				activeList.add(m.end);
			}
		}
		
		if(m.start.myPiece.mySymbol() == 'K') whiteKingSquare = m.start;
		else if(m.start.myPiece.mySymbol() == 'k') blackKingSquare = m.start;
		
		
		board[0][0].mySB.showLists();
//		displayLists();
	}
	
	//undo a REAL move
	public void undoMove(Move m) {
		undoMove(m, false);
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
	
	//checks if the king is threatened by enemy pieces
	public boolean isKingThreatened() {
		boolean isThreatened = false;
		//determine active king location
		Square ks = (activeColour == Colour.WHITE) ? whiteKingSquare : blackKingSquare;
		//check if the corresponding king's square is in that list
		if (ks.mySB.enemyValidSquares().contains(ks)) {
			isThreatened = true;
		}
		return isThreatened;
	}
	
	public boolean isCheckmate() {
		boolean result = true;
		//first define the list
		ArrayList<Square> myList = (activeColour==Colour.WHITE) ? myGame.whiteList : myGame.blackList;
		//try every move in myList
		for (Square s : myList) {
			ArrayList<Move> validMoves = s.mySB.getValidMoves();
			for (Move m: validMoves) {
				performMove(m, true); //the true parameter means we're performing a test move
				if (!isKingThreatened()) {
					//we have found a legal move!
					undoMove(moveStack.pop(), true);
					return false;
				} else {
					undoMove(moveStack.pop(), true);
				}
			}
		}
		return result;
	}
}


