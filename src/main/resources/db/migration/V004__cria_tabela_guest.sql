create table usersystem.guest
(
    id      bigint auto_increment
        primary key,
    email   varchar(255) null,
    name    varchar(255) null,
    phone   varchar(255) null,
    user_id bigint       null,
    constraint FKake2867xxr6o753o6kqc4rott
        foreign key (user_id) references usersystem.user (id)
);

