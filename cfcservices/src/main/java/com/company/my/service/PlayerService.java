package com.company.my.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Deck;
import com.company.my.bom.Player;
import com.company.my.dao.PlayerDao;

@Service
@Transactional
public class PlayerService {

	Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);
	@Autowired
	PlayerDao playerDao; 
	
	@Transactional
	public Player findPlayerWithDecksAndCardsByPlayerId(long playerId){
		
		
		Player toReturn = this.playerDao.findById(playerId);
		LOGGER.info("found player with id {}", playerId);
		LOGGER.debug("Found player {} with his {} decks", toReturn.getName(), toReturn.getDecks().size() );
		
		if (toReturn.getDecks().size() !=0){
			LOGGER.debug("parse the decks");
			
			for (Deck oneDeck : toReturn.getDecks()){
				LOGGER.debug("deck {} has {} cards", oneDeck.getId(), oneDeck.getDeckCards().size());
			}
			
		}
		
		return toReturn;
	}

	@Transactional(propagation=Propagation.NEVER)
	public void updatePlayerPersonalInfo(Long playerId, String newName, String newTagLine) {
		Player toUpdate = this.playerDao.findById(playerId);
		LOGGER.info("found player toUpdate with id {}", playerId);
		
		toUpdate.setName(newName);
		toUpdate.setTagLine(newTagLine);
		
		playerDao.save(toUpdate);
	}
	
	@Transactional
	public void savePLayer(Player toSave) {
		this.playerDao.save(toSave);
	}

	@Transactional
	public Player findPlayerByUserNameAndPassword(String name, String password) {
		Player toReturn = this.playerDao.findByNameAndPassword(name, password);
		
		return toReturn;
	}
	
	
}
