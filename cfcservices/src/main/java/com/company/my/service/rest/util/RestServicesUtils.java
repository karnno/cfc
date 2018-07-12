package com.company.my.service.rest.util;

import com.company.my.bom.GameMove;
import com.company.my.bom.GameMoveFrom;
import com.company.my.service.wrapper.GameMoveWrapper;

public class RestServicesUtils {

	public static GameMove getGameMoveFromGameMoveWrapper(GameMoveWrapper wrapper){
		GameMove toReturn = new GameMove();
		
		toReturn.getGameMovePK().setIdGame(wrapper.getIdGame());
		toReturn.getGameMovePK().setIdDeck1(wrapper.getIdDeck1());
		toReturn.getGameMovePK().setIdDeck2(wrapper.getIdDeck2());
		toReturn.getGameMovePK().setDateMove(wrapper.getDateMove());
		
		toReturn.setCard(wrapper.getCard());
		toReturn.setGameMoveFrom(wrapper.isCardFromDeck1()?GameMoveFrom.PLAYER1 : GameMoveFrom.PLAYER2);
		
		toReturn.setDeck1Energy(wrapper.getDeck1Energy());
		toReturn.setDeck1Credibility(wrapper.getDeck1Credibility());
		toReturn.setDeck1Motivation(wrapper.getDeck1Motivation());
		
		toReturn.setDeck2Energy(wrapper.getDeck2Energy());
		toReturn.setDeck2Credibility(wrapper.getDeck2Credibility());
		toReturn.setDeck2Motivation(wrapper.getDeck2Motivation());
		
		return toReturn;	
	}
}
