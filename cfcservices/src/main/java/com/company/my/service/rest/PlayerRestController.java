package com.company.my.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.bom.Player;
import com.company.my.service.PlayerService;

@RestController
@RequestMapping("/player/")
public class PlayerRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRestController.class);
	@Autowired
	PlayerService playerService;
	
	/**
	 * Get everything (decks and cards) for one player
	 * @param playerID
	 * @return
	 */
	@RequestMapping(value="byid", method = RequestMethod.GET, headers={"Accept=application/json"} ,produces = "application/json")
	public Player getPLayerFromId (@RequestParam(name="playerId") Long playerID){
		Player toReturn = playerService.findPlayerWithDecksAndCardsByPlayerId(1L);
		LOGGER.info("GET PLAYER WITH ID {}, NAMED {}", playerID, toReturn.getName());
		return toReturn;
	}
	
	@RequestMapping(value="savepersonalinfo", method = RequestMethod.GET, headers={"Accept=application/json"} ,produces = "application/json")
	public ResponseEntity<String> savePlayerPersonalInfo (@RequestParam(name="playerId")Long playerId, @RequestParam(name="name") String name, @RequestParam(name="tagLine")String tagLine){
		//playerService.updatePlayerPersonalInfo(playerId, name, tagLine);
		
		playerService.updatePlayerPersonalInfo(playerId, name, tagLine);
		
		String okText = "Player personal info updated id:"+playerId + " name:"+name + " tagLine:"+tagLine ;
		return new ResponseEntity<String>(okText, HttpStatus.OK);
		
	}
	
}
