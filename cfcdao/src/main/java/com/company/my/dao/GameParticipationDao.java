package com.company.my.dao;

import com.company.my.bom.GameParticipation;

public interface GameParticipationDao {

	GameParticipation foundById(long gpId);
	
	void save(GameParticipation gameParticipation);
	
	/**
	 * Found all the events of one game.
	 * @param gameId
	 * @return List<GameParticipation>
	 */
	java.util.List<GameParticipation> foundByGameId(long gameId);
	
	/**
	 * Returns all the game participations between 2 decks
	 * @param deckId1
	 * @param deckId2
	 * @return List<GameParticipation> 
	 */
	java.util.List<GameParticipation> foundByDeckIds(long deckId1, long deckId2);
	
	/**
	 * Returns all the game participations between 2 decks during one game.
	 * Useful to get one FULL game !
	 * @param deckId1
	 * @param deckId2
	 * @param gameId
	 * @return
	 */
	java.util.List<GameParticipation> foundByDeckIdsAndGameId(long deckId1, long deckId2, long gameId);
}
