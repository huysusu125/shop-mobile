create table "user"
(
    id       bigserial
        constraint user_pk
            primary key,
    username varchar(36),
    password varchar(255)
);


create table items
(
    id           uuid         default gen_random_uuid(),
    type         integer,
    title        varchar(255),
    description  varchar(255),
    min_price    numeric      default 0,
    image        text,
    time_baohanh varchar(255) default '6 tháng'::character varying,
    created_at   bigint       default 0
);

create table type_items
(
    id     serial
        constraint type_items_pk
            primary key,
    name   varchar(64),
    banner text
);

create table item_description
(
    id          bigserial
        constraint item_description_pk
            primary key,
    item_id     uuid         not null,
    description varchar(255) not null
);

create table item_image
(
    id      serial
        constraint item_image_pk
            primary key,
    item_id uuid,
    image   text
);



