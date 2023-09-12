package test;


import game.bet.Bet;
import game.bet.BetType;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test framework for the Bet/BetType class.
 *
 * @author RIT CS
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestBet {
    @Test
    @Order(1)
    public void test1_BetType() {
        BetType playerBet = BetType.PLAYER;
        assertEquals(4.0, playerBet.getPayout(2), 0);
        BetType bankerBet = BetType.BANKER;
        assertEquals(5.85, bankerBet.getPayout(3), .001);
        BetType tieBet = BetType.TIE;
        assertEquals(36.0, tieBet.getPayout(4), 0);
    }

    @Test
    @Order(2)
    public void test2_Bet() {
        Bet bet1 = new Bet(0, BetType.PLAYER, 5);
        assertEquals("PLAYER", bet1.getType().toString());
        assertEquals(5, bet1.getAmount());
        assertEquals(10.0, bet1.getPayout(), 0);

        Bet bet2 = new Bet(0, BetType.BANKER, 6);
        assertEquals("BANKER", bet2.getType().toString());
        assertEquals(6, bet2.getAmount());
        assertEquals(11.7, bet2.getPayout(), 0.01);

        Bet bet3 = new Bet(0, BetType.TIE, 7);
        assertEquals("TIE", bet3.getType().toString());
        assertEquals(7, bet3.getAmount());
        assertEquals(63.0, bet3.getPayout(), 0);
    }
}
