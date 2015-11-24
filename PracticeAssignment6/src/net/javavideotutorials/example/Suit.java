package net.javavideotutorials.example;

public enum Suit
{
  HEARTS(1), 
  CLUBS(2), 
  DIAMONDS(3), 
  SPADES(4);
  
  private int suitValue;
  
  private Suit (int suitValue)
  {
    this.suitValue = suitValue;
  }

  public int getSuitValue()
  {
    return suitValue;
  }
  
}
