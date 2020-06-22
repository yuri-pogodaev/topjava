DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
INSERT INTO meals(datetime, description, calories, user_id) VALUES
('2020-06-21 09:05:00', 'Завтрак', 500, 100000),
('2020-06-21 13:10:00', 'Обед', 300, 100000),
('2020-06-21 19:22:00', 'Ужин', 1400, 100000),
('2020-06-20 09:00:00', 'Завтрак', 500, 100000),
('2020-06-20 14:11:00', 'Обед', 900, 100000),
('2020-06-20 20:04:00', 'Ужин', 500, 100000),
('2020-06-22 08:00:00', 'Завтрак', 700, 100000),

('2020-06-21 09:04:00', 'Завтрак', 500, 100001),
('2020-06-21 13:11:00', 'Обед', 700, 100001),
('2020-06-21 19:44:00', 'Ужин', 790, 100001),
('2020-06-22 08:00:00', 'Завтрак', 400, 100001),
('2020-06-22 13:00:00', 'Обед', 1000, 100001),
('2020-06-22 19:00:00', 'Ужин', 800, 100001),
('2020-06-22 00:00:00', 'Ночной дожор', 600, 100001)