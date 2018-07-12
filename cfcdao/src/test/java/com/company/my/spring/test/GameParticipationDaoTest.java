package com.company.my.spring.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Card;
import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.GameParticipation;
import com.company.my.bom.GameStatus;
import com.company.my.dao.CardDao;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameDao;
import com.company.my.dao.GameParticipationDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations="/HibernateWithSpringTest-context.xml")// By default look for [class name]-context.xml file
@Transactional
public class GameParticipationDaoTest {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	GameDao gameDao;
	
	@Autowired
	DeckDao deckDao;
	
	@Autowired
	CardDao cardDao;
	
	@Autowired
	GameParticipationDao gpDao;
	
	@Test
	public void testSaveGameparticipation() throws ParseException {
		GameParticipation gp = new GameParticipation();
		
		Game game = new Game();
		game.setDateGame(new SimpleDateFormat("yyyy-MM-dd").parse("2017-11-15") ); //Calendar.getInstance().getTime());
		game.setGameStatus(GameStatus.INITIAL);
		gameDao.save(game);
		Long idGame = game.getId();
		System.out.println("Saved new game with id = " + idGame);
		final String oneDeckName = "testDeck_" + System.currentTimeMillis();
		final String oneCardName = "testCard";
		Card oneCard = new Card();
		Deck oneDeck = new Deck(oneDeckName);
		oneCard.setDeck(oneDeck);
		oneCard.setNameCard(oneCardName);
		oneCard.setEffectValues(5, 4, 3);
		
		deckDao.save(oneDeck);
		oneCard.setDeck(oneDeck);
		cardDao.save(oneCard);
		
		
//		final long savedDeckID = oneDeck.getId();
		
		gp.setDeck(oneDeck);
		gp.setGame(game);
		gp.setParticipationDate(Calendar.getInstance().getTime()) ;
		gp.setEnergy(oneCard.getEnergy());
		gp.setCredibility(oneCard.getCredibility());
		gp.setMotivation(oneCard.getMotivation());
		
		gpDao.save(gp);
		
		System.out.println("Saved new game participation with ID = " + gp.getId());
		
		
		List<GameParticipation> found = gpDao.foundByGameId(idGame);
		
		Assert.assertTrue(found.size() == 1);
		
		
	}
}
