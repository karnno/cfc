package com.company.my.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		// TODO Auto-generated method stub
		return null;
	}

	public Player findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
