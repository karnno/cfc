
--insert indo PLAYERS 2 players;
delete from PLAYERS;
insert into PLAYERS(ID, NAME, PASSWORD, TAGLINE) values(CFC_PLAYERS_SEQ.nextval, 'Karnno', 'karnno', 'the first player');
insert into PLAYERS(ID, NAME, PASSWORD, TAGLINE) values(CFC_PLAYERS_SEQ.nextval, 'Manuel', 'manuel', 'the second player');



--insert into DECK 2 decks
delete from DECKS;
insert into DECKS(ID, NAME, PLAYER_ID) values(CFC_DECKS_SEQ.nextval, 'DeckPremier', 1);
insert into DECKS(ID, NAME, PLAYER_ID) values(CFC_DECKS_SEQ.nextval, 'DeckSecond', 2);


--insert into card CARDS FOR DECK_id=1
delete from CARDS;

insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Admin Attack', 'ATTACK', -6, -4, -10, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Sneak Attack', 'ATTACK', -3, -4, -5, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Panicked Wife', 'ATTACK', -3, -4, -5, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Resignation Letter', 'ATTACK', -6, -5, -5, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Angry Developpers pack', 'ATTACK', -2, -6, -2, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Smoke Break', 'HEAL', 4, 4, 0, 1);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Focus Mode', 'DEFENSE', 3, 5, 3, 1);

insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Gay open space manager', 'ATTACK', -2, -5, -4, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Skeith shoulder press', 'ATTACK', -3, -4, -5, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Kitkat Break', 'HEAL', 5, 3, 0, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Pron website accidental visit', 'HEAL', 3, 5, -5, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Hysterical on phone', 'ATTACK', -5, -6, 3, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Instant messaging', 'HEAL', 2, -3, -3, 2);
insert into CARDS(ID, NAME, CARDTYPE, ENERGY, MOTIVATION, CREDIBILITY, DECK_ID) values (CFC_CARDS_SEQ.nextval, 'Crystalisation', 'DEFENSE', -4, -4, 0, 2);





commit;