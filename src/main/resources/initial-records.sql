insert into users  (id, active, first_name, last_name, username, password, email, date_of_birth, phone_number, address)
values (1, 1, 'admin', 'admin', 'admin', '$2a$10$bMaNOYGEwfNl2z6hg9lmRuc3s7uAgWxhRVzll2YguII6sBvVSX2qi', 'admin@admin.gr',
'1990/01/01', '21012345678', 'Athens');

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');
insert into users_roles values (1,1);