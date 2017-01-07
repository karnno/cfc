package com.company.my.dao;

import com.company.my.bom.GameMove;
import com.company.my.bom.GameMovePK;

public interface GameMoveDao {

	/**
	 * Save the last player's move and the state of the game
	 * @param gMove
	 */
	public void save(GameMove gMove);
	
	/**
	 * 
	 * Find one exact move via the composite primary key : 
	 * game id,
	 * deck1 id,
	 * deck2 id,
	 * date 
	 * @param gmPK
	 */
	public GameMove findByGameMovePK (GameMovePK gmPK);
	
	/**
	 * Find all moves from one game for given decks.
	 * @param idGame
	 * @param idDeck1
	 * @param idDeck2
	 */
	public java.util.List<GameMove> findAllMovesFromGameAndDecks(long idGame, long idDeck1, long idDeck2);
	
	/**
	 * find all game moves for one game
	 * @param idGame
	 */
	public java.util.List<GameMove> findByIdGame(long idGame);
	
		
}
