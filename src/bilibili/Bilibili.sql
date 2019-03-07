create database Bilibili;
create table VideoSet(
	aid int not null,
	title char(255),
	img text,
	pubdate long,
	intro text,
	`view` int,
	danmaku int,
	reply int,
	`like` int,
	favorite int,
	coin int,
	`share` int,
	`uid` int,
	`name` char(255),
	primary key (aid)
);
create table PageSet(
	id int not null,
	aid int,
	page int,
	cid int,
	primary key (id)
);
