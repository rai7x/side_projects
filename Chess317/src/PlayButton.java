import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends MenuButton implements ActionListener{
	
	public PlayButton() {
		setText("Play");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Board b = new Board();
		BoardPanel bp = new BoardPanel(b);
		b.buildBoard(bp);
		b.setup();
		BoardHolder bh = new BoardHolder(bp);
		System.out.print(b);
		
	}

}
