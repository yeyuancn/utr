create DATABASE utr;

create user 'yye'@'localhost' IDENTIFIED BY '252525';

GRANT ALL on utr.* to 'yye'@'localhost';