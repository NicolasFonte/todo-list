-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username, enabled,password,role_id) values ('user',  true, '$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1);
insert into user (username, enabled,password,role_id) values ('user2', true, '$2a$08$wgwoMKfYl5AUE9QtP4OjheNkkSDoqDmFGjjPE2XTPLDe9xso/hy7u',1);

-- insert frequency
insert into frequency (times, frequency_type) values (4, 'Weeks');
insert into frequency (times, frequency_type) values (5, 'Days');

-- Insert tasks
insert into task (complete, name, description, due_date, frequency_id, user_id) values (false,'Task1', 'Task1', '2017-05-05', 1, 1);
insert into task (complete, name, description, due_date, frequency_id, user_id) values (false,'Task2', 'Task2', '2017-12-05', 1, 1);
insert into task (complete, name, description, due_date, frequency_id, user_id) values (false,'Task3', 'Task3', '2017-05-05', 1, 2);
insert into task (complete, name, description, due_date, frequency_id, user_id) values (false,'Task4', 'Task4', '2017-12-05', 2, 2);