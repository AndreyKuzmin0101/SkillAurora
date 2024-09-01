create
    extension if not exists "uuid-ossp";

create table country
(
    id   smallserial primary key,
    code varchar(2) unique   not null,
    name varchar(128) unique not null,

    constraint country_code_check check (length(code) = 2)
);

comment
    on table country is 'Словарь стран';

create table profile_info
(
    id              uuid primary key   not null,
    username        varchar(32) unique not null,
    real_name       varchar(32),
    about_me        varchar(2000),
    profile_picture varchar(256),
    country_id      smallint references country (id),
    rating          bigint             not null default 0,
    register_date   date               not null default now(),
    account_status  varchar            not null default 'ACTIVE',

    constraint profile_info_username_check check (length(username) > 2 and length(username) < 33),
    constraint profile_info_account_status_check check (account_status in ('ACTIVE', 'DELETED'))
);

comment
    on table profile_info is 'Таблица данных профиля пользователя';

create table skill
(
    user_id uuid references profile_info (id) not null,
    tag_id  bigint                            not null,

    constraint skill_uniq unique (user_id, tag_id)
);

comment
    on table skill is 'Таблица навыков пользователя';