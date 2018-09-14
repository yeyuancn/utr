drop table if exists league;
create table league
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
 name varchar(50),
 city varchar(30),
 state char(5),
 create_time DATETIME,
 current_season_id mediumint,
  primary key(id),
  unique key(name)
) engine =myisam;

drop table if exists season;
create table season
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
 name varchar(50),
 league_id MEDIUMINT,
 create_time DATETIME,
  primary key(id),
  unique key(name, league_id)
) engine =myisam;

drop table if exists division;
create table division
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
 name varchar(20),
 season_id MEDIUMINT,
  primary key(id),
  unique key(name, season_id)
) engine =myisam;


insert into league(name, city, state, create_time, current_season_id) values (
 'UTR League', 'San Diego', 'CA', now(), 1);

insert into season(name, league_id, create_time) values ('Fall 2018', 1, now());

insert into division(name, season_id) values ('Division A', 1);
insert into division(name, season_id) values ('Division B', 1);
insert into division(name, season_id) values ('Division C', 1);
insert into division(name, season_id) values ('Division D', 1);
insert into division(name, season_id) values ('Division E', 1);
insert into division(name, season_id) values ('Division F', 1);
insert into division(name, season_id) values ('Division G', 1);
insert into division(name, season_id) values ('Division H', 1);
insert into division(name, season_id) values ('Division I', 1);

update player set division_id = 1 where id >= 1 and id < 10;
update player set division_id = 2 where id >= 10 and id < 20;
update player set division_id = 3 where id >= 20 and id < 30;
update player set division_id = 4 where id >= 30 and id < 39;
update player set division_id = 5 where id >= 39 and id < 48;
update player set division_id = 6 where id >= 48 and id < 58;
update player set division_id = 7 where id >= 58 and id < 68;
update player set division_id = 8 where id >= 68 and id < 78;
update player set division_id = 9 where id >= 78 and id < 88;

-- Views

drop view match_result_view;
drop view player_result_view;

drop table if exists match_result;
create table match_result
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
division_id mediumint,
winner_id mediumint,
loser_id mediumint,
set1_score char(5),
set2_score char(5),
set3_score char(5),
match_score char(5),
enter_by_winner boolean,
game_won smallint,
game_lost smallint,
match_date DATETIME,
record_time DATETIME,
match_memo VARCHAR(200),
primary key(id)
) engine =myisam;
alter table match_result add index(winner_id);
alter table match_result add index(loser_id);
alter table match_result add index(division_id);

drop table if exists player_result;
create table player_result
(
id MEDIUMINT NOT NULL AUTO_INCREMENT,
player_id mediumint,
division_id mediumint,
match_won int,
match_lost int,
match_won_percent decimal(5,2),
game_won int,
game_lost int,
game_won_percent decimal(5,2),
primary key(id)
) engine =myisam;
alter table player_result add index(player_id);
alter table player_result add index(division_id);



CREATE VIEW match_result_view AS SELECT m.id, m.division_id, m.winner_id, m.loser_id, m.set1_score, m.set2_score, m.set3_score,
                                   m.match_score, m.enter_by_winner, m.match_date, m.record_time, m.match_memo,
                                   u1.first_name as winner_first_name, u1.last_name as winner_last_name,
                                   u2.first_name as loser_first_name, u2.last_name as loser_last_name
                            FROM match_result m join player u1 join player u2 where m.winner_id = u1.id and m.loser_id = u2.id;

CREATE VIEW player_result_view AS SELECT p.id as player_id, p.first_name, p.last_name, pr.match_won, pr.match_lost, pr.match_won_percent,
                                    pr.game_won, pr.game_lost, pr.game_won_percent, pr.division_id
                            FROM player_result pr join player p where pr.player_id = p.id;
