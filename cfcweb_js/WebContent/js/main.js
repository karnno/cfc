
	

$(
function(){
	var canvasPlayerInfoWidth = 350;
	var canvasPlayerInfoHeight= 60;
var canvasWidth=430;
var canvasHeight=200;

	var ply1InfosCanvas = document.getElementById('ply1InfosCanvas');
	var ply2InfosCanvas = document.getElementById('ply2InfosCanvas');

var ply1Canvas = document.getElementById('ply1Canvas');
var ply2Canvas = document.getElementById('ply2Canvas');

var play1InfoTextId = 'ply1_info';
var play1InfoBarsId = 'ply1_info_bars';
var cardPlay1NameId = 'card_ply1_name'; 
var play1ExtraInfoCellId = 'ply1_extra_info_cell';
var carPlay1TextEnergyId = 'card_ply1_energy';
var cardPlay1TextMotivationId = 'card_ply1_motivation';
var cardPlay1TextCredibilityId = 'card_ply1_credibility';
var play1PotentialCardOnOpponent='ply1_playPotentialCardOnOpponent';
var play1PotentialCardOnSelf='ply1_playPotentialCardOnSelf';

var play2InfoTextId = 'ply2_info';
var play2InfoBarsId = 'ply2_info_bars';
var cardPlay2NameId = 'card_ply2_name'; 
var cardPlay2TextEnergyId = 'card_ply2_energy';
var play2ExtraInfoCellId = 'ply2_extra_info_cell';
var cardPlay2TextMotivationId = 'card_ply2_motivation';
var cardPlay2TextCredibilityId = 'card_ply2_credibility';
var play2PotentialCardOnOpponent='ply2_playPotentialCardOnOpponent';
var play2PotentialCardOnSelf='ply2_playPotentialCardOnSelf';

var gameGfxCtx1_playerInfo = ply1InfosCanvas.getContext('2d');
var gameGfxCtx2_playerInfo = ply2InfosCanvas.getContext('2d');
var gameGfxCtx1 = ply1Canvas.getContext('2d');
var gameGfxCtx2 = ply2Canvas.getContext('2d');


var playInfosArea1= new PlayInfosArea(gameGfxCtx1_playerInfo, ply1, play1InfoTextId, play1InfoBarsId);
playInfosArea1.setPlayerInfo();
var playInfosArea2= new PlayInfosArea(gameGfxCtx2_playerInfo, ply2, play2InfoTextId, play2InfoBarsId);
playInfosArea2.setPlayerInfo();

var playArea1= new PlayArea(gameGfxCtx1, deck1, ply1, play1InfoTextId, play1ExtraInfoCellId, cardPlay1NameId, carPlay1TextEnergyId, cardPlay1TextMotivationId, cardPlay1TextCredibilityId, play1PotentialCardOnOpponent, play1PotentialCardOnSelf);
var playArea2= new PlayArea(gameGfxCtx2, deck2, ply2, play2InfoTextId, play2ExtraInfoCellId, cardPlay2NameId, cardPlay2TextEnergyId, cardPlay2TextMotivationId, cardPlay2TextCredibilityId, play2PotentialCardOnOpponent, play2PotentialCardOnSelf);

	ply1InfosCanvas.width  = canvasPlayerInfoWidth;
	ply1InfosCanvas.height = canvasPlayerInfoHeight;
	ply2InfosCanvas.width  = canvasPlayerInfoWidth;
	ply2InfosCanvas.height = canvasPlayerInfoHeight;
	
 ply1Canvas.width  = canvasWidth;
 ply1Canvas.height = canvasHeight;
 ply2Canvas.width  = canvasWidth;
 ply2Canvas.height = canvasHeight;


$("#ply1Canvas").click(
	function(e){
//		alert('click on canvas 1');
		var x;
		var y;
		if (e.pageX || e.pageY) { 
		  x = e.pageX;
		  y = e.pageY;
		}
		else { 
		  x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft; 
		  y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop; 
		} 
		x -= document.getElementById('ply1Canvas').offsetLeft;
		y -= document.getElementById('ply1Canvas').offsetTop;		
		//alert(x + ' et ' + y);
		
		playArea1.updateModel(x, y);
	}
);

$("#ply1_playPotentialCardOnOpponent").click(
		function (event){
			if (playArea1.getPotentialCardToPlay()){
				playInfosArea2.updateCurrentUserByCardEffects(playArea1.getPotentialCardToPlay());
				playArea1.removePotentialCardFromDeck();
				playArea1.clearPotentialCard();
			}
		}
);
$("#ply1_playPotentialCardOnSelf").click(
		function (event){
			if (playArea1.getPotentialCardToPlay()){
				playInfosArea1.updateCurrentUserByCardEffects(playArea1.getPotentialCardToPlay());
				playArea1.removePotentialCardFromDeck();
				playArea1.clearPotentialCard();
			}
		}
);		
		
$("#ply2Canvas").click(
	function(e){
		//alert('click on canvas');
		var x;
		var y;
		if (e.pageX || e.pageY) { 
		  x = e.pageX;
		  y = e.pageY;
		}
		else { 
		  x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft; 
		  y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop; 
		} 
		x -= document.getElementById('ply2Canvas').offsetLeft;
		y -= document.getElementById('ply2Canvas').offsetTop;		
		//alert(x + ' et ' + y);
		
		playArea2.updateModel(x, y);
	}
);
$("#ply2_playPotentialCardOnOpponent").click(
		function (event){
			if (playArea2.getPotentialCardToPlay()){
				playInfosArea1.updateCurrentUserByCardEffects(playArea2.getPotentialCardToPlay());
				playArea2.removePotentialCardFromDeck();
				playArea2.clearPotentialCard();
			}
		}
);
$("#ply2_playPotentialCardOnSelf").click(
		function (event){
			if (playArea2.getPotentialCardToPlay()){
				playInfosArea2.updateCurrentUserByCardEffects(playArea2.getPotentialCardToPlay());
				playArea2.removePotentialCardFromDeck();
				playArea2.clearPotentialCard();
			}
		}
);	

setInterval(
	function(){
 
		playInfosArea1.render();
		playInfosArea2.render();
		
		playArea1.render();
		playArea2.render();
		
		},
	16
	);


$("#saveGameStateButton").click(
		function (event){
//			var confirmSaving = confirm("Save the game ?");
//			
//			if (!confirmSaving){
//				return;
//			}
			
			var player1Info = playInfosArea1.getPlayerInfo();
//			console.log(player1Info);
			var player1Deck = playArea1.getDeck();
//			console.log(player1Deck);
			
			var player2Info = playInfosArea2.getPlayerInfo();
//			console.log(player2Info);
			var player2Deck = playArea2.getDeck();
//			console.log(player2Deck);
			
			// for both player , in GAMEPARTICIPATIONS table, we will save the following info:
			// - the id of the Game
			// - the deck info : id (from the deck id, we know which player)
			// - the player latest info : energy, motivation, credibility
			// - currentDate (timestamp format)
			
			// get the date in milliseconds, JSON does not have a Date format.
			var currentDate = Date.now();
			if (!Date.now) {
				currentDate = function now() {
					return new Date().getTime();
				};
			}
			
			var gameParticipationWrapperPlayer1={
					"idGame" : 1,
					"idDeck" : 1,
					"dateParticipation" : currentDate,
					"energy" : player1Info.energy, 
					"motivation" : player1Info.motivation,
					"credibility" : player1Info.credibility
			};
			
			var gameParticipationWrapperPlayer2={
					"idGame" : 1,
					"idDeck" : 1,
					"dateParticipation" : currentDate,
					"energy" : player2Info.energy, 
					"motivation" : player2Info.motivation,
					"credibility" : player2Info.credibility
			};
			
			console.log(gameParticipationWrapperPlayer1);
			console.log(gameParticipationWrapperPlayer2);
			
			
			// ajax call with each Wrapper !!
		}
	);


});