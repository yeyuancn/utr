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

insert into division(name, season_id) values ('All', 1);
insert into division(name, season_id) values ('Division A', 1);
insert into division(name, season_id) values ('Division B', 1);
insert into division(name, season_id) values ('Division C', 1);
insert into division(name, season_id) values ('Division D', 1);
insert into division(name, season_id) values ('Division E', 1);
insert into division(name, season_id) values ('Division F', 1);
insert into division(name, season_id) values ('Division G', 1);
insert into division(name, season_id) values ('Division H', 1);
insert into division(name, season_id) values ('Division I', 1);


update player set division_id = (select id from division where season_id = 1 and name = 'Division A') where id >= 1 and id < 10;
update player set division_id = (select id from division where season_id = 1 and name = 'Division B') where id >= 10 and id < 20;
update player set division_id = (select id from division where season_id = 1 and name = 'Division C') where id >= 20 and id < 30;
update player set division_id = (select id from division where season_id = 1 and name = 'Division D') where id >= 30 and id < 39;
update player set division_id = (select id from division where season_id = 1 and name = 'Division E') where id >= 39 and id < 48;
update player set division_id = (select id from division where season_id = 1 and name = 'Division F') where id >= 48 and id < 58;
update player set division_id = (select id from division where season_id = 1 and name = 'Division G') where id >= 58 and id < 68;
update player set division_id = (select id from division where season_id = 1 and name = 'Division H') where id >= 68 and id < 78;
update player set division_id = (select id from division where season_id = 1 and name = 'Division I') where id >= 78 and id < 88;

-- Views

drop view if exists match_result_view;
drop view if exists player_result_view;

drop table if exists match_result;
create table match_result
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
division_id mediumint,
season_id mediumint,
winner_id mediumint,
loser_id mediumint,
loser_default boolean,
set1_score char(5),
set2_score char(5),
set3_score char(5),
match_score char(5),
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
alter table match_result add index(season_id);

drop table if exists player_result;
create table player_result
(
id MEDIUMINT NOT NULL AUTO_INCREMENT,
player_id mediumint,
division_id mediumint,
season_id mediumint,
match_won int,
match_lost int,
match_won_percent decimal(5,2),
game_won int,
game_lost int,
game_won_percent decimal(5,2),
primary key(id),
unique key(player_id, division_id)
) engine =myisam;
alter table player_result add index(player_id);
alter table player_result add index(division_id);
alter table player_result add index(season_id);


CREATE VIEW match_result_view AS SELECT m.id, m.division_id, m.season_id, m.winner_id, m.loser_id, m.set1_score, m.set2_score, m.set3_score,
                                   m.match_score, m.loser_default, m.match_date, m.record_time, m.match_memo,
                                   u1.first_name as winner_first_name, u1.last_name as winner_last_name,
                                   u2.first_name as loser_first_name, u2.last_name as loser_last_name
                            FROM match_result m join player u1 join player u2
                            where m.winner_id = u1.id and m.loser_id = u2.id;

CREATE VIEW player_result_view AS SELECT pr.id, pr.match_won, pr.match_lost, pr.match_won_percent,
            pr.game_won, pr.game_lost, pr.game_won_percent, pr.division_id, pr.season_id,
            p.id as player_id, p.first_name, p.last_name
            FROM player_result pr join player p where pr.player_id = p.id;

-- data
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (1, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (2, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (3, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (4, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (5, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (6, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (7, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (8, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (9, 2, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (10, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (11, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (12, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (13, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (14, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (15, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (16, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (17, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (18, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (19, 3, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (20, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (21, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (22, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (23, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (24, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (25, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (26, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (27, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (28, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (29, 4, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (30, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (31, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (32, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (33, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (34, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (35, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (36, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (37, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (38, 5, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (39, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (40, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (41, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (42, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (43, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (44, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (45, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (46, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (47, 6, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (48, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (49, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (50, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (51, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (52, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (53, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (54, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (55, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (56, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (57, 7, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (58, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (59, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (60, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (61, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (62, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (63, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (64, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (65, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (66, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (67, 8, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (68, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (69, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (70, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (71, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (72, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (73, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (74, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (75, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (76, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (77, 9, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (78, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (79, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (80, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (81, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (82, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (83, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (84, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (85, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (86, 10, 0, 0, 0.0, 0, 0, 0.0);
insert into player_result(player_id, division_id, match_won, match_lost, match_won_percent, game_won, game_lost, game_won_percent) values (87, 10, 0, 0, 0.0, 0, 0, 0.0);


UPDATE player_result pr JOIN division d ON pr.division_id = d.id SET pr.season_id = d.season_id;
