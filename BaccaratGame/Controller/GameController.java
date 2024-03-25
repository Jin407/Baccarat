package Controller;
import javax.swing.JOptionPane;

import Model.Dealer;
import Model.Player;
import View.ViewController;

public class GameController {

	private Dealer dealer;
	private Player player;
	private ViewController view;
	private int chipsOnTable;
	
	public GameController(Dealer dealer,Player player,ViewController view) {
		this.dealer = dealer;
		this.player = player;
		this.view = view;
		this.chipsOnTable = 0;
	}
	
	
	public void run() {
		
		boolean login = this.view.getPLayerlogin();
		
		if(login) {
			
			boolean carryOn = true;
			
			while(carryOn) {
				runOneRound();
				if(this.player.getChips()==0) {
					break;
				}
				int nextGame = this.view.getPlayerNextGame();
				
				if(nextGame == JOptionPane.NO_OPTION || nextGame == JOptionPane.CLOSED_OPTION) {
					carryOn = false;
				}
				
			}
			
			this.view.displayExitGame();	
			
		}	
	}
	
	public void runOneRound() {
		
		this.view.displayGameTitle();
		this.view.displayDoubleLine();
		this.view.displayPlayerNameAndChips(this.player);
		this.view.displaySingleLine();
		this.view.displayGameStart();
		this.view.displaySingleLine();
		this.dealer.shuffleCards();
		this.chipsOnTable = 0;
		boolean playerQuit = false;
		boolean GameOver = false;
		//1 for player, 2 for dealer
		int bet = 0;
		
		//player call
		int call = this.view.getPlayerCallOrQuit(this.player);
		if(call == JOptionPane.YES_OPTION) {
			int bet_option = view.getPlayerBet(this.player);
			if(bet_option == JOptionPane.YES_OPTION) {
				bet = 1;
			}else {
				bet = 2;
			}
			int chipsToBet = view.getPlayerCallBetChip(this.player);
			this.player.deductChips(chipsToBet);
			this.chipsOnTable+=2*chipsToBet;
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			this.view.displayBetOntable(this.chipsOnTable);
		}else {
			playerQuit = true;
		}
		
		this.view.displaySingleLine();
		this.view.displayDealerDealCardsAndGameRound();
		this.view.displaySingleLine();	
		
		//round 1 deal extra card
		this.dealer.dealCardTo(this.player);
		this.dealer.dealCardTo(this.dealer);
		this.dealer.dealCardTo(this.player);
		this.dealer.dealCardTo(this.dealer);
		
		this.view.displayPlayerCardsOnHand(this.dealer);
		this.view.displayBlankLine();
		this.view.displayPlayerCardsOnHand(player);
		this.view.displayPlayerTotalCardValue(player);
		this.view.displayDealerDealCardsAndGameRound();
		
		//check who win
		if(playerQuit) {
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			this.view.displayPlayerQuit();
		}else if(this.player.getTotalCardsValue() > 7 && this.dealer.getTotalCardsValue() < this.player.getTotalCardsValue()) {
			if(bet == 1) {
				this.player.addChips(chipsOnTable);
			}
			this.chipsOnTable = 0;
			this.view.displayPlayerWin(this.player);
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			GameOver = true;
		}else if(this.dealer.getTotalCardsValue() > 7 && this.dealer.getTotalCardsValue() > this.player.getTotalCardsValue()) {
			if(bet == 2) {
				this.player.addChips(chipsOnTable);
			}
			this.chipsOnTable = 0;
			this.view.displayDealerWin();
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			GameOver = true;
		}else if(this.dealer.getTotalCardsValue() == this.player.getTotalCardsValue()){
			this.player.addChips(chipsOnTable/2);
			this.chipsOnTable = 0;
			this.view.displayTie();
			this.view.displayPlayerNameAndLeftOverChips(this.player);
			GameOver = true;
		}
		
		if(!GameOver) {
		
			if(this.dealer.getTotalCardsValue() < 5) {
				this.dealer.dealCardTo(this.dealer);
			}
			
			if(this.player.getTotalCardsValue() < 5) {
				this.dealer.dealCardTo(this.player);
			}
		
		
			if(this.dealer.getTotalCardsValue() < this.player.getTotalCardsValue()) {
				if(bet == 1) {
					this.player.addChips(chipsOnTable);
				}
				this.chipsOnTable = 0;
				this.view.displayPlayerWin(this.player);
				this.view.displayPlayerNameAndLeftOverChips(this.player);
				
			}else if(this.dealer.getTotalCardsValue() > this.player.getTotalCardsValue()) {
				if(bet == 2) {
					this.player.addChips(chipsOnTable);
				}
				this.chipsOnTable = 0;
				this.view.displayDealerWin();
				this.view.displayPlayerNameAndLeftOverChips(this.player);
				
			}else {
				this.player.addChips(chipsOnTable/2);
				this.chipsOnTable = 0;
				this.view.displayTie();
				this.view.displayPlayerNameAndLeftOverChips(this.player);
			}
		}
		
		//put all the cards back to the deck
		dealer.addCardsBackToDeck(dealer.getCardsOnHand());
		dealer.addCardsBackToDeck(player.getCardsOnHand());
		dealer.clearCardsOnHand();
		player.clearCardsOnHand();	
	}
	
	
}
