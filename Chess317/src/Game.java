import java.awt.Color;
import java.util.ArrayList;

enum GameStatus{inProgress, winWhite, winBlack, draw}

public class Game {
	GameStatus status;
	Colour winner;
	Board myBoard;
	ArrayList<Square> whiteList = new ArrayList<Square>();
	ArrayList<Square> blackList = new ArrayList<Square>();
	
	public Game(Board myBoard) {
		this.myBoard = myBoard;
		//adding the white piece squares
		for(int row = 6; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				whiteList.add(myBoard.board[row][col]);
			}
		}
		//adding the black piece squares
		for(int row = 0; row < 2; row++) {
			for(int col = 0; col < 8; col++) {
				blackList.add(myBoard.board[row][col]);
			}
		}
	}
	
	public void printList(ArrayList<Square> ar) {
		int size = ar.size();
		for(int i = 0; i < size; i++) {
			System.out.println(ar.get(i));
		}
	}
	
	public void undo() {
		myBoard.undoMove(myBoard.moveStack.pop());
	}
	
	//checks if the king is threatened by enemy pieces
	public boolean isKingThreatened() {
		boolean isThreatened = false;
		//determine activeColour
		Colour activeColour = myBoard.activeColour;
		//determine active king location
		Square ks = (activeColour == Colour.WHITE) ? myBoard.whiteKingSquare : myBoard.blackKingSquare;
		//check if the corresponding king's square is in that list
		if (ks.mySB.enemyValidSquares().contains(ks)) {
			isThreatened = true;
		}
		return isThreatened;
	}
}
