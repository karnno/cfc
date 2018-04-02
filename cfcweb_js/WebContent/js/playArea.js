var MAX_DISPLAYABLE_CARDS = 3;
var CARD_WIDTH = 80;
var CARD_HEIGHT = 110;
var CARD_HORIZONTAL_GAP = 15;

var DECK_X1 = 10;
var DECK_Y1 = 10;
var DECK_X2 = DECK_X1 + CARD_WIDTH;
var DECK_Y2 = DECK_Y1 + CARD_HEIGHT; 

function PlayArea(gameGfxCtx, deck, playerInfo, playerInfoTextId, playExtraInfoCellId, playCardNameId, playTextEnergyId, playTextMotivationId, playTextCredibilityId, playPotentialCardOnOpponent, playPotentialCardOnSelf) {
	
	this.gameGfxCtx=gameGfxCtx;
	this.deck = deck;

	this.playerInfo = playerInfo;
	
	this.playerInfoTextId = playerInfoTextId;
	this.playerExtraInfoCellId = playExtraInfoCellId; 
	this.playCardNameId = playCardNameId;
	this.playTextEnergyId = playTextEnergyId; 
	this.playTextMotivationId = playTextMotivationId;
	this.playTextCredibilityId = playTextCredibilityId;
	this.playPotentialCardOnOpponentId = playPotentialCardOnOpponent;
	this.playPotentialCardOnSelfId = playPotentialCardOnSelf;
	
	
	this.cardsParserIndex = 0 ;
	
	this.potentialCardToPlay = undefined;
	var zis=this;
	
	// by default, one object displayed, the deck !
	this.displayable=[
		{
			x1 : DECK_X1,
			y1 : DECK_Y1,
			x2 : DECK_X2,
			y2 : DECK_Y2, 
			name:"deck",
			isCard:false,
			indexOfCardOnScreen : -1,
			render:function(ctx){
					ctx.beginPath();
					ctx.lineWidth="6";
					ctx.strokeStyle="red";
					ctx.rect(10,10,70,110);
					ctx.stroke();
			}, 
			updateModel:function(x, y){
				if (this.x1<=x && x <=this.x2  && this.y1<=y && y <=this.y2){
					//alert('click in deck');	
					zis.getOneCard();
					zis.clearPotentialCard();
				}
			}
			
		}
	];

	
	}

/*
 * Render every display-able objects
 */
PlayArea.prototype.render= function(){
	this.gameGfxCtx.clearRect(0,0,400,200);
	
	for (var i = 0 ; i < this.displayable.length ; i++){
		this.displayable[i].render(this.gameGfxCtx);
	}
};


/*
 * Propagate the click onto the displayable objects
 */
PlayArea.prototype.updateModel= function(x,y){
	for (var i = 0 ; i < this.displayable.length ; i++){
		this.displayable[i].updateModel(x,y);
	}
};


/*
 * Clear the cards area (3 cards width) before printing a new one
 *
 */
PlayArea.prototype.clearTheDisplayedCards= function(){
//	this.consoleLog(DECK_X2 + CARD_WIDTH +' , ' + DECK_Y1+' , ' +3*(CARD_WIDTH+CARD_HORIZONTAL_GAP)+' , ' + DECK_Y2)
	this.gameGfxCtx.clearRect(DECK_X2 + CARD_WIDTH,DECK_Y1,3*(CARD_WIDTH+CARD_HORIZONTAL_GAP), DECK_Y2);
	this.consoleLog('Zone for cards cleared !');
};


PlayArea.prototype.consoleLog= function(message){
	console.log(message);
};


/**
 * Extract last card from this.deck and call the rendering
 */
PlayArea.prototype.getOneCard= function(){

	if (this.deck.length == 0 ){
		//nothing to print
		return;
	}
	var displayedCardsNumber = 0;
	var arrivedAtLastCard = false;

	for (var i = 0 ; i < this.displayable.length ; i++){
		if (this.displayable[i].isCard){
			displayedCardsNumber ++;
		}
	}
	
	if (displayedCardsNumber != 0){
		this.cardsParserIndex++;
	}
	
	if (this.cardsParserIndex==this.deck.length){
		//no more card to show
		//  >> go back to first 
		this.cardsParserIndex = 0;
		arrivedAtLastCard=true;
	}
	
	
	var displayableCardsOnview = displayedCardsNumber % MAX_DISPLAYABLE_CARDS;
	this.consoleLog(displayableCardsOnview);
	if (arrivedAtLastCard  || (displayedCardsNumber!=0 && displayableCardsOnview == 0) ){
		//have to clean the area with displayed cards, and print one card
		this.clearTheDisplayedCards();
		this.removeDisplayedCardsFromDisplayableElements();
		displayableCardsOnview = arrivedAtLastCard?0:displayableCardsOnview;
	}
	
	var lastCard = this.deck[this.cardsParserIndex];

	this.consoleLog(this.cardsParserIndex + ': card [' + lastCard.name +']' + ' at ' + displayableCardsOnview);
	this.renderOneCard(lastCard, displayableCardsOnview );
};

/**
 *  
 * create a object to be inserted in the list of displayed objects
 * --> this.displayable
 *  
 */
