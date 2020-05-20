#SCRIPT DO BANCO AQUI PARA USAR NO FLYWAY
#QUALQUER SCRIPT SEGUINDO A ORDEM V001 V002 RODARÁ NO BANCO EM SEQUENCIA CONTROLADO PELO FLYWAY

create table usersystem.user
(
    id       bigint auto_increment
        primary key,
    login    varchar(255) null,
    password varchar(255) null
);

