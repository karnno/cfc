package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.my.bom.Card;
import com.company.my.dao.CardDao;
import com.company.my.hibernate.util.AbstractHibernateDao;

@Repository("cardDao")
public class CardDaoImpl extends AbstractHibernateDao implements CardDao {

	public Long save(Card card) {
		return (Long) this.getCurrentSession().save(card);
	}

	/**
	 * not so  interesting to list all cards
	 */
	@SuppressWarnings("unchecked")
	public List<Card> list() {
//		return this.getCurrentSession().createQuery("from Card").list();
		Criteria crit = this.getCurrentSession().createCriteria(Card.class);
		List<Card> toReturn = crit.list();
		
		return toReturn;
		
	}

	
	@SuppressWarnings("unchecked")
	public List<Card> findCardsWithDeckId(long idDeck){
		List<Card> toReturn = this.getCurrentSession().createCriteria(Card.class).add(Restrictions.eq("deck.id", new Long(idDeck))).list();
		return toReturn;
	}	
}
