package com.company.my.bom;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GameMovePK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5441212518393047760L;
	
	@Column(name = "GAME_ID", nullable = false)
	private long idGame;
	
	@Column(name = "DECK1_ID", nullable = false)
	private long idDeck1;
	
	@Column(name = "DECK2_ID", nullable = false)
	private long idDeck2;
    
	@Column(name = "DATE_MOVE", nullable = false)
	private Date dateMove;

	public GameMovePK(){
		
	}
	
	
	@Override
    public boolean equals(Object obj) {
        if(obj instanceof GameMovePK){
        	GameMovePK gmPk = (GameMovePK) obj;
 
            if(gmPk.idGame != this.idGame){
                return false;
            }
 
            if(gmPk.idDeck1 != this.idDeck1){
                return false;
            }
            
            if(gmPk.idDeck2 != this.idDeck2){
                return false;
            }
            
            if(gmPk.dateMove != this.dateMove){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return (int) (this.idGame + this.idDeck1 + this.idDeck2 + this.dateMove.hashCode());
    }
    
    
    
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

//	public Long getDateMove(){
//		return this.dateMove;
//	}
//	public Date getDateMoveInDateFormat() {
//		Date toReturn = new Date(this.dateMove);
//		return toReturn;
//	}

//	public void setDateMove(Long dateMoveInMilliseconds) {
//		this.dateMove = dateMoveInMilliseconds;
//	}
    
//	public void setDateMove(Date dateMove) {
//		this.dateMove = dateMove.getTime();
//	}
   
}  