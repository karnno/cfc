package com.company.my.service.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Game;
import com.company.my.dao.GameDao;

@Service
@Transactional
public class GameService {

	Logger LOGGER = LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	GameDao gameDao;
	
	@Transactional
	public void saveGame(Game gameToSave) {
		this.gameDao.save(gameToSave);
	}
}
