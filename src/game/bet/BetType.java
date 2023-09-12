package game.bet;


//An enum to represent the different types of bets the players can make.
public enum BetType {

    // three types of bets and their payouts
    BANKER(1.95), PLAYER(2.0), TIE(9.0);

    private final double payout;

    // constructor takes in payout and assigns it to local member variable, payout
    BetType(double payout) {
        this.payout = payout;
    }

    // return the payout for this bet based on an amount.
    public double getPayout(double amount){
        return payout * amount;
    }
}
