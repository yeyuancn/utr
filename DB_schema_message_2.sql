drop view if exists message_view;

drop table if exists message;
create table message
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
from_player_id mediumint,
to_player_id mediumint,
content varchar(2000),
message_time DATETIME,
removed_by_from boolean,
removed_by_to boolean,
primary key(id)
) engine =myisam;
alter table message add index(from_player_id);
alter table message add index(to_player_id);


CREATE VIEW message_view AS SELECT m.*, u1.first_name as from_first_name, u1.last_name as from_last_name,
                              u2.first_name as to_first_name, u2.last_name as to_last_name
                            FROM message m join player u1 join player u2 where m.from_player_id = u1.id and m.to_player_id = u2.id;


