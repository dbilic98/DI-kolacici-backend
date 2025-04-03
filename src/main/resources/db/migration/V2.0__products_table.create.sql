CREATE TABLE products
(
    id                  BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    product_name        VARCHAR(255)              NOT NULL,
    description         TEXT,
    price               DECIMAL(5, 2)            NOT NULL,
    image_data          LONGBLOB,
    image_type          VARCHAR(255),
    category_id         BIGINT(20),

    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (id)
);