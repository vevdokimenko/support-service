-- CREATING DATABASE --
CREATE DATABASE IF NOT EXISTS `support-service`;
USE `support-service`;

-- CREATING user_role --
CREATE TABLE IF NOT EXISTS user_role
(
    id               INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name        VARCHAR(30)  NOT NULL,
    role_description VARCHAR(255) NOT NULL
);

-- FILLING user_role --
INSERT INTO user_role(role_name, role_description)
VALUES ('USER', 'User'),
       ('ADMIN', 'Admin'),
       ('SUPER_ADMIN', 'Super admin');

-- CREATING profile --
CREATE TABLE IF NOT EXISTS profile
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name   VARCHAR(30) NOT NULL,
    last_name    VARCHAR(30) NOT NULL,
    email        VARCHAR(30) NOT NULL,
    phone_number VARCHAR(13) NOT NULL,
    postal_code  VARCHAR(5)  NOT NULL
);

-- FILLING profile --
INSERT INTO profile(first_name, last_name, email, phone_number, postal_code)
VALUES ('Иван', 'Иванов', 'ivanov.i@email.com', '+380661234567', '01234'),
       ('Сергей', 'Нестеренко', 'nesterenko.s@gmail.com', '+380501234567', '12345'),
       ('Виталий', 'Абрамов', 'abramov.v@hotmail.com', '+380951234567', '71100'),
       ('Андрей', 'Николаев', 'nikolaev.a@yahoo.com', '+380991234567', '51200'),
       ('Антон', 'Шевченко', 'shevchenko.a@i.ua', '+380631234567', '31400');

-- CREATING user --
CREATE TABLE IF NOT EXISTS user
(
    id           INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_name    VARCHAR(30) NOT NULL,
    password     VARCHAR(30) NOT NULL,
    user_role_id INT         NOT NULL,
    profile_id   INT         NOT NULL,

    FOREIGN KEY (user_role_id) REFERENCES user_role (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (profile_id) REFERENCES profile (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- FILLING user --
INSERT INTO user(user_name, password, user_role_id, profile_id)
VALUES ('ivanov.i', 'superadmin', 3, 1),
       ('nesterenko.s', 'admin', 2, 2),
       ('abramov.v', 'user', 1, 3),
       ('nikolaev.a', 'user', 1, 4),
       ('shevchenko.a', 'user', 1, 5);

-- CREATING service --
CREATE TABLE IF NOT EXISTS service
(
    id                  INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_name        VARCHAR(30) NOT NULL,
    is_active           BOOLEAN     NOT NULL,
    service_month_price DOUBLE      NOT NULL,
    customer_id         INT         NOT NULL
);

-- FILLING service --
INSERT INTO service(service_name, is_active, service_month_price, customer_id)
VALUES ('VPN', TRUE, 4.99, 1),
       ('Films+', TRUE, 1.99, 2),
       ('Music', TRUE, 0.99, 3),
       ('TV', TRUE, 1.99, 4),
       ('Football', TRUE, 2.99, 5),
       ('Serials', TRUE, 1.99, 6);

-- CREATING user_service --
CREATE TABLE IF NOT EXISTS user_service
(
    service_id INT NOT NULL,
    user_id    INT NOT NULL,

    FOREIGN KEY (service_id) REFERENCES service (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    PRIMARY KEY (service_id, user_id)
);

-- FILLING user_service --
INSERT INTO user_service(service_id, user_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (2, 3),
       (4, 3),
       (2, 4),
       (3, 4),
       (4, 4),
       (5, 4),
       (6, 4),
       (1, 5),
       (2, 5),
       (4, 5),
       (6, 5);

-- CREATING incident --
CREATE TABLE IF NOT EXISTS incident
(
    id                  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    service_name        VARCHAR(30)  NOT NULL,
    is_active           BOOLEAN      NOT NULL,
    problem_description VARCHAR(255) NOT NULL,
    user_id             INT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- FILLING incident --
INSERT INTO incident(service_name, is_active, problem_description, user_id)
VALUES ('VPN', FALSE, 'Problem with vpn', 3),
       ('Films', TRUE, 'Problem with films', 4),
       ('TV', TRUE, 'Problem with tv', 4),
       ('VPN', TRUE, 'Problem with vpn', 5),
       ('Serials', TRUE, 'Problem with serials', 5);

-- CREATING permissions --
CREATE TABLE IF NOT EXISTS permission
(
    id      INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    command VARCHAR(30) NOT NULL,
    role_id INT         NOT NULL,
    FOREIGN KEY (role_id) REFERENCES user_role (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- FILLING permissions --
INSERT INTO permission(command, role_id)
VALUES ('fetch_all_users', 3),
       ('fetch_all_users', 2),
       ('fetch_all_incidents', 3),
       ('fetch_all_incidents', 2),
       ('fetch_all_active_incidents', 3),
       ('fetch_all_active_incidents', 2),
       ('fetch_user_by_{}', 3),
       ('fetch_user_by_{}', 2),
       ('add_user', 3),
       ('add_user', 2),
       ('update_user_{}', 3),
       ('update_user_{}', 2),
       ('delete_user_{}', 3),
       ('delete_user_{}', 2),
       ('subscribe_service_{}', 3),
       ('subscribe_service_{}', 2),
       ('subscribe_service_{}', 1),
       ('unsubscribe_service_{}', 3),
       ('unsubscribe_service_{}', 2),
       ('unsubscribe_service_{}', 1),
       ('create_incident', 3),
       ('create_incident', 2),
       ('create_incident', 1),
       ('close_incident', 3),
       ('close_incident', 2)