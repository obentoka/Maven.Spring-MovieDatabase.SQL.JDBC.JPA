SELECT * FROM movies WHERE genre = 'Sci-Fi';
SELECT * FROM movies WHERE imdb_score > 6.4;
SELECT * FROM movies WHERE rating IN ('G', 'PG') AND runtime < 100;
SELECT AVG(runtime), genre FROM movies WHERE imdb_score < 7.5 GROUP BY genre;
UPDATE movies SET rating = 'R' WHERE title = 'Starship Troopers';
SELECT id, rating from movies WHERE genre IN ('Horror', 'Documentary');
SELECT AVG(imdb_score), MAX(imdb_score), MIN(imdb_score) from movies GROUP BY rating;
SELECT AVG(imdb_score), MAX(imdb_score), MIN(imdb_score), rating FROM movies GROUP BY rating HAVING COUNT(*) > 1;
DELETE FROM movies WHERE rating = 'R';