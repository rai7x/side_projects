import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsGui {
		public static Color darkColour = Color.DARK_GRAY;
		public static Color lightColour = Color.LIGHT_GRAY;
		public static JPanel darkColourPanel = new JPanel();
		public static JPanel lightColourPanel = new JPanel();
		public static MiniBoardPanel miniBoardPanel = new MiniBoardPanel();
		public static boolean firstTime = true;
		WestPanel myWestPanel;
		
	//holds the color chooser for choosing the dark color
	private class ColorChooserPanelDark extends JPanel implements ChangeListener {
		
		JColorChooser cc = new JColorChooser();
		
		public ColorChooserPanelDark() {
			add(cc);
			cc.getSelectionModel().addChangeListener(this);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			//whenever a colour is clicked, update the darkColour static variable
			Color darkColour = cc.getColor();
			OptionsGui.darkColour = darkColour;
			darkColourPanel.setBackground(darkColour);
			updateMiniBoard(miniBoardPanel);
		}
		
		
	}
	
	//holds the color chooser for choosing the light color
	private class ColorChooserPanelLight extends JPanel implements ChangeListener {
		
		JColorChooser cc = new JColorChooser();
		
		public ColorChooserPanelLight() {
			add(cc);
			cc.getSelectionModel().addChangeListener(this);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			//whenever a colour is clicked, update the lightColour static variable
			Color lightColour = cc.getColor();
			OptionsGui.lightColour = lightColour;
			lightColourPanel.setBackground(lightColour);
			updateMiniBoard(miniBoardPanel);
		}
		
	}
	
	//creates the frame for the colourChooserPanel
	private class DarkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("Dark Colour Chooser");
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JComponent newContentPane = new ColorChooserPanelDark();
			
			frame.setContentPane(newContentPane);
			frame.pack();
			frame.setVisible(true);
		}
	
	}
	
	//creates the frame for the colourChooserPanel
	private class LightListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("Light Colour Chooser");
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JComponent newContentPane = new ColorChooserPanelLight();
			
			frame.setContentPane(newContentPane);
			frame.pack();
			frame.setVisible(true);
		}
	
	}
	
	public OptionsGui(){
		myWestPanel = new WestPanel();
		myWestPanel.setLayout(new GridLayout(0,1));
		
		//create frame to hold panels
		JFrame frame = new JFrame();
		JButton chooseDark = new JButton("Choose Dark Colour");
		JButton chooseLight = new JButton("Choose Light Colour");
		
		//title panel to store title label "Options", will be added to frame
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		
		JLabel titleLabel = new JLabel("Options");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 75));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		titlePanel.add(titleLabel);
		
		//button panel to store buttons, will be added to frame
		JPanel colourBtnPanel = new JPanel();
		colourBtnPanel.setBorder(BorderFactory.createEmptyBorder(75,75,0,0));
		
		//add actionlisteners to buttons
		chooseDark.addActionListener(new DarkListener());
		chooseLight.addActionListener(new LightListener());
		
		//set the colour previews to default
		darkColourPanel.setBackground(darkColour);
		lightColourPanel.setBackground(lightColour);
		
		//add buttons to change dark/light squares to the button panel
		colourBtnPanel.add(chooseDark);
		colourBtnPanel.add(chooseLight);
		
		//construct the preview of the board
		if (firstTime) {
			buildMiniBoard(miniBoardPanel);
		}
		
		miniBoardPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		
		JRadioButton noAIBtn = new JRadioButton();
		noAIBtn.setText("No AI");
		noAIBtn.addActionListener(new noAIListener());
		noAIBtn.setFont(new Font("Default",Font.BOLD, 16));
		JRadioButton AIBtn1 = new JRadioButton();
		AIBtn1.setText("AI Level 1");
		AIBtn1.addActionListener(new AI1Listener());
		AIBtn1.setFont(new Font("Default",Font.BOLD, 16));
		AIBtn1.setSelected(true);
		JRadioButton AIBtn2 = new JRadioButton();
		AIBtn2.setText("AI Level 2");
		AIBtn2.addActionListener(new AI2Listener());
		AIBtn2.setFont(new Font("Default",Font.BOLD, 16));
		
		//buttongroup for inserting the radio buttons
		ButtonGroup AIgroup = new ButtonGroup();
		AIgroup.add(noAIBtn);
		AIgroup.add(AIBtn1);
		AIgroup.add(AIBtn2);
		
		//create JPanel for radio buttions (select AI)
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.add(noAIBtn);
		radioPanel.add(AIBtn1);
		radioPanel.add(AIBtn2);
		
		JLabel AILabel = new JLabel("Choose AI");
		AILabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		AILabel.setHorizontalAlignment(JLabel.CENTER);

		//this panel contains all AI selection stuff
		JPanel innerRadioP = new JPanel(new GridLayout(0,1));
		innerRadioP.add(AILabel, BorderLayout.NORTH);
		innerRadioP.add(radioPanel, BorderLayout.CENTER);
		
		//buttons for selecting which colour to play as
		JRadioButton clrBtnWhite = new JRadioButton();
		clrBtnWhite.setText("White");
		clrBtnWhite.addActionListener(new playWhiteListener());
		clrBtnWhite.setFont(new Font("Default",Font.BOLD, 16));
		clrBtnWhite.setSelected(true);
		JRadioButton clrBtnBlack = new JRadioButton();
		clrBtnBlack.setText("Black");
		clrBtnBlack.addActionListener(new playBlackListener());
		clrBtnBlack.setFont(new Font("Default",Font.BOLD, 16));
		
		//buttongroup for adding colour buttons
		ButtonGroup clrGroup = new ButtonGroup();
		clrGroup.add(clrBtnWhite);
		clrGroup.add(clrBtnBlack);
		
		//jpanel to store radio buttons
		JPanel clrPanel = new JPanel(new GridLayout(0,1));
		clrPanel.add(clrBtnWhite);
		clrPanel.add(clrBtnBlack);
		
		JLabel clrLabel = new JLabel("Play As:");
		clrLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 25));
		clrLabel.setHorizontalAlignment(JLabel.CENTER);
		
		//this panel contains all colour selection stuff
		JPanel innerClrP = new JPanel(new GridLayout(0,1));
		innerClrP.add(clrLabel);
		innerClrP.add(clrPanel);
		
		//this holds smaller panels for layout purposes
		JPanel panelHolder = new JPanel();
		panelHolder.add(innerRadioP);
		panelHolder.add(Box.createRigidArea(new Dimension(50, 0)));
		panelHolder.add(innerClrP);
		
		myWestPanel.add(colourBtnPanel);
		myWestPanel.add(panelHolder);
		
		//add the panels to the frame
		frame.add(myWestPanel, BorderLayout.WEST);
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(miniBoardPanel, BorderLayout.CENTER);
		
		
		frame.pack();
		frame.setTitle("Chess317 Options");
		frame.setSize(850, 600);		
		frame.setResizable(false);
		frame.setVisible(true);	
		
	}
	
	private class playWhiteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.AIColour = Colour.BLACK;
			System.out.println("AI is playing as " + Game.AIColour);
		}
		
	}
	
	private class playBlackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.AIColour = Colour.WHITE;
			System.out.println("AI is playing as " + Game.AIColour);
		}
		
	}
	
	private class noAIListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.myAILevel = AILevel.off;
			System.out.println(Game.AIColour);
			
		}
		
	}
	
	private class AI1Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.myAILevel = AILevel.levelOne;
			
		}
		
	}
	
	private class AI2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.myAILevel = AILevel.levelTwo;
			
		}
		
	}
	
	private static class MiniBoardPanel extends JPanel {
		public MiniBoardPanel() {
			setLayout(new GridLayout(8,8));
			setPreferredSize(new Dimension(200, 200));
		}
	}
	
	public static void buildMiniBoard(MiniBoardPanel panel) {
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				int sum = row+col;
				JPanel miniSquare = new JPanel();
				miniSquare.setSize(25,25);
				panel.add(miniSquare);
				if (sum%2==0) {
					miniSquare.setBackground(lightColour);
				} else {
					miniSquare.setBackground(darkColour);
				}
			}
		}  
		firstTime = false;
	}
	
	public static void updateMiniBoard(MiniBoardPanel panel) {
		boolean isDark = false;
		int counter = 0;
		
		//iterate through the MiniBoardPanel and repaint the squares
		for(Component jp : panel.getComponents()) {
			if (isDark) {
				jp.setBackground(darkColour);
			} else {
				jp.setBackground(lightColour);
			}
			
			isDark = !isDark;
			
			counter++;
			//if we reach the end of a row, flip colour again
			if (counter % 8 == 0) {
				isDark = !isDark;
			}
		}
	}
	
}
