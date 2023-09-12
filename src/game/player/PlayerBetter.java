package game.player;

import game.bet.Bet;
import game.bet.BetType;

// This player always bets on the player hand.
public class PlayerBetter extends Player{

    // constructor creates the PlayerBetter.
    public PlayerBetter(String name, int id){
        super(name, id);
    }


    //Always bet on the player hand.
    @Override
    public Bet makeBet() {
        Bet newBet = new Bet(id, BetType.PLAYER, BET_AMOUNT);
        return newBet;
    }

    // return bettype
    public BetType getBetType(){return BetType.PLAYER;}
}
