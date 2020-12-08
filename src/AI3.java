import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AI3 extends AIParent{
	Board myBoard;
	
	
	public AI3(Board myBoard, Colour myColour) {
		this.myBoard = myBoard;
		this.myColour = myColour;
	}
	
	public boolean makeMove(int depth) {
		//clear the myValidMoves list
		ArrayList<Move> myValidMoves = new ArrayList<Move>();
		ArrayList<Move> myLegalMoves = new ArrayList<Move>();
		PriorityQueue<Move> pq = new PriorityQueue<Move>();
//		myValidMoves.clear();
//		myLegalMoves.clear();
//		pq.clear();
		
		//grab all of the squares with my pieces
		ArrayList<Square> mySquares = (myBoard.activeColour == Colour.WHITE) ? myBoard.myGame.whiteList : myBoard.myGame.blackList;
		
		//generate list of ALL valid moves (iterate through each square)
		for (Square s : mySquares) {
			myValidMoves.addAll(s.mySB.getValidMoves());
		}
		
		//go through the valid moves list and find all LEGAL moves
		for (Move m : myValidMoves) {
			if (isLegal(m)) { //only add if move is legal
				myLegalMoves.add(m);
//				System.out.println(myBoard.activeColour + ": " + m + "Depth: " + depth);
			}
		}
		
		if (myLegalMoves.size() == 0) {
			return false; //this must mean I am checkmated, or stalemated
		}
		//try every legal move, then assign the board evaluation to each move
		for (Move m : myLegalMoves) {
			myBoard.performMove(m);
			if (depth == 2) System.out.println("Test Move: " + m);
			if (depth > 1) {
				myBoard.swapActiveColour();
				makeMove(depth-1);
			}
			
//			if (depth == 2) myBoard.swapActiveColour();
			
			m.evaluation = evaluate();
			if (depth == 2) System.out.println("Evaluation for " + myBoard.activeColour + " is:" + m.evaluation);
			pq.add(m);
			myBoard.undoMove(myBoard.moveStack.pop());
			if (depth > 1) myBoard.undoMove(myBoard.moveStack.pop());
		}
		
		//grab the move with the randomly selected index
		Move chosenMove = pq.peek();
		if (depth == 1) System.out.println("Chosen Move: " + chosenMove);
		
		myBoard.performMove(chosenMove);
		System.out.println("Stack size at the end of depth " + depth + " is: " + myBoard.moveStack.size());
//		myBoard.board[0][0].mySB.showLists();
		
		myBoard.swapActiveColour();
		
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

	@Override
	public boolean makeMove() {
		// TODO Auto-generated method stub
		makeMove(2);
		return false;
	}
	
}
