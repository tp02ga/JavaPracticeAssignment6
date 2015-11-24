package net.javavideotutorials.example;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Tests
{
  Game sut = new Game();
  
  @Test
  public void should_detect_king_high_card ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.NINE));
    hand.add(new Card(Suit.DIAMONDS, Value.THREE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.TEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.CLUBS, Value.KING));
    communityCards.add(new Card(Suit.SPADES, Value.QUEEN));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.HIGH_CARD));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.KING));
  }
  
  @Test
  public void should_detect_pair_of_nines ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.NINE));
    hand.add(new Card(Suit.DIAMONDS, Value.NINE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.TEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.CLUBS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.QUEEN));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.NINE));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.NINE));
  }
  
  @Test
  public void should_detect_pair_of_tens ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.NINE));
    hand.add(new Card(Suit.DIAMONDS, Value.TEN));
    
    communityCards.add(new Card(Suit.CLUBS, Value.TEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.CLUBS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.QUEEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.TWO));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(3).getValue(), is(Value.QUEEN));
    assertThat(pokerHand.getPokerHand().get(2).getValue(), is(Value.TEN));
    assertThat(pokerHand.getPokerHand().get(1).getValue(), is(Value.TEN));
    assertThat(pokerHand.getPokerHand().get(0).getValue(), is(Value.NINE));
  }
  
  @Test
  public void should_detect_three_aces ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.TEN));
    
    communityCards.add(new Card(Suit.CLUBS, Value.NINE));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.ACE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.ACE));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.THREE_OF_A_KIND));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(3).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(2).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(1).getValue(), is(Value.TEN));
    assertThat(pokerHand.getPokerHand().get(0).getValue(), is(Value.NINE));
  }
  
  @Test
  public void should_detect_two_aces_two_tens ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.TEN));
    
    communityCards.add(new Card(Suit.CLUBS, Value.NINE));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.ACE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(3).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(2).getValue(), is(Value.TEN));
    assertThat(pokerHand.getPokerHand().get(1).getValue(), is(Value.TEN));
  }
  
  @Test
  public void should_detect_two_aces_two_nines ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.NINE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.ACE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.NINE));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(3).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(2).getValue(), is(Value.NINE));
    assertThat(pokerHand.getPokerHand().get(1).getValue(), is(Value.NINE));
    assertThat(pokerHand.getPokerHand().get(0).getValue(), is(Value.EIGHT));
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void should_detect_full_house ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.ACE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.NINE));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.NINE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.NINE));
    communityCards.add(new Card(Suit.SPADES, Value.ACE));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.FULL_HOUSE));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), anyOf(is(Value.ACE), is(Value.NINE)));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), anyOf(is(Value.NINE), is(Value.ACE)));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), anyOf(is(Value.NINE), is(Value.ACE)));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), anyOf(is(Value.NINE), is(Value.ACE)));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), anyOf(is(Value.NINE), is(Value.ACE)));
  }
  
  @Test
  public void should_detect_four_aces ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.ACE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.NINE));
    communityCards.add(new Card(Suit.CLUBS, Value.TWO));
    communityCards.add(new Card(Suit.HEARTS, Value.ACE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.ACE));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.FOUR_OF_A_KIND));
    assertThat(pokerHand.getPokerHand().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(3).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(2).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(1).getValue(), is(Value.ACE));
    assertThat(pokerHand.getPokerHand().get(0).getValue(), is(Value.NINE));
  }
  
  @Test
  public void should_detect_ace_high_straight ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.ACE));
    hand.add(new Card(Suit.DIAMONDS, Value.ACE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.KING));
    communityCards.add(new Card(Suit.CLUBS, Value.QUEEN));
    communityCards.add(new Card(Suit.HEARTS, Value.JACK));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.STRAIGHT));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.TEN));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.JACK));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), is(Value.QUEEN));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), is(Value.KING));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), is(Value.ACE));
  }
  
  @Test
  public void should_detect_king_high_straight ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.TWO));
    hand.add(new Card(Suit.DIAMONDS, Value.THREE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.KING));
    communityCards.add(new Card(Suit.CLUBS, Value.QUEEN));
    communityCards.add(new Card(Suit.HEARTS, Value.JACK));
    communityCards.add(new Card(Suit.DIAMONDS, Value.NINE));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.STRAIGHT));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.NINE));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.TEN));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), is(Value.JACK));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), is(Value.QUEEN));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), is(Value.KING));
  }
  
  @Test
  public void should_detect_eight_high_straight ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.CLUBS, Value.FOUR));
    hand.add(new Card(Suit.DIAMONDS, Value.FIVE));
    
    communityCards.add(new Card(Suit.CLUBS, Value.SIX));
    communityCards.add(new Card(Suit.CLUBS, Value.SEVEN));
    communityCards.add(new Card(Suit.HEARTS, Value.EIGHT));
    communityCards.add(new Card(Suit.DIAMONDS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.STRAIGHT));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.FOUR));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.FIVE));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), is(Value.SIX));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), is(Value.SEVEN));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), is(Value.EIGHT));
  }
  
  @Test
  public void should_detect_spade_flush ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.SPADES, Value.FOUR));
    hand.add(new Card(Suit.SPADES, Value.FIVE));
    
    communityCards.add(new Card(Suit.SPADES, Value.SIX));
    communityCards.add(new Card(Suit.CLUBS, Value.SEVEN));
    communityCards.add(new Card(Suit.SPADES, Value.EIGHT));
    communityCards.add(new Card(Suit.DIAMONDS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.FLUSH));
    assertThat(pokerHand.getWinningCards().get(0).getSuit(), is(Suit.SPADES));
    assertThat(pokerHand.getWinningCards().get(1).getSuit(), is(Suit.SPADES));
    assertThat(pokerHand.getWinningCards().get(2).getSuit(), is(Suit.SPADES));
    assertThat(pokerHand.getWinningCards().get(3).getSuit(), is(Suit.SPADES));
    assertThat(pokerHand.getWinningCards().get(4).getSuit(), is(Suit.SPADES));
  }
  
  @Test
  public void should_detect_eight_high_straight_flush ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.DIAMONDS, Value.FOUR));
    hand.add(new Card(Suit.DIAMONDS, Value.FIVE));
    
    communityCards.add(new Card(Suit.DIAMONDS, Value.SIX));
    communityCards.add(new Card(Suit.DIAMONDS, Value.SEVEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.DIAMONDS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.STRAIGHT_FLUSH));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.FOUR));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.FIVE));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), is(Value.SIX));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), is(Value.SEVEN));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), is(Value.EIGHT));
    assertThat(pokerHand.getWinningCards().get(0).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(1).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(2).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(3).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(4).getSuit(), is(Suit.DIAMONDS));
  }
  
  @Test
  public void should_detect_royal_flush ()
  {
    List<Card> hand = new ArrayList<Card>();
    List<Card> communityCards = new ArrayList<Card>();
    
    hand.add(new Card(Suit.DIAMONDS, Value.TEN));
    hand.add(new Card(Suit.DIAMONDS, Value.JACK));
    
    communityCards.add(new Card(Suit.DIAMONDS, Value.QUEEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.KING));
    communityCards.add(new Card(Suit.DIAMONDS, Value.ACE));
    communityCards.add(new Card(Suit.CLUBS, Value.ACE));
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    
    ActualPokerHand pokerHand = sut.checkForHand(hand, communityCards);
    
    assertThat(pokerHand.getPokerHandEnum(), is(PokerHandEnum.ROYAL_FLUSH));
    assertThat(pokerHand.getWinningCards().get(0).getValue(), is(Value.TEN));
    assertThat(pokerHand.getWinningCards().get(1).getValue(), is(Value.JACK));
    assertThat(pokerHand.getWinningCards().get(2).getValue(), is(Value.QUEEN));
    assertThat(pokerHand.getWinningCards().get(3).getValue(), is(Value.KING));
    assertThat(pokerHand.getWinningCards().get(4).getValue(), is(Value.ACE));
    assertThat(pokerHand.getWinningCards().get(0).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(1).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(2).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(3).getSuit(), is(Suit.DIAMONDS));
    assertThat(pokerHand.getWinningCards().get(4).getSuit(), is(Suit.DIAMONDS));
  }
  
  @Test 
  public void should_result_in_draw_with_queen_high_straight ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(new Card(Suit.SPADES, Value.TEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.QUEEN));
    communityCards.add(new Card(Suit.HEARTS, Value.JACK));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.CLUBS, Value.NINE));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", new Card(Suit.SPADES, Value.TWO), new Card(Suit.DIAMONDS, Value.JACK));
    setupPlayer(players, "player2", new Card(Suit.HEARTS, Value.FIVE), new Card(Suit.DIAMONDS, Value.SEVEN));
    setupPlayer(players, "player3", new Card(Suit.SPADES, Value.THREE), new Card(Suit.HEARTS, Value.SIX));
    setupPlayer(players, "player4", new Card(Suit.CLUBS, Value.FIVE), new Card(Suit.SPADES, Value.JACK));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(determineWinner, is("There was a draw with hand: QUEEN high Straight"));
  }
  
  @SuppressWarnings("unchecked")
  @Test 
  public void should_result_in_win_with_two_pair_queens_and_tens ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(new Card(Suit.CLUBS, Value.TEN));
    communityCards.add(new Card(Suit.CLUBS, Value.FOUR));
    communityCards.add(new Card(Suit.SPADES, Value.SIX));
    communityCards.add(new Card(Suit.SPADES, Value.THREE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.TEN));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", new Card(Suit.DIAMONDS, Value.NINE), new Card(Suit.CLUBS, Value.JACK));
    setupPlayer(players, "player2", new Card(Suit.CLUBS, Value.EIGHT), new Card(Suit.SPADES, Value.FOUR));
    setupPlayer(players, "player3", new Card(Suit.DIAMONDS, Value.SIX), new Card(Suit.HEARTS, Value.EIGHT));
    setupPlayer(players, "player4", new Card(Suit.SPADES, Value.QUEEN), new Card(Suit.DIAMONDS, Value.QUEEN));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(determineWinner, anyOf(is("player4[QUEEN of SPADES, QUEEN of DIAMONDS] wins with Two pair - QUEENS and TENS"), is("player4[QUEEN of SPADES, QUEEN of DIAMONDS] wins with Two pair - TENS and QUEENS")));
  }
  
  @SuppressWarnings("unchecked")
  @Test 
  public void should_result_in_win_with_two_pair_tens_and_twos ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(new Card(Suit.CLUBS, Value.TEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.EIGHT));
    communityCards.add(new Card(Suit.SPADES, Value.SEVEN));
    communityCards.add(new Card(Suit.SPADES, Value.TWO));
    communityCards.add(new Card(Suit.CLUBS, Value.JACK));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", new Card(Suit.SPADES, Value.SIX), new Card(Suit.CLUBS, Value.SEVEN));
    setupPlayer(players, "player2", new Card(Suit.HEARTS, Value.TWO), new Card(Suit.SPADES, Value.TEN));
    setupPlayer(players, "player3", new Card(Suit.HEARTS, Value.QUEEN), new Card(Suit.CLUBS, Value.FIVE));
    setupPlayer(players, "player4", new Card(Suit.SPADES, Value.FOUR), new Card(Suit.HEARTS, Value.ACE));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(0).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(determineWinner, anyOf(is("player2[TWO of HEARTS, TEN of SPADES] wins with Two pair - TENS and TWOS"), is("player2[TWO of HEARTS, TEN of SPADES] wins with Two pair - TWOS and TENS")));
  }
  
  @SuppressWarnings("unchecked")
  @Test
  public void should_result_in_win_with_two_pair_tens_and_jacks ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(new Card(Suit.DIAMONDS, Value.TEN));
    communityCards.add(new Card(Suit.HEARTS, Value.TWO));
    communityCards.add(new Card(Suit.DIAMONDS, Value.FIVE));
    communityCards.add(new Card(Suit.HEARTS, Value.TEN));
    communityCards.add(new Card(Suit.SPADES, Value.JACK));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", new Card(Suit.CLUBS, Value.TWO), new Card(Suit.HEARTS, Value.EIGHT));
    setupPlayer(players, "player2", new Card(Suit.DIAMONDS, Value.THREE), new Card(Suit.SPADES, Value.TWO));
    setupPlayer(players, "player3", new Card(Suit.SPADES, Value.ACE), new Card(Suit.DIAMONDS, Value.JACK));
    setupPlayer(players, "player4", new Card(Suit.SPADES, Value.NINE), new Card(Suit.SPADES, Value.QUEEN));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(2).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(determineWinner, anyOf(is("player3[ACE of SPADES, JACK of DIAMONDS] wins with Two pair - TENS and JACKS"), is("player3[ACE of SPADES, JACK of DIAMONDS] wins with Two pair - JACKS and TENS")));
  }
  
  @Test
  public void ace_high_kicker_should_win_with_pair_of_sevens ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(new Card(Suit.SPADES, Value.ACE));
    communityCards.add(new Card(Suit.CLUBS, Value.ACE));
    communityCards.add(new Card(Suit.DIAMONDS, Value.NINE));
    communityCards.add(new Card(Suit.HEARTS, Value.QUEEN));
    communityCards.add(new Card(Suit.DIAMONDS, Value.KING));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", new Card(Suit.HEARTS, Value.KING), new Card(Suit.SPADES, Value.JACK));
    setupPlayer(players, "player2", new Card(Suit.DIAMONDS, Value.THREE), new Card(Suit.DIAMONDS, Value.ACE));
    setupPlayer(players, "player3", new Card(Suit.DIAMONDS, Value.JACK), new Card(Suit.SPADES, Value.FOUR));
    setupPlayer(players, "player4", new Card(Suit.DIAMONDS, Value.TWO), new Card(Suit.CLUBS, Value.QUEEN));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(1).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.THREE_OF_A_KIND));
    assertThat(determineWinner, is("player2[THREE of DIAMONDS, ACE of DIAMONDS] wins with Three ACES"));
  }
  
  @Test
  public void player_2_should_win_with_three_fours ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(createCard("FOUR of HEARTS"));
    communityCards.add(createCard("FOUR of SPADES"));
    communityCards.add(createCard("QUEEN of HEARTS"));
    communityCards.add(createCard("TEN of DIAMONDS"));
    communityCards.add(createCard("KING of CLUBS"));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", createCard("SEVEN of HEARTS"), createCard("KING of DIAMONDS"));
    setupPlayer(players, "player2", createCard("SIX of CLUBS"), createCard("FOUR of CLUBS"));
    setupPlayer(players, "player3", createCard("JACK of DIAMONDS"), createCard("FIVE of HEARTS"));
    setupPlayer(players, "player4", createCard("FIVE of SPADES"), createCard("SEVEN of CLUBS"));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(0).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(players.get(1).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.THREE_OF_A_KIND));
    assertThat(players.get(2).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(players.get(3).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(determineWinner, is("player2[SIX of CLUBS, FOUR of CLUBS] wins with Three FOURS"));
  }
  
  @Test
  public void player_1_should_win_with_pair_of_jacks ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(createCard("TEN of SPADES"));
    communityCards.add(createCard("JACK of CLUBS"));
    communityCards.add(createCard("EIGHT of HEARTS"));
    communityCards.add(createCard("FOUR of HEARTS"));
    communityCards.add(createCard("SIX of DIAMONDS"));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", createCard("FIVE of CLUBS"), createCard("EIGHT of DIAMONDS"));
    setupPlayer(players, "player2", createCard("ACE of HEARTS"), createCard("SEVEN of SPADES"));
    setupPlayer(players, "player3", createCard("KING of DIAMONDS"), createCard("FOUR of SPADES"));
    setupPlayer(players, "player4", createCard("NINE of SPADES"), createCard("THREE of SPADES"));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(0).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(players.get(0).getPlayerHandStrength().toString(), is("Pair of EIGHTS"));
    assertThat(players.get(1).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.HIGH_CARD));
    assertThat(players.get(2).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(players.get(3).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.HIGH_CARD));
    assertThat(determineWinner, is("player1[FIVE of CLUBS, EIGHT of DIAMONDS] wins with Pair of EIGHTS"));
  }
  
  @Test
  public void player_4_should_win_with_spades_flush ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(createCard("QUEEN of CLUBS"));
    communityCards.add(createCard("THREE of SPADES"));
    communityCards.add(createCard("FIVE of SPADES"));
    communityCards.add(createCard("QUEEN of DIAMONDS"));
    communityCards.add(createCard("TEN of SPADES"));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", createCard("JACK of DIAMONDS"), createCard("SEVEN of HEARTS"));
    setupPlayer(players, "player2", createCard("EIGHT of SPADES"), createCard("ACE of CLUBS"));
    setupPlayer(players, "player3", createCard("TEN of DIAMONDS"), createCard("SEVEN of CLUBS"));
    setupPlayer(players, "player4", createCard("SEVEN of SPADES"), createCard("TWO of SPADES"));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(0).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(players.get(1).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.PAIR));
    assertThat(players.get(2).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(players.get(3).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.FLUSH));
    assertThat(determineWinner, is("player4[SEVEN of SPADES, TWO of SPADES] wins with SPADES flush"));
  }
  
