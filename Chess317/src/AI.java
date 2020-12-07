import java.util.ArrayList;
import java.util.Random;

public class AI extends AIParent{
	Board myBoard;
	Colour myColour; //AI can only play as black
	ArrayList<Move> myValidMoves = new ArrayList<Move>();
	ArrayList<Move> myLegalMoves = new ArrayList<Move>();
	
	public AI(Board myBoard, Colour myColour) {
		this.myBoard = myBoard;
		this.myColour = myColour;
	}
	
	public boolean makeMove() {
		//clear the myValidMoves list
		myValidMoves.clear();
		myLegalMoves.clear();
		
		//grab all of the squares with black pieces
		ArrayList<Square> mySquares = myBoard.myGame.blackList;
		
		//generate list of ALL valid moves (iterate through each square)
		for (Square s : mySquares) {
			myValidMoves.addAll(s.mySB.getValidMoves());
		}
		
		//go through the valid moves list and find all LEGAL moves
		for (Move m : myValidMoves) {
			if (isLegal(m)) { //only add if move is legal
				myLegalMoves.add(m);
			}
		}
		
		if (myLegalMoves.size() == 0) {
			return false; //this must mean I am checkmated, or stalemated
		}
		
		//randomly generate a number between 0 and size of myLegalMoves - 1
		Random rand = new Random();
		int index;
		int upperBound = myLegalMoves.size() - 1;
		
		if (upperBound == 0) {
			index = 0;
		} else {
			index = rand.nextInt(upperBound);
		}
		
		//grab the move with the randomly selected index
		Move chosenMove = myLegalMoves.get(index);
		
		myBoard.performMove(chosenMove);
		
		myBoard.board[0][0].mySB.showLists();
		
		myBoard.activeColour = (myBoard.activeColour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
		
		return true;
	}
	
	public boolean isLegal(Move m) {
		boolean result = true;
		
		//have to use true arguement because it is a test move
		myBoard.performMove(m);
		
		if(myBoard.isKingThreatened()) { //this is an illegal move
			result = false;
		}
		
		//undo the test move
		myBoard.undoMove(myBoard.moveStack.pop()); 
		
		return result;
	}

	@Override
	public int evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
