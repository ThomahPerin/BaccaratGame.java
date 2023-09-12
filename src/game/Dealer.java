package game;

import game.bet.BetType;
import game.cards.*;
import game.player.Player;

import java.util.ArrayList;

// Represents the dealer, who is responsible for playing the rounds of the game.
public class Dealer {


   // the name of the discard pile
    static final String DISCARD_PILE_NAME = "Discard";

    // the minimum value the dealer will hit, if applicable
    static final int HIT_VALUE = 5;

   // the minimum number of cards needed to play the next round
    static final int MIN_CARDS_TO_RESHUFFLE = 6;

    // the name of the shoe pile
    static final String SHOE_PILE_NAME = "Shoe";

   // the minimum value of a stand from the initial deal
    static final int STAND_VALUE = 8;

    // declare other member variables
    private Baccarat house;
    public Player[] players;

    public Pile shoePiles;
    public Pile discardPiles;

    public Hand playerHand;
   public Hand bankerHand;

   public boolean eightOrNine;
   public int playerTotal;
   public int bankerTotal;
   public BetType winningType;

   // dealer constructor creates two piles: dealer and discard piles
    public Dealer(Baccarat house, Player[] players){
        this.house = house;
        this.players = players;
        // create pile and discard pile
        shoePiles = new Pile(SHOE_PILE_NAME);
        discardPiles = new Pile(DISCARD_PILE_NAME);
        // create new deck of cards in same way every time (it is only changed when it is shuffled)
        // using the seed
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values()) {
                shoePiles.addCard(new Card(rank, suit));
            }
        }
        // shuffle cards
        shoePiles.shuffle();
    }

    // play a round
    void playRound(){
        // create player and banker hand
        playerHand = new Hand("Player Hand");
        bankerHand = new Hand("Banker Hand");

        //Gather bets from each of the four players.
        for (int i = 0; i < players.length; i++){
            players[i].makeBet();
        }
        System.out.println("BETS");
        for (int i = 0; i < Baccarat.NUM_PLAYERS; i++){
            System.out.println("\t" + players[i].getName() + " bet 1 on " + players[i].makeBet().getType());
        }
        //Deal the initial two cards to the player and banker hand (in that order).
        if (shoePiles.shoePile.size() >= MIN_CARDS_TO_RESHUFFLE){
            playerHand.addCard(shoePiles.drawCard());
            playerHand.addCard(shoePiles.drawCard());
            bankerHand.addCard(shoePiles.drawCard());
            bankerHand.addCard(shoePiles.drawCard());
        }
        // if the shoe pile has less than 6 cards, all the cards in the discard pile
        // should be added to the shoe pile and it should be reshuffled before dealing any cards.
        else {
            for (int i = 0; i < discardPiles.shoePile.size(); i++){
                shoePiles.addCard(discardPiles.drawCard());
            }
            shoePiles.shuffle();
            playerHand.addCard(shoePiles.drawCard());
            playerHand.addCard(shoePiles.drawCard());
            bankerHand.addCard(shoePiles.drawCard());
            bankerHand.addCard(shoePiles.drawCard());
        }
        // print out hands
        System.out.println("HANDS");
        System.out.println("\t Player (" + playerHand.getValue() + "): " + playerHand.toString());
        System.out.println("\t Dealer (" + bankerHand.getValue() + "): " + bankerHand.toString());



        //Based on the cards in the hand and the rules, the dealer handles the hit or stand for each hand.
        eightOrNine = false;

        // get initial player and banker hand values after each get two cards
        playerTotal = playerHand.getValue();
        bankerTotal = bankerHand.getValue();

        //If either hand has a total of eight or nine, both hands stand.
        if (playerTotal >= STAND_VALUE || bankerTotal >= STAND_VALUE){eightOrNine = true;}

       // If both hands do not have a total eight or nine:
        // If the player's total is five or less, the player receives another card, otherwise stands.
        // Likewise, the banker hits on a total of five or less, and otherwise stands
        System.out.println("HIT/STAND");
        if (eightOrNine == false) {
            if (playerTotal <= HIT_VALUE) {
                playerHand.addCard(shoePiles.drawCard());
                System.out.println("\tPlayer hits");
                playerTotal = playerHand.getValue();
            }
            if (bankerTotal <= HIT_VALUE) {
                bankerHand.addCard(shoePiles.drawCard());
                System.out.println("\tDealer hits");
                bankerTotal = bankerHand.getValue();
            }
            System.out.println("HANDS");
            System.out.println("\t Player (" + playerHand.getValue() + "): " + playerHand.toString());
            System.out.println("\t Dealer (" + bankerHand.getValue() + "): " + bankerHand.toString());
        }
        else{
            System.out.println("\tPlayer and Banker stand");
        }

        //After the hands are finalized, the dealer handles the winners and losers by cycling
        // through the player bets and adjusting their balance plus their win/loss ratio.
        boolean playerWin = false;
        boolean bankerWin = false;

        // determine if banker or player won
        // if neither are true then it is a tie
        if (playerTotal > bankerTotal){playerWin = true;}
        else if (playerTotal < bankerTotal){bankerWin = true;}

        //print out results: which bet won
        System.out.println("RESULTS");
        if (playerWin == true){winningType = BetType.PLAYER;}
        else if (bankerWin == true){winningType = BetType.BANKER;}
        else {winningType = BetType.TIE;}

        // if the player's hand wins than those who bet on the player win
        // those who didn't bet on the player lose
        System.out.println("\tWINNER: " + winningType);
        if (playerWin == true){
            System.out.println("\tPlayerBetter won " + BetType.PLAYER.getPayout(1));
            System.out.println("\tBankerBetter lost 1");
            System.out.println("\tTieBetter lost 1" );
            winningType = BetType.PLAYER;
            house.indicateWinner(BetType.PLAYER);
            //players[0].addWin();
            players[1].addLoss();
            players[2].addLoss();
            players[0].adjustBalance(BetType.PLAYER.getPayout(1));
            house.earn(-BetType.PLAYER.getPayout(1));
            players[1].adjustBalance(-1);
            house.earn(1.0);
            players[2].adjustBalance(-1);
            house.earn(1.0);
            if (players[3].getBetType() == BetType.PLAYER){
                //players[3].addWin();
                players[3].adjustBalance(BetType.PLAYER.getPayout(1));
                house.earn(-BetType.PLAYER.getPayout(1));
                System.out.println("\tCustomBetter won " + BetType.PLAYER.getPayout(1));
            }
            else{
                //players[3].addLoss();
                players[3].adjustBalance(-1);
                house.earn(1.0);
                System.out.println("\tCustomBetter lost 1");
            }
        }

        // if the banker's hand wins than those who bet on the player win
        // those who didn't bet on the banker lose
        else if (bankerWin == true){
            winningType = BetType.BANKER;
            house.indicateWinner(BetType.BANKER);
            System.out.println("\tPlayerBetter lost 1");
            System.out.println("\tBankerBetter won " + BetType.BANKER.getPayout(1));
            System.out.println("\tTieBetter lost 1");
            //players[1].addWin();
            players[0].addLoss();
            players[2].addLoss();
            players[1].adjustBalance(BetType.BANKER.getPayout(1));
            house.earn(-BetType.BANKER.getPayout(1));
            players[0].adjustBalance(-1);
            house.earn(1.0);
            players[2].adjustBalance(-1);
            house.earn(1.0);
            if (players[3].getBetType() == BetType.BANKER){
                //players[3].addWin();
                players[3].adjustBalance(BetType.BANKER.getPayout(1));
                house.earn(-BetType.BANKER.getPayout(1));
                System.out.println("\tCustomBetter won " + BetType.BANKER.getPayout(1));
            }
            else{
                //players[3].addLoss();
                players[3].adjustBalance(-1);
                house.earn(1.0);
                System.out.println("\tCustomBetter lost 1");
            }
        }
        // only other option is that it was a tie
        // if it's a tie than those who bet on the tie win
        // those who didn't bet on the tie lose
        else {
            System.out.println("\tPlayerBetter lost 1");
            System.out.println("\tBankerBetter lost 1");
            System.out.println("\tTieBetter won " + BetType.TIE.getPayout(1));
            winningType = BetType.TIE;
            house.indicateWinner(BetType.TIE);
            //players[2].addWin();
            players[0].addLoss();
            players[1].addLoss();
            players[2].adjustBalance(BetType.TIE.getPayout(1));
            house.earn(-BetType.TIE.getPayout(1));
            players[0].adjustBalance(-1);
            house.earn(1.0);
            players[1].adjustBalance(-1);
            house.earn(1.0);
            if (players[3].getBetType() == BetType.TIE){
                //players[3].addWin();
                players[3].adjustBalance(BetType.TIE.getPayout(1));
                house.earn(-BetType.TIE.getPayout(1));
                System.out.println("\tCustomBetter won " + BetType.TIE.getPayout(1));
            }
            else{
                //players[3].addLoss();
                players[3].adjustBalance(-1);
                house.earn(1.0);
                System.out.println("\tCustomBetter lost 1");
            }
        }
        // print out the players
        System.out.println("PLAYERS");
        for (int i = 0; i < players.length; i++){
            System.out.println("\t" + players[i].toString());
        }

        // To end the round, the cards in the player and banker hands are added to the discard pile (in that order).
        ArrayList<Card> playerUsedHand = (ArrayList<Card>) playerHand.getCards();
        for (int i = 0; i < playerUsedHand.size(); i++){
            discardPiles.addCard(playerUsedHand.get(i));
        }

        ArrayList<Card> bankerUsedHand = (ArrayList<Card>) bankerHand.getCards();
        for (int i = 0; i < bankerUsedHand.size(); i++){
            discardPiles.addCard(bankerUsedHand.get(i));
        }

    }
}
