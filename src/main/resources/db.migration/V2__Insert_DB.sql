ALTER SEQUENCE books_id_seq RESTART WITH 1;
UPDATE books SET id=nextval('books_id_seq');

ALTER SEQUENCE authors_id_seq RESTART WITH 1;
UPDATE authors SET id=nextval('authors_id_seq');

INSERT INTO books (title, description)
VALUES
('Рита Хейуорт и спасение из Шоушенка', 'Книга про побег'),
('Гарри Поттер', 'Книга про мальчика'),
('Унесённые ветром', 'Книга про любовь'),
('Война и мир', 'Книга про войну и мир'),
('Зеленая миля', 'Книга про тюрьму'),
('Оно', 'Книга про оно'),
('Гарри Поттер 2', 'Книга про мальчика 2'),
('Дневной дозор', 'Книга про вампиров');

INSERT INTO authors ("name")
VALUES
('Кинг Стивен'),
('Митчелл Маргарет'),
('Толстой Лев'),
('Роулинг Джоан'),
('Васильев Владимир'),
('Лукьяненко Сергей');

INSERT INTO book_author (id_book, id_author)
VALUES
(1, 1),
(2, 4),
(3, 2),
(4, 3),
(5, 1),
(6, 1),
(7, 4),
(8, 5),
(8, 6);