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
