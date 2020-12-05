import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public abstract class MenuButton extends JButton{

	public MenuButton() {
		this.setBackground(Color.black);
		this.setFocusPainted(false);
		this.setFont(new Font("Courier New", Font.PLAIN, 30));
		this.setForeground(Color.white);
		this.setBounds(100, 600, 200, 100);
	}
	
}
