alter table questions
    add column status varchar not null default 'OPEN';

alter table questions
    add constraint questions_status_check check (status IN ('OPEN', 'CLOSED', 'RESOLVED'));

alter table questions
    add column views bigint not null default 0;

alter table answers
    add column best_answer bool not null default false;