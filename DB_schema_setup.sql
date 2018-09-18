create DATABASE utr;

create user 'yye'@'localhost' IDENTIFIED BY '252525';

SELECT * FROM mysql.user;

GRANT ALL on utr.* to 'yye'@'%';

select * from mysql.user;
drop user 'utr_yye'@'localhost';