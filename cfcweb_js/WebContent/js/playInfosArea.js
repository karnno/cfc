var LINE_WIDTH=10;

var MAX_BAR_WIDTH=300;

var MAX_PLAYER_INFO=20;
var MIN_PLAYER_INFO=0;

var ENERGY_Y1 = 10;
var MOTIVATION_Y1= 25;
var CREDIBILITY_Y1= 40;

var COLOR_ENERGY = '#00FF00';
var COLOR_MOTIVATION = '#FF9100';
var COLOR_CREDIBILITY = '#00BFFF';


function PlayInfosArea(gameGfxCtx_playerInfo, playerInfo, playerInfoTextId, playInfoBarsId) {

	this.gameGfxCtx_playerInfo=gameGfxCtx_playerInfo;

	this.playerInfo = playerInfo;
	
	this.playerInfoTextId = playerInfoTextId;

	this.playerInfoBarsId = playInfoBarsId;

}

/*
 * Render three bars
 */
PlayInfosArea.prototype.render= function(){
	
	this.gameGfxCtx_playerInfo.clearRect(0,0, 390, 100);

	this.gameGfxCtx_playerInfo.beginPath();
	this.gameGfxCtx_playerInfo.lineWidth=10;
	
	// DRAW THE ENERGY BAR
	var lengthEnergyBar = (MAX_BAR_WIDTH * this.playerInfo.energy)/MAX_PLAYER_INFO;
	this.gameGfxCtx_playerInfo.strokeStyle = COLOR_ENERGY;
	this.gameGfxCtx_playerInfo.moveTo(5, ENERGY_Y1);
	this.gameGfxCtx_playerInfo.lineTo(5+ lengthEnergyBar,ENERGY_Y1);
	this.gameGfxCtx_playerInfo.stroke();
	
	// DRAW THE MOTIVATION BAR
	var lengthMotivationBar = (MAX_BAR_WIDTH * this.playerInfo.motivation)/MAX_PLAYER_INFO;
	this.gameGfxCtx_playerInfo.beginPath();
	this.gameGfxCtx_playerInfo.strokeStyle = COLOR_MOTIVATION;
	this.gameGfxCtx_playerInfo.moveTo(5, MOTIVATION_Y1);
	this.gameGfxCtx_playerInfo.lineTo(5+ lengthMotivationBar,MOTIVATION_Y1);
	this.gameGfxCtx_playerInfo.stroke();
	
	// DRAW THE CREDIBILITY BAR
	var lengthCredibilityBar = (MAX_BAR_WIDTH * this.playerInfo.credibility)/MAX_PLAYER_INFO;
	this.gameGfxCtx_playerInfo.beginPath();
	this.gameGfxCtx_playerInfo.strokeStyle = COLOR_CREDIBILITY;
	this.gameGfxCtx_playerInfo.moveTo(5, CREDIBILITY_Y1);
	this.gameGfxCtx_playerInfo.lineTo(5+ lengthCredibilityBar,CREDIBILITY_Y1);
	this.gameGfxCtx_playerInfo.stroke();
	

};

/*
 * Modifies user info after card effect
 */
PlayInfosArea.prototype.updateCurrentUserByCardEffects= function(card){
	//alert('Play ' + card.name + ' !');
	
	this.playerInfo.energy += card.energy;
	this.playerInfo.motivation += card.motivation;
	this.playerInfo.credibility += card.credibility;
	
	if (this.playerInfo.energy>MAX_PLAYER_INFO){
		this.playerInfo.energy = MAX_PLAYER_INFO;
	}
	if (this.playerInfo.energy<0){
		this.playerInfo.energy = MIN_PLAYER_INFO;
	}
	
	if (this.playerInfo.motivation>MAX_PLAYER_INFO){
		this.playerInfo.motivation = MAX_PLAYER_INFO;
	}
	if (this.playerInfo.motivation<0){
		this.playerInfo.motivation = MIN_PLAYER_INFO;
	}
	
	if (this.playerInfo.credibility>MAX_PLAYER_INFO){
		this.playerInfo.credibility = MAX_PLAYER_INFO;
	}
	if (this.playerInfo.credibility<0){
		this.playerInfo.credibility = MIN_PLAYER_INFO;
	}
	this.setPlayerInfo();
};

/*
 * Display the user info in json string value
 */
PlayInfosArea.prototype.setPlayerInfo= function(){
	var playerName = this.playerInfo.name;
	$('#' +this.playerInfoTextId).text(playerName);

	var playerBars = this.playerInfo.energy + '<br/>' + this.playerInfo.motivation + '<br/>' + this.playerInfo.credibility;
	$('#' +this.playerInfoBarsId).html(playerBars);
 
};

PlayInfosArea.prototype.getPlayerInfo = function(){
	return this.playerInfo;
}
