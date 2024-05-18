create table refresh_tokens
(
    id bigserial primary key,
    user_id bigint references users(id) not null,
    refresh_token varchar(255) not null,
    expires_at timestamp not null
);