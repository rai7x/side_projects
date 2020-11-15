import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class SquareButton extends JButton implements ActionListener {
	Square mySquare;
	Board myBoard;
	Color defaultColor;
	
	public SquareButton() {
		addActionListener(this);
	}
	
	public void setSquare(Square s) {
		mySquare = s;
	}
	public void setBoard(Board b) {
		myBoard = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if starting square is selected
		if (myBoard.selectedSquare!=null) {
			//create list of valid moves
			ArrayList<Move> validMoves = myBoard.selectedSquare.mySB.getValidMoves();
			//remove highlight from board
			int size = validMoves.size();
			for (int i = 0; i < size; i++) {
				SquareButton sb = validMoves.get(i).end.mySB;
				sb.setBackground(sb.defaultColor);
			}
			//making sure we do not move to the same square
			if (myBoard.selectedSquare!=mySquare) {
				Move attemptedMove = new Move(myBoard.selectedSquare, mySquare);
				System.out.println("Attempted move is: " + attemptedMove);
				
				//check if attempted move is valid
				if(validMoves.contains(attemptedMove)) {
					myBoard.performMove(attemptedMove);
				} else {
					//unselect the square
					myBoard.selectedSquare = null;
					System.out.println("Invalid Move");
					for (int i = 0; i < size; i++) {
						System.out.println(validMoves.get(i));
					}
				}
				System.out.print(myBoard);
			}
			myBoard.selectedSquare = null;
		}
		//if starting square is not selected
		else {
			if (mySquare.myPiece!=null)	{
				myBoard.selectedSquare = mySquare;
				System.out.println("Selected starting square: " + myBoard.selectedSquare.row + ", " + myBoard.selectedSquare.col);
				//generate valid moves
				ArrayList<Move> validMoves = getValidMoves();
				
				//highlight valid squares if starting square has not been selected yet
					int size = validMoves.size();
					for (int i = 0; i < size; i++) {
						SquareButton sb = validMoves.get(i).end.mySB;
						sb.setBackground(sb.getBackground().brighter());
					}
			}
		}
	}
	
	ArrayList<Move> getValidMoves() {
		ArrayList<Move> result = new ArrayList<Move>();
		//check what piece I have
		
		//iterate through each of my piece's directions, visiting each square until I reach the edge or another piece
		//for each empty square visited, create a corresponding Move object, and add the Move object to the list of valid Moves
		int size = mySquare.myPiece.directions.size();
		for(int i = 0; i < size; i++) {
			Direction d = mySquare.myPiece.directions.get(i);
			result.addAll(getValidMoves(d));
		}
		
		//return the list of valid Moves
		return result;
	}
	
	ArrayList<Move> getValidMoves(Direction d) {
		ArrayList<Move> result = new ArrayList<Move>();
		int currentRow, currentCol;
		switch (d) {
			case N:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col;
				while (currentRow >= 0) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow--; //go up the board
				}
				break;
			case E:
				currentRow = mySquare.row;
				currentCol = mySquare.col + 1;
				while (currentCol <= 7) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentCol++; //go right the board
				}
				break;
			case S:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col;
				while (currentRow <= 7) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow++; //go down the board
				}
				break;
			case W:
				currentRow = mySquare.row;
				currentCol = mySquare.col - 1;
				while (currentCol >= 0) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentCol--; //go left the board
				}
				break;
			case NE:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col + 1;
				while ((currentRow >= 0) && (currentCol <= 7)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow--; //go up the board
					currentCol++; //go right the board
				}
				break;
			case SE:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col + 1;
				while ((currentRow <= 7) && (currentCol <= 7)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow++; //go up the board
					currentCol++; //go right the board
				}
				break;
			case SW:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col - 1;
				while ((currentRow <= 7) && (currentCol >= 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow++; //go up the board
					currentCol--; //go right the board
				}
				break;
			case NW:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col - 1;
				while ((currentRow >= 0) && (currentCol >= 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare));
					} else break;
					currentRow--; //go up the board
					currentCol--; //go right the board
				}
				break;
			case NNE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 2) < 0) || (currentCol + 1) > 7)) {
					Square targetSquare = myBoard.board[currentRow - 2][currentCol + 1];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case NEE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 1) < 0) || (currentCol + 2) > 7)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol + 2];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case SEE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 1) > 7) || (currentCol + 2) > 7)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol + 2];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case SSE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 2) > 7) || (currentCol + 1) > 7)) {
					Square targetSquare = myBoard.board[currentRow + 2][currentCol + 1];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case SSW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move will go out of bounds
				if (!(((currentRow + 2) > 7) || (currentCol - 1) < 0)) {
					Square targetSquare = myBoard.board[currentRow + 2][currentCol - 1];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case SWW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 1) > 7) || (currentCol - 2) < 0)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol - 2];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case NWW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 1) < 0) || (currentCol - 2) < 0)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol - 2];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
			case NNW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 2) < 0) || (currentCol - 1) < 0)) {
					Square targetSquare = myBoard.board[currentRow - 2][currentCol - 1];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare));
					}
				}
				break;
		}
			
		return result;
	}
}


