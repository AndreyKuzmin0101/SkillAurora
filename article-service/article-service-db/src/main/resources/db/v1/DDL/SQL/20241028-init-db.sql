create
extension if not exists "uuid-ossp";

create table articles
(
    id                UUID primary key             default uuid_generate_v4(),
    title             varchar(128) unique not null,
    content           varchar             not null,
    cover             varchar,
    description       varchar(512)        not null,
    author_id         UUID                not null,
    publication_time  timestamp           not null,
    moderation_status varchar             not null,
    views             bigint              not null default 0,
    rating            bigint              not null default 0,

    check (moderation_status in ('WAITING', 'CONFIRMED', 'REJECTED'))
);

create table comments
(
    id         UUID primary key       default uuid_generate_v4(),
    article_id UUID          not null references articles (id),
    author_id  UUID          not null,
    content    varchar(2000) not null,
    parent_id  UUID references comments (id),
    created_at timestamp     not null default now()
);

create table comment_ratings
(
    comment_id UUID not null references comments (id),
    user_id    UUID not null,
    value      bool not null,

    primary key (comment_id, user_id)
);

create table article_ratings
(
    article_id UUID not null references articles (id),
    user_id    UUID not null,
    value      int  not null,

    primary key (article_id, user_id)
);

create table tags
(
    id          bigserial primary key,
    name        varchar(64) unique not null,
    description varchar(128),
    count_usage bigint          not null default 0
);

create table article_tag
(
    article_id UUID references articles (id),
    tag_id     bigint references tags (id),

    primary key (article_id, tag_id)
);
