package game.player;

import game.Baccarat;
import game.bet.Bet;
import game.bet.BetType;

// this player bets based on whichever hand is furthest below the expected winning percentages
public class CustomBetter extends Player{



    //the expected win percentage for the player
    public static final double PLAYER_WIN_PCT = 0.4462;
    // the expected win percentage for the banker
    public static final double BANKER_WIN_PCT = 0.4585;

    //the expected win percentage for a tie
    public static final double TIE_WIN_PCT = 0.0953;

    // declare other member variables
    public static int round = 0;

    public Baccarat house;

    private BetType customsBet;

   // constructor creates the CustomBetter
    public CustomBetter(Baccarat house, String name, int id){

        super(name, id);
        this.house = house;
    }

    // return bettype
    public BetType getBetType(){return this.customsBet;}

    // This player makes their bet based on which win percentage is the lowest from the expected.
    public Bet makeBet(){
        // for the first round, bets on the banker
        if (round == 0) {
            Bet newBet = new Bet(id, BetType.BANKER, BET_AMOUNT);
            round++;
            return newBet;
        }
        // for other rounds determines the outcome that is lowest from expected
        else {
            double playerDiff = house.getPlayerWinPct() - PLAYER_WIN_PCT;
            double bankerDiff = house.getBankerHandWins() - BANKER_WIN_PCT;
            double tieDiff = house.getTieWinPct() - TIE_WIN_PCT;
            if (playerDiff <= bankerDiff && playerDiff <= tieDiff){
                Bet newBet = new Bet(id, BetType.PLAYER, BET_AMOUNT);
                customsBet = BetType.PLAYER;
                return newBet;
            }
            if (bankerDiff <= playerDiff && bankerDiff <= tieDiff){
                Bet newBet = new Bet(id, BetType.BANKER, BET_AMOUNT);
                customsBet = BetType.BANKER;
                return newBet;
            }
            else{
                Bet newBet = new Bet(id, BetType.TIE, BET_AMOUNT);
                customsBet = BetType.TIE;
                return newBet;
            }

        }
    }
}
