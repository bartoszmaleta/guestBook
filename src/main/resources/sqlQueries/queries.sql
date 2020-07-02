-- create database
    CREATE DATABASE guestbook WITH OWNER postgres;
CREATE TABLE IF NOT EXISTS guestbook (content TEXT, name VARCHAR(20), date_time TIMESTAMP);

-- create entry
INSERT INTO guestbook VALUES('post example', 'name example', '2004-04-12 02:00');

-- read all entries
SELECT * FROM guestbook; 
