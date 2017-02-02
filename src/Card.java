
/**
 * 
 * <p>
 * Interface for Card instances.
 * </p>
 * 
 * <p>
 * Constructor takes rank and suit as its parameter to create a card instance
 * with getter and setter for later access.
 * </p>
 * 
 * <p>
 * A card can be compared by another card. Note that this interface assumes Suit
 * is also comparable, while in Texas Hold'em it is not.
 * </p>
 * 
 * @author Yikai Cao
 * @version 0.1
 * 
 */

public class Card {

	// instance variables
	private Rank rank;
	private Suit suit;

	// constructor
	public Card(Rank rank, Suit suit) {
		this.setRank(rank);
		this.setSuit(suit);
	}

	// getRank method
	public Rank getRank() {
		return rank;
	}

	// setRank method
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	// getSuit method
	public Suit getSuit() {
		return suit;
	}

	// setSuit method
	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	// compareTo method
	public int compareTo(Card card) throws DuplicateCardException {
		if (compareRank(card) != 0)
			return compareRank(card);
		if (compareSuit(card) != 0)
			return compareSuit(card);
		throw new DuplicateCardException();
	}

	// compareRank method
	public int compareRank(Card card) {
		return this.rank.getValue() - card.rank.getValue();
	}

	// compareSuit method
	public int compareSuit(Card card) {
		return this.suit.getSuit() - card.getSuit().getSuit();
	}
}
