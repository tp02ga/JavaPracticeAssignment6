package net.javavideotutorials.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck 
{
  private List<Card> cards = new ArrayList<Card>();
  
  public Deck ()
  {
    for (Suit suit : Suit.values())
    {
      for (Value value : Value.values())
      {
        Card card = new Card(suit, value);
        cards.add(card);
      }
    }
  }
  
  public List<Card> getCards()
  {
    return cards;
  }
  
  public void shuffleDeck()
  {
    Collections.shuffle(cards);
  }
}
