insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_AUTHOR'),
       ('ROLE_EMPLOYER'),
       ('ROLE_MODER'),
       ('ROLE_ADMIN');

insert into tags (name, description, is_custom)
values ('Java', 'Язык программирования', false),
       ('C++', 'Язык программирования', false),
       ('Spring', 'Java Framework', false),
       ('HTML', 'Язык разметки', false),
       ('CSS', 'Каскадная таблица стилей', false),
       ('Hibernate', 'ORM библиотека', false),
       ('Kotlin', 'Язык программирования', false),
       ('OOP', 'Объектно ориентированное программирование', false),
       ('Testing', 'Тестирование', false);