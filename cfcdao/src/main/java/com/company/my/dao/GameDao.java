package com.company.my.dao;

import com.company.my.bom.Game;

public interface GameDao {

	/**
	 * Searches and returns the game via its Id
	 * @param gameId
	 * @return the game or null 
	 */
	Game foundById(long gameId);
	
	/**
	 * Save the game data
	 * @param gameToSave
	 */
	void save(Game gameToSave);
	
	/**
	 * update the game, for instance with the status
	 * @param gameToUpdate
	 */
	void update(Game gameToUpdate);
}
