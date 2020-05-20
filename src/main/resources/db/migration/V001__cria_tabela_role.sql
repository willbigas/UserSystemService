#SCRIPT DO BANCO AQUI PARA USAR NO FLYWAY
#QUALQUER SCRIPT SEGUINDO A ORDEM V001 V002 RODARÁ NO BANCO EM SEQUENCIA CONTROLADO PELO FLYWAY

create table usersystem.role
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);
