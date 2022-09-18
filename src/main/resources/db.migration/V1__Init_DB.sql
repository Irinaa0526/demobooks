CREATE SEQUENCE books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE books (
    id BIGINT NOT NULL DEFAULT nextval('books_id_seq'),
    title VARCHAR(255),
    description text,
    PRIMARY KEY (id));


CREATE SEQUENCE authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE authors (
    id BIGINT NOT NULL DEFAULT nextval('authors_id_seq'),
    name VARCHAR(255),
    PRIMARY KEY (id));


CREATE TABLE book_author (
    id_book BIGINT NOT NULL,
    id_author BIGINT NOT NULL,
    PRIMARY KEY (id_book, id_author));


ALTER TABLE if EXISTS book_author
    ADD CONSTRAINT  book_fk
    FOREIGN KEY (id_book) REFERENCES books;

ALTER TABLE if EXISTS book_author
    ADD CONSTRAINT  author_fk
    FOREIGN KEY (id_author) REFERENCES authors;