create table if not exists users (
    id serial not null primary key,
    username varchar(255) default null unique,
    password varchar(255) default null
);