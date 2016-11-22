package com.company.my.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.my.bom.Player;
import com.company.my.dao.PlayerDao;

@Repository("playerDao")
public class PlayerDaoImpl implements PlayerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public void save(Player player) {
		this.getCurrentSession().saveOrUpdate(player);
	}

	public Player findById(Long id) {
		Session session = this.getCurrentSession();
		Criteria crit = session.createCriteria(Player.class);
		crit.add(Restrictions.eq("player.id", id));
		
		return (Player) crit.uniqueResult();
	}

	public Player findByName(String name) {
		Session session = this.getCurrentSession();
		Criteria crit = session.createCriteria(Player.class);
		crit.add(Restrictions.eq("player.name", name));
		
		return (Player) crit.uniqueResult();
	}

}
