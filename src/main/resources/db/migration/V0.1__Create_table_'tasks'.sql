create type status_enum as enum('TO_DO', 'DOING', 'DONE');

create table if not exists tasks (
    id serial primary key not null,
    title varchar(255) default null,
    description varchar(255) default null,
    status status_enum default 'TO_DO',
    created_at timestamp default now(),
    expires_at timestamp default null
);