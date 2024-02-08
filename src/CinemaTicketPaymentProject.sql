-- create table `users`  
-- 	id int primary key auto_increment,
--     first_name varchar(50) not null,
--     last_name varchar(50) not null,
--     email varchar(50) not null, 
--     password varchar(100) not null
-- );

create table cinema (
	id int primary key auto_increment,
    variant varchar(50) not null ,
    name varchar(50) not null
);

create table cinema_layout(
	id int primary key auto_increment,
    x_index int not null,
    y_index int not null
);

create table cinema_layout_and_cinema_relationship(
	cinema_layout_id int not null,
	cinema_id int not null
);

create