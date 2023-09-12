package game.cards;

import java.util.ArrayList;
import java.util.Collection;

// represent a hand in the game (either the player or banker).
public class Hand {

    // declare private member variables
    String name;
    ArrayList<Card> cardList = new ArrayList<>();


    // constructor creates a new empty Hand
    public Hand(String name){
     this.name = name;
    }

    // Adds a card to the hand
    public void addCard(Card card){
        cardList.add(card);
    }

    // Clears the hand out by removing all the cards.
    public void clear(){
        cardList.clear();
    }

    // Clear the hand out by removing all the cards
    public int compareTo(Hand other){
        int diff;
        diff = getValue() - other.getValue();
        return diff;}

    //returns the collection of cards in the hand
    public Collection<Card> getCards(){return cardList;}

    // returns hand value in terms of a baccarat hand
    public int getValue(){
        int total = 0;
        for (int i = 0; i < cardList.size(); i++){
            Card s = cardList.get(i);
            total += s.getValue();
        }
        total = total % 10;

        return total;}

    // return string representation of a card
    public String toString() {
        String s = "";
        for (int i = 0; i < cardList.size(); i++) {
            Card card1 = cardList.get(i);
            s += card1.toString() + " ";

        }
        return s;
    }

}
