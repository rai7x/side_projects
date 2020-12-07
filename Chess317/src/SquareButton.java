import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SquareButton extends JButton implements ActionListener {
	Square mySquare;
	Board myBoard;
	Color defaultColor;
	ArrayList<Move> validMoves;
	
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
		//if the game has ended, do not allow actions to be performed
		if (myBoard.myGame.status != GameStatus.inProgress) {
			return;
		}
		//if starting square is selected
		if (myBoard.selectedSquare!=null) {
			//create list of valid moves
			ArrayList<Move> validMovesList = myBoard.selectedSquare.mySB.validMoves;
			//remove highlight from board
			int size = validMovesList.size();
			for (int i = 0; i < size; i++) {
				SquareButton sb = validMovesList.get(i).end.mySB;
				sb.setBackground(sb.defaultColor);
			}
			//making sure we do not move to the same square
			if (myBoard.selectedSquare!=mySquare) {
				Move attemptedMove = new Move(myBoard.selectedSquare, mySquare, myBoard.selectedSquare.myPiece.colour);
				System.out.println("Attempted move is: " + attemptedMove);
				
				//check if attempted move is valid
				if(validMovesList.contains(attemptedMove)) {
					Move moveFound = validMovesList.get(validMovesList.indexOf(attemptedMove));
					System.out.println("Move Found Type: " + moveFound.moveType);
					myBoard.performMove(moveFound);
					//check if the move is legal - if so, undo the move
					if (myBoard.isKingThreatened()) {
						myBoard.undoMove(myBoard.moveStack.pop());
					} else { //the player has made a legal move
						myBoard.swapActiveColour();
						//determine if we are playing against AI
						if (Game.myAILevel != AILevel.off) {
							myBoard.myGame.myAI.makeMove();
						}
					}
//					//debugging
//					System.out.println("Enemy's List - My active colour was: " + myBoard.activeColour);
//					ArrayList<Square> result = enemyValidSquares();
//					for (Square s: result) {
//						s.mySB.setBackground(Color.ORANGE);
//					}
//					myBoard.whiteKingSquare.mySB.setBackground(Color.PINK);
//					myBoard.blackKingSquare.mySB.setBackground(Color.magenta);
					
					
					showLists();
					//check if my opponent is checkmated by this move
					//if opponent is in check
					if (myBoard.isKingThreatened()) {
						//try every possible move
						if (myBoard.isCheckmate()) {
							Colour winningColour = (myBoard.activeColour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
							
							JOptionPane.showMessageDialog(new JFrame(), myBoard.activeColour + " is checkmated! \n" + winningColour + " wins!");
							myBoard.myGame.status = (winningColour == Colour.WHITE) ? GameStatus.winWhite : GameStatus.winBlack;
							//turn off all buttons in the east panel
							for (Component comp : myBoard.myGame.myBoardHolder.myEastPanel.getComponents()) {
								comp.setEnabled(false);
							}
							System.out.println("Checkmate!");
						}
					} 
//					//end of debug
					

					
				} else {
					//unselect the square
					myBoard.selectedSquare = null;
					System.out.println("Invalid Move");
					for (int i = 0; i < size; i++) {
						System.out.println(validMovesList.get(i));
					}
				}
				System.out.print(myBoard);
			}
			myBoard.selectedSquare = null;
			if (myBoard.myGame.status == GameStatus.inProgress) myBoard.myGame.myBoardHolder.myEastPanel.getComponent(0).setEnabled(true);
			
		}
		//if starting square is not selected
		else {
			//debugging
			showLists();
			//end of debug
			if ((mySquare.myPiece!=null) && (mySquare.myPiece.colour == myBoard.activeColour)) {
				myBoard.selectedSquare = mySquare;
				System.out.println("Selected starting square: " + myBoard.selectedSquare.row + ", " + myBoard.selectedSquare.col);
				//generate valid moves
				validMoves = getValidMoves();
				
				//highlight valid squares if starting square has not been selected yet
				int size = validMoves.size();
				for (int i = 0; i < size; i++) {
					SquareButton sb = validMoves.get(i).end.mySB;
					sb.setBackground(sb.getBackground().brighter());
				}
			}
			myBoard.myGame.myBoardHolder.myEastPanel.getComponent(0).setEnabled(false);
		}
	}
	
	ArrayList<Move> getValidMoves() {
		ArrayList<Move> result = new ArrayList<Move>();
		//check what piece I have
		
		//iterate through each of my piece's directions, visiting each square until I reach the edge or another piece
		//for each empty square visited, create a corresponding Move object, and add the Move object to the list of valid Moves
		
		if (mySquare.myPiece==null) mySquare.mySB.setBackground(Color.YELLOW);
        //System.out.println("Active Colour: " + myBoard.activeColour);
		
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
		int steps = mySquare.myPiece.range;
		switch (d) {
			case N:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col;
				while ((currentRow >= 0) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow--; //go up the board
					steps--;
				}
				break;
			case E:
				currentRow = mySquare.row;
				currentCol = mySquare.col + 1;
				while ((currentCol <= 7) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentCol++; //go right the board
					steps--;
				}
				break;
			case S:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col;
				while ((currentRow <= 7) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow++; //go down the board
					steps--;
				}
				break;
			case W:
				currentRow = mySquare.row;
				currentCol = mySquare.col - 1;
				while ((currentCol >= 0) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentCol--; //go left the board
					steps--;
				}
				break;
			case NE:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col + 1;
				while ((currentRow >= 0) && (currentCol <= 7) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow--; //go up the board
					currentCol++; //go right the board
					steps--;
				}
				break;
			case SE:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col + 1;
				while ((currentRow <= 7) && (currentCol <= 7) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow++; //go up the board
					currentCol++; //go right the board
					steps--;
				}
				break;
			case SW:
				currentRow = mySquare.row + 1;
				currentCol = mySquare.col - 1;
				while ((currentRow <= 7) && (currentCol >= 0) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow++; //go up the board
					currentCol--; //go right the board
					steps--;
				}
				break;
			case NW:
				currentRow = mySquare.row - 1;
				currentCol = mySquare.col - 1;
				while ((currentRow >= 0) && (currentCol >= 0) && (steps > 0)) {
					//if this square is an empty square
					Square currSquare = myBoard.board[currentRow][currentCol];
					if (currSquare.myPiece==null) {
						result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
					} else {
						if (currSquare.myPiece.colour!=mySquare.myPiece.colour) {
							result.add(new Move(mySquare, currSquare, mySquare.myPiece.colour));
						}
						break;
					}
					currentRow--; //go up the board
					currentCol--; //go right the board
					steps--;
				}
				break;
			case NNE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 2) < 0) || (currentCol + 1) > 7)) {
					Square targetSquare = myBoard.board[currentRow - 2][currentCol + 1];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case NEE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 1) < 0) || (currentCol + 2) > 7)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol + 2];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case SEE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 1) > 7) || (currentCol + 2) > 7)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol + 2];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case SSE:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 2) > 7) || (currentCol + 1) > 7)) {
					Square targetSquare = myBoard.board[currentRow + 2][currentCol + 1];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case SSW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move will go out of bounds
				if (!(((currentRow + 2) > 7) || (currentCol - 1) < 0)) {
					Square targetSquare = myBoard.board[currentRow + 2][currentCol - 1];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case SWW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow + 1) > 7) || (currentCol - 2) < 0)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol - 2];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case NWW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 1) < 0) || (currentCol - 2) < 0)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol - 2];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case NNW:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//check if move is within bounds
				if (!(((currentRow - 2) < 0) || (currentCol - 1) < 0)) {
					Square targetSquare = myBoard.board[currentRow - 2][currentCol - 1];
					if ((targetSquare.myPiece == null)||(targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case NP:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//starting row case
				if (currentRow==6) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
						targetSquare = myBoard.board[currentRow - 2][currentCol];
						if (targetSquare.myPiece == null) result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				} else if (currentRow > 0) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol];
					if (targetSquare.myPiece == null) result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
				}
				//check if capturing is possible
				//check for left capturing
				if ((currentCol > 0) && (currentRow > 0)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol - 1];
					if ((targetSquare.myPiece!=null) && (targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				//check for right capturing
				if ((currentCol < 7) && (currentRow > 0)) {
					Square targetSquare = myBoard.board[currentRow - 1][currentCol + 1];
					if ((targetSquare.myPiece!=null) && (targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case SP:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				//starting row case
				if (currentRow==1) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol];
					if (targetSquare.myPiece == null) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
						targetSquare = myBoard.board[currentRow + 2][currentCol];
						if (targetSquare.myPiece == null) result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				} else if (currentRow < 7) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol];
					if (targetSquare.myPiece == null) result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
				}
				//check if capturing is possible
				//check for left capturing
				if ((currentCol > 0) && (currentRow < 7)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol - 1];
					if ((targetSquare.myPiece!=null) && (targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				//check for right capturing
				if ((currentCol < 7) && (currentRow < 7)) {
					Square targetSquare = myBoard.board[currentRow + 1][currentCol + 1];
					if ((targetSquare.myPiece!=null) && (targetSquare.myPiece.colour!=mySquare.myPiece.colour)) {
						result.add(new Move(mySquare, targetSquare, mySquare.myPiece.colour));
					}
				}
				break;
			case KSC:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				if (mySquare.myPiece.moveCount==0) {
					Piece myRook = myBoard.board[currentRow][7].myPiece;
					if ((myRook != null) && (myRook instanceof Rook) && (myRook.moveCount==0) && (myRook.colour==mySquare.myPiece.colour)) {
						//check if between squares are empty
						if ((myBoard.board[currentRow][currentCol+1].myPiece == null) &&
						(myBoard.board[currentRow][currentCol+2].myPiece == null)) {
							Square targetSquare = myBoard.board[currentRow][currentCol + 2];
							Move castling_move = new Move(mySquare, targetSquare, mySquare.myPiece.colour);
							castling_move.moveType = d;
							System.out.println("d is: " + castling_move.moveType);
							result.add(castling_move);
						}
					}
				}
				break;
			case QSC:
				currentRow = mySquare.row;
				currentCol = mySquare.col;
				if (mySquare.myPiece.moveCount==0) {
					Piece myRook = myBoard.board[currentRow][0].myPiece;
					if ((myRook != null) && (myRook instanceof Rook) && (myRook.moveCount==0) && (myRook.colour==mySquare.myPiece.colour)) {
						//check if between squares are empty
						if ((myBoard.board[currentRow][currentCol-1].myPiece == null) &&
						(myBoard.board[currentRow][currentCol-2].myPiece == null) &&
						(myBoard.board[currentRow][currentCol-3].myPiece == null)) {
							Square targetSquare = myBoard.board[currentRow][currentCol - 2];
							Move castling_move = new Move(mySquare, targetSquare, mySquare.myPiece.colour);
							castling_move.moveType = d;
							System.out.println("d is: " + castling_move.moveType);
							result.add(castling_move);
						}
					}
				}
				break;
		}
			
		return result;
	}
	
	//returns a giant ArrayList consisting of all the valid moves of my enemy's pieces
	public ArrayList<Square> enemyValidSquares() {
		ArrayList<Square> result = new ArrayList<Square>();
		ArrayList<Square> enemyList = (myBoard.activeColour == Colour.WHITE) ? myBoard.myGame.blackList : myBoard.myGame.whiteList;
		//for every square in my enemy list
		ArrayList<Move> tempList;
		for (Square sq : enemyList) {
			tempList = sq.mySB.getValidMoves();
			for (Move m: tempList) {
				result.add(m.end);
			}
		}
		return result;
	}
	
	public void showLists() {
		for (int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				myBoard.board[row][col].mySB.setBackground(myBoard.board[row][col].mySB.defaultColor);
			}
		}
		for(Square s: myBoard.myGame.whiteList) {
			s.mySB.setBackground(Color.CYAN);
		}
		for(Square s: myBoard.myGame.blackList) {
			s.mySB.setBackground(Color.BLUE);
		}
	}
	
	
}


