DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM DISHES;
DELETE
FROM RESTAURANTS;
DELETE
FROM VOTES;
DELETE
FROM MENUS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO RESTAURANTS (NAME)
VALUES ('Restor_1'),
       ('Restor_2'),
       ('Restor_3');

INSERT INTO MENUS (DAY, RESTAURANT_ID)
VALUES ('2015-05-30', 100003),
       ('2015-05-30', 100004);

INSERT INTO VOTES (DAY, MENU_ID, USER_ID)
VALUES ('2015-05-30', 100005, 100000),
       ('2015-05-30', 100005, 100001);

INSERT INTO DISHES (NAME, PRICE, MENU_ID)
VALUES ('dish1', 1231, 100005),
       ('dish2', 1231, 100006),
       ('dish3', 1231, 100006)