//  player1[FIVE of CLUBS, SEVEN of CLUBS] - Two pair - QUEENS and FIVES
//  player2[SEVEN of HEARTS, THREE of HEARTS] - HEARTS flush
//  player3[THREE of SPADES, JACK of HEARTS] - HEARTS flush
//  player4[FIVE of DIAMONDS, EIGHT of SPADES] - Two pair - QUEENS and FIVES
//
//  TWO of HEARTS
//  FIVE of HEARTS
//  QUEEN of DIAMONDS
//  QUEEN of HEARTS
//  SIX of HEARTS
//
//  player3[THREE of SPADES, JACK of HEARTS] wins with HEARTS flush

  @Test
  public void player_4_should_win_with_hearts_flush_jack_kicker ()
  {
    List<Card> communityCards = new ArrayList<Card>();
    communityCards.add(createCard("TWO of HEARTS"));
    communityCards.add(createCard("FIVE of HEARTS"));
    communityCards.add(createCard("QUEEN of DIAMONDS"));
    communityCards.add(createCard("QUEEN of HEARTS"));
    communityCards.add(createCard("SIX of HEARTS"));
    
    sut.setCommunityCards(communityCards);
    
    List<Player> players = new ArrayList<Player>();
    
    setupPlayer(players, "player1", createCard("FIVE of CLUBS"), createCard("SEVEN of CLUBS"));
    setupPlayer(players, "player2", createCard("SEVEN of HEARTS"), createCard("THREE of HEARTS"));
    setupPlayer(players, "player3", createCard("THREE of SPADES"), createCard("JACK of HEARTS"));
    setupPlayer(players, "player4", createCard("FIVE of DIAMONDS"), createCard("EIGHT of SPADES of SPADES"));
    
    sut.setPlayers(players);
    
    String determineWinner = sut.determineWinner();
    
    assertThat(players.get(0).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(players.get(1).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.FLUSH));
    assertThat(players.get(2).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.FLUSH));
    assertThat(players.get(3).getPlayerHandStrength().getPokerHandEnum(), is(PokerHandEnum.TWO_PAIR));
    assertThat(determineWinner, is("player3[THREE of SPADES, JACK of HEARTS] wins with HEARTS flush, JACK kicker"));
  }
  
  private Card createCard(String cardString)
  {
    String[] split = cardString.split(" of ");
    return new Card(Suit.valueOf(split[1]), Value.valueOf(split[0]));
  }

  private void setupPlayer(List<Player> players, String name, Card card1, Card card2)
  {
    Player player = new Player(name);
    List<Card> hand = new ArrayList<Card>();
    hand.add(card1);
    hand.add(card2);
    player.setHand(hand);
    players.add(player);
  }
}
