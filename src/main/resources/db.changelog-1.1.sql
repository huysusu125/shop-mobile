-- liquibase formatted sql
-- changeset huytd:1.1

create table if not exists "user"
(
    id         bigserial
        constraint user_pk
            primary key,
    username   varchar(36),
    password   varchar(36),
    is_deleted boolean
);

create table if not exists items
(
    id          uuid    default gen_random_uuid(),
    type        int,
    title       varchar(255),
    description varchar(255),
    "minPrice"  decimal default 0,
    image       TEXT
);

create table if not exists type_items
(
    id   serial
        constraint type_items_pk
            primary key,
    name varchar(64)
);

alter table type_items
    add if not exists banner TEXT;

alter table items
    rename column "minPrice" to min_price;



