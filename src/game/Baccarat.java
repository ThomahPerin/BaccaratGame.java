package game;

import game.bet.BetType;
import game.cards.Pile;
import game.player.*;

 // the main program for the Baccarat casino game.
// It runs on the command line with two arguments - the random seed and the number of rounds to play.
// reads in seed and number of rounds to play
// creates all players and dealers

public class Baccarat {

    // private data members
    public static int rounds;
    double houseBalance = 0.0;

    //name of the banker better
    static final String BANKER_BETTER = "BankBetter";

    // name of the custom better
    static final String CUSTOM_BETTER = "CustomBetter";

    //always have 4 players in the game
    static final int NUM_PLAYERS = 4;
    // name of the player better
    static final String PLAYER_BETTER = "PlayerBetter";

    // name of the tie better
    static final String TIE_BETTER = "TieBetter";

    public BankerBetter BankerBet;
   public CustomBetter CustomBet;
    public PlayerBetter PlayerBet;
    public TieBetter TieBet;
    private Player[] player;
    private Dealer Deal;

    public static int seed1;

    // Create a new Baccarat instance by initializing the players and dealer.
    public Baccarat(int rounds){
        // assign rounds
        this.rounds = rounds;
        // create four players
        PlayerBet = new PlayerBetter(PLAYER_BETTER, 0);
        BankerBet = new BankerBetter(BANKER_BETTER, 1);
        TieBet = new TieBetter(TIE_BETTER, 2);
        CustomBet = new CustomBetter(this, CUSTOM_BETTER, 3);

        // create array of players
        player = new Player[NUM_PLAYERS];
        player[0] = PlayerBet;
        player[1] = BankerBet;
        player[2] = TieBet;
        player[3] = CustomBet;

        // initialize dealer
        Deal = new Dealer(this, player);
    }

    // return seed
    public int getSeed(){return this.seed1;}

    // adjust house balance
    void earn(double amount){
        houseBalance += amount;
    }

    //Get the number of banker hand wins.
    public int getBankerHandWins(){
        return BankerBet.wins;
    }

   // Get the banker hand win percentage.
    public  double getBankerWinPct(){return BankerBet.getWinPercent()/ (PlayerBet.wins + BankerBet.wins + TieBet.wins);}
    // Get the number of player hand wins.
    public int getPlayerHandWins(){return PlayerBet.wins/ (PlayerBet.wins + BankerBet.wins + TieBet.wins);}
   // Get the player hand win percentage.
    public double getPlayerWinPct(){return PlayerBet.getWinPercent();}

    //Get the number of ties.
    public int getTieHandWins(){return TieBet.wins/ (PlayerBet.wins + BankerBet.wins + TieBet.wins);}
   // Get the tie win percentage.
    public double getTieWinPct(){return TieBet.getWinPercent();}
   // Called by the dealer win a winner is determined so the house can adjust the win/loss statistics.
    public void indicateWinner(BetType winner){
       if (winner == BetType.BANKER){BankerBet.wins++;}
        if (winner == BetType.PLAYER){PlayerBet.wins++;}
        if (winner == BetType.TIE){TieBet.wins++;}
    }

    // play the game
    public void playGame(){
        int roundsDone = 0;
        // play the amount of rounds specified in the command line
        while (roundsDone < rounds) {
            // print round number
            System.out.println("ROUND " + (roundsDone + 1));
            // play round
            Deal.playRound();
            System.out.println("");

            roundsDone++;
        }
        // print out statistics
        System.out.println("STATISTICS");
        System.out.println("\tRounds played: " + this.rounds);
        System.out.println("\tPlayer hand wins: " + Deal.players[0].wins);
        System.out.println("\tBanker hand wins: " + Deal.players[1].wins );
        System.out.println("\tTie hand wins: " + Deal.players[2].wins );
        System.out.println("\tPlayer hand win %: " + Deal.players[0].getWinPercent());
        System.out.println("\tBanker hand win %: " + Deal.players[1].getWinPercent());
        System.out.println("\tTie hand win %: " + Deal.players[2].getWinPercent());
        System.out.println("\tHouse balance: " + houseBalance);
    }

    // The main program takes the random number generator seed, and the number of rounds to play.
    public static void main(String[] args) {
        // get seed
        String seed = args[0];
        seed1 = Integer.parseInt(seed);
        // set seed
        Pile.setSeed(seed1);
        // get number of rounds
        String r = args[1];
        int x = Integer.parseInt(r);
        // create the house
        Baccarat house = new Baccarat(x);
        // house plays the game
        house.playGame();
    }


}