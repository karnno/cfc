package com.company.my.bom;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.company.my.deck.Deck;

/*
 * one player's DECK  participates in many games; 
 * 
 * during that game, he will have his energy, motivation, credibility impacted.
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
	
	@ManyToOne
	@JoinColumn(name = "DECK_ID")
	private Deck deck;
	
	@ManyToOne
	@JoinColumn(name = "GAME_ID")
	private Game game; 
	
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
