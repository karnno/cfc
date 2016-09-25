package com.company.my.dao;

import java.util.List;

import com.company.my.deck.Deck;

public interface DeckDao {

	void save(Deck deck);
	List<Deck> list();
	Deck findDeckWithCardsById(long idDeck);
}
