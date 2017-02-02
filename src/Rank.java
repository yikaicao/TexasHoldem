/**
 * <p>
 * This is an enum class for different ranks for a card. It contains methods
 * like getValue to return the value of a rank for comparison in other classes.
 * </p>
 * 
 * @author yikaicao
 * @version 0.1
 */

public enum Rank {
	Deuce(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), Jack(11), Queen(12), King(
			13), Ace(14);

	// instance variable
	private int rank;

	// constructor
	private Rank(int rank) {
		this.setRank(rank);
	}

	// getRank method
	public int getRank() {
		return rank;
	}

	// setRank method
	public void setRank(int rank) {
		this.rank = rank;
	}

	// getValue method
	public int getValue() {
		return rank;
	}
}
