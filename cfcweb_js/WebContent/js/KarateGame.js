"use strict";

var KARATE_WAIT="KARATE_WAIT";
var KARATE_PUNCH="KARATE_PUNCH";

function KarateGame() {
this.karateWait = new Image();
this.karateWait.src='pics/karatestand.png';
this.karatePunch = new Image();
this.karatePunch.src='pics/karatepunch.png';
	
	this.currentState=KARATE_WAIT;
	
	this.timer =0;
}

KarateGame.prototype.updateModel= function(){

		//in punch mode, come back to wait mode after a while
	if(this.currentState===KARATE_PUNCH){
		this.timer++;
		if( this.timer>120){
			this.timer=0;
			this.currentState=KARATE_WAIT;
		}
	}
	
	
	
}


KarateGame.prototype.pushInput= function( key ){
	//on space we punch
	switch(key) {
	// case 38 : 
	// //case 122 : case 119 : case 90 : case 87 : 
	// // Flèche haut, z, w, Z, W
		// this.ply.bufferedCommand=Direction.UP;
		// break;
	// case 40 : 
	// //case 115 : case 83 : // Flèche bas, s, S
		// this.ply.bufferedCommand=Direction.DOWN;
		// break;
	// case 37 : 
	// //case 113 : case 97 : case 81 : case 65 : // Flèche gauche, q, a, Q, A
		// this.ply.bufferedCommand=Direction.LEFT;
		// break;
	// case 39 : 
	// //case 100 : case 68 : 
	// // Flèche droite, d, D
		// this.ply.bufferedCommand=Direction.RIGHT;
		// break;
	case 32: //space, poser marteau
		this.currentState=KARATE_PUNCH;
		break;
	default : 
		//alert(key);
		// Si la touche ne nous sert pas, nous n'avons aucune raison de bloquer son comportement normal.
		return true;
	}
	
		
	//PB if ply not alive, key consumed anyway?
	//key consumed
	return false;
		

}


//TODO to be moved in renderer to abstract from scaling ( complex to do cross browser )
KarateGame.prototype.render= function( gfxCtx ){
	if(this.currentState===KARATE_WAIT){
		gfxCtx.drawImage(this.karateWait,0,0);
	
	}else 	if(this.currentState===KARATE_PUNCH){
		gfxCtx.drawImage(this.karatePunch,0,0);
	}
		
}
