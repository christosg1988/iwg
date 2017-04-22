insert into users  (id, active, first_name, last_name, username, password, email, date_of_birth, phone_number, address)
values (1, 1, 'admin', 'admin', 'admin', '$2a$10$bMaNOYGEwfNl2z6hg9lmRuc3s7uAgWxhRVzll2YguII6sBvVSX2qi', 'admin@admin.gr',
'1990/01/01', '21012345678', 'Athens');

insert into roles values (1, 'ROLE_ADMIN');
insert into roles values (2, 'ROLE_USER');
insert into users_roles values (1,1);

insert into games values (1, 'Instant Game', '/images/games/cash_bandits.jpg', 'Cash Bandits', 55, 12, 20);
insert into games values (2, 'Instant Game', '/images/games/crazy_vegas.jpeg', 'Crazy Vegas', 22, 35, 150);
insert into games values (3, 'Instant Game', '/images/games/lucky_tiger.jpg', 'Lucky Tiger', 82, 5, 8);
insert into games values (4, 'Instant Game', '/images/games/paydirt.jpg', 'Paydirt', 55, 8, 17);
insert into games values (5, 'Instant Game', '/images/games/texan_tycoon.jpeg', 'Texan Tycoon', 40, 10, 16);
insert into games values (6, 'Instant Game', '/images/games/hen_house.png', 'Hen House', 75, 5, 10);
insert into games values (7, 'Instant Game', '/images/games/black_jack.jpeg', 'Black Jack', 62, 3, 6);
insert into games values (8, 'Instant Game', '/images/games/cash_blast.jpeg', 'Cash Blast', 33, 15, 120);
insert into games values (9, 'Instant Game', '/images/games/fruit_fiesta.jpg', 'Fruit Fiesta', 47, 12, 23);
insert into games values (10, 'Instant Game', '/images/games/joker_pro.jpg', 'Joker Pro', 71, 8, 18);
insert into games values (11, 'Instant Game', '/images/games/king_cashlot.jpeg', 'King Cashlot', 95, 1, 3);
insert into games values (12, 'Instant Game', '/images/games/the_groovy_sixties.png', 'The Groovy Sixties', 66, 17, 30);
insert into games values (13, 'Instant Game', '/images/games/twin_spin.jpg', 'Twin Spin', 69, 7, 13);
