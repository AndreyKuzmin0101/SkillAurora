create table users
(
    id bigserial primary key,
    name varchar(32),
    email varchar(128) unique,
    password varchar(64),
    register_date date,
    rating bigint
);

create table articles
(
    id bigserial primary key,
    title varchar(128),
    content varchar,
    author_id bigint references users(id),
    publication_date date,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected'))
);

create table questions
(
    id bigserial primary key,
    author_id bigint references users(id),
    title varchar(128),
    content varchar,
    created_date date
);

create table answers
(
    id bigserial primary key,
    author_id bigint references users(id),
    question_id bigint references questions(id),
    content varchar,
    created_date date
);

create table tags
(
    id serial primary key,
    name varchar(64),
    description varchar,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected'))
);

create table user_tag
(
    user_id bigint references users(id),
    tag_id int references tags(id),
    primary key (user_id, tag_id)
);

create table article_tag
(
    article_id bigint references articles(id),
    tag_id int references tags(id),
    primary key (article_id, tag_id)
);

create table question_tag
(
    question_id bigint references questions(id),
    tag_id int references tags(id),
    primary key (question_id, tag_id)
);

create table resumes
(
    id bigint primary key,
    user_id bigint references users(id),
    content varchar,
    created_date date,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected'))
);

create table vacancies
(
    id bigint primary key,
    employer_id bigint references users(id),
    content varchar,
    created_date date,
    moderation_status varchar check (moderation_status in ('waiting', 'confirmed', 'rejected'))
);

create table vacancy_tag
(
    vacancy_id bigint references vacancies(id),
    tag_id int references tags(id),
    primary key (vacancy_id, tag_id)
);