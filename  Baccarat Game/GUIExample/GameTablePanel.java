package GUIExample;
import java.awt.*;

import javax.swing.*;

import Model.*;

public class GameTablePanel extends JPanel {

	private Player player;
	private Dealer dealer;
	private ImageIcon cardBackImage;
	private int chipsOnTable;
	int a = 600;
	int b = 20;
	
	

	public GameTablePanel(Dealer dealer, Player player) {

		setLayout(new FlowLayout());
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1324, 768));
		cardBackImage = new ImageIcon("images/back.png");
		this.dealer = dealer;
		this.player = player;
		this.chipsOnTable = 0;
		
	}

	
	public void setChipsOnTable(int chipsOnTable) {
		this.chipsOnTable = chipsOnTable;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		cardBackImage.paintIcon(this, g, a, b);
        cardBackImage.paintIcon(this, g, 600, 20);
        cardBackImage.paintIcon(this, g, 600, 20);
        
        int x = 250;
        int y = 370;

       
        for (Card c : dealer.getCardsOnHand()) {
			// display dealer cards
			c.paintIcon(this, g, x, y);
			x += 50;
		}
		
		g.drawString("Dealer", 350, 580);

		// display player cards
		int x1 = 850;
		int y1 = 370;

		for (Card c : player.getCardsOnHand()) {
			// display dealer cards
			c.paintIcon(this, g, x1, y1);
			x1 += 50;
		}
		
		g.drawString("Player", 950, 580);
		
		g.drawString("Value: "+player.getTotalCardsValue(), 940, 600);
		g.drawString("Value: "+dealer.getTotalCardsValue(), 340, 600);
		g.drawString("Balance Chips: "+player.getChips(), 600, 700);
		
		
	}
}
