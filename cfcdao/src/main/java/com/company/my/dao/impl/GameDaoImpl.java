package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.my.bom.Game;
import com.company.my.bom.GameStatus;
import com.company.my.dao.GameDao;
import com.company.my.hibernate.util.AbstractHibernateDao;

@Repository("gameDao")
public class GameDaoImpl extends AbstractHibernateDao implements GameDao {

	
	@SuppressWarnings("unchecked")
	public List<Game> list() {
//		return this.getCurrentSession().createQuery("from Deck").list();
		Criteria crit = this.getCurrentSession().createCriteria(Game.class);
		List<Game> toReturn = crit.list();
		return toReturn;
	}
	
	@SuppressWarnings("unchecked")
	public List<Game> listAllAvailableGames(List<Long> playerDeckIds) {
		Criteria crit = this.getCurrentSession().createCriteria(Game.class);
		crit.add(Restrictions.eq("gameStatus", GameStatus.INITIAL));
		
		if (playerDeckIds.size() != 0) {
//			crit.createAlias("deck1", "deck1Alias");
//			crit.createAlias("deck2", "deck2Alias");
			
			/*
			 * where 
			 *      (deck1id is null AND deck2id is NOT null AND deck2id is NOT in (...))
			 *      or
			 *      (deck2id is null AND deck1id is NOT null AND deck1id is NOT in (...))
			 */
			crit.add(Restrictions.or(
							Restrictions.and(
								Restrictions.isNull("deck1.id"),
								Restrictions.isNotNull("deck2.id"),
								Restrictions.not(Restrictions.in("deck2.id", playerDeckIds))
								),
							Restrictions.and(
								Restrictions.isNull("deck2.id"),
								Restrictions.isNotNull("deck1.id"),
								Restrictions.not(Restrictions.in("deck1.id", playerDeckIds))
								)
			));
			
		}

		List<Game> toReturn = crit.list();
		return toReturn;
	}
	
	public Game findById(long gameId) {
		
		Game toReturn = (Game) this.getCurrentSession().get(Game.class, new Long (gameId));
		return toReturn;
	}

	public void save(Game gameToSave) {
		this.getCurrentSession().saveOrUpdate(gameToSave);
	}
	
	public void update(Game gameToUpdate) {
		this.getCurrentSession().saveOrUpdate(gameToUpdate);
	}

}
