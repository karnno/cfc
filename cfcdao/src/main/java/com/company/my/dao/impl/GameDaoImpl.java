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
	public List<Game> listAllAvailableGames() {
		Criteria crit = this.getCurrentSession().createCriteria(Game.class);
		crit.add(Restrictions.eq("gameStatus", GameStatus.INITIAL));
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
