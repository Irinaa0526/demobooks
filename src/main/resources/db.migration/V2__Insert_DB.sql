INSERT INTO books (title, description)
VALUES
('Рита Хейуорт и спасение из Шоушенка', 'Книга про побег'),
('Гарри Поттер', 'Книга про мальчика'),
('Унесённые ветром', 'Книга про любовь'),
('Война и мир', 'Книга про войну и мир');

INSERT INTO authors ("name")
VALUES
('Стивен Кинг'),
('Маргарет Митчелл'),
('Лев Толстой'),
('Джоан Роулинг');

INSERT INTO book_author (id_book, id_author)
VALUES
(1, 1),
(2, 4),
(3, 2),
(4, 3);