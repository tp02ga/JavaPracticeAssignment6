package net.javavideotutorials.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game
{
  private Deck deck;
  private List<Player> players = new ArrayList<Player>();
  private List<Card> communityCards = new ArrayList<Card>();
  
  public Game ()
  {
    for (int i=1; i<=4; i++)
      players.add(new Player("player"+i));
    
    deck = new Deck();
    deck.shuffleDeck();
    
    deal();
    // Once you have a good chunk of your Unit Tests passing, you can
    // uncomment the line below and start running this code via the Runnable class
    //System.out.println(determineWinner());
  }
  
  /**
   * This method is fully implemented and should work properly... if you need to make changes
   * then feel free
   * @return either the winning or tieing statement
   */
  String determineWinner()
  {
    int winningPlayerHandStrength = 0;
    List<Card> winningPlayerWinningCards = null;
    List<Card> winningPlayerWinningHand = null;
    Player winningPlayer = null;
    boolean draw = false;
    String kicker = "";
    
    for (Player player : players)
    {
      player.setPlayerHandStrength(checkForHand(player.getHand(), communityCards));
      player.setPlayableHand(player.getPlayerHandStrength().getPokerHand());
      List<Card> playerWinningCards = player.getPlayerHandStrength().getWinningCards();
      if (player.getPlayerHandStrength().getPokerHandEnum().getStrength() > winningPlayerHandStrength)
      {
        winningPlayer = player;
        winningPlayerHandStrength = player.getPlayerHandStrength().getPokerHandEnum().getStrength();
        winningPlayerWinningCards = playerWinningCards;
        winningPlayerWinningHand = player.getPlayableHand();
        draw = false;
        kicker = "";
      }
      else if (player.getPlayerHandStrength().getPokerHandEnum().getStrength() == winningPlayerHandStrength && winningPlayer != player)
      {
        Boolean newWinner = null;
        
        // compare two players' winning cards first
        Collections.sort(winningPlayerWinningCards);
        Collections.sort(playerWinningCards);
        for (int i=playerWinningCards.size()-1; i>0; i--)
        {
          if (playerWinningCards.get(i).getValue().getSuitValue() > winningPlayerWinningCards.get(i).getValue().getSuitValue())
          {
            newWinner = true;
            draw = false;
            kicker = "";
            if (player.getPlayerHandStrength().getPokerHandEnum().equals(PokerHandEnum.FLUSH))
            {
              kicker = ", " + playerWinningCards.get(i).getValue() + " kicker";
            }
          }
          else if (playerWinningCards.get(i).getValue().getSuitValue() == winningPlayerWinningCards.get(i).getValue().getSuitValue())
          {
          }
          else
          {
            newWinner = false;
            break;
          }
        }
        
        // if no winner is found then compare the entire hands against each other
        if (newWinner == null)
        {
          for (int i=4; i>=0; i--)
          {
            if (player.getPlayableHand().get(i).getValue().getSuitValue() > winningPlayerWinningHand.get(i).getValue().getSuitValue())
            {
              newWinner = true;
              draw = false;
              if (!player.getPlayerHandStrength().getPokerHand().equals(PokerHandEnum.HIGH_CARD))
                  kicker = ", " + player.getPlayableHand().get(i).getValue() + " kicker";
              break;
            }
            else if (player.getPlayableHand().get(i).getValue().getSuitValue() == winningPlayerWinningHand.get(i).getValue().getSuitValue())
            {
            }
            else
            {
              newWinner = false;
              break;
            }
          }
        }
        
        if (newWinner == null)
        {
          draw = true;
        }
        else if (newWinner)
        {
          draw = false;
          winningPlayer = player;
          winningPlayerHandStrength = player.getPlayerHandStrength().getPokerHandEnum().getStrength();
          winningPlayerWinningHand = player.getPlayableHand();
          winningPlayerWinningCards = playerWinningCards;
        }
      }
      System.out.print(player);
      System.out.print(" - " + player.getPlayerHandStrength());
      System.out.println();
    }
    
    System.out.println();
    for (Card card : communityCards)
    {
      System.out.println(card);
    }
    System.out.println();
    if (draw)
     return "There was a draw with hand: " + winningPlayer.getPlayerHandStrength();
    else
      return winningPlayer + " wins with " + winningPlayer.getPlayerHandStrength() + kicker;
  }

  /**
   * This method is the core of the game.  This is where we will determine what poker hands can be
   *  created from the cards in the player's hand and the community cards.  In my solution, I identified
   *  all the different Poker Hands that could be created from the 7 cards (2 in hand, 5 community cards on 'table')
   *  then returned only the strongest hand.
   *  
   * @param hand This is the list of cards that are held by an individual player (2 cards)
   * @param communityCards This is the list of cards that can be shared by all players (5 cards)
   * @return The strongest poker hand that a particular player holds
   *         This hand does not have to be made with the hards in the player's hand, it can be solely
   *         made up of just the community cards if need be... or it can be made up of either 1 or both
   *         cards in the player's hand.
   */
  ActualPokerHand checkForHand(List<Card> hand, List<Card> communityCards)
  {
    // initialize an empty list of actual poker hands that will be populated
    // as it gets passed into the individual PokerHand checking methods
    List<ActualPokerHand> pokerHands = new ArrayList<ActualPokerHand>();
    
    // All poker hands have a high card, this method will determine which card
    // is the high card.  Once it figures out which card is the high card, it will
    // assign a new ActualPokerHand to the List of pokerHands.
    //
    // ex. pokerHands.add(new ActualPokerHand(PokerHandEnum.HIGH_CARD, winningCard));
    checkForHighcard(hand, communityCards, pokerHands);
    
    checkForPair(hand, communityCards, pokerHands);
    checkForThreeOfAKind(hand, communityCards, pokerHands);
    checkForTwoPair(hand, communityCards, pokerHands);
    checkForStraight(hand, communityCards, pokerHands);
    checkForFlush(hand, communityCards, pokerHands);
    checkForFullHouse(hand, communityCards, pokerHands);
    checkForFourOfAKind(hand, communityCards, pokerHands);
    checkForStraightFlush(hand, communityCards, pokerHands);
    
    //....
    
    return null;
  }

  /**
   * In my solution, I used this method to determine if we had a straight flush AND if we had a royal flush... so I didnt
   * create a separate method to check for a royal flush.
   * 
   * @param hand the 2 cards in a given player's hand
   * @param communityCards the 5 community cards
   * @param pokerHands a list of all the actual PokerHands that can be made from the 7 available cards
   */
  private void checkForStraightFlush(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForFullHouse(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForTwoPair(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }
  
  private void checkForHighcard(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForFlush(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForStraight(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForPair(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }

  private void checkForThreeOfAKind(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
    
  }
  
  private void checkForFourOfAKind(List<Card> hand, List<Card> communityCards, List<ActualPokerHand> pokerHands)
  {
     
  }

  void deal ()
  {
    for (Player player : players)
    {
      List<Card> playerHand = new ArrayList<Card>();
      playerHand.add(deck.getCards().remove(0));
      playerHand.add(deck.getCards().remove(0));
      
      player.setHand(playerHand);
    }
    
    for (int i=0; i<5; i++)
    {
      communityCards.add(deck.getCards().remove(0));
    }
  }

  public void setDeck(Deck deck)
  {
    this.deck = deck;
  }

  public void setPlayers(List<Player> players)
  {
    this.players = players;
  }

  public void setCommunityCards(List<Card> communityCards)
  {
    this.communityCards = communityCards;
  }
  
}
