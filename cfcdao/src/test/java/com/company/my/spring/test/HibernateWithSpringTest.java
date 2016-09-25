package com.company.my.spring.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.card.Card;
import com.company.my.dao.DeckDao;
import com.company.my.deck.Deck;

/**
 * Does not work : it calls the bean "deckDao" but since it's not Transactional, the session is never set !
 * 
 * 
 * Have to put @TransactionConfiguration and @Transactional on class declaration
 * 
 * @see http://www.springbyexample.org/examples/simple-spring-transactional-junit4-test-code-example.html
 * @author novkar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration // By default look for [class name]-context.xml file
@TransactionConfiguration
@Transactional
public class HibernateWithSpringTest {

	@Autowired
	DeckDao deckDao;
	
	@Test
	public void testIt() {
		try {
			
//			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//					"springcontext.xml");
//			
//			DeckDao deckDao = (DeckDao) context.getBean("deckDao");

//			 deckDao.save(new Deck("myFirstDeck2_" + System.currentTimeMillis()));
			List<Deck> decksList = deckDao.list();
			
			System.out.println(decksList.size());
			
			Deck deck2 = null;
			
			for (Deck dl : decksList) {
				//System.out.println("--> deck : " + dl);

				
				
				if (dl.getDeckName().equals("Deck__1427894881391")){
					deck2 = dl;
				}
			}

			if (deck2 != null){
				System.out.println("\n\n\n>");
				for (Card aCard : deck2.getDeckCards()){
					System.out.println(aCard);
				}
				System.out.println("\n\n\n<");
			}
			
			// close resources
//			context.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}