package com.company.my.bom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * BI-DIRECTIONAL one to many relationship between Deck and Card
 * 
 *  Deck IS the owner of the relationship
 *  
 * @author knoviseth
 *
 */

@Entity
@Table(name = "DECKS")
public class Deck {

	@SequenceGenerator(
	        name="CFC_DECKS_SEQUENCE_GENERATOR",
	        sequenceName="CFC_DECKS_SEQ",
	        initialValue=1,
	        allocationSize=1
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_DECKS_SEQUENCE_GENERATOR")
	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String deckName;

	/**
	 * owning side of the relationship with player
	 */
	@ManyToOne(optional=true)
	@JoinColumn(name = "PLAYER_ID", nullable = true, unique = false, updatable = true)
	@JsonIgnore
	private Player player;
	
	
	
	/*
	 * Uncomment the annotation for BI-Directional relationship !
	 * 
	 * 'mappedBy' – means “I am NOT on the OWNING side”, I am mapped by Card from
	 * the other side of the relationship. It will also not create the database
	 * column which makes sense, I would expect a foreign key on the CARD
	 * table instead.
	 */
	/* */
	@OneToMany(
			cascade = CascadeType.ALL
			, mappedBy = "deck"
			, fetch = FetchType.LAZY
			)
	/* */
	
	// I let spring handle the entity manager so I can stay with my jpa annotations and not mix with pure Hibernate annotations
	//@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
	private List<Card> deckCards = new ArrayList<Card>();


	public Deck() {
		this.deckName = "deckName" + System.currentTimeMillis();
	}

	public Deck(String name) {
		this.deckName = name;
	}

	

	public String getDeckName() {
		return deckName;
	}

	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Deck : [").append(this.getId()).append("] name [")
				.append(this.getDeckName()).append("]");
		return sb.toString();
	}

	/*
	 * uncomment for BI-Directional relationship
	 */
	public void setDeckCards(List<Card> cardList) {
		this.deckCards = cardList;
	}

	public List<Card> getDeckCards() {
		return this.deckCards;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/* */
}
