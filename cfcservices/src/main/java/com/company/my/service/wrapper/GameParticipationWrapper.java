package com.company.my.service.wrapper;

import java.util.Date;


public class GameParticipationWrapper {

	private long idGame, idDeck;
	private Date dateParticipation;
	
	private int energy;
	private int motivation;
	private int credibility;
	
	
	public long getIdGame() {
		return idGame;
	}
	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}
	public long getIdDeck() {
		return idDeck;
	}
	public void setIdDeck(long idDeck) {
		this.idDeck = idDeck;
	}
	public Date getDateParticipation() {
		return dateParticipation;
	}
	public void setDateParticipation(Date dateParticipation) {
		this.dateParticipation = dateParticipation;
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
