package com.company.my.spring.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Card;
import com.company.my.bom.Deck;
import com.company.my.bom.GameMove;
import com.company.my.bom.GameMoveFrom;
import com.company.my.bom.GameMovePK;
import com.company.my.bom.Player;
import com.company.my.dao.CardDao;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameMoveDao;
import com.company.my.dao.PlayerDao;

/**
 * Does not work : it calls the bean "deckDao" but since it's not Transactional, the session is never set !
 * 
 * 
 * Have to put @TransactionConfiguration and @Transactional on THIS class declaration
 * 
 * @see http://www.springbyexample.org/examples/simple-spring-transactional-junit4-test-code-example.html
 * @author novkar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations="/cfcdao-test-context.xml")// By default look for [class name]-context.xml file

@Transactional
public class DeckAndCardsTest {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DeckDao deckDao;
	
	@Autowired
	CardDao cardDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	GameMoveDao gameMoveDao;
	
	
	@Test
	@Ignore
	public void testSaveOneDeckWithOneCard() {
		try {
			
			final String oneDeckName = "myFirstDeck2_" + System.currentTimeMillis();
			final String oneCardName = "testCard";
			Card oneCard = new Card();
			Deck oneDeck = new Deck(oneDeckName);
			oneCard.setDeck(oneDeck);
			oneCard.setNameCard(oneCardName);
			oneCard.setEffectValues(5, 4, 3);
			
			oneDeck.getDeckCards().add(oneCard);
			deckDao.save(oneDeck);
			//cardDao.save(oneCard);
			
			
			final long savedDeckID = oneDeck.getId();
			
			List<Deck> decksList = deckDao.list();
			
			System.out.println(decksList.size());
			
			
			
			for (Deck dl : decksList) {
				
				if (savedDeckID == dl.getId()){
					org.junit.Assert.assertEquals(oneDeckName, dl.getDeckName());
					
					System.out.println("\n\n\n>");
//					Deck deckWithCards = deckDao.findDeckWithCardsById(savedDeckID);
//					for (Card aCard : deckWithCards.getDeckCards()){
//						
//						System.out.println(aCard);
//					}
					
					List<Card> cards = cardDao.findCardsWithDeckId(savedDeckID);
					Assert.assertEquals(1, cards.size());
					for (Card aCard : cards){
						
						System.out.println(aCard);
						Assert.assertEquals(oneCardName, aCard.getNameCard());
					}
					System.out.println("\n\n\n<");
				}
				
				
			}

			
			
			// close resources
//			context.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	
	
	@Test
//	@Ignore
	public void testSavePlayerWithADeck(){
		final String pName = "playerName";
		final String pTag ="player tag line !!!";
		
		
		Player player1 = new Player();
		player1.setName(pName);
		player1.setTagLine(pTag);
		
		
		Deck playerDeck = new Deck("playerDeck");
		
		
		
		player1.getDecks().add(playerDeck);
		
		playerDao.save(player1);
		final long pId = player1.getId() ;

		deckDao.save(playerDeck);
		
		
		Player savedPlayer = playerDao.findById(pId);
		
		Assert.assertEquals(pName, savedPlayer.getName());
		Assert.assertEquals(1, savedPlayer.getDecks().size());
		
		Deck savedPDeck = savedPlayer.getDecks().get(0);
		Assert.assertEquals("playerDeck", savedPDeck.getDeckName());
		
	}
	
	/**
	 * Saves two game moves with 2 minutes difference and retrieves them.
	 */
	@Test
	@Ignore
	public void testSaveAndRetrieveGameMoves(){
		Card oneCard = new Card();
		oneCard.setNameCard("gameMoveCard");
		oneCard.setEffectValues(3, 2, 0);
		

		Calendar cal = Calendar.getInstance();
		Date move2_date = cal.getTime();
		
		cal.add(Calendar.MINUTE, -2);
		Date move1_date = cal.getTime();
		
		
		
		GameMove move1 = new GameMove();
		GameMovePK pk1 = new  GameMovePK();
		pk1.setIdGame(22L);
		pk1.setIdDeck1(1L);
		pk1.setIdDeck2(2L);
		pk1.setDateMove(move1_date);
		move1.setGameMovePK(pk1);
		move1.setCard(oneCard);
		move1.setGameMoveFrom(GameMoveFrom.PLAYER1);
		
		
		GameMove move2 = new GameMove();
		GameMovePK pk2 = new  GameMovePK();
		pk2.setIdGame(22L);
		pk2.setIdDeck1(1L);
		pk2.setIdDeck2(2L);
		pk2.setDateMove(move2_date);
		move2.setGameMovePK(pk2);
		move2.setCard(oneCard);
		move1.setGameMoveFrom(GameMoveFrom.PLAYER2);
		
		
		cardDao.save(oneCard);
		
		gameMoveDao.save(move1);
		gameMoveDao.save(move2);
		
		
		List<Card> savedCards = cardDao.list();
		
		Assert.assertTrue(savedCards.size() != 0);
		
		List<GameMove>gameMoves = gameMoveDao.findByIdGame(22L);
		Assert.assertTrue(gameMoves.size() ==2);
		
		List<GameMove>gameMoves2 = gameMoveDao.findAllMovesFromGameAndDecks(22L, 1L, 2L);
		Assert.assertTrue(gameMoves2.size() ==2);
		
		Date latestMoveDate = gameMoves2.get(0).getGameMovePK().getDateMove();
		Date earliestMoveDate = gameMoves2.get(1).getGameMovePK().getDateMove();
		
		Assert.assertTrue(latestMoveDate.after(earliestMoveDate));
	}
	
	
}