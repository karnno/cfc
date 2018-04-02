package com.company.my.dao.impl;

import org.springframework.stereotype.Repository;

import com.company.my.bom.Game;
import com.company.my.dao.GameDao;
import com.company.my.hibernate.util.HibernateDao;

@Repository("gameDao")
public class GameDaoImpl extends HibernateDao implements GameDao {

	public Game foundById(long gameId) {
		
		Game toReturn = (Game) this.getCurrentSession().get(Game.class, new Long (gameId));
		return toReturn;
	}

	public Long save(Game gameToSave) {
		return (Long) this.getCurrentSession().save(gameToSave);
	}
	
	public void update(Game gameToUpdate) {
		this.getCurrentSession().saveOrUpdate(gameToUpdate);
	}

}
