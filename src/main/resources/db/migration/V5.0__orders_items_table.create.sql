CREATE TABLE orders_items
(
    id         BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
    quantity   BIGINT                    NOT NULL,
    order_id   BIGINT,
    product_id BIGINT,

    CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders (id),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES products (id)
);