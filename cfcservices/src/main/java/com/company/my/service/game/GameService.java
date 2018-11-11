package com.company.my.service.game;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Deck;
import com.company.my.bom.Game;
import com.company.my.bom.GameStatus;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameDao;
import com.company.my.service.exception.CfcBusinessException;

@Service
@Transactional
public class GameService {

	Logger LOGGER = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	GameDao gameDao;
	
	@Autowired
	DeckDao deckDao;
	
	@Transactional
	public void saveGame(Game gameToSave) {
		this.gameDao.save(gameToSave);
	}
	
	
	@Transactional
	public Game createNewGame(long deck1Id)  {
		Game toReturn = new Game(); // created with status = initial, date = today date
		Deck deck1 = deckDao.findById(deck1Id);
		toReturn.setDeck1(deck1);
		
		gameDao.save(toReturn);
		LOGGER.info("SAVED new game with deck1 {} saved with id {}", deck1Id, toReturn.getId());
		return toReturn;
	}
	
	@Transactional
	public Game joinExistingGame(long gameId, long deck2Id) throws CfcBusinessException {
		Game toReturn = gameDao.findById(gameId);
		
		if (toReturn == null) {
			throw new CfcBusinessException ("Game with Id["+ gameId + "] does not exist");
		}
		
		Deck deck2 = deckDao.findById(deck2Id);
		
		toReturn.setDeck2(deck2);
		
		gameDao.update(toReturn);
		
		LOGGER.info("UPDATED game({}) updated with deck2 {}", gameId, deck2Id);
		return toReturn;
		
	}

	@Transactional
	public List<Game> listAllAvailableGames(long playerId) {
		// find the decks for the player
		List<Deck> playerDecks = deckDao.findByPlayerId(playerId);
		
		if (0 == playerDecks.size()) {
			// the current player has no deck ?? 
			// =  ask to create one !
			// TODO : throw an exception "no deck for current user"
			LOGGER.warn("the player id={} does not have any deck !", playerId);
//			return empty list for now ! 
			return new ArrayList<Game>();
		}
		
		List<Long> playerDecksId = new ArrayList<Long>();
		for (Deck deck : playerDecks) {
			playerDecksId.add(new Long(deck.getId()));
		}
		
		// returns the games which don't involve the previous decks
		return gameDao.listAllAvailableGames(playerDecksId);
	}

	@Transactional
	public void endGame(long gameId, long winnerDeckId) {
		Game endingGame = gameDao.findById(gameId);
		
		endingGame.setGameStatus(GameStatus.FINISHED);
		endingGame.setWinnerDeckId(winnerDeckId);
		
		gameDao.update(endingGame);
		
		LOGGER.info("End game {} . Winner is deck {}", gameId, winnerDeckId);
	}
}
