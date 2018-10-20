package com.company.my.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.bom.Player;
import com.company.my.service.PlayerService;

@RestController

public class LoginRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestController.class);
	@Autowired
	PlayerService playerService;
	
	
	@RequestMapping("/login")
	public Player userLogin(String name, String password) {
		
		LOGGER.info("trying to login with user {} and pass {}", name, password);
		
		Player playerSearch = playerService.findPlayerByUserNameAndPassword(name, password);
		
		if (null == playerSearch) {
			LOGGER.info("no player found !");
		}
		
		return playerSearch ;
	}
}
