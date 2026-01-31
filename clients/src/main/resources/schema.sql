CREATE TABLE clients
(
    id             UUID PRIMARY KEY,
    full_name      VARCHAR(255) NOT NULL,
    gender         VARCHAR(20)  NOT NULL,
    age            INT          NOT NULL,
    identification VARCHAR(50)  NOT NULL,
    address        VARCHAR(255) NOT NULL,
    phone_number   VARCHAR(50)  NOT NULL,
    password       VARCHAR(255) NOT NULL,
    status         VARCHAR(20)  NOT NULL
);


-- Insert 1
INSERT INTO clients (id, password, status, full_name, gender, age, identification, address, phone_number)
VALUES (
           'e3f1c9c3-4b2a-4f8b-9b6d-1a2e3f4b5c6d',
           'pass123',
           'ACTIVE',
           'Juan David',
           'MALE',
           21,
           '12345678',
           'Calle Falsa 123',
           '555-1111'
       );

-- Insert 2
INSERT INTO clients (id, password, status, full_name, gender, age, identification, address, phone_number)
VALUES (
           'a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d',
           'mypassword',
           'ACTIVE',
           'Maria Lopez',
           'FEMALE',
           30,
           '87654321',
           'Av. Siempre Viva 742',
           '555-2222'
       );

-- Insert 3
INSERT INTO clients (id, password, status, full_name, gender, age, identification, address, phone_number)
VALUES (
           'f1e2d3c4-b5a6-7d8c-9e0f-1a2b3c4d5e6f',
           'secretpass',
           'INACTIVE',
           'Carlos Perez',
           'MALE',
           40,
           '11223344',
           'Calle Luna 12',
           '555-3333'
       );
