package game.player;

import game.bet.Bet;
import game.bet.BetType;

// This player always bets on a tie.
public class TieBetter extends Player{

    // creates the TieBetter
    public TieBetter(String name, int id){
        super(name, id);
    }

    // Always bet on a tie.
    @Override
    public Bet makeBet() {
        Bet newBet = new Bet(id, BetType.TIE, BET_AMOUNT);
        return newBet;
    }

    // return bettype
    public BetType getBetType(){return BetType.TIE;}
}
