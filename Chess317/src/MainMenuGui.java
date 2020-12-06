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
		
		/*
		Container container = window.getContentPane();
		
		//create title panel and add it to container
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(100, 100, 1000, 125);
		
		JLabel titleLabel = new JLabel("Chess317");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 100));
		titlePanel.add(titleLabel);
		
		container.add(titlePanel);
		
		//create buttons, add it to container
		int btnSpacing = 50;
		int btnWidth = 200;
		int btnHeight = 100;
		
		JButton playButton = new JButton("Play");
		playButton.setBackground(Color.black);
		playButton.setFocusPainted(false);
		playButton.setFont(new Font("Courier New", Font.PLAIN, 50));
		playButton.setForeground(Color.white);
		playButton.setBounds(100, 600, btnWidth, btnHeight);
		container.add(playButton);

		JButton loginButton = new JButton("Log-In");
		loginButton.setBackground(Color.black);
		loginButton.setFocusPainted(false);
		loginButton.setFont(new Font("Courier New", Font.PLAIN, 40));
		loginButton.setForeground(Color.white);
		loginButton.setBounds(100 + btnSpacing + btnWidth, 600, btnWidth, btnHeight);
		container.add(loginButton);
		
		JButton regAccButton = new JButton("Register");
		regAccButton.setBackground(Color.black);
		regAccButton.setFocusPainted(false);
		regAccButton.setFont(new Font("Courier New", Font.PLAIN, 30));
		regAccButton.setForeground(Color.white);
		regAccButton.setBounds(100 + 2*btnSpacing + 2*btnWidth, 600, btnWidth, btnHeight);
		container.add(regAccButton);
		
		JButton optButton = new JButton("Options");
		optButton.setBackground(Color.black);
		optButton.setFocusPainted(false);
		optButton.setFont(new Font("Courier New", Font.PLAIN, 35));
		optButton.setForeground(Color.white);
		optButton.setBounds(100 + 3*btnSpacing + 3*btnWidth, 600, btnWidth, btnHeight);
		container.add(optButton);
		
		*/
	}
	
}

