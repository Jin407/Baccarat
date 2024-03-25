package GUIExample;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;

import Model.*;

public class GameTableFrame extends JFrame{
	 
    private GameTablePanel gameTablePanel;
    private GameLoginPanel gameLoginPanel;
    private JPanel callPanel;
    private JTextField input;
    private Dealer dealer;
    private Player player;
    private int count = 3;
    
    public GameTableFrame(Dealer dealer, Player player)
    {
        this.dealer = dealer;
        this.player = player;
        gameTablePanel = new GameTablePanel(dealer,player);
        gameLoginPanel = new GameLoginPanel();
        callPanel = new JPanel();
        JLabel label = new JLabel("State bet:");
        input = new JTextField(10);
        callPanel.add(label);
        callPanel.add(input);
        this.count=3;
    }
    
    public void updateGameTable()
    {
        gameTablePanel.repaint();
    }
    
    public void getGameTable() {
    	setTitle("HighSum Game");
 		add(gameTablePanel);
		pack();
	   	setVisible(true);
    }
    
    public GameTablePanel getGameTablePanel() {
    	return this.gameTablePanel;
    }
    
    public JPanel getCallPanel() {
    	return this.callPanel;
    }
    
    public GameLoginPanel getLoginPanel() {
    	return this.gameLoginPanel;
    }
    
    public JTextField getInput() {
    	return this.input;
    }
    
    //pause for 500msec
    private void pause() {
    	 try{
             Thread.sleep(500);
             
          }catch(Exception e){}
    }
    
}
