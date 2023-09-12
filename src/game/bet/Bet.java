package game.bet;

// represents a single bet by a player.
public class Bet {

    // declare member variables
    int playerID;
    BetType type;
    int amount;

    // constructor creates a new Bet
    public Bet(int playerID, BetType type, int amount){
       this.playerID = playerID;
       this.type = type;
       this.amount = amount;
    }

    // return bet amount
    public int getAmount(){return amount;}

    // return bet payout
    public double getPayout(){
        return type.getPayout(getAmount());
    }

    // return bettype
    public BetType getType(){return type;}

    // represent a bet with a string
    public String toString(){
        String s = "";
        s += "ID, type, amount: " + playerID + " " + type + " " + amount;
        return s;
    }

}
