import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class UndoButton extends JButton implements ActionListener {
	Board myBoard;
	BoardHolder myBoardHolder;
	
	UndoButton(Board myBoard, BoardHolder myBoardHolder) {
		this.myBoard = myBoard;
		this.myBoardHolder = myBoardHolder;
		
		setText("Takeback");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//check if we're playing an AI game
		
		if (!myBoard.moveStack.isEmpty()) {
			if (myBoard.myGame.myAI != null) {
				myBoard.undoMove(myBoard.moveStack.pop());
				myBoard.undoMove(myBoard.moveStack.pop());
			} else {
				myBoard.undoMove(myBoard.moveStack.pop());
				myBoard.activeColour = (myBoard.activeColour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE; //swap active colour
			}
			
		}
	}
}

