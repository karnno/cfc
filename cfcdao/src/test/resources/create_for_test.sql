--drop table GAMEPARTICIPATIONS;
--drop table GAMEMOVES;
--drop table CARDS;
--drop table DECKS;
--drop table GAMES;
--drop table PLAYERS;

--drop sequence if exists CFC_PLAYERS_SEQ;
--drop sequence if exists CFC_GAMES_SEQ;
--drop sequence if exists CFC_DECKS_SEQ;
--drop sequence if exists CFC_CARDS_SEQ;
--drop sequence if exists CFC_GAMEPARTICIPATIONS_SEQ;


create sequence if not exists CFC_PLAYERS_SEQ start with 1 increment by 1;
create sequence if not exists CFC_GAMES_SEQ start with 1 increment by 1;
create sequence if not exists CFC_DECKS_SEQ start with 1 increment by 1;
create sequence if not exists CFC_CARDS_SEQ start with 1 increment by 1;
create sequence if not exists CFC_GAMEPARTICIPATIONS_SEQ start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS PLAYERS (
ID INT default CFC_PLAYERS_SEQ.NEXTVAL primary key,
NAME varchar(255),
TAGLINE varchar(255)
);


CREATE TABLE IF NOT EXISTS GAMES (
ID INT default CFC_GAMES_SEQ.NEXTVAL primary key,
GAME_DATE date,
GAME_STATUS varchar(25)
);

CREATE TABLE IF NOT EXISTS DECKS(
ID INT default CFC_DECKS_SEQ.NEXTVAL primary key,
NAME varchar(255),
PLAYER_ID INT,
FOREIGN KEY(PLAYER_ID) REFERENCES PLAYERS(ID)
);

CREATE TABLE IF NOT EXISTS CARDS(
ID INT default CFC_CARDS_SEQ.NEXTVAL primary key,
NAME varchar(255),
ENERGY INT,
MOTIVATION INT,
CREDIBILITY INT,
DECK_ID INT,
FOREIGN KEY(DECK_ID) REFERENCES DECKS(ID)
);

CREATE TABLE IF NOT EXISTS GAMEPARTICIPATIONS(
ID INT default CFC_GAMEPARTICIPATIONS_SEQ.NEXTVAL primary key,
DECK_ID INT,
GAME_ID INT,
PARTICIPATIONDATE date,
ENERGY INT,
MOTIVATION INT,
CREDIBILITY INT,
FOREIGN KEY(DECK_ID) REFERENCES DECKS(ID),
FOREIGN KEY(GAME_ID) REFERENCES GAMES(ID)
);

CREATE TABLE IF NOT EXISTS GAMEMOVES (
GAME_ID INT not null,
DECK1_ID INT,
DECK2_ID INT,
DATE_MOVE TIMESTAMP,
CARD_ID INT not null, 
CARD_DECK1 INT,
DECK1_ENERGY INT,
DECK1_MOTIVATION INT,
DECK1_CREDIBILITY INT,
DECK2_ENERGY INT,
DECK2_MOTIVATION INT,
DECK2_CREDIBILITY INT,
primary key (GAME_ID, DECK1_ID, DECK2_ID, DATE_MOVE),
constraint card_id_fk foreign key(CARD_ID) references CARDS(ID)
);
commit;