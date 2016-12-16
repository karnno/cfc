package com.company.my.dao;

import com.company.my.bom.Game;

public interface GameDao {

	/**
	 * Searches and returns the game via its Id
	 * @param gameId
	 * @return
	 */
	Game foundById(long gameId);
	
	/**
	 * Save the game data
	 * @param gameToSave
	 * @return identifier
	 */
	Long save(Game gameToSave);
}
