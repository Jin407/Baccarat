package GUIExample;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Model.*;

public class GameLoginPanel extends JPanel {
	
	private JTextField loginField;
	private JLabel loginLabel;
	private JPasswordField passwordField;
	private JLabel passwordLabel;
	
	public GameLoginPanel() {
		
		setPreferredSize(new Dimension(250, 80));
		
		
		loginLabel = new JLabel("Login name: ");
		loginField = new JTextField(10);
		passwordLabel = new JLabel("Password:    ");
		passwordField = new JPasswordField(10);
		
		
		add(loginLabel);
		add(loginField);
		add(passwordLabel);
		add(passwordField);
		
	}
	
	public String getInputPassword() {

		return new String(passwordField.getPassword());
	}
	
	public String getInputName() {
		return loginField.getText();
	}
	

	
	
	

}
