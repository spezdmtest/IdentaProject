-- USERS
create sequence user_seq start 1 increment 1;

create table users
(
    id    int8 not null,
    email varchar(255),
    primary key (id)
);

--PRODUCT
CREATE SEQUENCE product_seq START 1 INCREMENT 1;

CREATE TABLE products
(
    id        int8 NOT NULL,
    title     varchar(255),
    available int8,
    price     numeric(19, 2),
    primary key (id)
);

--BUCKET
CREATE SEQUENCE bucket_seq START 1 INCREMENT 1;

CREATE TABLE buckets
(
    id      int8 NOT NULL,
    user_id int8,
    PRIMARY KEY (id)
);

-- LINK BETWEEN BUCKETS AND USER
alter table if exists buckets
    add constraint buckets_fk_user
        foreign key (user_id) references users;


--PRODUCTS IN BUCKET
CREATE TABLE buckets_products
(
    bucket_id  int8 NOT NULL,
    product_id int8 NOT NULL
);

ALTER TABLE IF EXISTS buckets_products
    ADD CONSTRAINT buckets_products_fk_bucket
        FOREIGN KEY (bucket_id) REFERENCES buckets;

ALTER TABLE IF EXISTS buckets_products
    ADD CONSTRAINT buckets_products_fk_product
        FOREIGN KEY (product_id) REFERENCES products;







