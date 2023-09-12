package game.player;

import game.bet.Bet;
import game.bet.BetType;

// This is a player who always bets on the banker hand.
public class BankerBetter extends Player{

    // create the BankerBetter
    public BankerBetter(String name, int id){
        super(name, id);
    }

    // Always bet on the banker hand
    public Bet makeBet(){
       Bet newBet = new Bet(id, BetType.BANKER, BET_AMOUNT);
       return newBet;
    }

    // return BetType
    public BetType getBetType(){return BetType.BANKER;}
}
