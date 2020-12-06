import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ResignButton extends JButton implements ActionListener {
	Board myBoard;
	BoardHolder myBoardHolder;
	
	ResignButton(Board myBoard, BoardHolder myBoardHolder) {
		this.myBoard = myBoard;
		this.myBoardHolder = myBoardHolder;
		
		setText("Resign");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//determine the winning colour
		Colour winningColour = (myBoard.activeColour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
		myBoard.myGame.status = (winningColour == Colour.WHITE) ? GameStatus.winWhite : GameStatus.winBlack;
		
		//turn off all buttons in the east panel
		for (Component comp : myBoardHolder.myEastPanel.getComponents()) {
			comp.setEnabled(false);
		}
		
		JOptionPane.showMessageDialog(new JFrame(), winningColour + " wins!");
		System.out.println(winningColour + " wins!");
	}
}

