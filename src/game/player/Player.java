package game.player;

import game.bet.Bet;
import game.bet.BetType;
import game.cards.Hand;

// common abstract superclass for all subclasses of players
// Represents a player/better in the game
public abstract class Player {

    // declare private member variables


    //the amount each player bets
    static int BET_AMOUNT = 1;
    String name;
    int id;

    public int wins = 0;
    int losses = 0;
    double balance = 0;

    //Create a new Player
    Player(String name, int id){
        this.name = name;
        this.id = id;
    }

    // return name
    public String getName(){
        return this.name;
    };

    // return player'd id
    public int getId(){
        return this.id;
    };

    // return players' win percent
    public double getWinPercent(){
        if ((losses + wins)== 0){return 0.0;}
        else {
            double winsAndLosses = (double) losses + wins;
            double win1 = (double) wins;
            return (win1 / (winsAndLosses));
        }
    }

   // Player won their bet.
    public void addWin(){
     wins = wins + 1;
    }

    //Player lost their bet.
    public void addLoss(){
    losses = losses + 1;
    }
    // Adjust the player's balance by a positive/negative amount.
    public void adjustBalance(double amount){
      balance += amount;
    }

    // subclasses implement makeBet
    //The player makes their own bet based on their playing style.
    public abstract Bet makeBet();

    // subclasses each return their own bettype
    public abstract BetType getBetType();

    // return string representation of a player
    public String toString(){
        // Format: Player{name='BankerBetter', id=1, balance=-1.05, wins=1, losses=1, win percentage=0.5}
        String s = "";
        s += "Player{name='" + this.name + "', id=" + this.id + ", balance=" +
                this.balance + ", wins=" + this.wins + ", losses=" + this.losses
                + ", win percentage="  + this.getWinPercent();
        return s;
    }

}
