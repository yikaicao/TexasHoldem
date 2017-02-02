/**
 * 
 * <p>
 * Interface for a @PokerHand with 5 @Card. Can be extended to arbitrary number
 * of @Card since all methods here use while loop to iterate a hand.
 * </p>
 * 
 * <p>
 * This class is developed to returns a series of operations for one Texas poker
 * hand.
 * <p>
 * 
 * 
 * <ol>
 * <li>The constructor takes 5 @Card and insert them in the order of first come
 * first serve</li>
 * <li>compareTo method takes two @PokerHand and compare them in a Texas Hold'em
 * way, from straight flush all the way down to high cards</li>
 * </ol>
 * 
 * @author yikaicao
 * @version 0.1
 */

public class PokerHand implements Comparable<PokerHand> {

	// Instance variables
	@SuppressWarnings("unused")
	private Card card1, card2, card3, card4, card5;
	private LinkNode<Card> head;
	private LinkNode<Card> tail;

	// PokerHand is constructed by a list of LinkNode<Card>

	// Constructor
	public PokerHand(Card card1, Card card2, Card card3, Card card4, Card card5) throws DuplicateCardException {
		insertInOrder(card1);
		insertInOrder(card2);
		insertInOrder(card3);
		insertInOrder(card4);
		insertInOrder(card5);
	}

	// InsertInOrder method
	// Every card is originally inserted in a list starts with "head"
	public void insertInOrder(Card card) {
		// Insert the first card
		if (head == null) {
			head = new LinkNode<Card>(card, null);
			return;
		}

		LinkNode<Card> ref = head;
		LinkNode<Card> temp;

		// Insert at the first position
		if (ref.getData().compareTo(card) > 0) {
			temp = new LinkNode<Card>(card, ref);
			head = temp;
			return;
		}

		// Inserting in regular case
		while (ref.getNext() != null) {
			if (ref.getNext().getData().compareTo(card) > 0) {
				temp = new LinkNode<Card>(card, ref.getNext());
				ref.setNext(temp);
				return;
			}
			ref = ref.getNext();
		}

		// Insert at the last position
		temp = new LinkNode<Card>(card, null);
		ref.setNext(temp);
	}

	// InsertBackOrder method returns nothing and saves high cards from large to
	// small at the left into a list starts with "tail". This method would be
	// useful if we need to compare high cards.
	public void insertBackOrder() {
		LinkNode<Card> ref = head;

		while (ref != null) {
			if (ref.getData().getRank().getValue() != hasOnePair() && ref.getData().getRank().getValue() != hasTwoPair()
					&& !existInTail(ref.getData()))
				tail = new LinkNode<Card>(ref.getData(), tail);
			ref = ref.getNext();
		}
	}

	// ExistInTail method returns true if the card is already in tail list
	// This method is created because sometimes insertBackOrder() method might
	// be called for several times. We don not want duplicate card stored in
	// tail list
	public boolean existInTail(Card aCard) {
		LinkNode<Card> ref = tail;
		while (ref != null) {
			if (ref.getData().compareSuit(aCard) == 0 && ref.getData().compareRank(aCard) == 0)
				return true;
			ref = ref.getNext();
		}
		return false;
	}

	// CheckDuplication method returns nothing
	// It compares every card between this pokerhand and another pokerhand
	private void checkDuplication(PokerHand aHand) {
		LinkNode<Card> ref = head;
		LinkNode<Card> anotherRef = aHand.head;

		while (ref != null) {
			while (anotherRef != null) {
				ref.getData().compareTo(anotherRef.getData());
				anotherRef = anotherRef.getNext();
			}
			anotherRef = aHand.head;
			ref = ref.getNext();
		}
	}

	// CompareTo method
	public int compareTo(PokerHand aHand) {
		checkDuplication(aHand);

		// Since we might compare high cards, we need to use
		// insertBackOrder for both hands so that we can find the highest
		// card from the list
		insertBackOrder();
		aHand.insertBackOrder();

		// Check straight flush
		if (hasStraightFlush() - aHand.hasStraightFlush() != 0)
			return hasStraightFlush() - aHand.hasStraightFlush();

		// Check quad
		if (hasQuad() - aHand.hasQuad() != 0)
			return hasQuad() - aHand.hasQuad();

		// Check boat
		if (hasBoat() - aHand.hasBoat() != 0)
			return hasBoat() - aHand.hasBoat();

		// Check flush
		if (hasFlush() - aHand.hasFlush() != 0)
			return hasFlush() - aHand.hasFlush();

		// Check straight
		if (hasStraight() - aHand.hasStraight() != 0)
			return hasStraight() - aHand.hasStraight();

		// Check triplet
		if (hasTriplet() - aHand.hasTriplet() != 0)
			return hasTriplet() - aHand.hasTriplet();

		// Check two pair
		if (hasTwoPair() - aHand.hasTwoPair() != 0)
			return hasTwoPair() - aHand.hasTwoPair();

		// Check one pair
		if (hasOnePair() - aHand.hasOnePair() != 0)
			return hasOnePair() - aHand.hasOnePair();

		// Compare high card
		return compareHighCard(aHand);
	}

