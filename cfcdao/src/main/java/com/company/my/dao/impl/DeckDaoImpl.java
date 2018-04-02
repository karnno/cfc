package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.company.my.bom.Deck;
import com.company.my.dao.DeckDao;
import com.company.my.hibernate.util.HibernateDao;

@Repository("deckDao")
public class DeckDaoImpl extends HibernateDao implements DeckDao{

	private static final Logger LOG = LoggerFactory.getLogger(DeckDaoImpl.class) ;
	 
	public void save(final Deck deck) {
		LOG.debug("-- Begin Save deck");
		this.getCurrentSession().saveOrUpdate(deck);
		LOG.debug("-- End Save deck");
	}

	@SuppressWarnings("unchecked")
	public List<Deck> list() {
		LOG.debug("-- begin list");
		Criteria criteria = this.getCurrentSession().createCriteria(Deck.class);
		List<Deck> toReturn =  criteria.list();
//		List<Deck> toReturn = (List<Deck>) (this.getCurrentSession().createQuery("from Deck").list());
		LOG.debug("-- ran list !");
		return toReturn;
	}

	public Deck findById(long idDeck) {
		return (Deck) this.getCurrentSession().get(Deck.class, idDeck);
		
	}
	
	public Deck findDeckWithCardsById(long idDeck){
		
		Deck toReturn = (Deck) this.getCurrentSession().get(Deck.class, idDeck);
		
		Hibernate.initialize(toReturn.getDeckCards()); 
		
		return toReturn;
	}

	/*
	 * basic example to filter with the other class's properties here :
	 * http://stackoverflow.com/questions/1787086/hibernate-query-a-foreign-key-field-with-id
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Deck> findByPlayerId(long playerId) {
		Criteria crit = this.getCurrentSession().createCriteria(Deck.class);
		
		crit.add(Restrictions.eq("player.id", playerId));
		
		return (List<Deck>)(crit.list());
	}
	
	
}
