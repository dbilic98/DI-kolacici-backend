CREATE TABLE products
(
    id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    image_url   VARCHAR(255),
    category_id BIGINT(20),

    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (id)
);