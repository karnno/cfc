package com.company.my.service.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.my.service.gameparticipation.GameParticipationService;
import com.company.my.service.wrapper.GameParticipationWrapper;

@RestController
@RequestMapping("/gameparticipation/")
public class GameParticipationRestController {

	private static Logger LOGGER = LoggerFactory.getLogger(GameParticipationRestController.class);
	
	@Autowired
	GameParticipationService gpService;
	
	@RequestMapping(value="save", method = RequestMethod.POST, headers={"Accept=application/json"} ,produces = "application/json")
	public void saveGameParticipation(GameParticipationWrapper gpWrapper) {
		LOGGER.info(" Saving game participation. param : {}", gpWrapper);
		
//		try {
//			gpService.saveGameParticipation(gpWrapper.getIdGame(), gpWrapper.getDateParticipation(), gpWrapper.getIdDeck(), gpWrapper.getEnergy(), gpWrapper.getMotivation(), gpWrapper.getCredibility());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
