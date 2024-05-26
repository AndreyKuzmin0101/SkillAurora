alter table ratings
    add constraint ratings_uniq unique (user_id, article_id);

alter table chats
    add constraint chats_uniq unique (first_user, second_user);