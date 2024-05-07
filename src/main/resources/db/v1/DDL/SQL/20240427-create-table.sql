create table chats
(
    id bigserial primary key,
    first_user bigint references users(id) not null ,
    second_user bigint references users(id) not null
);

create table messages
(
    id bigserial primary key,
    sender_id bigint references users(id) not null,
    chat_id bigint references chats(id) not null,
    content text not null,
    send_time timestamp not null
);