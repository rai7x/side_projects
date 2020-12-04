import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardHolder extends JFrame {
	BoardPanel myBoardPanel;
	WestPanel myWestPanel;
	EastPanel myEastPanel;
	BoardHolder(BoardPanel boardPanel) {
		myBoardPanel = boardPanel;
		
		myWestPanel = new WestPanel();
		myWestPanel.setLayout(new FlowLayout());
		myWestPanel.add(myBoardPanel);
		
		
		myEastPanel = new EastPanel();
		UndoButton ub = new UndoButton(myBoardPanel.myBoard);
		myEastPanel.add(ub);
		
		add(myWestPanel, BorderLayout.WEST);
		add(myEastPanel, BorderLayout.EAST);
		
		setSize(800, 700);
//		setResizable(false);
//		myBoardPanel.setVisible(true);
		myWestPanel.setVisible(true);
		setVisible(true);
	}
}