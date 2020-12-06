import java.awt.Color;
import java.util.ArrayList;

enum GameStatus{inProgress, winWhite, winBlack, draw}
enum AILevel{off, levelOne}

public class Game {
	GameStatus status = GameStatus.inProgress;
	Colour winner;
	Board myBoard;
	ArrayList<Square> whiteList = new ArrayList<Square>(); //list of squares containing white pieces
	ArrayList<Square> blackList = new ArrayList<Square>(); //list of squares containing black pieces
	BoardHolder myBoardHolder;
	public static AILevel myAILevel = AILevel.levelOne;
	AI myAI;
	
	public Game(Board myBoard, 	BoardHolder myBoardHolder) {
		this.myBoard = myBoard;
		this.myBoardHolder = myBoardHolder;
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
}
