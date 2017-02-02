
/**
 * <p>This JUnit test file tests if two cards are correctly comparable to each other.</p>
 * 
 * @author yikaicao
 *   
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest {

	@Test
	public void testCompareRank() {
		assertEquals(-1, C2.compareRank(C3));
		assertEquals(-2, C4.compareRank(C6));
		assertEquals(-1, C5.compareRank(D6));
		assertEquals(3, C7.compareRank(D4));
	}

	// Set up several cards that is needed to be used in the tests
	private final static Card C2 = new Card(Rank.Deuce, Suit.Clubs);
	private final static Card C3 = new Card(Rank.Three, Suit.Clubs);
	private final static Card C4 = new Card(Rank.Four, Suit.Clubs);
	private final static Card C5 = new Card(Rank.Five, Suit.Clubs);
	private final static Card C6 = new Card(Rank.Six, Suit.Clubs);
	private final static Card C7 = new Card(Rank.Seven, Suit.Clubs);

	private final static Card D4 = new Card(Rank.Four, Suit.Diamonds);
	private final static Card D6 = new Card(Rank.Six, Suit.Diamonds);

}
