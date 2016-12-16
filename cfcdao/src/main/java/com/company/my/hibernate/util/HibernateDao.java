package com.company.my.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * See HibernateDao bean declaration in springcontext.xml configuration file
 * 
 */
public abstract class HibernateDao {

	static final Logger LOGGER = LoggerFactory.getLogger(HibernateDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		LOGGER.info("Session factory initialized in HibernateDao [{}]", (this.sessionFactory != null));
	}

	public  Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}
