package com.company.my.service.gamemove;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.my.bom.Card;
import com.company.my.bom.CardType;
import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.GameMove;
import com.company.my.bom.GameMoveFrom;
import com.company.my.bom.GameMovePK;
import com.company.my.bom.GameMoveType;
import com.company.my.bom.Player;
import com.company.my.service.PlayerService;
import com.company.my.service.card.CardService;
import com.company.my.service.deck.DeckService;
import com.company.my.service.game.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/cfcservices-context-test.xml",
		"/HibernateWithSpringTest-context.xml" })
public class GameMoveServiceTest {

	@Autowired
	GameService gameService;
	
	@Autowired
	GameMoveService gmService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	CardService cardService;
	
	@Autowired
	DeckService deckService;
	
	@Test
	public void runTest() throws Exception{
		
		Calendar cal = Calendar.getInstance();
		// create player1
		Player player1 = new Player();
		player1.setName("player1");
		player1.setTagLine("May the 4th be with you");
		playerService.savePLayer(player1);
		
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
		deckService.saveDeck(player1_Deck);
		
		// create player2
		Player player2 = new Player();
		player2.setName("player2");
		player2.setTagLine("Join the dark side, it's too hot there");
		playerService.savePLayer(player2);
		
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
		deckService.saveDeck(player2_Deck);
		
		
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
		gameService.saveGame(theGame);
		
		Assert.assertTrue(theGame.getId() != 0);
		
		GameMovePK gameMovePk = new GameMovePK(theGame.getId(), player1_Deck.getId(), player2_Deck.getId(), cal.getTime());
		
		
		// create one game move where player 1 starts playing
		GameMove player1_plays = new GameMove();
		player1_plays.setGameMovePK(gameMovePk);
		player1_plays.setGameMoveType(GameMoveType.START_PLAYING);
		player1_plays.setGameMoveFrom(GameMoveFrom.PLAYER1);
		gmService.saveGameMove(player1_plays);
		
		// create one game move where player 1 applied OFFENSE deck1-card 1 to player 2
		GameMove player1_offense_card = new GameMove();
		player1_offense_card.setGameMovePK(GameMovePK.newGameMovePK(gameMovePk));
		player1_offense_card.setGameMoveType(GameMoveType.PLAY_CARD);
		player1_offense_card.setGameMoveFrom(GameMoveFrom.PLAYER1);
		player1_offense_card.setCard(card1_deck1_attack);
		player1_offense_card.setVitals(20, 20, 20, 15, 15, 17);
		gmService.saveGameMove(player1_offense_card);

		// create one game move where player 2 starts playing
		GameMove player2_plays = new GameMove();
		player2_plays.setGameMovePK(GameMovePK.newGameMovePK(gameMovePk));
		player2_plays.setGameMoveType(GameMoveType.START_PLAYING);
		player2_plays.setGameMoveFrom(GameMoveFrom.PLAYER2);
		gmService.saveGameMove(player2_plays);
		
		// create one game move where player 2 applied OFFENSE card on player 1
		GameMove player2_attack_card = new GameMove();
		player2_attack_card.setGameMovePK(GameMovePK.newGameMovePK(gameMovePk));
		player2_attack_card.setGameMoveType(GameMoveType.PLAY_CARD);
		player2_attack_card.setGameMoveFrom(GameMoveFrom.PLAYER2);
		player2_attack_card.setCard(card1_deck2_attack);
		player2_attack_card.setVitals(15, 15, 18, 15, 15, 17);
		gmService.saveGameMove(player2_attack_card);
		
		// create one game move where player 2 applied defense card on himself
		GameMove player2_defense_card = new GameMove();
		player2_defense_card.setGameMovePK(GameMovePK.newGameMovePK(gameMovePk));
		player2_defense_card.setGameMoveType(GameMoveType.PLAY_CARD);
		player2_defense_card.setGameMoveFrom(GameMoveFrom.PLAYER2);
		player2_defense_card.setCard(card2_deck2_defense);
		player2_defense_card.setVitals(15, 15, 18, 15, 15, 17);
		gmService.saveGameMove(player2_defense_card);
		
		// verify everything is saved by retrieving all the game moves !
		
		List<GameMove> gmList = gmService.findGameMovesByGameAndDecks(theGame.getId(), player1_Deck.getId(), player2_Deck.getId()) ;
		
		System.out.println(gmList.size());
		// verify it's player 2's turn
		Assert.assertTrue(gmList.get(0).getGameMoveFrom() == GameMoveFrom.PLAYER2);
		
		
	}
}
