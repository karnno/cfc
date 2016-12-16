package com.company.my.bom;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.my.card.Card;
import com.company.my.deck.Deck;

/*
 * one player's DECK  participates in many games; 
 * 
 * during that game, he will have his energy, motivation, credibility impacted, because of a card !.
 * 
 */
@Entity
@Table(name=GameParticipation.GAME_PARTICIPATIONS)
public class GameParticipation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String GAME_PARTICIPATIONS = "GAMEPARTICIPATIONS";
	
	@SequenceGenerator(
	        name="CFC_GAMEPARTICIPATIONS_SEQUENCE_GENERATOR",
	        sequenceName="CFC_GAMEPARTICIPATIONS_SEQ"
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_GAMEPARTICIPATIONS_SEQUENCE_GENERATOR")
	@Id
	@Column(name = "ID")
	private long id;
	
	
	@ManyToOne
	@JoinColumn(name = "DECK_ID")
	private Deck deck;
	
	@ManyToOne
	@JoinColumn(name = "GAME_ID")
	private Game game; 
	
	@ManyToOne
	@JoinColumn(name = "CARD_ID")
	private Card card; 
	
	/*
	 * This date may be interesting to log each time the user was impacted :D
	 */
	@Column(name = "PARTICIPATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date participationDate;
	
	
	@Column(name = "ENERGY")
	private int energy;
	
	@Column(name = "MOTIVATION")
	private int motivation;
	
	@Column(name = "CREDIBILITY")
	private int credibility; 
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Deck getDeck(){
		return this.deck;
	}
	
	public void setDeck(Deck deck){
		this.deck = deck;
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Date getParticipationDate() {
		return participationDate;
	}

	public void setParticipationDate(Date participationDate) {
		this.participationDate = participationDate;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public int getCredibility() {
		return credibility;
	}

	public void setCredibility(int credibility) {
		this.credibility = credibility;
	}
	
}
