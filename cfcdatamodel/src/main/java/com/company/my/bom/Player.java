package com.company.my.bom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * A user has a tag line:
 * ex:
 * 	"Skelettor - Living skull head world conqueror" 
 * @author knoviseth
 *
 */
@Entity
@Table(name = Player.PLAYERS)
public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PLAYERS= "PLAYERS";
	
	@SequenceGenerator(
	        name="CFC_PLAYERS_SEQUENCE_GENERATOR",
	        sequenceName="CFC_PLAYERS_SEQ",
	        initialValue=1,
	        allocationSize=1
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_PLAYERS_SEQUENCE_GENERATOR")
	@Id
	@Column(name = "ID")
	long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "TAGLINE")
	private String tagLine; 
	
	@OneToMany(
			cascade = CascadeType.PERSIST
			, mappedBy="player"
			, fetch = FetchType.LAZY
			)
	List<Deck> decks;
	
	public Player(){
		this.decks = new ArrayList<Deck>();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagLine() {
		return tagLine;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public List<Deck> getDecks() {
		return decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}
	
	
}
