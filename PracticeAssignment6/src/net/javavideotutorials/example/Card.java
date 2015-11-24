package net.javavideotutorials.example;

public class Card implements Comparable<Card>
{
  private Suit suit;
  private Value value;
  
  public Card (Suit suit, Value value)
  {
    this.suit = suit;
    this.value = value;
  }
  
  public Suit getSuit()
  {
    return suit;
  }
  public Value getValue()
  {
    return value;
  }

  @Override
  public int compareTo(Card o)
  {
    // You will need to implement the proper code here
    // so that the cards can be properly sorted
    return 0;
  }
}
