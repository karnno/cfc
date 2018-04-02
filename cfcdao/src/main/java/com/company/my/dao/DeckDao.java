package com.company.my.dao;

import java.util.List;

import com.company.my.bom.Deck;

public interface DeckDao {

	void save(Deck deck);
	List<Deck> list();
	
	/**
	 * find the deck from its id
	 * @param idDeck
	 * @return one Deck or null
	 */
	public Deck findById(long idDeck);
	
	/**
	 * Returns the decks with all its cards
	 * @param idDeck
	 * @return Deck
	 */
	Deck findDeckWithCardsById(long idDeck);
	
	/**
	 * Returns the deck from the owner
	 * @param playerId
	 * @return List<Deck>
	 */
	List<Deck> findByPlayerId(long playerId);
}
