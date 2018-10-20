package com.company.my.bom;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * One game is defined once. In DB, looks like:
 * 
 * ID,
 * GAME_DATE,
 * GAME_STATUS,
 * DECK1_ID,
 * DECK2_ID,
 * WINNER
 * 
 * 
 * NB : DECK1_ID and DECK2_ID can be null, so a user can start a game and wait for opponent
 */
@Entity
@Table(name=Game.GAMES)
public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String GAMES = "GAMES";
	
	@SequenceGenerator(
	        name="CFC_GAMES_SEQUENCE_GENERATOR",
	        sequenceName="CFC_GAMES_SEQ",
	        initialValue=1,
	        allocationSize=1
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_GAMES_SEQUENCE_GENERATOR")
	@Id
	@Column(name = "ID")
	long id;
	
	
	
	@Column(name = "GAME_DATE")
	@Temporal(TemporalType.DATE)
	private Date dateGame = new Date(); 
	
	@Enumerated(EnumType.STRING)
	@Column(name= "GAME_STATUS")
	private GameStatus gameStatus;
	
	
	@OneToOne(optional=true)
    @JoinColumn(name = "DECK1_ID", nullable = true, unique = false, updatable = true)
	Deck deck1;
	
	@OneToOne(optional=true)
    @JoinColumn(name = "DECK2_ID", nullable = true, unique = false, updatable = true)
	Deck deck2; 
	
	
	@Column(name="WINNER", nullable = true, unique = false, updatable = true)
	private long winnerDeckId;
	
	
	/**
	 * Main constructor
	 */
	public Game() {
		this.gameStatus = GameStatus.INITIAL;
		this.dateGame = Calendar.getInstance().getTime();
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateGame() {
		return dateGame;
	}

	public void setDateGame(Date dateGame) {
		this.dateGame = dateGame;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	public Deck getDeck1() {
		return deck1;
	}
	public void setDeck1(Deck deck1) {
		this.deck1 = deck1;
	}
	public Deck getDeck2() {
		return deck2;
	}
	public void setDeck2(Deck deck2) {
		this.deck2 = deck2;
	}
	public long getWinnerDeckId() {
		return winnerDeckId;
	}
	public void setWinnerDeckId(long winnerDeckId) {
		this.winnerDeckId = winnerDeckId;
	}
}
