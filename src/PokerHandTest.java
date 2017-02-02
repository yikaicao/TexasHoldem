
/**
 * <p>
 * This class stores test cases to test if Card.java, Rank.java,
 * Suit.java and PokerHand.java are functional and meet specific requirements
 * </p>
 * 
 * @author yikaicao
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the PokerHand class and the enums.
 * 
 * Tests include every possible hands.
 * 
 * Includes all 52 cards to save some time
 * 
 * There are also some additional test cases to test edge cases.
 */
public class PokerHandTest {

	@Test
	public void testSuitEnum() {
		String result = "";
		for (Suit suit : Suit.values())
			result += suit + " ";
		assertEquals("Clubs Diamonds Hearts Spades", result.trim());
	}

	@Test
	public void testRankEnum() {
		String result = "";
		for (Rank rank : Rank.values())
			result += rank + " ";
		assertEquals("Deuce Three Four Five Six Seven Eight Nine Ten Jack Queen King Ace", result.trim());
	}

	@Test(expected = DuplicateCardException.class)
	public void testDuplicatedCard() {
		new PokerHand(C2, C3, C4, C5, C5);
	}

	@Test(expected = DuplicateCardException.class)
	public void testDuplicatedCardInTwoHands() {
		PokerHand duplicate1 = new PokerHand(H3, CA, D4, H6, DA);
		duplicate1.toString();
		PokerHand duplicate2 = new PokerHand(H3, C5, HA, SA, C6);
		assertTrue(duplicate1.compareTo(duplicate2) < 0);
	}

	@Test
	public void testInsertBackOrder() {
		PokerHand a = new PokerHand(D7, D6, H8, C8, DJ);
		a.insertBackOrder();
		assertEquals("Diamonds Jack\nDiamonds Seven\nDiamonds Six\n", a.toStringBackward());

		PokerHand b = new PokerHand(C3, H3, D4, C4, SJ);
		b.insertBackOrder();
		assertEquals("Spades Jack\n", b.toStringBackward());

		PokerHand c = new PokerHand(C3, H4, D4, C4, SJ);
		c.insertBackOrder();
		assertEquals(4, c.hasTriplet());
		assertEquals("Spades Jack\nClubs Three\n", c.toStringBackward());

		c.insertBackOrder();
		c.insertBackOrder();
		c.insertBackOrder();
		c.insertBackOrder();
		assertEquals("Spades Jack\nClubs Three\n", c.toStringBackward());
	}

	@Test
	public void testToString() {
		PokerHand toString1 = new PokerHand(H3, H4, H5, H6, H7);
		assertEquals("Hearts Three\n" + "Hearts Four\n" + "Hearts Five\n" + "Hearts Six\n" + "Hearts Seven\n",
				toString1.toString());
		PokerHand toString2 = new PokerHand(H3, CA, D4, H6, DA);
		assertEquals("Hearts Three\n" + "Diamonds Four\n" + "Hearts Six\n" + "Clubs Ace\n" + "Diamonds Ace\n",
				toString2.toString());
		PokerHand toString3 = new PokerHand(H3, C3, D3, H6, S3);
		assertEquals("Clubs Three\n" + "Diamonds Three\n" + "Hearts Three\n" + "Spades Three\n" + "Hearts Six\n",
				toString3.toString());
	}

	@Test
	public void testStraightFlush() {
		PokerHand straightFlush1 = new PokerHand(C7, C8, C9, C10, CJ);
		assertEquals(11, straightFlush1.hasStraightFlush());
		PokerHand straightFlush2 = new PokerHand(D2, D3, D4, D5, D6);
		assertEquals(6, straightFlush2.hasStraightFlush());
		PokerHand straightFlush3 = new PokerHand(H2, H3, H4, H5, H6);
		assertEquals(6, straightFlush3.hasStraightFlush());
		PokerHand straightFlush4 = new PokerHand(H2, H3, H4, H5, HA);
		assertEquals(5, straightFlush4.hasStraightFlush());
	}

	@Test
	public void testQuad() {
		PokerHand quad1 = new PokerHand(C3, C4, D4, H4, S4);
		assertEquals(4, quad1.hasQuad());

		PokerHand nothing = new PokerHand(C3, C2, D4, H4, S4);
		assertEquals(0, nothing.hasQuad());
	}

