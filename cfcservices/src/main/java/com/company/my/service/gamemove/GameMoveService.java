package com.company.my.service.gamemove;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.GameMove;
import com.company.my.dao.GameMoveDao;

@Service
@Transactional
public class GameMoveService {

	private final static Logger LOG = LoggerFactory.getLogger(GameMoveService.class);
	
	@Autowired
	private GameMoveDao gameMoveDao;
	
	public void saveGameMove(GameMove gameMove) throws Exception{
		this.gameMoveDao.save(gameMove);
	}
	
	/**
	 * returns all the game moves order by the move date in DESC order
	 * @param game
	 * @param deck1
	 * @param deck2
	 * @return List<GameMove>
	 * @throws Exception
	 */
	public List<GameMove> findGameMovesByGameAndDecks (Long gameId, Long deck1Id, Long deck2Id) throws Exception{
		List<GameMove> toReturn = new ArrayList<GameMove>();
		
		toReturn = this.gameMoveDao.findAllMovesFromGameAndDecks(gameId, deck1Id, deck2Id);
		LOG.debug("Found {} moves for game id {}, deck1 {}, deck2 {}", toReturn.size(), gameId, deck1Id, deck2Id);
		
		return toReturn;
	}
}
