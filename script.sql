create sequence questions_seq
    increment by 50;

alter sequence questions_seq owner to postgres;

create sequence answers_seq
    increment by 50;

alter sequence answers_seq owner to postgres;

create table person
(
    cnp  varchar(255) not null
        primary key,
    name varchar(255)
);

alter table person
    owner to postgres;

create table users
(
    cnp      bigint not null
        primary key,
    email    varchar(255),
    password varchar(255),
    role     varchar(255),
    username varchar(255)
);

alter table users
    owner to postgres;

create table questions
(
    id            bigint not null
        primary key,
    creation_date timestamp(6),
    question      varchar(255),
    title         varchar(255),
    author_id     bigint
        constraint fkii8pqtr2qjv47ht06bg8vtl9n
            references users
);

alter table questions
    owner to postgres;

create table answers
(
    id            bigint not null
        primary key,
    answer        varchar(255),
    creation_date timestamp(6),
    author_id     bigint
        constraint fkaih8gs2e2ii62666wr3b91ulf
            references users,
    question_id   bigint
        constraint fk3erw1a3t0r78st8ty27x6v3g1
            references questions
);

alter table answers
    owner to postgres;


