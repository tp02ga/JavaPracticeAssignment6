package net.javavideotutorials.example;

public enum PokerHandEnum
{
  HIGH_CARD(1, 1),
  PAIR(2, 2),
  TWO_PAIR(3, 4),
  THREE_OF_A_KIND(4, 3),
  STRAIGHT(5, 5),
  FLUSH(6, 5),
  FULL_HOUSE(7, 5),
  FOUR_OF_A_KIND(8, 4),
  STRAIGHT_FLUSH(9, 5),
  ROYAL_FLUSH(10, 5);
  
  private int strength;
  private int numSignificantCards;
  
  private PokerHandEnum(int strength, int numSignificantCards)
  {
    this.strength = strength;
    this.numSignificantCards = numSignificantCards;
  }

  public int getStrength()
  {
    return strength;
  }

  public int getNumSignificantCards()
  {
    return numSignificantCards;
  }
  
}
