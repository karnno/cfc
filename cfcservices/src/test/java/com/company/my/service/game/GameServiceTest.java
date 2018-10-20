package com.company.my.service.game;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.Player;
import com.company.my.service.PlayerService;
import com.company.my.service.card.CardService;
import com.company.my.service.deck.DeckService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/cfcservices-context-test.xml", "classpath:cfcdao-test-context.xml" })
public class GameServiceTest {
	
	@Autowired
	GameService gameService;
	
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	DeckService deckService;
	
	@Test
	public void runTest() throws Exception{
		// create player1
		Player player1 = new Player();
		player1.setName("player1");
		player1.setTagLine("May the 4th be with you");
		playerService.savePLayer(player1);
		
		// create deck 1
		Deck player1_Deck = new Deck("player1_Deck");
		player1.getDecks().add(player1_Deck);
		 
		deckService.saveDeck(player1_Deck);
		
		// create player2
		Player player2 = new Player();
		player2.setName("player2");
		player2.setTagLine("Join the dark side, it's too hot there");
		playerService.savePLayer(player2);
		
		// create deck 2 
		Deck player2_Deck = new Deck("player2_Deck");
		player2.getDecks().add(player2_Deck);
		
		deckService.saveDeck(player2_Deck);
		
		
		// ASSERT : id of player 1 and 2, deck 1 and 2 and cards exist !!!
		Assert.assertTrue(player1.getId()!=0);
		Assert.assertTrue(player2.getId()!=0);
		
		Assert.assertTrue(player1_Deck.getId()!=0);
		Assert.assertTrue(player2_Deck.getId()!=0);
		
	
		
		// create game through Game Service
		Game theGame = gameService.createNewGame(player1_Deck.getId());
		
		Assert.assertTrue(theGame.getId() != 0);
		Assert.assertTrue(theGame.getDeck1().getId() != 0);
		
		
		// make the player 2 join the game !!
		theGame = gameService.joinExistingGame(theGame.getId(), player2_Deck.getId());
		
		
		Assert.assertTrue(theGame.getDeck2().getId() ==  player2_Deck.getId() );
		
	}

}
