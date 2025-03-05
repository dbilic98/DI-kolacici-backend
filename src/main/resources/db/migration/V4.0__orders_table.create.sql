CREATE TABLE orders
(
    id          BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    order_date  DATE        NOT NULL,
    status      VARCHAR(20) NOT NULL,
    customer_id BIGINT(20),

    CONSTRAINT fk_customer_id FOREIGN KEY (customer_id) REFERENCES customers (id)
);