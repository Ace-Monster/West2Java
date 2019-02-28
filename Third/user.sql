create database Users;
use Users;
create table user(
	id int not NULL,
    username varchar(255),
    `password` varchar(255),
    `name` varchar(255),
    gender varchar(255),
    phone int,
	primary key (id)
);
insert into user (id,username,`name`,`password`) values (0,'root', 'Administrator', 'root');
select * from user;
delete from user where id in(
	select temp.id from (
		select id from user where `name`='Administrator'
	) as temp
);
select * from user;
truncate table user;
drop database Users;
