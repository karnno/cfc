package com.company.my.bom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Enumerated(EnumType.STRING)
	@Column(name= "GAME_MOVE_TYPE")
	private GameMoveType gameMoveType;
	/**
	 * to know which card was played at that moment
	 */
	@ManyToOne
	@JoinColumn(name = "CARD_ID")
	private Card card; 

	/**
	 * to know if it's the player 1 who used the card or not
	 */
	@Enumerated(EnumType.STRING)
	@Column(name= "GAME_MOVE_FROM")
	private GameMoveFrom gameMoveFrom;
	
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
		
		this.gameMoveType = GameMoveType.PLAY_CARD;
	}
	
	public GameMoveType getGameMoveType() {
		return gameMoveType;
	}
	
	public void setGameMoveType(GameMoveType gmType) {
		this.gameMoveType = gmType;
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
	
	public GameMoveFrom getGameMoveFrom() {
		return this.gameMoveFrom;
	}

	public void setGameMoveFrom(GameMoveFrom gmf) {
		this.gameMoveFrom = gmf;
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

	public void setVitals(int player1_energy, int player1_motivation, int player1_credibility, 
			int player2_energy, int player2_motivation, int player2_credibility) {
		this.deck1Energy 		= player1_energy;
		this.deck1Motivation 	= player1_motivation;
		this.deck1Credibility 	= player1_credibility;
		
		this.deck2Energy 		= player2_energy;
		this.deck2Motivation 	= player2_motivation;
		this.deck2Credibility 	= player2_credibility;
	}
}
