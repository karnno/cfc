package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.my.dao.DeckDao;
import com.company.my.deck.Deck;

@Repository("deckDao")
public class DeckDaoImpl implements DeckDao{

	private static final Logger LOG = LoggerFactory.getLogger(DeckDaoImpl.class) ;
	@Autowired
	private SessionFactory sessionFactory;
	
	 protected final Session getCurrentSession() {
	        return sessionFactory.getCurrentSession();
	    }
	 
	public void save(final Deck deck) {
		LOG.debug("-- Begin Save deck");
		this.getCurrentSession().saveOrUpdate(deck);
		LOG.debug("-- End Save deck");
	}

	@SuppressWarnings("unchecked")
	public List<Deck> list() {
		LOG.debug("-- begin list");
		List<Deck> toReturn = (List<Deck>) (this.getCurrentSession().createQuery("from Deck").list());
		LOG.debug("-- ran list !");
		return toReturn;
	}

	
	public Deck findDeckWithCardsById(long idDeck){
		
		Deck toReturn = (Deck) this.getCurrentSession().get(Deck.class, idDeck);
		
		Hibernate.initialize(toReturn.getDeckCards()); 
		
		return toReturn;
	}
	
	
}
