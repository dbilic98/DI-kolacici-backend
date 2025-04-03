CREATE TABLE customers
(
    id            BIGINT     AUTO_INCREMENT PRIMARY KEY,
    first_name    VARCHAR(100)              NOT NULL,
    last_name     VARCHAR(100)              NOT NULL,
    email         VARCHAR(255)              NOT NULL UNIQUE,
    phone         VARCHAR(20)               NOT NULL,
    address       TEXT
);