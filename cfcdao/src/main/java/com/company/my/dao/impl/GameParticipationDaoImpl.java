package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.my.bom.GameParticipation;
import com.company.my.dao.GameParticipationDao;
import com.company.my.hibernate.util.HibernateDao;

@SuppressWarnings("unchecked")
@Repository("gameParticipationDao")
public class GameParticipationDaoImpl extends HibernateDao implements GameParticipationDao {

	public GameParticipation foundById(long gpId) {
		Criteria crit = this.getCurrentSession().createCriteria(GameParticipation.class);
		crit.add(Restrictions.eq("id", gpId));
		return (GameParticipation)crit.uniqueResult();
		
	}

	public void save(GameParticipation gameParticipation) {
		this.getCurrentSession().save(gameParticipation);
	}

	public List<GameParticipation> foundByGameId(long gameId) {
		Criteria crit = this.getCurrentSession().createCriteria(GameParticipation.class);
		crit.add(Restrictions.eq("game.id", gameId));
		return (List<GameParticipation>)(crit.list());
	}

	
	public List<GameParticipation> foundByDeckIds(long deckId1, long deckId2) {
		Criteria crit = this.getCurrentSession().createCriteria(GameParticipation.class);
		crit.add(
				Restrictions.or(
				        Restrictions.eq( "deck.id", deckId1 ),
				        Restrictions.eq( "deck.id", deckId2 )
				)
				);
		return (List<GameParticipation>)(crit.list());
	}

	public List<GameParticipation> foundByDeckIdsAndGameId(long deckId1, long deckId2, long gameId) {
		Criteria crit = this.getCurrentSession().createCriteria(GameParticipation.class);
		crit.add(
				Restrictions.or(
				        Restrictions.eq( "deck.id", deckId1 ),
				        Restrictions.eq( "deck.id", deckId2 )
				)
				);
		crit.add(Restrictions.eq("game.id", gameId));
		return (List<GameParticipation>)(crit.list());
	}

}
