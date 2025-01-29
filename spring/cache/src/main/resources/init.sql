CREATE SCHEMA IF NOT EXISTS testdb;

CREATE TABLE IF NOT EXISTS workers
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

-- Insert 3 sample records into the 'workers' table
-- INSERT INTO workers (name)
-- VALUES ('Alice'),
--       ('Bob'),
--       ('Charlie');
