CREATE TABLE customers
(
    id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    username    VARCHAR(255)              NOT NULL,
    phone       VARCHAR(25)               NOT NULL,
    address     TEXT
);