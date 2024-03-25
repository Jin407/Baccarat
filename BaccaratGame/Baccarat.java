import Model.*;
import Controller.*;
import View.*;

public class Baccarat {

	private Dealer dealer;
	private Player player;
	private ViewController view;
    private GameController gc;

    public Baccarat() {
    	//create all the required objects
    	this.dealer = new Dealer();
        this.player = new Player("Username","Password",100);
        this.view = new ViewController(dealer,player);
        //bring them together
    	this.gc = new GameController(this.dealer,this.player,this.view);
    }

    public void run() {
    	//starts the game!
    	gc.run();
    }

	public static void main(String[] args) {
	    new Baccarat().run();
	}

}
