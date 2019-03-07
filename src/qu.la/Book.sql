create database Book;
create table Bookshelf(
	id int not null,
	img text,
	`name` char(255),
	author char(255),
	intro text,
	primary key (id)
);
create table wait_que(
	id int not null,
	book_id int,
	is_url int,
	url text,
	primary key (id)
);
insert into wait_que (id, book_id, is_url) value (0, 1, 0);