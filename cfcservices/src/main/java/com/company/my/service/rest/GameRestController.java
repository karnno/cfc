package com.company.my.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.bom.Game;
import com.company.my.service.exception.CfcBusinessException;
import com.company.my.service.game.GameService;

@RestController
@RequestMapping("/game")
public class GameRestController {

	@Autowired
	GameService gameService; 
	
	private final static Logger LOGGER = LoggerFactory.getLogger(GameRestController.class );
	
	
	
	/**
	 * Instantiate a new game, with no opponent
	 * @param deck1Id the player's deck ID
	 * @return the newly instantiated game Id
	 */
	@PostMapping(value="/new" , produces = "application/json" )
	public long newGame(@RequestParam(name="deck1Id") Long deck1Id) {
		Game created = gameService.createNewGame(deck1Id);
		LOGGER.info("Created new game with id {}.", created.getId());
		return created.getId();
		
	}
	
	
	/**
	 * List all available games one user can join
	 */
	@GetMapping(value="/list" ,produces = "application/json" )
	public void listAllAvailableGames(long playerId) {
		gameService.listAllAvailableGames(playerId);
	}
	
	/**
	 * When a game is available, join it
	 * @param gameId
	 * @param deck2Id
	 * @return the game id 
	 * @throws CfcBusinessException 
	 */
	@PostMapping(value="/join", produces = "application/json" )
	public long joinGame(long gameId, long deck2Id) throws CfcBusinessException {
		gameService.joinExistingGame(gameId, deck2Id);
		LOGGER.info("JOINED game {}.", gameId );
		return gameId;
		
	}
	
	@PostMapping(value="/end", produces = "application/json")
	public long endGame(long gameId, long winnerDeckId) {
		gameService.endGame(gameId, winnerDeckId);
		LOGGER.info("ENDED game {}", gameId);
		return gameId; 
	}
}
