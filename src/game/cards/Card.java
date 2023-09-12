package game.cards;


// class creates a card with a rank and suit
public class Card {

    // declare private member variables
    Suit suit;
    Rank rank;

    // creates the card with the rank and suit
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
    }

    // Two cards are equal if the have the same rank (suit is ignored)
    // return true if equal
    public boolean equals(Object other){

        boolean i = other instanceof Card;

        if (i == true){
            Card s = (Card) other;
            if (getValue() == s.getValue()){
                return true;
            }
        }
            return false;
    }

    // return a cards rank value
    public int getValue(){
        return rank.getValue();
    }

    // string representation of a card includes it's rank and suit
    public String toString(){
        return rank.toString() + suit.toString();
    }
}
