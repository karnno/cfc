package com.company.my.service.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Card;
import com.company.my.dao.CardDao;

@Service
public class CardService {
	public CardService(){
		
	}
	
	@Autowired
	private CardDao cardDao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Card> getCardsFromDeckId(long idDeck){
		return this.cardDao.findCardsWithDeckId(idDeck);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveCard(Card toSave){
		this.cardDao.save(toSave);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<Card> getAllCards() {
		return this.cardDao.list();
	}
}
