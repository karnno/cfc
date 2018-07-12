package com.company.my.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * One game is defined once.
 * 
 * But it is implied in many participations
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
	
	@OneToMany(mappedBy="game")
	private List<GameParticipation> gameParticipations;
	
	
	@Column(name = "GAME_DATE")
	@Temporal(TemporalType.DATE)
	private Date dateGame = new Date(); 
	
	@Enumerated(EnumType.STRING)
	@Column(name= "GAME_STATUS")
	private GameStatus gameStatus;
	
	public Game() {
		this.gameStatus = GameStatus.INITIAL;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<GameParticipation> getGameParticipations() {
		return gameParticipations;
	}

	public void setGameParticipations(List<GameParticipation> gameParticipations) {
		this.gameParticipations = gameParticipations;
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
}
