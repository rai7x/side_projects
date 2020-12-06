import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionsGui {
		public static Color darkColour = Color.DARK_GRAY;
		public static Color lightColour = Color.LIGHT_GRAY;
		public static JPanel darkColourPanel = new JPanel();
		public static JPanel lightColourPanel = new JPanel();
		public static MiniBoardPanel miniBoardPanel = new MiniBoardPanel();
		public static boolean firstTime = true;
		
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
		//create frame to hold panels
		JFrame frame = new JFrame();
		JButton chooseDark = new JButton("Choose Dark Colour");
		JButton chooseLight = new JButton("Choose Light Colour");
		
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		
		//create title label (will be added to title panel)
		JLabel titleLabel = new JLabel("Options");
		titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 75));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		
		titlePanel.add(titleLabel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBorder(BorderFactory.createEmptyBorder(75,75,0,0));
		//btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.LINE_AXIS));
		
		//add actionlisteners to buttons
		chooseDark.addActionListener(new DarkListener());
		chooseLight.addActionListener(new LightListener());
		
		//set the colour previews to default
		darkColourPanel.setBackground(darkColour);
		lightColourPanel.setBackground(lightColour);
		
		//add buttons to change dark/light squares to the button panel
		btnPanel.add(chooseDark);
		btnPanel.add(chooseLight);
		
		//construct the preview of the board
		if (firstTime) {
			buildMiniBoard(miniBoardPanel);
		}
		
		miniBoardPanel.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		
		//add the panels to the frame
		frame.add(btnPanel, BorderLayout.WEST);	
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(miniBoardPanel, BorderLayout.CENTER);
		
		
		frame.pack();
		frame.setTitle("Chess317 Options");
		frame.setSize(850, 600);		
		frame.setResizable(false);
		frame.setVisible(true);	
		
	}
	
	private static class MiniBoardPanel extends JPanel{
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
