import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class UndoButton extends JButton implements ActionListener {
	Board myBoard;
	UndoButton(Board myBoard) {
		this.myBoard = myBoard;
		setText("Undo");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!myBoard.moveStack.isEmpty()) {
			myBoard.undoMove(myBoard.moveStack.pop());
			myBoard.activeColour = (myBoard.activeColour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE; //swap active colour
		}
	}
}

