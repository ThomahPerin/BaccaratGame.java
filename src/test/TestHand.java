package test;

import game.cards.Card;
import game.cards.Rank;
import game.cards.Suit;
import game.cards.Hand;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test framework for the Hand class.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHand {
    /** standard output */
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void test1_createEmpty() {
        Hand hand = new Hand("Test");
        assertEquals(0, hand.getValue());
        assertEquals("Test (0): ", hand.toString());
    }

    @Test
    @Order(2)
    public void test2_addCards() {
        Hand hand = new Hand("Test");
        hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand.addCard(new Card(Rank.FOUR, Suit.HEART));
        assertEquals(4, hand.getValue());
        assertEquals("Test (4): 10♠ 4♥ ", hand.toString());
        hand.addCard(new Card(Rank.NINE, Suit.DIAMOND));
        assertEquals(3, hand.getValue());
        assertEquals("Test (3): 10♠ 4♥ 9♢ ", hand.toString());
    }

    @Test
    @Order(3)
    public void test3_compareTo() {
        Hand hand1 = new Hand("Test1");
        hand1.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEART));
        Hand hand2 = new Hand("Test2");
        hand2.addCard(new Card(Rank.THREE, Suit.SPADE));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        assertEquals(0, hand1.compareTo(hand2));

        hand1.addCard(new Card(Rank.TWO, Suit.CLUB));
        assertEquals(2, hand1.compareTo(hand2));
        assertEquals(-2, hand2.compareTo(hand1));
    }

    @Test
    @Order(4)
    public void test4_getCards() {
        Hand hand1 = new Hand("Test1");
        hand1.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand1.addCard(new Card(Rank.FOUR, Suit.HEART));
        assertEquals("[10♠, 4♥]", hand1.getCards().toString());
        Hand hand2 = new Hand("Test2");
        hand2.addCard(new Card(Rank.THREE, Suit.SPADE));
        hand2.addCard(new Card(Rank.ACE, Suit.DIAMOND));
        assertEquals("[3♠, A♢]", hand2.getCards().toString());
    }

    @Test
    @Order(5)
    public void test5_clear() {
        Hand hand = new Hand("Test");
        hand.addCard(new Card(Rank.TEN, Suit.SPADE));
        hand.addCard(new Card(Rank.FOUR, Suit.HEART));
        hand.clear();
        assertEquals(0, hand.getValue());
        assertEquals("Test (0): ", hand.toString());
    }
}
