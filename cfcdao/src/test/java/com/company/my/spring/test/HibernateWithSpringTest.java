package com.company.my.spring.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.card.Card;
import com.company.my.dao.CardDao;
import com.company.my.dao.DeckDao;
import com.company.my.deck.Deck;

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
@ContextConfiguration (name="HibernateWithSpringTest-context.xml")// By default look for [class name]-context.xml file
@TransactionConfiguration
@Transactional
public class HibernateWithSpringTest {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DeckDao deckDao;
	
	@Autowired
	CardDao cardDao;
	
	@Test
	public void testIt() {
		try {
			
			Deck oneDeck = new Deck("myFirstDeck2_" + System.currentTimeMillis());
			Card oneCard = new Card();
			oneCard.setDeck(oneDeck);
			oneCard.setNameCard("testCard");
			oneCard.setEffectValues(5, 4, 3);
			
			deckDao.save(oneDeck);
			cardDao.save(oneCard);
			
			
			final long savedDeckID = oneDeck.getId();
			
			List<Deck> decksList = deckDao.list();
			
			System.out.println(decksList.size());
			
			
			
			for (Deck dl : decksList) {
				
				if (savedDeckID == dl.getId()){
					System.out.println("\n\n\n>");
//					Deck deckWithCards = deckDao.findDeckWithCardsById(savedDeckID);
//					for (Card aCard : deckWithCards.getDeckCards()){
//						
//						System.out.println(aCard);
//					}
					
					List<Card> cards = cardDao.findCardsWithDeckId(savedDeckID);
					for (Card aCard : cards){
						
						System.out.println(aCard);
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
}