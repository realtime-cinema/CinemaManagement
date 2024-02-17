create
database cinema_management;
use
cinema_management;
create table users
(
    id         binary(16) primary key,
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    email      varchar(50)  not null,
    password   varchar(100) not null,
    role       varchar(50)  not null
);

create table cinema
(
    id      binary(16) primary key,
    variant varchar(50) not null,
    name    varchar(50) not null
);

create table cinema_layout
(
    id      binary(16) primary key,
    x_index int not null,
    y_index int not null
);

create table cinema_room
(
    id               binary(16) key,
    cinema_id        binary(16) not null,
    cinema_layout_id binary(16) not null,
    name             int not null,
    foreign key (cinema_id) references cinema (id),
    foreign key (cinema_layout_id) references cinema_layout (id)
);



create table cinema_layout_and_cinema_relationship
(
    cinema_layout_id binary(16) not null,
    cinema_id        binary(16) not null,
    primary key (cinema_layout_id, cinema_id),
    foreign key (cinema_layout_id) references cinema_layout (id),
    foreign key (cinema_id) references cinema (id)
);


create table cinema_manager
(
    cinema_id binary(16) not null,
    user_id   binary(16) not null,
    primary key (cinema_id, user_id),
    foreign key (cinema_id) references cinema (id),
    foreign key (user_id) references users (id)
);

create table tag
(
    id  binary(16) primary key,
    tag varchar(100) not null
);

create table film
(
    id           binary(16) primary key,
    title        varchar(255) not null,
    director     varchar(50),
    release_date timestamp,
    country      varchar(50),
    restrict_age integer
);

create table film_tag_relationship
(
    film_id binary(16) not null,
    tag_id  binary(16) not null,
    primary key (film_id, tag_id),
    foreign key (film_id) references tag (id),
    foreign key (tag_id) references film (id)
);

create table comment
(
    id      binary(16) primary key,
    user_id binary(16) not null,
    dest_id binary(16),
    foreign key (user_id) references users (id),
    body    varchar(250) not null
);

create table view_type
(
    id        binary(16) primary key,
    view_type varchar(10) not null
);

create table translate_type
(
    id             binary(16) primary key,
    translate_type varchar(50) not null
);

create table perform
(
    id                binary(16) not null primary key,
    film_id           binary(16) not null,
    view_type_id      binary(16) not null,
    translate_type_id binary(16) not null,
    dest_id           binary(16) not null,
    start_time        timestamp not null,
    end_time          timestamp not null,
    foreign key (film_id) references film (id),
    foreign key (view_type_id) references view_type (id),
    foreign key (translate_type_id) references translate_type (id),
    foreign key (dest_id) references cinema_room (id)
);

insert into cinema (variant, name)
values ("Ha Noi", "CGV"),
       ("Phu Tho", "StarLight")