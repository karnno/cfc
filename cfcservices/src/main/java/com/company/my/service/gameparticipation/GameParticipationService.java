package com.company.my.service.gameparticipation;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.GameParticipation;
import com.company.my.dao.DeckDao;
import com.company.my.dao.GameDao;
import com.company.my.dao.GameParticipationDao;


/**
 * This service class will help to save the game state from one Player.
 * 
 * One row for one player with the respective game
 * @author karnno
 *
 */

@Service
@Transactional
public class GameParticipationService {

	private static Logger LOGGER = LoggerFactory.getLogger(GameParticipationService.class);
	
	@Autowired
	DeckDao deckDao;
	
	@Autowired
	GameDao gameDao;
	
	@Autowired
	GameParticipationDao gpDao;
	
	public void saveGameParticipation(Long gameId, Date dateParticipation, Long deckId, int energy, int motivation, int credibility) throws Exception{
		LOGGER.info(" service save game participation");
		
		java.util.List<GameParticipation> gpList = gpDao.foundByDeckIdsAndGameId(deckId, deckId, gameId);
		if (gpList.size() == 0) {
			throw new Exception (" Game participations NOT FOUND for game id "+ gameId + " and deck id " + deckId + "["+ gpList.size() +"]" ) ;
		}
		if (gpList.size() > 1) {
			throw new Exception (" too many game participations found for game id "+ gameId + " and deck id " + deckId + "["+ gpList.size() +"]" ) ;
		}
		
		GameParticipation gp = gpList.get(0);
		gp.setParticipationDate(dateParticipation);
		gp.setEnergy(energy);
		gp.setMotivation(motivation);
		gp.setCredibility(credibility);
		
		gpDao.save(gp);
		
	}
}
