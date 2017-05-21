package com.company.my.service.wrapper;

import java.util.Date;

import com.company.my.card.Card;

/**
 * Pojo Wrapper for the rest service to be automatically filled with user query data
 * @author karnno
 *
 */
public class GameMoveWrapper {
	private long idGame, idDeck1, idDeck2;
	private Date dateMove;
	private Card card;
	private boolean cardFromDeck1;
	
	private int deck1Energy;
	private int deck1Motivation;
	private int deck1Credibility;
	
	private int deck2Energy;
	private int deck2Motivation;
	private int deck2Credibility;
	public long getIdGame() {
		return idGame;
	}
	public void setIdGame(long idGame) {
		this.idGame = idGame;
	}
	public long getIdDeck1() {
		return idDeck1;
	}
	public void setIdDeck1(long idDeck1) {
		this.idDeck1 = idDeck1;
	}
	public long getIdDeck2() {
		return idDeck2;
	}
	public void setIdDeck2(long idDeck2) {
		this.idDeck2 = idDeck2;
	}
	public Date getDateMove() {
		return dateMove;
	}
	public void setDateMove(Date dateMove) {
		this.dateMove = dateMove;
	}
	public Card getCard() {
		return this.card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public boolean isCardFromDeck1() {
		return cardFromDeck1;
	}
	public void setCardFromDeck1(boolean card_deck1) {
		this.cardFromDeck1 = card_deck1;
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
