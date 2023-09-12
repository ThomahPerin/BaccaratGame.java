package test;

import game.cards.Card;
import game.cards.Rank;
import game.cards.Suit;
import game.cards.Pile;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * JUnit test framework for the Pile class.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPile {
    private final PrintStream standardOut = System.out;
    /** standard output capturer */
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeAll
    public static void seedPile() {
        Pile.setSeed(0);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @Order(1)
    public void test1_createEmpty() {
        Pile testPile = new Pile("Test");
        assertEquals(0, testPile.numCards());
        assertEquals("Test pile: ", testPile.toString());
    }

    @Test
    @Order(2)
    public void test2_addCards() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        assertEquals(4, testPile.numCards());
        assertEquals("Test pile: 3♧ 10♢ Q♥ A♠ ", testPile.toString());
    }

    @Test
    @Order(3)
    public void test3_drawCards() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        assertEquals("3♧", testPile.drawCard().toString());
        assertEquals("Test pile: 10♢ Q♥ A♠ ", testPile.toString());
        assertEquals("10♢", testPile.drawCard().toString());
        assertEquals("Test pile: Q♥ A♠ ", testPile.toString());
        assertEquals("Q♥", testPile.drawCard().toString());
        assertEquals("Test pile: A♠ ", testPile.toString());
        assertEquals("A♠", testPile.drawCard().toString());
        assertEquals("Test pile: ", testPile.toString());
        assertEquals(0, testPile.numCards());
    }

    @Test
    @Order(4)
    public void test4_shuffle() {
        Pile testPile = new Pile("Test");
        testPile.addCard(new Card(Rank.THREE, Suit.CLUB));
        testPile.addCard(new Card(Rank.TEN, Suit.DIAMOND));
        testPile.addCard(new Card(Rank.QUEEN, Suit.HEART));
        testPile.addCard(new Card(Rank.ACE, Suit.SPADE));
        testPile.shuffle();
        assertEquals("Test pile: A♠ 3♧ 10♢ Q♥ ", testPile.toString());
        testPile.shuffle();
        assertEquals("Test pile: 3♧ A♠ Q♥ 10♢ ", testPile.toString());
        assertEquals(4, testPile.numCards());
    }
}
