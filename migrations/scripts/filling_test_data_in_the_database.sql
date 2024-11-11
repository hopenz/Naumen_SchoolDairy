BEGIN;

-- Вставка тестовых данных в таблицу teacher
INSERT INTO teacher (name, surname, patronymic, phone_number)
VALUES ('Иван', 'Иванов', 'Иванович', '1234567890'),
       ('Петр', 'Петров', 'Петрович', '0987654321'),
       ('Светлана', 'Сидорова', 'Алексеевна', '1122334455');

-- Вставка тестовых данных в таблицу school_class
INSERT INTO school_class (class_name, teacher_id)
VALUES ('1А', 1),
       ('2Б', 2),
       ('3В', 3);

-- Вставка тестовых данных в таблицу subject
INSERT INTO subject (name)
VALUES ('Математика'),
       ('Русский язык'),
       ('История'),
       ('Физика');

-- Вставка тестовых данных в таблицу student
INSERT INTO student (name, surname, patronymic, date_of_birth, parent_contact, phone_number, class_id)
VALUES ('Алексей', 'Алексеев', 'Алексеевич', '2010-05-01', '89161234567', '89161234567', 1),
       ('Мария', 'Мариева', 'Мариевна', '2011-06-15', '89169876543', '89169876543', 1),
       ('Дмитрий', 'Дмитриев', 'Дмитриевич', '2009-08-20', '89163456789', '89163456789', 2),
       ('Анна', 'Аннаева', 'Антоновна', '2012-09-10', '89164567890', '89164567890', 3);

-- Вставка тестовых данных в таблицу daily_schedule
INSERT INTO daily_schedule (date_day, class_id, day_of_week)
VALUES ('2024-11-10', 1, 'Понедельник'),
       ('2024-11-10', 2, 'Понедельник'),
       ('2024-11-10', 3, 'Понедельник');

-- Вставка тестовых данных в таблицу lesson
INSERT INTO lesson (subject_id, daily_schedule_id, lesson_number)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 1),
       (4, 3, 1);

-- Вставка тестовых данных в таблицу homework
INSERT INTO homework (lesson_id, subject_id, description)
VALUES (1, 1, 'Упражнение 5 на странице 23'),
       (2, 2, 'Написать сочинение на тему "Моя семья"'),
       (3, 3, 'Подготовить презентацию по теме "Древний Рим"');

-- Вставка тестовых данных в таблицу mark
INSERT INTO mark (student_id, subject_id, lesson_id, mark, grade_date)
VALUES (1, 1, 1, 5, '2024-11-01'),
       (2, 2, 2, 4, '2024-11-01'),
       (3, 3, 3, 3, '2024-11-01'),
       (4, 4, 4, 5, '2024-11-01');

COMMIT;