package com.company.my.hibernate.test;
 
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.company.my.card.Card;
import com.company.my.deck.Deck;
import com.company.my.hibernate.util.HibernateUtil;

/**
 * Little poc that uses hibernate session directly
 * it's using hibernate api without any Spring class required
 * 
 * drawback : it may throw HibernateException , a Runtime Exception
 * that does not have to be caught or checked 
 * 
 * NB : connects to testDB  !!!
 * @author novkar
 *
 */
public class HibernateTest {
 
	@Test
	public void testIt() {
        try{  
        Session session = HibernateUtil.getSessionFactory().openSession();
  
        session.beginTransaction();
 
        /**
        Deck deck1 = new Deck("Deck__" + System.currentTimeMillis());
        
        
        List<Card> cardList = new ArrayList<Card>();
        
        Card card1 = new Card("Card_" + System.currentTimeMillis(),false,deck1);
        Card card2 = new Card("Card_" + System.currentTimeMillis(),false,deck1);
      
        cardList.add(card1);
        cardList.add(card2);
        
        deck1.setDeckCards(cardList);
        
        session.save(deck1);
        
        session.getTransaction().commit();
        /* */
        
        
        Query q = session.createQuery("From Deck ");
                 
        @SuppressWarnings("unchecked")
		List<Deck> resultList = q.list();
        System.out.println("num of Deck :" + resultList.size());
        for (Deck next : resultList) {
            System.out.println("next deck : " + next);
            System.out.println("\t" + next.getDeckCards());
        }
 
        session.close();
        HibernateUtil.shutdown();
        }catch(Exception ee){
        	ee.printStackTrace();
        }
    }
    
}