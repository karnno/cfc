package com.company.my.service.deck;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.my.bom.Card;
import com.company.my.bom.Deck;
import com.company.my.service.card.CardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/cfcservices-context-test.xml",
		"/HibernateWithSpringTest-context.xml" })
@ActiveProfiles("testProfile")
public class DeckServiceTest {

	@Autowired
	DeckService deckService;
	
	@Autowired
	CardService cardService;
	
	@Test
	public void basicGet() {
		List<Card> allCards = this.cardService.getAllCards();
		System.out.println("NUMBER OF CARDS  " + allCards.size());
		for (Card aCard : allCards){
			System.out.println(aCard);
		}
		
		
	}
	
	
	
	@Ignore
	@Test
	public void testThis() {
		// try {
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext(
		// "springcontext-services.xml", "cfc-dao.xml");
		// } catch (Exception ee) {
		// ee.printStackTrace();
		// }
		//service.saveTestDeck();
	try{	
		/*
		 * Creating objects : one Deck with Two cards
		 */
//		Deck testDeck = new Deck("mydeck01_"+System.currentTimeMillis());
//		Card card1 = new Card("Card_"+System.currentTimeMillis(), false, testDeck);
//		Card card2 = new Card("Card_"+System.currentTimeMillis(), false, testDeck);
//		card1.setDeck(testDeck);
//		card2.setDeck(testDeck);
//		
//		List<Card> cardsList = new ArrayList<Card>();
//		 
//		cardsList.add(card1);
//		cardsList.add(card2);
//
//		testDeck.setDeckCards(cardsList);
		
		/*
		 * Save the deck
		 */
//		service.saveDeck(testDeck);
		
		
		/*
		 * Retrieve the deck
		 *
		List<Deck> decksList = service.listDecksWithCards();
		for (Deck dl : decksList) {
			System.out.println("--> DECK : " + dl + " / " + dl.getDeckCards()) ;
		}
		/* */

		List<Deck> decksListWithoutCards = this.deckService.listDecksWithoutCards();
		Deck deckToFind = null;
		
		for (Deck aDeck : decksListWithoutCards){
			if (aDeck.getDeckName().equals("Deck__1427894881391")){ //Deck__1427894881391  // Deck__1427879004922
				deckToFind = aDeck;
			}
		}
		
		if (deckToFind != null){
			System.out.println("find cards for deck with id : " + deckToFind.getId());
			List<Card> foundCards =  this.cardService.getCardsFromDeckId(deckToFind.getId()) ;
			if (foundCards.size()!=0){
				System.out.println("founded " + foundCards.size() + " cards");
				for (Card aCard : foundCards){
					System.out.println(aCard);
				}
			
			}else{
				// create 2 news cards
				
				Card newCard1 = new Card("Card_"+System.currentTimeMillis(), deckToFind);
				Card newCard2 = new Card("Card_"+System.currentTimeMillis(), deckToFind);
				newCard1.setDeck(deckToFind);
				newCard2.setDeck(deckToFind);
				
				this.cardService.saveCard(newCard1);
				this.cardService.saveCard(newCard2);
				
				System.out.println("new cards saved !");
			}
		}

	}catch(Exception ee){
		ee.printStackTrace();
	}
	}
}
