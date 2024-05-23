insert into courses (course, status, created_at, updated_at) values ('8vo', true, '2024-05-20', '2024-05-22');

insert into paralelos (paralelo, status, created_at, updated_at) values ('A', true, '2024-05-20', '2024-05-22');

insert into students (first_name, last_name, email, code, status, course_id, paralelo_id, created_at, updated_at) values ('Cristina lady', 'Velazquez ramirez', 'crislinalady@gmail.com', 'rt1254jh', true, 1, 1, '2024-05-20', '2024-05-22');

insert into quimestres (nombre_quimestre, student_id, status, created_at, updated_at) values ('Q1', 1, true, '2024-05-20', '2024-05-22');
insert into quimestres (nombre_quimestre, student_id, status, created_at, updated_at) values ('Q2', 1, true, '2024-05-20', '2024-05-22');

insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_1', 1, true, '2024-05-20', '2024-05-22');
insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_2', 1, true, '2024-05-20', '2024-05-22');
insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_3', 1, true, '2024-05-20', '2024-05-22');
insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_1', 2, true, '2024-05-20', '2024-05-22');
insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_2', 2, true, '2024-05-20', '2024-05-22');
insert into parciales (name_parcial, quimestre_id, status, created_at, updated_at) values ('Parcial_3', 2, true, '2024-05-20', '2024-05-22');

--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (8.70, 5, 2, 10, 6.42, 1, true, '2024-05-20', '2024-05-22');
--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (0, 4.50, 10, 10, 6.12, 2, true, '2024-05-20', '2024-05-22');
--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (10, 10, 10, 10, 10, 3, true, '2024-05-20', '2024-05-22');
--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (7, 9, 2, 10, 7, 4, true, '2024-05-20', '2024-05-22');
--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (10, 10, 10, 0, 7.5, 5, true, '2024-05-20', '2024-05-22');
--insert into notas (note_task, note_group_work, note_lesson, exam, promedio, parcial_id, status, created_at, updated_at) values (6, 3, 2, 10, 5.25, 6, true, '2024-05-20', '2024-05-22');