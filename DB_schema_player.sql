drop table if exists acct;
create table acct
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
 email varchar(40),
 phone varchar(15),
 password varchar(20),
 last_login DATETIME,
 login_cnt mediumint,
 reset_key mediumint,
  primary key(id),
  unique key(email)
) engine = myisam;
alter table acct add index(email);

drop table if exists player;
create table player
(id MEDIUMINT NOT NULL AUTO_INCREMENT,
acct_id mediumint,
division_id mediumint,
first_name varchar(20),
last_name varchar(20),
email varchar(40),
phone varchar(15),
utr_score float,
utr_id mediumint,
primary key(id)
) engine =myisam;
alter table player add index(acct_id);
alter table player add index(division_id);
alter table player add index(first_name, last_name);



insert into player (first_name, last_name, email, phone, utr_score) values ('Aakarsh','Vermani','aakarshver@gmail.com','18588484530', 8.54);
insert into player (first_name, last_name, email, phone, utr_score) values ('Calvin','Cheah','joseph30005@yahoo.com','18584725433', 7.85);
insert into player (first_name, last_name, email, phone, utr_score) values ('Adrien','Cousin','charleslaety@gmail.com','18585788108', 7.85);
insert into player (first_name, last_name, email, phone, utr_score) values ('Michael','Tu','michaeltu@roadrunner.com','18584876023', 7.82);
insert into player (first_name, last_name, email, phone, utr_score) values ('Nelson A.','Alapi','Nalapi@sbcglobal.net','18583826571', 7.7);
insert into player (first_name, last_name, email, phone, utr_score) values ('Fred','Soto','beachasian@aol.com','16195480516', 7.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Alexander','Do','turbo300z@yahoo.com','18582541208', 7.27);
insert into player (first_name, last_name, email, phone, utr_score) values ('Steven','Veld','Ron.veldadvisors@gmail.com','19176935768', 7.25);
insert into player (first_name, last_name, email, phone, utr_score) values ('Kushal','Pallaka','keshureddyp@gmail.com','16193844999', 7.12);
insert into player (first_name, last_name, email, phone, utr_score) values ('Tatum','Buffington','brett.buffington@gmail.com','18582293569', 6.94);
insert into player (first_name, last_name, email, phone, utr_score) values ('Ayanna','Shah','reshamshah@yahoo.com','18017125054', 6.76);
insert into player (first_name, last_name, email, phone, utr_score) values ('Micah','Soliven','daxrn@me.com','18585184934', 6.6);
insert into player (first_name, last_name, email, phone, utr_score) values ('Evan','Sider','tennissider@gmail.com','18589675888', 6.56);
insert into player (first_name, last_name, email, phone, utr_score) values ('Kevin','Chen','kevinkchen1@gmail.com','18583537195', 6.55);
insert into player (first_name, last_name, email, phone, utr_score) values ('Justin','Nguyen','easydubbgaming@gmail.com','18582156871', 6.55);
insert into player (first_name, last_name, email, phone, utr_score) values ('Tejasvi','Palakodeti','rpalakodeti@gmail.com','18583577474', 6.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Phillip','Bogle','boglephillip@gmail.com','18588606295', 6.36);
insert into player (first_name, last_name, email, phone, utr_score) values ('Andreas','Udall','aurelieprotennis@gmail.com','14133136351', 6.34);
insert into player (first_name, last_name, email, phone, utr_score) values ('Xijian','Chen','Yonghuichen@gmail.com','18584058576', 6.29);
insert into player (first_name, last_name, email, phone, utr_score) values ('Brighton','Read','Wes9read@gmail.com','18587538119', 6.27);
insert into player (first_name, last_name, email, phone, utr_score) values ('Devam','Shrivastava','devamshri@gmail.com','18583361533', 6.27);
insert into player (first_name, last_name, email, phone, utr_score) values ('William','Kleege','bruce@kleeege.com','16199931078', 6.24);
insert into player (first_name, last_name, email, phone, utr_score) values ('Blake','Bothmer','sand2seab@msn.com','17603151832', 6.23);
insert into player (first_name, last_name, email, phone, utr_score) values ('Sammy','Stoianov','ROSSISTO@OUTLOOK.COM','18586037679', 6.21);
insert into player (first_name, last_name, email, phone, utr_score) values ('Yatheesh','Unadi','vyr.unadi@gmail.com','18582528245', 6.21);
insert into player (first_name, last_name, email, phone, utr_score) values ('Edwin Ray','Hastings Iii','stepankaf@hotmail.com','18584720975', 6.09);
insert into player (first_name, last_name, email, phone, utr_score) values ('Ethan','Kuei','ckuei1@gmail.com','17606134375', 6.0);
insert into player (first_name, last_name, email, phone, utr_score) values ('Wyatt','Burns','sarahmurdough@yahoo.com','18586923765', 5.81);
insert into player (first_name, last_name, email, phone, utr_score) values ('Gaurish','Gaur','gaurishgaur3@gmail.com','18586499241', 5.73);
insert into player (first_name, last_name, email, phone, utr_score) values ('Satyam','Sharma','sman072503@gmail.com','18586102727', 5.65);
insert into player (first_name, last_name, email, phone, utr_score) values ('Daniel','Shafer','dshafer1045@gmail.com','18587223305', 5.64);
insert into player (first_name, last_name, email, phone, utr_score) values ('Sreenidhi','Surineni','sreenidhi04@gmail.com','18582635413', 5.64);
insert into player (first_name, last_name, email, phone, utr_score) values ('Brandon','Phan','Timphan4@hotmail.com','17608087152', 5.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Michael','Chan','michael@nioq.com','16176403038', 5.44);
insert into player (first_name, last_name, email, phone, utr_score) values ('Amara','Sai Kunal','manojavm@gmail.com','18586103775', 5.35);
insert into player (first_name, last_name, email, phone, utr_score) values ('Tony','Shen','jians@sbcglobal.net','15128517293', 5.27);
insert into player (first_name, last_name, email, phone, utr_score) values ('Zach','Amendolagine','cdelory@hotmail.com','16195489835', 5.12);
insert into player (first_name, last_name, email, phone, utr_score) values ('Eesh','Vij','vijrohit@gmail.com','18585255287', 5.03);
insert into player (first_name, last_name, email, phone, utr_score) values ('Kevin','Ma ','mabo.mail@gmail.com','18586038572', 5.01);
insert into player (first_name, last_name, email, phone, utr_score) values ('David','Wu','mjsd66@yahoo.com','18586634045', 4.95);
insert into player (first_name, last_name, email, phone, utr_score) values ('Billy','Chen','harrychen1@gmail.com','18585278057', 4.8);
insert into player (first_name, last_name, email, phone, utr_score) values ('Angela','Jooste','annejooste2@gmail.com','18583376684', 4.76);
insert into player (first_name, last_name, email, phone, utr_score) values ('John','Huang','boeingblackbird@gmail.com','18582043276', 4.41);
insert into player (first_name, last_name, email, phone, utr_score) values ('Adriana','Collins','valcollins1@yahoo.com','18584135517', 4.33);
insert into player (first_name, last_name, email, phone, utr_score) values ('Juliana','Hong','Kittycatrainbow72@gmail.com','18582659460', 4.26);
insert into player (first_name, last_name, email, phone, utr_score) values ('Andrew','Pu','song_pu@yahoo.com','18582316097', 4.0);
insert into player (first_name, last_name, email, phone, utr_score) values ('Aiden','Pu','songspu@gmail.com','18582316097', 3.93);
insert into player (first_name, last_name, email, phone, utr_score) values ('Bryce','Neely','mitchellneely@hotmail.com','18478458732', 3.88);
insert into player (first_name, last_name, email, phone, utr_score) values ('Albert','Mao','yma3491@gmail.com','18589007410', 3.8);
insert into player (first_name, last_name, email, phone, utr_score) values ('Saurav','Nagpal','sauravnagpal0@gmail.com','18588606559', 3.8);
insert into player (first_name, last_name, email, phone, utr_score) values ('Livia','Wang','wangchinhua@gmail.com','18587229918', 3.7);
insert into player (first_name, last_name, email, phone, utr_score) values ('Brandon','Cheah','jjusuf@hotmail.com','18584725433', 3.63);
insert into player (first_name, last_name, email, phone, utr_score) values ('Alexander','Nguyen','newemails4u@gmail.com','18585269420', 3.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Chet','Neely','mitchellneely@gmail.com','18478458732', 3.42);
insert into player (first_name, last_name, email, phone, utr_score) values ('Ryan','Liu','prcemily@gmail.com','16102178859', 3.37);
insert into player (first_name, last_name, email, phone, utr_score) values ('Arnon','Jittathai','arnon.jittathai@gmail.com','18589254499', 3.14);
insert into player (first_name, last_name, email, phone, utr_score) values ('Chloe','Luwa','doreenluwa@yahoo.com','18584058928', 3.13);
insert into player (first_name, last_name, email, phone, utr_score) values ('Ryan','Sun','bgsun@hotmail.com','18582315045', 3.08);
insert into player (first_name, last_name, email, phone, utr_score) values ('Alisa','Privorotskiy','alisadavina317@gmail.com','18582457927', 3.05);
insert into player (first_name, last_name, email, phone, utr_score) values ('Nyesha','Sinha','rajeshsinha007@gmail.com','18586993867', 3.05);
insert into player (first_name, last_name, email, phone, utr_score) values ('Emily','Cervantes','babytoesx2@gmail.com','19512265229', 3.0);
insert into player (first_name, last_name, email, phone, utr_score) values ('Andrew','Phan','timphan4@gmail.com','17608087152', 3.0);
insert into player (first_name, last_name, email, phone, utr_score) values ('Eric','Li','taolpersonal@gmail.com','18584140579', 2.99);
insert into player (first_name, last_name, email, phone, utr_score) values ('Kavya','Gupta','gura71@yahoo.com','18582456608', 2.93);
insert into player (first_name, last_name, email, phone, utr_score) values ('Ava','Elleraas','nelleraas@yahoo.com','18587756119', 2.86);
insert into player (first_name, last_name, email, phone, utr_score) values ('Vivian','Ye','Yeyuancn2@yahoo.com','16264751501', 2.78);
insert into player (first_name, last_name, email, phone, utr_score) values ('Amogh','Havanagi','shivanandh@gmail.com','18583374231', 2.76);
insert into player (first_name, last_name, email, phone, utr_score) values ('Mckay','Read','mckay.read@gmail.com','18587538119', 2.57);
insert into player (first_name, last_name, email, phone, utr_score) values ('Christopher','Weng','xyu2k@yahoo.com','18587051025', 2.48);
insert into player (first_name, last_name, email, phone, utr_score) values ('Joaquin','Anand','matthewanand@gmail.com','18586058177', 2.43);
insert into player (first_name, last_name, email, phone, utr_score) values ('Nikita','Stephan','nat.healingjourney@gmail.com','18584319273', 2.41);
insert into player (first_name, last_name, email, phone, utr_score) values ('Noelle','Dang','ddang@tanchau.com','19788079160', 2.11);
insert into player (first_name, last_name, email, phone, utr_score) values ('Alina','Stephan','bondarenkon@yahoo.com','18584319273', 1.94);
insert into player (first_name, last_name, email, phone, utr_score) values ('Gaurav','Gupta','sudhendra.kg@gmail.com','18583426149', 1.9);
insert into player (first_name, last_name, email, phone, utr_score) values ('Niyathi','Jagan','bharathis.jagan@gmail.com','17602087234', 1.82);
insert into player (first_name, last_name, email, phone, utr_score) values ('Anna','Cao','fcao_99@yahoo.com','12623276081', 1.74);
insert into player (first_name, last_name, email, phone, utr_score) values ('Nina','Del-Zio','johndelzio@allstate.com','17608466459', 1.66);
insert into player (first_name, last_name, email, phone, utr_score) values ('Shryuk','Grandhi','lakshmigrandhi10@gmail.com','18583547620', 1.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Shreya','Nagesh','shreya.nagesh16@gmail.com','16032035965', 1.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Sri Vidya','Ujjini','sri.ujjini@gmail.com','18583449696', 1.5);
insert into player (first_name, last_name, email, phone, utr_score) values ('Brundasree','Unadi','Umadevi.unadi@yahoo.com','18582528245', 1.29);
insert into player (first_name, last_name, email, phone, utr_score) values ('Gus','Lundquist','laiwing8@gmail.com','18583490528', 1.24);
insert into player (first_name, last_name, email, phone, utr_score) values ('Aditi','Vangala','vivangala@gmail.com','16107454067', 1.12);
insert into player (first_name, last_name, email, phone, utr_score) values ('Paaramita','Asuri','bhushan.asuri@gmail.com','19496774939', 1.05);
insert into player (first_name, last_name, email, phone, utr_score) values ('Gautam','Gupta','deeshigonu@gmail.com','16197217208', 1.04);
insert into player (first_name, last_name, email, phone, utr_score) values ('Krish','Mandadi','krish.r.mandadi@gmail.com','18582452296', 1.0);
insert into player (first_name, last_name, email, phone, utr_score) values ('Sanjai','Subramanian','sanjai.awesome112358@gmail.com','18589517309', 1.0);
