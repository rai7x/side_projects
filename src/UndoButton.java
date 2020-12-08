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
			if (Game.myAILevel != AILevel.off) {
				myBoard.undoMove(myBoard.moveStack.pop());
				myBoard.swapActiveColour();
				//make sure the stack is not empty
				if (!myBoard.moveStack.isEmpty()) {
					myBoard.undoMove(myBoard.moveStack.pop());
					myBoard.swapActiveColour();
				} else {
					myBoard.myGame.myAI.makeMove(); //make the ai first move again
				}
			} else {
				myBoard.undoMove(myBoard.moveStack.pop());
				myBoard.swapActiveColour();
			}
			
		}
	}
}