	@Test
	public void testBoat() {
		PokerHand onePair1 = new PokerHand(H3, CA, D4, H6, DA);
		PokerHand twoPair1 = new PokerHand(H3, CA, D3, H6, DA);
		PokerHand triplet1 = new PokerHand(H4, C4, D4, H6, SA);
		assertEquals(0, onePair1.hasBoat());
		assertEquals(0, twoPair1.hasBoat());
		assertEquals(0, triplet1.hasBoat());

		PokerHand boat1 = new PokerHand(H4, C4, D4, H5, S5);
		assertEquals(4, boat1.hasBoat());
		PokerHand boat2 = new PokerHand(H6, C6, D6, H7, S7);
		assertEquals(6, boat2.hasBoat());
	}

	@Test
	public void testFlush() {
		PokerHand flush1 = new PokerHand(D7, D8, D9, D10, DJ);
		assertEquals(11, flush1.hasFlush());
		PokerHand flush2 = new PokerHand(H7, H3, H9, HK, HA);
		assertEquals(14, flush2.hasFlush());
	}

	@Test
	public void testStraight() {
		PokerHand nothing = new PokerHand(S2, H3, C4, C5, D8);
		assertEquals(0, nothing.hasStraight());
		PokerHand straight1 = new PokerHand(D7, D8, S9, D10, DJ);
		assertEquals(11, straight1.hasStraight());
		PokerHand straight2 = new PokerHand(DQ, D8, D9, D10, DJ);
		assertEquals(12, straight2.hasStraight());
	}

	@Test
	public void testLowStraight() {
		PokerHand lowStraight = new PokerHand(D5, D3, S2, DA, D4);
		assertEquals(5, lowStraight.hasStraight());
		PokerHand nothing = new PokerHand(DK, D3, S2, DA, D4);
		assertEquals(0, nothing.hasStraight());

	}

	@Test
	public void testTriplet() {
		PokerHand triplet1 = new PokerHand(H4, C4, D4, H6, SA);
		assertEquals(4, triplet1.hasOnePair());
		assertEquals(4, triplet1.hasTriplet());

		PokerHand triplet2 = new PokerHand(H4, C5, D5, H5, SA);
		assertEquals(5, triplet2.hasOnePair());
		assertEquals(5, triplet2.hasTriplet());

		PokerHand triplet3 = new PokerHand(H2, C4, D6, H6, S6);
		assertEquals(6, triplet3.hasOnePair());
		assertEquals(6, triplet3.hasTriplet());
	}

	@Test
	public void testTwoPair() {
		PokerHand twoPair1 = new PokerHand(H3, CA, D3, H6, DA);
		assertEquals(14, twoPair1.hasTwoPair());
		assertEquals(0, twoPair1.hasTriplet());

		PokerHand twoPair2 = new PokerHand(H5, C4, D3, S5, D4);
		assertEquals(5, twoPair2.hasTwoPair());
		assertEquals(0, twoPair2.hasTriplet());
	}

	@Test
	public void testOnePair() {
		PokerHand onePair1 = new PokerHand(H3, CA, D4, H6, DA);
		assertEquals("Hearts Three\n" + "Diamonds Four\n" + "Hearts Six\n" + "Clubs Ace\n" + "Diamonds Ace\n",
				onePair1.toString());
		assertEquals(14, onePair1.hasOnePair());
		assertEquals(0, onePair1.hasTriplet());

		PokerHand onePair2 = new PokerHand(H3, C3, D4, H6, DA);
		assertEquals("Clubs Three\n" + "Hearts Three\n" + "Diamonds Four\n" + "Hearts Six\n" + "Diamonds Ace\n",
				onePair2.toString());
		assertEquals(3, onePair2.hasOnePair());
		assertEquals(0, onePair2.hasTriplet());
	}

	@Test
	public void testHasNothing() {
		PokerHand nothing = new PokerHand(S2, H3, C4, C5, D8);
		assertEquals(0, nothing.hasOnePair());
		assertEquals(0, nothing.hasTwoPair());
		assertEquals(0, nothing.hasTriplet());
		assertEquals(0, nothing.hasStraight());
		assertEquals(0, nothing.hasFlush());
		assertEquals(0, nothing.hasQuad());
		assertEquals(0, nothing.hasBoat());
		assertEquals(0, nothing.hasStraightFlush());
	}

	@Test
	public void testCompareStraightFlush() {
		PokerHand straightFlush1 = new PokerHand(C7, C8, C9, C10, CJ);
		PokerHand straightFlush2 = new PokerHand(D2, D3, D4, D5, D6);
		assertEquals(5, straightFlush1.compareTo(straightFlush2));

		PokerHand straightFlush3 = new PokerHand(H2, H3, H4, H5, H6);
		assertEquals(0, straightFlush2.compareTo(straightFlush3));

		PokerHand straightFlush4 = new PokerHand(S2, S3, S4, S5, SA);
		assertEquals(1, straightFlush3.compareTo(straightFlush4));
	}

