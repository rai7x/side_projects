import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class BoardHolder extends JFrame {
	BoardPanel myBoardPanel;
	WestPanel myWestPanel;
	EastPanel myEastPanel;
	NorthPanel myNorthPanel;
	BoardHolder(BoardPanel boardPanel) {
		myBoardPanel = boardPanel;
		
		myWestPanel = new WestPanel();
		myWestPanel.setLayout(new FlowLayout());
		myWestPanel.add(myBoardPanel);
		
		
		myEastPanel = new EastPanel();
		UndoButton ub = new UndoButton(myBoardPanel.myBoard, this);
		myEastPanel.add(ub);
		
		ResignButton rb = new ResignButton(myBoardPanel.myBoard, this);
		myEastPanel.add(rb);
		
		myNorthPanel = new NorthPanel();
		
		add(myWestPanel, BorderLayout.WEST);
		add(myEastPanel, BorderLayout.EAST);
		add(myNorthPanel, BorderLayout.NORTH);
		
		setSize(800, 700);
//		setResizable(false);
//		myBoardPanel.setVisible(true);
		myWestPanel.setVisible(true);
		if (Game.myAILevel == AILevel.off)	setTitle("Chess317 Game");
		else setTitle("Chess317 Game vs AI");
		setResizable(false);
		setVisible(true);
	}
}
