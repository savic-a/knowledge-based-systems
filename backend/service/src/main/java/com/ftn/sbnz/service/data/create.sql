-- Database: sbnz

DROP DATABASE IF EXISTS sbnz;

CREATE DATABASE sbnz
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE USER root WITH PASSWORD 'ftn';
GRANT ALL PRIVILEGES ON DATABASE sbnz TO root;

CREATE TABLE credit_card (
    id SERIAL PRIMARY KEY,
    balance DOUBLE PRECISION,
    client_id BIGINT
);

CREATE TABLE client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);