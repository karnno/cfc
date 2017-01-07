package com.company.my.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.company.my.bom.GameMove;
import com.company.my.bom.GameMovePK;
import com.company.my.dao.GameMoveDao;
import com.company.my.hibernate.util.HibernateDao;

@SuppressWarnings("unchecked")
@Repository("gameMoveDao")
public class GameMoveDaoImpl extends HibernateDao implements GameMoveDao {

	public void save(GameMove gMove) {
		this.getCurrentSession().save(gMove);
	}

	public GameMove findByGameMovePK(GameMovePK gmPK) {
		return (GameMove) this.getCurrentSession().get(GameMove.class, gmPK);
	}

	public List<GameMove> findAllMovesFromGameAndDecks(long idGame, long idDeck1, long idDeck2) {
		
		Criteria crit = this.getCurrentSession().createCriteria(GameMove.class);
		
		crit.add(Restrictions.eq("idGame", idGame));
		
		crit.add(Restrictions.eq("idDeck1", idGame));

		crit.add(Restrictions.eq("idDeck2", idGame));

		
		return (List<GameMove>)(crit.list());

	}

	public java.util.List<GameMove>  findByIdGame(long idGame) {
		Criteria crit = this.getCurrentSession().createCriteria(GameMove.class);
		crit.add(Restrictions.eq("id.idGame", idGame));
		return (List<GameMove>)(crit.list());
	}

}
