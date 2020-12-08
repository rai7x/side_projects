import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionsButton extends MenuButton implements ActionListener{
	
	public OptionsButton() {
		setText("Options");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new OptionsGui();
		
	}

}
