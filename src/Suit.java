
/**
 * <p>
 * This is an enum class for different suits for a card. It contains methods
 * like getValue to return the value of a suit for comparison in other classes.
 * </p>
 * 
 * @author yikaicao
 * @version 0.1
 */

public enum Suit {
	Clubs(1), Diamonds(2), Hearts(3), Spades(4);

	// instance variable
	private int suit;

	// constructor
	private Suit(int suit) {
		this.setSuit(suit);
	}

	// getSuit method
	public int getSuit() {
		return suit;
	}

	// setSuit method
	public void setSuit(int suit) {
		this.suit = suit;
	}

	// getValue method
	public int getValue() {
		return suit;
	}
}
