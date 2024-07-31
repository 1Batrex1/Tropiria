insert into role (name) values ('ROLE_ADMIN');
insert into account (login, password, is_active) values ('admin', '$2a$12$TxsSy9F39BTyMO6kjgVLg.NnyY0hgAhIdFWhgogb0Xnv/MkhHmE3G', true);
insert into account_roles (account_id, roles_id) values (1, 1);
insert into species (name) values ('Gekon orzęsiony');
insert into morph (name) values ('Harlequin');
insert into morph (name) values ('Dalmatian');
insert into morph (name) values ('Brindle');
insert into morph (name) values ('Pinstripe');
insert into morph (name) values ('Flame');
insert into morph (name) values ('Patternless');
insert into morph (name) values ('Bicolor');
insert into morph (name) values ('Tiger');
insert into morph (name) values ('Halloween');
insert into morph (name) values ('Bicolor Crested gecko');
insert into morph (name) values ('Creamsicle');
insert into morph (name) values ('Phantom');
insert into morph (name) values ('Blonde');
insert into morph (name) values ('Flame Crested gecko');
insert into morph (name) values ('Lilly white');



INSERT INTO animal (name, description, sex, date_of_birth, species_id) VALUES ('zuko', 'this is zuko', 1, '2020-01-01', 1);
Insert Into animal_morphs (animal_id, morphs_id) values (1, 1);
Insert Into animal_morphs (animal_id, morphs_id) values (1, 2);


INSERT INTO photos (photo_name) values ('placeholder-1.png'), ('placeholder-2.png'), ('placeholder-3.png');


INSERT INTO animal_photos (animal_id, photos_id) values (1, 1), (1, 2), (1, 3);


INSERT INTO animal (name, description, sex, date_of_birth, species_id) values ('gekon-1', 'this is gekon-1', 2, '2020-01-02', 1);

INSERT INTO animal (name, description, sex, date_of_birth, species_id) values ('gekon-2', 'this is gekon-2', 1, '2020-01-03', 1);

INSERT INTO animal (name, description, sex, date_of_birth, species_id) values ('gekon-3', 'this is gekon-3', 2, '2020-01-04', 1);


INSERT INTO animal_for_sale (animal_id, price,reservation_status) values (2, 100, 'ZAREZEWOWANO');

INSERT INTO animal_for_sale (animal_id, price,reservation_status) values (3, 200, 'NA SPRZEDAŻ');

INSERT INTO animal_for_sale (animal_id, price,reservation_status) values (4, 300, 'NA SPRZEDAŻ');


INSERT INTO animal_morphs (animal_id, morphs_id) values (2, 3);

INSERT INTO animal_morphs (animal_id, morphs_id) values (3, 4);

INSERT INTO animal_morphs (animal_id, morphs_id) values (4, 5);

INSERT INTO animal_for_sale_parents (animal_for_sale_animal_id, parents_id) values (2, 1);

INSERT INTO animal_for_sale_parents (animal_for_sale_animal_id, parents_id) values (3, 1);

INSERT INTO animal_for_sale_parents (animal_for_sale_animal_id, parents_id) values (4, 1);

insert into animal_photos (animal_id, photos_id) values (2, 1), (3, 2), (4, 3);








