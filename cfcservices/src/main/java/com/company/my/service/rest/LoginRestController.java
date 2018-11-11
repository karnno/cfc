package com.company.my.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.bom.Player;
import com.company.my.service.PlayerService;
import com.company.my.service.rest.wrapper.UserLoginWrapper;

@RestController
public class LoginRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestController.class);
	@Autowired
	PlayerService playerService;
	
	
	//@RequestMapping(name="/login", method = RequestMethod.POST, headers={"Accept=application/json"} ,produces = "application/json")
	//since spring 4.3 you can user @PostMapping instead of @RequestMapping
	@PostMapping(path="/login", consumes="application/json", produces="application/json" )
	public Player userLogin(@RequestBody UserLoginWrapper wrapper) {
		
		
		LOGGER.info("trying to login with user {} and pass {}", wrapper.getName(), wrapper.getPassword());
		
		Player playerSearch = playerService.findPlayerByUserNameAndPassword(wrapper.getName(), wrapper.getPassword());
		
		if (null == playerSearch) {
			LOGGER.info("no player with name [{}] found or password incorrect !", wrapper.getName());
			return null ;
		}else {
			
			// due to lazy loading, we must not return the found user otherwise it will try to load the 
			// decks out of the transaction.
			// == better return an empty player
			
			Player toReturn = new Player();
			toReturn.setName(playerSearch.getName());
			toReturn.setId(playerSearch.getId());
			toReturn.setTagLine(playerSearch.getTagLine());
			
			return toReturn;
		}
		
	}
	
	
}
