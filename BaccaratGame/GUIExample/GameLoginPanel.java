package GUIExample;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Model.*;

public class GameLoginPanel extends JPanel {
	
	private JTextField loginField;
	private JLabel loginLabel;
	private JTextField chipsField;
	private JLabel chipsLabel;
	
	public GameLoginPanel() {
		
		setPreferredSize(new Dimension(250, 80));
		
		
		loginLabel = new JLabel("Username: ");
		loginField = new JTextField(10);
		chipsLabel = new JLabel("Chips:    ");
		chipsField = new JTextField(10);
		
		
		add(loginLabel);
		add(loginField);
		add(chipsLabel);
		add(chipsField);
		
	}
	
	public String getChips() {

		return chipsField.getText();
	}
	
	public String getInputName() {
		return loginField.getText();
	}
	

	
	
	

}