	// CompareHighCard method returns the result after comparing high card
	// This method works for every type of rankings.
	public int compareHighCard(PokerHand aHand) {
		LinkNode<Card> ref = tail;
		LinkNode<Card> anotherRef = aHand.tail;
		while (ref != null) {
			if (ref.getData().compareRank(anotherRef.getData()) != 0)
				return ref.getData().compareRank(anotherRef.getData());
			ref = ref.getNext();
			anotherRef = anotherRef.getNext();
		}
		// Return 0 after comparing ONE PAIR cases
		return 0;
	}

	// HasStraightFlush method returns the largest rank in the straight
	public int hasStraightFlush() {
		if (hasStraight() * hasFlush() != 0)
			return hasStraight();
		return 0;
	}

	// HasQuad method returns the rank of the quad
	public int hasQuad() {
		LinkNode<Card> ref = head;
		while (ref != null) {
			// Check if there exists next
			if (ref.getNext() != null)
				// Compare rank with next
				if (ref.getData().compareRank(ref.getNext().getData()) == 0)
					// Check if there exists next next
					if (ref.getNext().getNext() != null)
						// Compare rank with next next
						if (ref.getNext().getData().compareRank(ref.getNext().getNext().getData()) == 0)
							// Check if there exists next next next
							if (ref.getNext().getNext().getNext() != null)
								// Compare rank with next next next
								if (ref.getNext().getData()
										.compareRank(ref.getNext().getNext().getNext().getData()) == 0)
									return ref.getData().getRank().getValue();
			ref = ref.getNext();
		}
		return 0;
	}

	// HasBoat method returns the rank of the triplet
	public int hasBoat() {
		if (hasTwoPair() * hasTriplet() != 0)
			return hasTriplet();
		return 0;
	}

	// HasFlush method returns the largest rank in the flush
	public int hasFlush() {
		LinkNode<Card> ref = head;
		while (ref.getNext() != null) {
			if (ref.getData().compareSuit(ref.getNext().getData()) != 0)
				return 0;
			ref = ref.getNext();
		}
		return ref.getData().getRank().getValue();
	}

	// HasStraight method returns the largest rank in the straight
	public int hasStraight() {
		LinkNode<Card> ref = head;
		// Check if the first card is Two and the last card is Ace
		if (head.getData().getRank().getValue() == 2
				&& head.getNext().getNext().getNext().getNext().getData().getRank().getValue() == 14) {
			// Check if the following cards follows
			while (ref.getNext().getNext() != null) {
				if (ref.getData().compareRank(ref.getNext().getData()) != -1)
					return 0;
				ref = ref.getNext();
			}
			return 5;
		}

		// Regular cases
		ref = head;
		while (ref.getNext() != null) {
			if (ref.getData().compareRank(ref.getNext().getData()) != -1)
				return 0;
			ref = ref.getNext();
		}
		return ref.getData().getRank().getValue();
	}

	// HasTriplet method returns the rank of the triplet
	public int hasTriplet() {
		LinkNode<Card> ref = head;
		while (ref != null) {
			// Check if there exists next
			if (ref.getNext() != null)
				// Compare rank with next
				if (ref.getData().compareRank(ref.getNext().getData()) == 0)
					// Check if there exists next next
					if (ref.getNext().getNext() != null)
						// Compare rank with next next
						if (ref.getNext().getData().compareRank(ref.getNext().getNext().getData()) == 0)
							return ref.getData().getRank().getValue();
			ref = ref.getNext();
		}
		return 0;
	}

	// HasTwoPair method returns the rank of the largest pair
	public int hasTwoPair() {
		LinkNode<Card> ref = head;
		if (hasOnePair() != 0)
			while (ref != null) {
				if (ref.getNext() != null)
					if (ref.getData().compareRank(ref.getNext().getData()) == 0
							&& ref.getData().getRank().getValue() != hasOnePair())
						return ref.getData().getRank().getValue();
				ref = ref.getNext();
			}
		return 0;
	}

	// HasOnePair method returns the rank of the smallest pair
	public int hasOnePair() {
		LinkNode<Card> ref = head;
		while (ref != null) {
			if (ref.getNext() != null) {
				// Check if there is a pair
				if (ref.getData().compareRank(ref.getNext().getData()) == 0) {
					// If there is a pair, record the paired rank and return
					return ref.getData().getRank().getValue();
				}
			}
			ref = ref.getNext();
		}
		return 0;
	}

	// ToString method
	public String toString() {
		String toReturn = "";
		LinkNode<Card> ref = head;
		while (ref != null) {
			toReturn += ref.getData().getSuit() + " ";
			toReturn += ref.getData().getRank() + "\n";
			ref = ref.getNext();
		}
		return toReturn;
	}

	// ToStringBackward method
	public String toStringBackward() {
		String toReturn = "";
		LinkNode<Card> ref = tail;
		while (ref != null) {
			toReturn += ref.getData().getSuit() + " ";
			toReturn += ref.getData().getRank() + "\n";
			ref = ref.getNext();
		}
		return toReturn;
	}
}
