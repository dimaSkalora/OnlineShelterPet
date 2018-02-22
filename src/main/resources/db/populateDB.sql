DELETE FROM user_roles;
DELETE FROM pets;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, downplay_weight) VALUES
  ('User', 'user@yandex.ru', 'password', 0.2),
  ('Admin', 'admin@gmail.com', 'admin', 0.3);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
    ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO pets (createdDate, typePet, namePet, breed, sex, color, age, growth, weight, namePerson, phone, email, user_id) VALUES
('2018-01-29 10:00:00','Dog','d1','таксф','w','чорный',1.5,40,16,'петя','0631234567','gui@ki',100000),
('2018-01-29 20:00:00','Cat','c1','сиамский','m','белый',2,20,4,'вася','0731234567','sadr@saae',100000),
('2018-01-29 22:00:00','Dog','d2','метис','m','чорный',3,60,50,'саша','0731234567','qefeq@qd',100000),
('2018-01-30 10:00:00','Cat','c1','сеамс','m','белый',1,15,3.5,'катф','0671234567','arfva@dav',100000),
('2018-01-30 15:00:00','Cat','c2','бразил','w','коричневый',1.8,18,4,'люда','0671234567','aeqe@eqwd',100000),
('2018-01-30 20:00:00','Dog','d1','авчарка','m','белый',2,50,40.5,'вова','0731234567','aedc@ds',100000),
('2018-01-30 16:00:00','Cat','c3','бразил','w','коричневый',1.8,18,4,'admin1','0644234567','aersgqe@eqwd',100001),
('2018-01-30 21:00:00','Dog','d3','авчарка','m','белый',2,50,40.5,'admin2','0731244567','aedgrsrgc@ds',100001);


