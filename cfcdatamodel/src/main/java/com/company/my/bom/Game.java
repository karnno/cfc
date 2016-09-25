package com.company.my.bom;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	long id;
	
	@OneToMany
	@JoinColumn(name="GAME_ID", 	referencedColumnName="ID")
	private List<GameParticipation> gameParticipations;
	
	
	@Column(name = "GAME_DATE")
	private Date dateGame = new Date(); 
	
	@Enumerated(EnumType.STRING)
	@Column(name= "GAME_STATUS")
	private GameStatus gameStatus;
	
	
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
