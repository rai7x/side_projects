import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class AI2 extends AIParent{
	Board myBoard;
	ArrayList<Move> myValidMoves = new ArrayList<Move>();
	ArrayList<Move> myLegalMoves = new ArrayList<Move>();
	PriorityQueue<Move> pq = new PriorityQueue<Move>();
	
	public AI2(Board myBoard, Colour myColour) {
		this.myBoard = myBoard;
		this.myColour = myColour;
	}
	
	public boolean makeMove() {
		//clear the myValidMoves list
		myValidMoves.clear();
		myLegalMoves.clear();
		pq.clear();
		
		//grab all of the squares with black pieces
		ArrayList<Square> mySquares = (myColour == Colour.WHITE) ? myBoard.myGame.whiteList : myBoard.myGame.blackList;
		
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
		
		//try every legal move, then assign the board evaluation to each move
		for (Move m : myLegalMoves) {
			myBoard.performMove(m);
			m.evaluation = evaluate();
			pq.add(m);
			myBoard.undoMove(myBoard.moveStack.pop());
		}
		
		//grab the move with the randomly selected index
		Move chosenMove = pq.peek();
		
		myBoard.performMove(chosenMove);
		
		//myBoard.board[0][0].mySB.showLists(); //debugging
		
		myBoard.swapActiveColour();
		
		return true;
	}
	
	public boolean isLegal(Move m) {
		boolean result = true;
		
		//have to use false argument / real move to update lists
		myBoard.performMove(m);
		
		if(myBoard.isKingThreatened()) { //this is an illegal move
			result = false;
		}
		
		//undo the test move
		myBoard.undoMove(myBoard.moveStack.pop()); 
		
		return result;
	}

	@Override
	//evaluates the board position
	public int evaluate() {
		// TODO Auto-generated method stub
		ArrayList<Square> myList = (myBoard.activeColour == Colour.WHITE) ? myBoard.myGame.whiteList : myBoard.myGame.blackList;
		ArrayList<Square> opList = (myBoard.activeColour == Colour.WHITE) ? myBoard.myGame.blackList : myBoard.myGame.whiteList;
		int result = 0;
		int myScore = 0, opScore = 0;
		for (Square s: myList) {
			myScore += s.myPiece.value;
		}
		for (Square s: opList) {
			opScore += s.myPiece.value;
		}
		result = myScore - opScore;
		return result;
	}
	
}
