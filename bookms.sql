create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(255) null,
    password varchar(255) null,
    phone    varchar(255) null,
    email    varchar(255) null,
    power    int          null
);


