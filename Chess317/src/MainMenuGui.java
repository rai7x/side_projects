import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class MainMenuGui extends JFrame{
	
	public MainMenuGui() {
		//create frame to hold the container
		JFrame frame = new JFrame();
		PlayButton plyBtn = new PlayButton();
		OptionsButton optnBtn = new OptionsButton();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.white);
		
		//create button panel (will be added to frame)
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		
		//create title panel (will be added to frame)
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		
		//create title label (will be added to title panel)
		JLabel titleLabel = new JLabel("Chess317");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 100));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
	
		titlePanel.add(titleLabel);
		
		frame.add(titlePanel, BorderLayout.NORTH);
		
		btnPanel.add(plyBtn);
		//add spacer between buttons
		btnPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		btnPanel.add(optnBtn);
		
		frame.add(btnPanel, BorderLayout.CENTER);

		frame.pack();
		frame.setTitle("Chess317");
		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
}