PlayArea.prototype.renderOneCard= function(oneCard, alreadyDisplayedCardsNumber){
	var toDisplayX1 = DECK_X2 + alreadyDisplayedCardsNumber*(CARD_WIDTH + CARD_HORIZONTAL_GAP);
	var indexOnScreen = alreadyDisplayedCardsNumber + 1;
	var zis = this;
	var displaybleObject = {
		x1 : toDisplayX1,
		y1 : DECK_Y1,
		x2 : toDisplayX1 + CARD_WIDTH,
		y2 : DECK_Y1 + CARD_HEIGHT, 

		card : oneCard,
		
		isCard:true,
		indexOfCardOnScreen : indexOnScreen, 
		render:function(ctx){
			
				if (this.card.picture){
					//zis.consoleLog('> picture ? : ' + this.card.picture);
					var cardPicture = new Image();
					cardPicture.src = this.card.picture;
					zis.gameGfxCtx.drawImage(cardPicture,this.x1,this.y1, CARD_WIDTH, CARD_HEIGHT);
				}else{
				
					ctx.beginPath();
					ctx.lineWidth="3";
					ctx.strokeStyle="green";
					ctx.rect(this.x1,this.y1,this.x2-this.x1,this.y2-this.y1);
					ctx.stroke();
					ctx.fillText(this.card.name, this.x1 + 5, this.y1 +10+ (10* alreadyDisplayedCardsNumber));
				}
		}, 
		updateModel:function(x, y){
			if (this.x1<=x && x <=this.x2  && this.y1<=y && y <=this.y2){
				zis.clickedOnACard(this.card);
				zis.setPotentialCardToPlay(this.card);
			}
		}
	};
	
	// to render the card, push it into the objects to be displayed table !
	this.displayable.push(displaybleObject);
};


PlayArea.prototype.removeDisplayedCardsFromDisplayableElements= function(){
	var lastIndex = this.displayable.length-1;
	
	for (var i = lastIndex ; i!=0 ; i--){
		if (this.displayable[i].isCard){
			this.displayable.remove(i);
		}
	}
};

PlayArea.prototype.clickedOnACard= function(card){
	$('#' +this.playCardNameId).text(card.name);
	$('#' +this.playTextEnergyId).text(card.energy);
	$('#' +this.playTextMotivationId).text(card.motivation);
	$('#' +this.playTextCredibilityId).text(card.credibility);
	$('#' +this.playPotentialCardOnOpponentId).html('Play Card :&nbsp;&nbsp; <b>ON OPPONENT</B>');
	$('#' +this.playPotentialCardOnSelfId).html('&nbsp;&nbsp;/&nbsp;&nbsp;<b>ON SELF</B>');
};


PlayArea.prototype.setPotentialCardToPlay= function(card){
	this.potentialCardToPlay = card;
};

PlayArea.prototype.getPotentialCardToPlay= function(){
	return this.potentialCardToPlay;
};

PlayArea.prototype.clearPotentialCard= function(){
	this.potentialCardToPlay=undefined;
	$('#' +this.playCardNameId).html('');
	$('#' +this.playTextEnergyId).text('');
	$('#' +this.playTextMotivationId).text('');
	$('#' +this.playTextCredibilityId).text('');
	$('#' +this.playPotentialCardOnOpponentId).html('');
	$('#' +this.playPotentialCardOnSelfId).html('');
};

/**
 * After the card is played, removed it from the deck
 */
PlayArea.prototype.removePotentialCardFromDeck = function () {
	var potentialCardName = this.potentialCardToPlay.name;
	this.consoleLog('Remove this card : ' + potentialCardName + ' position on screen : ' + this.potentialCardToPlay.indexOfCardOnScreen);
	this.consoleLog('before  : ' + this.deck.length + ' / ' + this.displayable.length);
	var indexCardToRemove = -1;
	
	//remove from deck
	for (var i=0 ; i<this.deck.length ; i++){
		//not using ids for now :^) 
		if (this.deck[i].name == potentialCardName){
			indexCardToRemove = i;
			break;
		}
	}
	
	if (indexCardToRemove != -1){
		this.deck.splice(indexCardToRemove, 1);
	}
	
	//remove from displayable objects (and from screen)
	var indexDispToRemove = -1;
	
	for (var i=0 ; i<this.displayable.length ; i++){
		if (this.displayable[i].isCard && this.displayable[i].card.name == potentialCardName){
			indexDispToRemove = i;
			break;
		}
	}
	
	if (indexDispToRemove == -1){
		return;
	}
	var previousX1 = [];
	previousX1[0] = this.displayable[indexDispToRemove].x1;
	var previousX2= []; 
	previousX2[0] = this.displayable[indexDispToRemove].x2;
	
	this.displayable.splice(indexDispToRemove, 1);
	this.consoleLog('after   : ' + this.deck.length + ' / ' + this.displayable.length);
	
	//TODO modify the index of other cards 
	for (var j = indexDispToRemove ; j <this.displayable.length;j++){
		if (this.displayable[j].isCard){
			//if a card is displayed after the removed one
			// set its coordinates to removed card's coordinates
			this.replaceCoordinates (this.displayable[j], previousX1, previousX2);
		}
	}
	
	
	if (this.deck.length == 0){
		$('#' +this.playerExtraInfoCellId).html('No more cards !');
		
	}
	
};

PlayArea.prototype.replaceCoordinates = function (displayableObject, previousX1, previousX2) {
	var tempX1=displayableObject.x1, tempX2 = displayableObject.x2;
	
	displayableObject.x1 = previousX1[0]; 
	displayableObject.x2 = previousX2[0];
	
	previousX1[0] = tempX1;
	previousX2[0] = tempX2;
};

PlayArea.prototype.getDeck = function(){
	return this.deck;
}
