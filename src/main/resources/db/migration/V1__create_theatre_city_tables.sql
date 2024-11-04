create table if not exists city
(
    id      uuid primary key,
    name    varchar(255) not null,
    state   varchar(255) not null,
    country varchar(255) not null,
    zipCode varchar(255) not null
);

create table if not exists theatre
(
    id     uuid primary key,
    name   varchar(255) not null,
    cityId uuid         not null references city (id)
);
