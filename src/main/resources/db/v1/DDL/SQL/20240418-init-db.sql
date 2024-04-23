create table users
(
    id bigserial primary key,
    username varchar(64) unique not null,
    real_name varchar(128),
    age int check (age > 0 and age < 120),
    email varchar(128) unique not null,
    country varchar(128),
    city varchar(128),
    password varchar(64) not null,
    register_date date not null,
    rating bigint not null
);

create table articles
(
    id bigserial primary key,
    title varchar(128) unique not null,
    content varchar not null,
    author_id bigint references users(id) not null,
    publication_date date not null,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected')) not null
);

create table questions
(
    id bigserial primary key,
    author_id bigint references users(id) not null,
    title varchar(128) not null,
    content varchar,
    created_date date not null
);

create table answers
(
    id bigserial primary key,
    author_id bigint references users(id) not null,
    question_id bigint references questions(id) not null,
    content varchar not null,
    created_date date not null
);

create table tags
(
    id serial primary key,
    name varchar(64) unique not null,
    description varchar,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected')) not null
);

create table user_tag
(
    user_id bigint references users(id) not null,
    tag_id int references tags(id) not null,
    primary key (user_id, tag_id)
);

create table article_tag
(
    article_id bigint references articles(id) not null,
    tag_id int references tags(id) not null,
    primary key (article_id, tag_id)
);

create table question_tag
(
    question_id bigint references questions(id) not null,
    tag_id int references tags(id) not null,
    primary key (question_id, tag_id)
);

create table resumes
(
    id bigint primary key,
    user_id bigint references users(id) not null,
    content varchar not null,
    created_date date not null,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected')) not null
);

create table vacancies
(
    id bigint primary key,
    employer_id bigint references users(id) not null,
    content varchar not null,
    created_date date not null,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected')) not null
);

create table vacancy_tag
(
    vacancy_id bigint references vacancies(id) not null,
    tag_id int references tags(id) not null,
    primary key (vacancy_id, tag_id)
);

create table roles
(
    id serial primary key,
    name varchar(32) unique not null
);

create table user_role
(
    user_id bigint references users(id) not null,
    role_id int references roles(id) not null,

    primary key (user_id, role_id)
);
