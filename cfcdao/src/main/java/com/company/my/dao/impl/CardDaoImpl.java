package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.my.card.Card;
import com.company.my.dao.CardDao;
import com.company.my.hibernate.util.HibernateDao;

@Repository("cardDao")
public class CardDaoImpl extends HibernateDao implements CardDao {

	public void save(Card card) {
		this.getCurrentSession().save(card);
	}

	/**
	 * not so  interesting to list all cards
	 */
	@SuppressWarnings("unchecked")
	public List<Card> list() {
		return this.getCurrentSession().createQuery("from Card").list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Card> findCardsWithDeckId(long idDeck){
		List<Card> toReturn = this.getCurrentSession().createCriteria(Card.class).add(Restrictions.eq("deck.id", new Long(idDeck))).list();
		return toReturn;
	}	
}
