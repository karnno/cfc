package com.company.my.service.deck;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.my.bom.Deck;
import com.company.my.dao.DeckDao;

@Service
public class DeckService {

	public DeckService(){
		
	}
	
	
	@Autowired
	private DeckDao deckDao;
	
	 
	
	@Transactional(propagation=Propagation.REQUIRED)
	public  List<Deck> listDecksWithoutCards(){
		return  this.deckDao.list();
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public  List<Deck> listDecksWithCards(){
		List<Deck> toReturn = this.deckDao.list();
		
		for (Deck oneDeck : toReturn){
			//this works ?
			//System.out.println(" in service : " +oneDeck + " / " + oneDeck.getDeckCards());
			//no : 
			//oneDeck.setDeckCards(oneDeck.getDeckCards());
			 
			//this works
			Hibernate.initialize(oneDeck.getDeckCards());
		}
		return toReturn;
		
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDeck(Deck toSave) {
		this.deckDao.save(toSave);
	}
	
	public Deck getDeckWithItsCards(long idDeck){
		Deck toReturn = this.deckDao.findDeckWithCardsById(idDeck);
		
		Hibernate.initialize(toReturn.getDeckCards());
		
		return toReturn;
		
	}
}
