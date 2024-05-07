alter table articles drop column rating;

create table ratings
(
    id bigserial primary key,
    user_id bigint references users(id) not null,
    article_id bigint references articles(id) not null,
    rating_value int not null
);