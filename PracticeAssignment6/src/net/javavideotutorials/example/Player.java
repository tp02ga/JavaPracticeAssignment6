package net.javavideotutorials.example;

import java.util.List;

public class Player
{
  private String name;
  /**
   * These are the two cards that the player holds in their hand
   */
  private List<Card> hand;
  ActualPokerHand playerHandStrength;
  /**
   * These are the five cards that make up the player's best hand
   */
  private List<Card> playableHand; 
  
  public Player (String name)
  {
    this.name = name;
  }
  
  /**
   * @return The best five card hand that this player has 
   */
  public List<Card> getPlayableHand()
  {
    return playableHand;
  }


  public void setPlayableHand(List<Card> playableHand)
  {
    this.playableHand = playableHand;
  }

  public String getName()
  {
    return name;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  
  /**
   * @return the 2 cards in this player's hand
   */
  public List<Card> getHand()
  {
    return hand;
  }
  public void setHand(List<Card> hand)
  {
    this.hand = hand;
  }
  
  public ActualPokerHand getPlayerHandStrength()
  {
    return playerHandStrength;
  }

  public void setPlayerHandStrength(ActualPokerHand playerHandStrength)
  {
    this.playerHandStrength = playerHandStrength;
  }

  @Override
  public String toString()
  {
    return this.name + this.hand;
  }
}
