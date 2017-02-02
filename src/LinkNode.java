/**
 * <p>
 * A custom link node data structure to store a hand. Note that the number of
 * cards are not limited here because for Texas it's 5 for some other games it
 * could be more.
 * </p>
 * 
 * @author yikaicao
 * 
 */

@SuppressWarnings("hiding")
public class LinkNode<Card> {

	// instance variables
	private Card data;
	private LinkNode<Card> next;

	// constructor
	public LinkNode(Card someData, LinkNode<Card> nextOne) {
		setData(someData);
		setNext(nextOne);
	}

	// setData method
	public void setData(Card someData) {
		data = someData;
	}

	// getData method
	public Card getData() {
		return data;
	}

	// setNext method
	public void setNext(LinkNode<Card> nextOne) {
		next = nextOne;
	}

	// getNext method
	public LinkNode<Card> getNext() {
		return next;
	}

}
