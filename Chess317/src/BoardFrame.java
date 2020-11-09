import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
public class BoardFrame extends JFrame {
	Board myBoard;
	public BoardFrame(Board b) {
		myBoard = b;
		setLayout(new GridLayout(b.board.length, b.board.length));
	}
}
