import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class BoardPanel extends JPanel {
	Board myBoard;
	public BoardPanel(Board b) {
		myBoard = b;
		setLayout(new GridLayout(b.board.length, b.board.length));
		setPreferredSize(new Dimension(600, 600));
	}
}
