package com.company.my.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.bom.GameMove;
import com.company.my.service.gamemove.GameMoveService;
import com.company.my.service.rest.util.RestServicesUtils;
import com.company.my.service.wrapper.GameMoveWrapper;

@RestController
@RequestMapping("/gamemove")
@SuppressWarnings("unused")
public class GameMoveRestController {

	private final static Logger LOGGER = LoggerFactory.getLogger(GameMoveRestController.class);
	
	@Autowired
	GameMoveService gmService;
	/**
	 * Saves one game move !!
	 * @param wrapper
	 * @throws Exception 
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, headers={"Accept=application/json"} ,produces = "application/json")
    public void saveGameMove(GameMoveWrapper wrapper, HttpServletRequest request, HttpServletResponse response) throws Exception{
		LOGGER.info(" GameMoveRestController : save game move {}", wrapper.toString());
		
		GameMove toSave = RestServicesUtils.getGameMoveFromGameMoveWrapper(wrapper);
		
		this.gmService.saveGameMove(toSave);
		LOGGER.info(" GameMoveRestController : succeeded in game move save!");
		response.setStatus(HttpStatus.OK.value());
	}
	
	
	/**
	 * Returns all the history for one game between two decks !
	 * @param gameId
	 * @param deck1Id
	 * @param deck2Id
	 * @return List<GameMove>
	 * @throws Exception
	 */
	@RequestMapping(value="/findall", method = RequestMethod.GET, produces = "application/json")
	public List<GameMove> findAllGameMovesForGameAndDecks(Long gameId, Long deck1Id, Long deck2Id) throws Exception {
		List<GameMove> toReturn = new ArrayList<GameMove>();
		LOGGER.info("GameMoveRestController : find all game moves from game id {}, deck1 id {}, deck2 id {}", gameId, deck1Id, deck2Id);
		return this.gmService.findGameMovesByGameAndDecks(gameId, deck1Id, deck2Id);
	}
	
}
