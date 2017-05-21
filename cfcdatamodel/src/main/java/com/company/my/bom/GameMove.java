package com.company.my.bom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.company.my.card.Card;

/**
 * 
 * an entity to give, in one line, the state of the game !!
 * find more info about composite key here : http://uaihebert.com/tutorial-jpa-composite-primary-key/
 * @author karnno
 *
 */
@Entity
@Table(name=GameMove.GAME_MOVES)
public class GameMove implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String GAME_MOVES = "GAMEMOVES";
	
	@EmbeddedId
	private GameMovePK gameMovePK;
	

	/**
	 * to know which card was played at that moment
	 */
	@ManyToOne
	@JoinColumn(name = "CARD_ID")
	private Card card; 

	/**
	 * to know if it's the player 1 who used the card or not
	 */
	@Column(name="CARD_DECK1")
	private boolean cardFromDeck1;
	
	@Column(name = "DECK1_ENERGY")
	private int deck1Energy;
	
	@Column(name = "DECK1_MOTIVATION")
	private int deck1Motivation;
	
	@Column(name = "DECK1_CREDIBILITY")
	private int deck1Credibility;
	
	@Column(name = "DECK2_ENERGY")
	private int deck2Energy;
	
	@Column(name = "DECK2_MOTIVATION")
	private int deck2Motivation;
	
	@Column(name = "DECK2_CREDIBILITY")
	private int deck2Credibility;
	

	public GameMove(){
		if (this.getGameMovePK()==null){
			this.setGameMovePK(new GameMovePK());
		}
	}
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	public GameMovePK getGameMovePK() {
		return gameMovePK;
	}
	
	public boolean isCardFromDeck1() {
		return cardFromDeck1;
	}

	public void setCardFromDeck1(boolean cardFromDeck1) {
		this.cardFromDeck1 = cardFromDeck1;
	}

	

	public void setGameMovePK(GameMovePK gameMovePK) {
		this.gameMovePK = gameMovePK;
	}

	public int getDeck1Energy() {
		return deck1Energy;
	}

	public void setDeck1Energy(int deck1Energy) {
		this.deck1Energy = deck1Energy;
	}

	public int getDeck1Motivation() {
		return deck1Motivation;
	}

	public void setDeck1Motivation(int deck1Motivation) {
		this.deck1Motivation = deck1Motivation;
	}

	public int getDeck1Credibility() {
		return deck1Credibility;
	}

	public void setDeck1Credibility(int deck1Credibility) {
		this.deck1Credibility = deck1Credibility;
	}

	public int getDeck2Energy() {
		return deck2Energy;
	}

	public void setDeck2Energy(int deck2Energy) {
		this.deck2Energy = deck2Energy;
	}

	public int getDeck2Motivation() {
		return deck2Motivation;
	}

	public void setDeck2Motivation(int deck2Motivation) {
		this.deck2Motivation = deck2Motivation;
	}

	public int getDeck2Credibility() {
		return deck2Credibility;
	}

	public void setDeck2Credibility(int deck2Credibility) {
		this.deck2Credibility = deck2Credibility;
	}

}
