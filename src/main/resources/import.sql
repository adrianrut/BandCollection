INSERT INTO bands(name, genre, year_of_creation, status) VALUES ('Camyenna', 'Black', '2001', 'true');
INSERT INTO recording(band_id, name, songs, year_of_creation, possession) VALUES (1, 'Legenda', 1, '2022', 'true');
INSERT INTO song(recording_id, name) VALUES (1, 'Dar śmierci'), (1, 'Euphoria Lamnetoso');
INSERT INTO musician(band_id, role, name) VALUES (1, 'Guitar', 'Morsu'), (1, 'Guitar', 'Migdal'), (1, 'Bass', 'Suhy'), (1, 'Drum', 'Omen');