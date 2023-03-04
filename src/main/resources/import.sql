INSERT INTO band(name, genre, year_of_creation, status) VALUES ('Camyenna', 'BLACK', '2001', 'true'), ('Behemoth', 'DEATH', '1990', 'true');
INSERT INTO recording(band_id, name, year_of_creation, possession) VALUES (1, 'Legenda', '2022', 'true'), (2, 'Demigod', '2010', 'true');
INSERT INTO song(recording_id, name) VALUES (1, 'Dar śmierci'), (1, 'Euphoria Lamnetoso'), (2, 'Slaves shall serve');
INSERT INTO musician(role, name) VALUES ('GUITAR', 'Morsu'), ('GUITAR', 'Migdal'), ('BASS', 'Suhy'), ('DRUM', 'Omen'), ('VOCAL','Nergal');
INSERT INTO MUSICIAN_BANDS(MUSICIANS_ID, BANDS_ID) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 2);
INSERT into users(first_name, last_name, date_of_birth) VALUES ('Andrzej', 'Krzywy', '1980-06-24'), ('Adam', 'Małysz', '1970-03-25'), ('Krzysztof', 'Krawczyk', '1950-03-15');
