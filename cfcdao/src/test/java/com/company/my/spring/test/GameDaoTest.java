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

import com.company.my.bom.Game;
import com.company.my.bom.GameStatus;
import com.company.my.dao.GameDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations="/cfcdao-test-context.xml")// By default look for [class name]-context.xml file
@Transactional
public class GameDaoTest {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(GameDaoTest.class);
	@Autowired
	GameDao gameDao;
	
	
	@Test
	public void getOnlyAvailableGames() {
		
		Game game1 = new Game();
		game1.setGameStatus(GameStatus.INITIAL);
		
		Game game2 = new Game();
		game2.setGameStatus(GameStatus.ONGOING);
		
		gameDao.save(game1);
		gameDao.save(game2);
		
		LOGGER.info("Saved game1 with id {} and game2 with id {}", game1.getId(), game2.getId());
		
		List<Game> availableGames = gameDao.listAllAvailableGames();
		
		Assert.assertEquals(1, availableGames.size());
		for (Game aGame : availableGames) {
//			LOGGER.info("status : {}", aGame.getGameStatus().toString());
			Assert.assertTrue(aGame.getGameStatus() == GameStatus.INITIAL);
		}
	}
}
