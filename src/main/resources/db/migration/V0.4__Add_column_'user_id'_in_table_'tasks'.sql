alter table
    tasks
add
    user_id bigint not null;

alter table
    tasks
add
    constraint fk_tasks_user_id foreign key (user_id) references users (id);