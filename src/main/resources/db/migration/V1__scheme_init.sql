create table if not exists managers
(
    id           serial not null primary key,
    password     varchar(255) not null,
    phone_number varchar(255) not null
);

alter table managers
    owner to postgres;

create table if not exists orders
(
    id            bigserial not null primary key,
    description   varchar(255) not null,
    ending_time   timestamp,
    latitude      double precision not null,
    longitude     double precision not null,
    radius        double precision not null,
    starting_time timestamp not null ,
    manager_id    bigint not null
        constraint fk3kb98kmk1xemhxbjomoaec280
            references managers
);

alter table orders
    owner to postgres;

create table if not exists workers
(
    id           bigserial not null primary key,
    latitude     double precision not null,
    longitude    double precision not null,
    password     varchar(255) not null,
    phone_number varchar(255) not null,
    time         timestamp,
    order_id     bigint
        constraint fkjgov8q76lg7dbadi4y922xnen
            references orders
);

alter table workers
    owner to postgres;