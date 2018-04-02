package com.company.my.bom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CARDS")
public class Card {

	@SequenceGenerator(
	        name="CFC_CARDS_SEQUENCE_GENERATOR",
	        sequenceName="CFC_CARDS_SEQ",
	        initialValue=1,
	        allocationSize=1
	    )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CFC_CARDS_SEQUENCE_GENERATOR")
	@Id 
	@Column(name = "ID")
	long idCard;

	@Column(name = "NAME")
	String nameCard;

	@Column(name = "ENERGY")
	int energy;
	
	@Column(name = "MOTIVATION")
	int motivation;
	
	@Column(name = "CREDIBILITY")
	int credibility;
	
	/*
	 * @ManyToOne annotation defines a single-valued association to another
	 * entity class that has many-to-one multiplicity. It is not normally
	 * necessary to specify the target entity explicitly since it can usually be
	 * inferred from the type of the object being referenced.
	 * 
	 * @JoinColumn indicate the OWNING side of the relationship, it is
	 * responsible for updating the database column. It will create the
	 * DECK_ID column on the CARD table
	 * 
	 * 
	 * NB : To properly map a unidirectional One-to-Many relationship, you only
	 * need to use the @ManyToOne annotation !!
	 */
	@ManyToOne
	@JoinColumn(name = "DECK_ID")
	@JsonIgnore
	private Deck deck;

	public Card() {

	}

	public Card(String name, Deck deck) {
		this.nameCard = name;
		this.deck = deck;
	}

	public long getIdCard() {
		return idCard;
	}

	public void setIdCard(long idCard) {
		this.idCard = idCard;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(final String name) {
		this.nameCard = name;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
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

	public void setEffectValues(int energy, int motivation, int credibility){
		this.setEnergy(energy);
		this.setMotivation(motivation);
		this.setCredibility(credibility);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder
			.append("Card name [").append(this.getNameCard()).append("] ")
			.append(" ENERGY [").append(this.getEnergy()).append("] ")
			.append(" MOTIVATION [").append(this.getMotivation()).append("] ")
			.append(" CREDIBILITY [").append(this.getCredibility()).append("] ")
			;

		return sBuilder.toString();
	}
}
