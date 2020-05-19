#SCRIPT DO BANCO AQUI PARA USAR NO FLYWAY
#QUALQUER SCRIPT SEGUINDO A ORDEM V001 V002 RODARÁ NO BANCO EM SEQUENCIA CONTROLADO PELO FLYWAY

create table usersystem.user_role
(
    user_id bigint not null,
    role_id bigint not null,
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references usersystem.user (id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references usersystem.role (id)
);
