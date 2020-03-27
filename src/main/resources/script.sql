INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Howard the Duck', 110, 'Sci-Fi', 4.6, 'PG');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Lavalantula', 83, 'Horror', 4.7, 'TV-14');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Starship Troopers', 129, 'Sci-Fi', 7.2, 'PG-13');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Waltz With Bashir', 90, 'Documentary', 8.0, 'R');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Spaceballs', 96, 'Comedy', 7.1, 'PG');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Monsters Inc.', 92, 'Animation', 8.1, 'G');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Your Name', 106, 'Animation', 8.4, 'PG');
INSERT INTO movies (title, runtime, genre, imdb_score, rating)
VALUES ('Weathering with You', 112, 'Animation', 7.6, 'PG-13');

SELECT * FROM movies WHERE genre = 'Sci-Fi';
SELECT * FROM movies WHERE imdb_score > 6.4;
SELECT * FROM movies WHERE rating IN ('G', 'PG') AND runtime >= 100;
SELECT AVG(runtime) FROM movies WHERE imdb_score < 7.5 GROUP BY genre;
UPDATE movies SET rating = 'R' WHERE title = 'Starship Troopers';
SELECT id, rating from movies WHERE genre IN ('Horror', 'Documentary');
SELECT AVG(imdb_score), MAX(imdb_score), MIN(imdb_score) from movies GROUP BY rating;
SELECT rating FROM movies HAVING COUNT(*) > 1;
DELETE * FROM movies WHERE rating = 'R';