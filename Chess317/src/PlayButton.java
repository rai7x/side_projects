import java.awt.Color;
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
		
		//set the board colour scheme according to options setting
		Board.darkColour = OptionsGui.darkColour;
		Board.lightColour = OptionsGui.lightColour;
		
		b.buildBoard(bp);
		b.setup();
		BoardHolder bh = new BoardHolder(bp);
		
		Game newGame = new Game(b, bh);
		switch (Game.myAILevel) {
		case off:
			newGame.myAI = null;
			break;
		case levelOne:
			newGame.myAI = new AI(b, Game.AIColour);
			break;
		case levelTwo:
			newGame.myAI = new AI2(b, Game.AIColour);
			break;
		case levelThree:
			newGame.myAI = new AI3(b, Game.AIColour);
			break;
		}
		
		b.myGame = newGame;
		if (Game.myAILevel != AILevel.off) {
			if (b.myGame.myAI.myColour == Colour.WHITE) {
				b.myGame.myAI.makeMove();
			}
		}
		System.out.print(b);
		
	}

}
