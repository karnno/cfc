package com.company.my.spring.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.GameStatus;
import com.company.my.bom.Player;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameDao;
import com.company.my.dao.PlayerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations="/cfcdao-test-context.xml")// By default look for [class name]-context.xml file
@Transactional
public class GameDaoTest {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameDaoTest.class);
	@Autowired
	GameDao gameDao;
	
	@Autowired
	PlayerDao playerDao;
	
	@Autowired
	DeckDao deckDao;
	
	@Test
	public void getOnlyAvailableGames() {
		
		// create player1
		Player player1 = new Player();
		player1.setName("player1");
		player1.setTagLine("May the 4th be with you");
		playerDao.save(player1);
		
		// create deck 1 with one card 1
		Deck player1_Deck = new Deck("player1_Deck");
		player1.getDecks().add(player1_Deck);
		deckDao.save(player1_Deck);
		
		
		Game game1 = new Game();
		game1.setGameStatus(GameStatus.INITIAL);
		game1.setDeck1(player1_Deck);
		
		
		Game game2 = new Game();
		game2.setGameStatus(GameStatus.INITIAL);
		game2.setDeck2(player1_Deck);
		
		gameDao.save(game1);
		gameDao.save(game2);
		
		LOGGER.info("Saved game1 with id {} and game2 with id {}", game1.getId(), game2.getId());
		
		java.util.List<Long> fake_deckId = new java.util.ArrayList<Long>();
		fake_deckId.add(2L);
		
		List<Game> allGames = gameDao.list();
		for (Game oneGame : allGames) {
			
			LOGGER.info(" one game {}, deck {} and deck {}", oneGame.getId(), 
					oneGame.getDeck1()==null?"" : oneGame.getDeck1().getId(), 
					oneGame.getDeck2()==null?"" : oneGame.getDeck2().getId());
		}
		
		// returns the initialized games where the current deck Id is not involved
		List<Game> availableGames = gameDao.listAllAvailableGames(fake_deckId);
		
		Assert.assertEquals(2, availableGames.size());
		for (Game aGame : availableGames) {
			LOGGER.info("status : {}", aGame.getGameStatus().toString());
			Assert.assertTrue(aGame.getGameStatus() == GameStatus.INITIAL);
		}
	}
}