	@Test
	public void testCompareQuad() {
		PokerHand quad1 = new PokerHand(C3, C4, D4, H4, S4);
		assertEquals(4, quad1.hasQuad());
		PokerHand quad2 = new PokerHand(C5, C8, D8, H8, S8);
		assertEquals(8, quad2.hasQuad());

		assertEquals(-4, quad1.compareTo(quad2));
	}

	@Test(expected = DuplicateCardException.class)
	public void testCompareQuadDuplication() {
		PokerHand quad1 = new PokerHand(C7, C4, D4, H4, S4);
		assertEquals(4, quad1.hasQuad());
		PokerHand quad2 = new PokerHand(C2, C4, D5, S6, C9);
		assertEquals(0, quad1.compareTo(quad2));
	}

	@Test
	public void testCompareBoat() {
		PokerHand boat1 = new PokerHand(H4, C4, D4, H5, S5);
		PokerHand boat2 = new PokerHand(H6, C6, D6, H7, S7);

		assertEquals(-2, boat1.compareTo(boat2));
	}

	@Test
	public void testCompareFlush() {
		PokerHand flush1 = new PokerHand(D2, D3, D4, D5, DJ);
		assertEquals(11, flush1.hasFlush());
		PokerHand flush2 = new PokerHand(H7, H3, H9, HK, HA);
		assertEquals(14, flush2.hasFlush());
		PokerHand flush3 = new PokerHand(C6, C7, C9, C10, CJ);
		assertEquals(11, flush3.hasFlush());
		PokerHand flush4 = new PokerHand(D6, D7, D9, D10, DJ);
		assertEquals(11, flush4.hasFlush());
		PokerHand flush5 = new PokerHand(C6, C7, C9, C10, CJ);
		assertEquals(11, flush5.hasFlush());

		assertEquals(-3, flush1.compareTo(flush2));
		assertEquals(-5, flush1.compareTo(flush3));
		assertEquals(0, flush4.compareTo(flush5));
		assertEquals(0, flush3.compareTo(flush4));

	}

	@Test
	public void testCompareStraright() {
		PokerHand straight1 = new PokerHand(D2, D3, D4, D5, H6);
		PokerHand straight2 = new PokerHand(C3, C4, C5, C6, H7);
		assertEquals(-1, straight1.compareTo(straight2));
	}

	@Test
	public void testCompareStraightSameRank() {
		PokerHand straight1 = new PokerHand(D2, D3, D4, D5, H6);
		PokerHand straight2 = new PokerHand(C3, C4, C5, C6, S2);
		assertEquals(0, straight1.compareTo(straight2));
	}

	@Test
	public void testCompareLowStraight() {
		PokerHand straight1 = new PokerHand(CA, C2, C3, C4, H5);
		PokerHand straight2 = new PokerHand(D2, D3, D4, D5, H6);
		assertEquals(-1, straight1.compareTo(straight2));
	}

	@Test
	public void testCompareTriplet() {
		PokerHand triplet1 = new PokerHand(C3, C4, D4, H4, C9);
		PokerHand triplet2 = new PokerHand(S4, C5, D5, H5, SA);

		assertEquals(-1, triplet1.compareTo(triplet2));

	}

	@Test(expected = DuplicateCardException.class)
	public void testCompareTripletDuplicatedCard() {
		PokerHand triplet1 = new PokerHand(H3, C5, D5, H5, DA);
		PokerHand triplet2 = new PokerHand(S3, C5, D5, H5, SK);
		assertEquals(-1, triplet1.compareTo(triplet2));

	}

	@Test
	public void testCompareTwoPair() {
		PokerHand twoPair1 = new PokerHand(D3, C4, D4, S5, H5);
		PokerHand twoPair2 = new PokerHand(S3, H4, S4, C5, D5);

		assertEquals(16, twoPair1.hasOnePair() * twoPair2.hasOnePair());
		assertEquals(25, twoPair1.hasTwoPair() * twoPair2.hasTwoPair());
		assertEquals(0, twoPair1.compareTo(twoPair2));

		PokerHand twoPair3 = new PokerHand(C6, D6, C7, D7, D8);
		PokerHand twoPair4 = new PokerHand(H6, S6, H7, S7, S8);

		assertEquals(36, twoPair3.hasOnePair() * twoPair4.hasOnePair());
		assertEquals(49, twoPair3.hasTwoPair() * twoPair4.hasTwoPair());
		assertEquals(0, twoPair3.compareTo(twoPair4));

		assertEquals(-2, twoPair1.compareTo(twoPair3));
		assertEquals(-2, twoPair1.compareTo(twoPair4));
	}

