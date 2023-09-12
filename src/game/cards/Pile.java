package game.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Represents the pile of cards that are dealt out to the hands
// There are two piles created, the shoe pile and discard pile.
public class Pile {
    // declares private member variables
    String name1;
    public ArrayList<Card> shoePile = new ArrayList<>();
    Pile pile;
    public static Random rng;
    public static long seed1;

    // constructor creates a new empty Pile
    public Pile(String name){
        rng = new Random();
        rng.setSeed(seed1);
        this.name1 = name;
    }

    // add card to pile
   public void addCard(Card card){
        shoePile.add(card);
   }

   // add other pile to this classes pile
    public void addCards(Pile pile){
        this.pile = pile;
        for (int i = 0; i < pile.numCards(); i++){
            shoePile.add(pile.drawCard());
        }
    }

    // draw and revove the first card from this pile
    public Card drawCard(){
        Card s = shoePile.get(0);
        shoePile.remove(0);
        return s;}

    // return the number of cards
    public int numCards(){
        if (shoePile == null){
            return 0;
        }
        return shoePile.size();
    }

    // set the seed
    public static void setSeed(long seed) {
        seed1 = seed;
    }

    // shuffle card pile using seed
    public void shuffle(){

        // worked after putting rng in constructor, so it was only called once...?
        if (rng != null) {
            Collections.shuffle(shoePile, rng);
        }
        }


        // return string representation of the pile
    public String toString(){
        String s = "";
        s += "Test pile: ";
        for (int i = 0; i < shoePile.size(); i++){
            Card temp = shoePile.get(i);
            s += temp.toString() + " ";
        }
        return s;
    }

}
