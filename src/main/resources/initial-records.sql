insert into users  (id, active, first_name, last_name, username, password, email, date_of_birth, phone_number, address)
values (1, 1, 'admin', 'admin', 'admin', '$2a$10$bMaNOYGEwfNl2z6hg9lmRuc3s7uAgWxhRVzll2YguII6sBvVSX2qi', 'admin@admin.gr',
'1990/01/01', '21012345678', 'Athens');

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');
insert into users_roles values (1,1);


insert into games values (1, 'Cash Bandits', 'Instant Game', 55, 12, 20, '/images/games/cash_bandits.jpg');
insert into games values (2, 'Crazy Vegas', 'Instant Game', 22, 35, 150, '/images/games/crazy_vegas.jpeg');
insert into games values (3, 'Lucky Tiger', 'Instant Game', 82, 5, 8, '/images/games/lucky_tiger.jpg');
insert into games values (4, 'Paydirt', 'Instant Game', 55, 8, 17, '/images/games/paydirt.jpg');
insert into games values (5, 'Texan Tycoon', 'Instant Game', 40, 10, 16, '/images/games/texan_tycoon.jpeg');
insert into games values (6, 'Hen House', 'Instant Game', 75, 5, 10, '/images/games/hen_house.png');
insert into games values (7, 'Black Jack', 'Instant Game', 62, 3, 6, '/images/games/black_jack.jpeg');
insert into games values (8, 'Cash Blast', 'Instant Game', 33, 15, 120, '/images/games/cash_blast.jpeg');
insert into games values (9, 'Fruit Fiesta', 'Instant Game', 47, 12, 23, '/images/games/fruit_fiesta.jpg');
insert into games values (10, 'Joker Pro', 'Instant Game', 71, 8, 18, '/images/games/joker_pro.jpg');
insert into games values (11, 'King Cashlot', 'Instant Game', 95, 1, 3, '/images/games/king_cashlot.jpeg');
insert into games values (12, 'The Groovy Sixties', 'Instant Game', 66, 17, 30, '/images/games/the_groovy_sixties.png');
insert into games values (13, 'Twin Spin', 'Instant Game', 69, 7, 13, '/images/games/twin_spin.jpg');