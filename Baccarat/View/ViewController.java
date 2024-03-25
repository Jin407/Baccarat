package View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import GUIExample.GameTableFrame;
import javax.swing.Timer;

import Helper.Keyboard;
import Model.*;

//all input and output should be done view ViewController
//so that it is easier to implement GUI later
public class ViewController {
	
	private GameTableFrame gameFrame;
	private Dealer dealer;
	private Player player;
	
	public ViewController(Dealer dealer, Player player) {
		gameFrame = new GameTableFrame(dealer,player);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.player = player;
		this.dealer = dealer;
	}
	
    private void pause() {
   	 try{
            Thread.sleep(500);
         }catch(Exception e){}
    }

	public void displayExitGame() {
		System.out.println("Thank you for playing HighSum game");
		JOptionPane.showMessageDialog(null, "Thank you for playing HighSum game","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
		gameFrame.dispose();
	}
	
	
	public void displayBetOntable(int bet) {
		System.out.println("Bet on table : "+bet);
		gameFrame.getGameTablePanel().setChipsOnTable(bet);
		gameFrame.updateGameTable();
		pause();
	}
	
	public void displayPlayerWin(Player player) {
		System.out.println(player.getLoginName()+" Wins!");
		gameFrame.getGameTablePanel().setChipsOnTable(0);
		gameFrame.updateGameTable();
		JOptionPane.showMessageDialog(null, player.getLoginName()+" wins!","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayDealerWin() {
		System.out.println("Dealer Wins!");
		gameFrame.getGameTablePanel().setChipsOnTable(0);
		gameFrame.updateGameTable();
		JOptionPane.showMessageDialog(null,"Dealer wins!","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayTie() {
		System.out.println("It is a tie!.");
		gameFrame.getGameTablePanel().setChipsOnTable(0);
		gameFrame.updateGameTable();
		JOptionPane.showMessageDialog(null,"It's a tie!","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayPlayerQuit() {
		System.out.println("You have quit the current game.");
		JOptionPane.showMessageDialog(null, "You have quit the current game.","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayPlayerQuitFirstRoundMessage() {
		JOptionPane.showMessageDialog(null, "Player cannot quit in the first round","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayPlayerCardsOnHand(Player player) {
		
		System.out.println(player.getLoginName());

		if(player instanceof Dealer) {
			
			for(int i=0;i<player.getCardsOnHand().size();i++) {
				
				System.out.print(player.getCardsOnHand().get(i).toString()+" ");
				
			}
		}else {
			for(Card card:player.getCardsOnHand()) {
				System.out.print(card+" ");
			}
		}
		System.out.println();
		
		gameFrame.updateGameTable();
	}
	
	public void displayBlankLine() {
		System.out.println();
	}
	
	public void displayPlayerTotalCardValue(Player player) {
		System.out.println("Value:"+player.getTotalCardsValue());
	}
	
	public void displayDealerDealCardsAndGameRound() {
		System.out.println("Dealer dealing cards - ROUND ");
		gameFrame.updateGameTable();
	}
	
	public void displayGameStart() {
		System.out.println("Game starts - Dealer shuffle deck");
		gameFrame.getGameTable();
	}
	
	public void displayPlayerNameAndChips(Player player) {
		System.out.println(player.getLoginName()+", You have "+player.getChips()+" chips");
		gameFrame.updateGameTable();
	}
	
	public void displayPlayerNameAndLeftOverChips(Player player) {
		System.out.println(player.getLoginName()+", You are left with "+player.getChips()+" chips");
		gameFrame.updateGameTable();
		JOptionPane.showMessageDialog(null, player.getLoginName()+", you have "+player.getChips()+" chips left","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	public void displayGameTitle() {
		System.out.println("HighSum GAME");
	}
	
	public void displaySingleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	public void displayDoubleLine() {
		for(int i=0;i<30;i++) {
			System.out.print("=");
		}
		System.out.println();
	}
	
	
	public int getPlayerBet(Player player) {
		
		Object[] options = {"Player", "Dealer"};
		int call = JOptionPane.showOptionDialog(null, "Select an option:", "HighSum Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (call == JOptionPane.YES_OPTION && player.getChips() > 0) {
		    // Dealer selected
		} else if (player.getChips() == 0) {
		    call = JOptionPane.NO_OPTION;
		}

		return call;

	}
	
	public int getPlayerCallOrQuit(Player player) {
		
		int call = JOptionPane.showConfirmDialog(null,"Do you wish to bet?", "HighSum Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
		
		if(call == JOptionPane.YES_OPTION && player.getChips()>0) {
			
		}else if(player.getChips()==0) {
	
			call = JOptionPane.NO_OPTION;
		}
		
		
		
		return call;
	}
	
	public int getPlayerNextGame() {
		int nextGame = JOptionPane.showConfirmDialog(null,"Next Game?", "HighSum Game", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
		return nextGame;
	}
	
	public int getPlayerCallBetChip(Player player) {
		boolean validBetAmount = false;
		int chipsToBet = 0;
		while(!validBetAmount) {
			int option = JOptionPane.showOptionDialog(null, gameFrame.getCallPanel(), "HighSum Game",
	                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
	                null, new Object[]{"OK"}, null);
			
			String bet = gameFrame.getInput().getText();
			
			if(option == JOptionPane.YES_OPTION && player.getChips()>0) {
				try {
					
					chipsToBet = Integer.parseInt(bet);
					
					if(chipsToBet<=0) {
						System.out.println("Chips cannot be negative");
						JOptionPane.showMessageDialog(null, "Please enter a number more than zero","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
					}else if(chipsToBet>player.getChips()) {
						JOptionPane.showMessageDialog(null, "Insufficent chips, you have "+player.getChips()+" chips","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
						System.out.println("You do not have enough chips");
					}else {
						validBetAmount = true;
					}
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter a number","HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
				}
			}
		}
		return chipsToBet;
	}
	
	public int getDealerCallBetChips() {
		System.out.println("Dealer call, state bet: 10");
		return 10;
	}
	
	public boolean getPLayerlogin() {
		boolean login = false;
		int count = 3;
		while(count>0) {
	        int option = JOptionPane.showOptionDialog(null, gameFrame.getLoginPanel(), "Game Login",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                null, new Object[]{"Login"}, null);
	        
	        if(option == JOptionPane.OK_OPTION) {
	
	     	       String result = "Successful login";
	     	       
	     	       if(player.checkPassword(gameFrame.getLoginPanel().getInputPassword()) && player.getLoginName().equals(gameFrame.getLoginPanel().getInputName())) {
	     	    	   login = true;
	     	    	   count = 0;
	     	   	   		
	     	       }else {
	     	    	   count--;
	     	    	   result = "Incorrect username or password, you have "+count+" attempt left";   
	     	       }
	     	       
	     	       
	     	       JOptionPane.showMessageDialog(null, result,"HighSum Game",JOptionPane.PLAIN_MESSAGE,null);
	     	       
	        } else {
	        	login = false;
	        }
        }   
		
		return login;
	}
	
	
}