	@Test
	public void testCompareTwoPairWhenTwoPairIsEqual() {
		PokerHand twoPair1 = new PokerHand(D3, C4, D4, S5, H5);
		PokerHand twoPair2 = new PokerHand(S2, H4, S4, C5, D5);

		assertEquals(1, twoPair1.compareTo(twoPair2));
		assertEquals("Diamonds Three\n", twoPair1.toStringBackward());
		assertEquals("Spades Deuce\n", twoPair2.toStringBackward());

	}

	@Test
	public void testCompareTwoPairWhenOnePairIsEqual() {
		PokerHand twoPair1 = new PokerHand(C4, HK, D4, H3, DK);
		PokerHand twoPair2 = new PokerHand(H4, C10, CA, DA, S4);
		assertEquals(-1, twoPair1.compareTo(twoPair2));
		assertEquals(1, twoPair2.compareTo(twoPair1));
	}

	@Test
	public void testCompareOnePair() {
		PokerHand onePair1 = new PokerHand(C7, H10, D10, HJ, DK);
		PokerHand onePair2 = new PokerHand(H7, C8, D8, DJ, SA);

		assertEquals(2, onePair1.compareTo(onePair2));
	}

	@Test
	public void testCompareOnePairWhenOnePairIsEqual() {
		PokerHand onePair1 = new PokerHand(C7, H10, D10, HJ, DK);
		PokerHand onePair2 = new PokerHand(H7, C10, S10, DJ, SA);

		assertEquals(-1, onePair1.compareTo(onePair2));

		PokerHand onePair3 = new PokerHand(C7, H10, D10, HJ, DA);
		PokerHand onePair4 = new PokerHand(H6, C10, S10, DJ, SA);

		assertEquals(1, onePair3.compareTo(onePair4));

		PokerHand onePair5 = new PokerHand(C7, H10, D10, HJ, DA);
		PokerHand onePair6 = new PokerHand(H7, C10, S10, DJ, SA);

		assertEquals(0, onePair5.compareTo(onePair6));
	}

	@Test
	public void testCompareDifferentRanking() {

		PokerHand straightFlush1 = new PokerHand(C7, C8, C9, C10, CJ);
		PokerHand quad1 = new PokerHand(C3, SQ, DQ, HQ, CQ);
		PokerHand boat1 = new PokerHand(H4, C4, D4, H5, S5);
		PokerHand flush1 = new PokerHand(H7, H8, H9, H10, HK);
		PokerHand straight1 = new PokerHand(D7, D8, S9, D10, DJ);
		PokerHand triplet1 = new PokerHand(C3, C4, D4, H4, C9);
		PokerHand twoPair1 = new PokerHand(D3, C7, D7, S5, H5);
		PokerHand onePair1 = new PokerHand(H3, CA, D4, H6, SA);
		PokerHand nothing1 = new PokerHand(C2, S9, H10, CJ, DA);

		// Rank difference is one level
		assertEquals(11, straightFlush1.compareTo(quad1));
		assertEquals(12, quad1.compareTo(boat1));
		assertEquals(4, boat1.compareTo(flush1));
		assertEquals(13, flush1.compareTo(straight1));
		assertEquals(11, straight1.compareTo(triplet1));
		assertEquals(4, triplet1.compareTo(twoPair1));
		assertEquals(7, twoPair1.compareTo(onePair1));
		assertEquals(14, onePair1.compareTo(nothing1));

		// Rank difference is multiple levels
		assertEquals(11, straightFlush1.compareTo(onePair1));
		assertEquals(12, quad1.compareTo(nothing1));

	}

	// Initialize instances for 52 cards so we can use C2 instead of new
	// Card(Rank.Deuce,
	// Suit.Clubs)
	private final static Card C2 = new Card(Rank.Deuce, Suit.Clubs);
	private final static Card C3 = new Card(Rank.Three, Suit.Clubs);
	private final static Card C4 = new Card(Rank.Four, Suit.Clubs);
	private final static Card C5 = new Card(Rank.Five, Suit.Clubs);
	private final static Card C6 = new Card(Rank.Six, Suit.Clubs);
	private final static Card C7 = new Card(Rank.Seven, Suit.Clubs);
	private final static Card C8 = new Card(Rank.Eight, Suit.Clubs);
	private final static Card C9 = new Card(Rank.Nine, Suit.Clubs);
	private final static Card C10 = new Card(Rank.Ten, Suit.Clubs);
	private final static Card CJ = new Card(Rank.Jack, Suit.Clubs);
	private final static Card CQ = new Card(Rank.Queen, Suit.Clubs);
	private final static Card CK = new Card(Rank.King, Suit.Clubs);
	private final static Card CA = new Card(Rank.Ace, Suit.Clubs);

