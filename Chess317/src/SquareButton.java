import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SquareButton extends JButton implements ActionListener {
	Square mySquare;
	Board myBoard;
	
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
			//making sure we do not move to the same square
			if (myBoard.selectedSquare!=mySquare) {
				myBoard.performMove(new Move(myBoard.selectedSquare, mySquare));
				myBoard.selectedSquare = null;
			}
		}
		//if starting square is not selected
		else {
			if (mySquare.myPiece!=null)	{
				myBoard.selectedSquare = mySquare;
				System.out.println("Selected starting square: " + myBoard.selectedSquare.row + ", " + myBoard.selectedSquare.col);
			}
			
		}
	}
}


