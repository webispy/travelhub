DROP TABLE IF EXISTS TB_USERS;

create table TB_USERS
(
	ID int not null auto_increment primary key,
	NAME varchar(20) not null,
	EMAIL varchar(255) not null,
	PASSWD varchar(255) not null,
	PHOTO_FILE varchar(255) not null
);

DROP TABLE IF EXISTS TB_BOOKS;

create table TB_BOOKS
(
	ID int not null auto_increment primary key,
	TITLE VARCHAR(255) not null,
	SDATE_TIMESTAMP int not null,
	DURATION int not null,
	tb_users_ID int not null,

	FOREIGN KEY(tb_users_ID) REFERENCES TB_USERS(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS TB_BOOK_ITEM_MAP;

create table TB_BOOK_ITEM_MAP
(
	ID int not null auto_increment primary key,
	DAYS int not null,
	SEQ int not null,

	tb_books_ID int not null,
	tb_items_ID int not null,

	FOREIGN KEY(tb_books_ID) REFERENCES TB_BOOKS(ID) ON DELETE CASCADE,
	FOREIGN KEY(tb_items_ID) REFERENCES TB_ITEMS(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS TB_ITEMS;

create table TB_ITEMS
(
	ID int not null auto_increment primary key,
	NAME varchar(255) not null,
	LAT varchar(50) not null,
	LNG varchar(50) not null,
	DESCRIPTION text not null,

	tb_users_ID int not null,
	tb_cities_ID int not null,

	FOREIGN KEY(tb_users_ID) REFERENCES TB_USERS(ID) ON DELETE CASCADE,
	FOREIGN KEY(tb_cities_ID) REFERENCES TB_CITIES(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS TB_CITIES;

create table TB_CITIES
(
	ID int not null auto_increment primary key,
	NAME varchar(255) not null,

	tb_countries_ID int not null,

	FOREIGN KEY(tb_countries_ID) REFERENCES TB_COUNTRIES(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS TB_COUNTRIES;

create table TB_COUNTRIES
(
	ID int not null auto_increment primary key,
	NAME varchar(255) not null
);

DROP TABLE IF EXISTS TB_ITEM_PHOTO_MAP;

create table TB_ITEM_PHOTO_MAP
(
	tb_items_ID int not null,
	tb_item_photos_ID int not null,

	FOREIGN KEY(tb_items_ID) REFERENCES TB_ITEMS(ID) ON DELETE CASCADE,
	FOREIGN KEY(tb_item_photos_ID) REFERENCES TB_ITEM_PHOTOS(ID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS TB_ITEM_PHOTOS;

create table TB_ITEM_PHOTOS
(
	ID int not null auto_increment primary key,
	FILENAME varchar(255) not null
);







