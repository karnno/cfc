package com.company.my.spring.test;

import java.text.ParseException;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Card;
import com.company.my.bom.CardType;
import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.GameMove;
import com.company.my.bom.GameMoveFrom;
import com.company.my.bom.GameMovePK;
import com.company.my.bom.GameMoveType;
import com.company.my.bom.Player;
import com.company.my.dao.CardDao;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameDao;
import com.company.my.dao.GameMoveDao;
import com.company.my.dao.PlayerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations="/cfcdao-test-context.xml")// By default look for [class name]-context.xml file
@Transactional
public class GameMoveDaoTest {
	
	@Autowired
	GameDao gameDao;
	
	@Autowired
	PlayerDao playerDao;
	@Autowired
	DeckDao deckDao;
	
	@Autowired
	CardDao cardDao;
	
	@Autowired
	GameMoveDao gmDao;
	
	@Test
	public void testSaveGameMove() throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		// create player1
		Player player1 = new Player();
		player1.setName("player1");
		player1.setTagLine("May the 4th be with you");
		playerDao.save(player1);
		
		// create deck 1 with one card 1
		Deck player1_Deck = new Deck("player1_Deck");
		player1.getDecks().add(player1_Deck);
		
		Card card1_deck1_attack = new Card();
		card1_deck1_attack.setCardType(CardType.ATTACK);
		card1_deck1_attack.setNameCard("card1_deck1_name");
		card1_deck1_attack.setEffectValues(-5, -5, -3);
		card1_deck1_attack.setDeck(player1_Deck);
		
		
		//cardService.saveCard(deck1Card);
		player1_Deck.getDeckCards().add(card1_deck1_attack);
		deckDao.save(player1_Deck);
		
		// create player2
		Player player2 = new Player();
		player2.setName("player2");
		player2.setTagLine("Join the dark side, it's too hot there");
		playerDao.save(player2);
		
		// create deck 2 with one card 2, one OFFENSE, one DEFENSE
		Deck player2_Deck = new Deck("player2_Deck");
		player2.getDecks().add(player2_Deck);
		
		
		
		
		Card card1_deck2_attack = new Card();
		card1_deck2_attack.setCardType(CardType.ATTACK);
		card1_deck2_attack.setNameCard("card1_deck2_name");
		card1_deck2_attack.setEffectValues(-5, -5, -2);
		card1_deck2_attack.setDeck(player2_Deck);
		
		Card card2_deck2_defense = new Card();
		card2_deck2_defense.setCardType(CardType.DEFENSE);
		card2_deck2_defense.setNameCard("card2_deck2_name");
		card2_deck2_defense.setEffectValues(3, 3 , 3);
		card2_deck2_defense.setDeck(player2_Deck);
		
		
		player2_Deck.getDeckCards().add(card1_deck2_attack);
		player2_Deck.getDeckCards().add(card2_deck2_defense);
		deckDao.save(player2_Deck);
		
		
		// ASSERT : id of player 1 and 2, deck 1 and 2 and cards exist !!!
		Assert.assertTrue(player1.getId()!=0);
		Assert.assertTrue(player2.getId()!=0);
		
		Assert.assertTrue(player1_Deck.getId()!=0);
		Assert.assertTrue(player2_Deck.getId()!=0);
		
		Assert.assertTrue(card1_deck1_attack.getIdCard()!=0);
		Assert.assertTrue(card1_deck2_attack.getIdCard()!=0);
		Assert.assertTrue(card2_deck2_defense.getIdCard()!=0);
		
		// create game 
		Game theGame = new Game();
		theGame.setDateGame(cal.getTime());
		gameDao.save(theGame);
		
		Assert.assertTrue(theGame.getId() != 0);
		
		GameMovePK gameMovePk = new GameMovePK(theGame.getId(), player1_Deck.getId(), player2_Deck.getId(), cal.getTime());
		
		
		// create one game move where player 1 starts playing
		GameMove player1_plays = new GameMove();
		player1_plays.setGameMovePK(gameMovePk);
		player1_plays.setGameMoveType(GameMoveType.START_PLAYING);
		player1_plays.setGameMoveFrom(GameMoveFrom.PLAYER1);
		gmDao.save(player1_plays);
		
	}
}