	private final static Card D2 = new Card(Rank.Deuce, Suit.Diamonds);
	private final static Card D3 = new Card(Rank.Three, Suit.Diamonds);
	private final static Card D4 = new Card(Rank.Four, Suit.Diamonds);
	private final static Card D5 = new Card(Rank.Five, Suit.Diamonds);
	private final static Card D6 = new Card(Rank.Six, Suit.Diamonds);
	private final static Card D7 = new Card(Rank.Seven, Suit.Diamonds);
	private final static Card D8 = new Card(Rank.Eight, Suit.Diamonds);
	private final static Card D9 = new Card(Rank.Nine, Suit.Diamonds);
	private final static Card D10 = new Card(Rank.Ten, Suit.Diamonds);
	private final static Card DJ = new Card(Rank.Jack, Suit.Diamonds);
	private final static Card DQ = new Card(Rank.Queen, Suit.Diamonds);
	private final static Card DK = new Card(Rank.King, Suit.Diamonds);
	private final static Card DA = new Card(Rank.Ace, Suit.Diamonds);

	private final static Card H2 = new Card(Rank.Deuce, Suit.Hearts);
	private final static Card H3 = new Card(Rank.Three, Suit.Hearts);
	private final static Card H4 = new Card(Rank.Four, Suit.Hearts);
	private final static Card H5 = new Card(Rank.Five, Suit.Hearts);
	private final static Card H6 = new Card(Rank.Six, Suit.Hearts);
	private final static Card H7 = new Card(Rank.Seven, Suit.Hearts);
	private final static Card H8 = new Card(Rank.Eight, Suit.Hearts);
	private final static Card H9 = new Card(Rank.Nine, Suit.Hearts);
	private final static Card H10 = new Card(Rank.Ten, Suit.Hearts);
	private final static Card HJ = new Card(Rank.Jack, Suit.Hearts);
	private final static Card HQ = new Card(Rank.Queen, Suit.Hearts);
	private final static Card HK = new Card(Rank.King, Suit.Hearts);
	private final static Card HA = new Card(Rank.Ace, Suit.Hearts);

	private final static Card S2 = new Card(Rank.Deuce, Suit.Spades);
	private final static Card S3 = new Card(Rank.Three, Suit.Spades);
	private final static Card S4 = new Card(Rank.Four, Suit.Spades);
	private final static Card S5 = new Card(Rank.Five, Suit.Spades);
	private final static Card S6 = new Card(Rank.Six, Suit.Spades);
	private final static Card S7 = new Card(Rank.Seven, Suit.Spades);
	private final static Card S8 = new Card(Rank.Eight, Suit.Spades);
	private final static Card S9 = new Card(Rank.Nine, Suit.Spades);
	private final static Card S10 = new Card(Rank.Ten, Suit.Spades);
	private final static Card SJ = new Card(Rank.Jack, Suit.Spades);
	private final static Card SQ = new Card(Rank.Queen, Suit.Spades);
	private final static Card SK = new Card(Rank.King, Suit.Spades);
	private final static Card SA = new Card(Rank.Ace, Suit.Spades);

	// TEST CARD HGH HANDS

	private static PokerHand nothing72 = new PokerHand(C2, C3, C4, C5, D7);
	private static PokerHand nothing73 = new PokerHand(D2, D4, D5, D6, C7);
	private static PokerHand nothingJ = new PokerHand(C8, C9, C10, SJ, D3);
	private static PokerHand nothingK9 = new PokerHand(CK, CQ, CJ, D10, H9);
	private static PokerHand nothingK8 = new PokerHand(HK, HQ, HJ, H10, S8);
	private static PokerHand nothingA = new PokerHand(S9, SJ, SQ, SK, CA);
	private static PokerHand nothingS = new PokerHand(H8, S2, S5, S7, S10);

	@Test
	public void testNothing0() {
		assertEquals(-1, nothing72.compareTo(nothing73));
	}

	@Test
	public void testNothing1() {
		assertEquals(-4, nothing73.compareTo(nothingJ));
	}

	@Test
	public void testNothing2() {
		assertEquals(-2, nothingJ.compareTo(nothingK8));
	}

	@Test
	public void testNothing3() {
		assertEquals(13, nothingK9.compareTo(nothingK8));
	}

	@Test
	public void testNothing4() {
		assertEquals(-1, nothingK8.compareTo(nothingA));
	}

	@Test
	public void testNothing5() {
		assertEquals(4, nothingA.compareTo(nothingS));
	}

}