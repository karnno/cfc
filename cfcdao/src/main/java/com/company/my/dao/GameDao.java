package com.company.my.dao;

import java.util.List;

import com.company.my.bom.Game;

public interface GameDao {

	/**
	 * Returns all games
	 * @return
	 */
	public List<Game> list();
	
	/**
	 * Returns list of available games 
	 * @return
	 */
	public List<Game> listAllAvailableGames();
	
	/**
	 * Searches and returns the game via its Id
	 * @param gameId
	 * @return the game or null 
	 */
	Game findById(long gameId);
	
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
