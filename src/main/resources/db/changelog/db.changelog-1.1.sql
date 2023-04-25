-- liquibase formatted sql
-- changeset huytd:1.1

create table "user"
(
    id         bigserial
        constraint user_pk
            primary key,
    username   varchar(36),
    password   varchar(36),
    is_deleted boolean
);