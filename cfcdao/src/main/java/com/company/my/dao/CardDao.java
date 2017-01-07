package com.company.my.dao;

import java.util.List;

import com.company.my.card.Card;


public interface CardDao {

	Long save(Card card);
	
	List<Card> list();
	
	List<Card> findCardsWithDeckId(long idDeck);
}
