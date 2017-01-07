package com.company.my.deck;

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

import com.company.my.bom.Player;
import com.company.my.card.Card;


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
	        allocationSize=1
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_DECKS_SEQUENCE_GENERATOR")
	@Id
	@Column(name = "ID")
	private long id;

	@Column(name = "NAME")
	private String deckName;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PLAYER_ID")
	private Player player;
	/*
	 * Uncomment the annotation for BI-Directional relationship !
	 * 
	 * 'mappedBy' – means “I am NOT on the SLAVE side”, I am mapped by Card from
	 * the other side of the relationship. It will also not create the database
	 * column which makes sense, I would expect a foreign key on the CARD
	 * table instead.
	 */
	/* */
	@OneToMany(
			cascade = CascadeType.PERSIST, 
			mappedBy = "deck", 
			fetch = FetchType.LAZY)
	/* */
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
