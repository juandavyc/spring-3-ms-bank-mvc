CREATE TABLE accounts
(
    id              UUID PRIMARY KEY,
    number          VARCHAR(255)   NOT NULL,
    opening_balance NUMERIC(38, 2) NOT NULL,
    balance         NUMERIC(38, 2) NOT NULL,
    status          VARCHAR(20)    NOT NULL,
    type            VARCHAR(20)    NOT NULL,
    client_id       UUID
);

CREATE TABLE transactions
(
    id         UUID PRIMARY KEY,
    account_id UUID           NOT NULL,
    type       VARCHAR(20)    NOT NULL,
    amount     NUMERIC(38, 2) NOT NULL,
    status     VARCHAR(20)    NOT NULL,
    timestamp  TIMESTAMP      NOT NULL
);

INSERT INTO accounts (id,
                      number,
                      opening_balance,
                      balance,
                      status,
                      type,
                      client_id)
VALUES ('d024607e-bfbb-45f7-ada8-f6c33702c9f7',
        '5338581564',
        123.00,
        10.00,
        'ACTIVE',
        'DEBIT',
        '67fd5c0f-dc3e-4907-a5b8-80bfd5df5bdb');

INSERT INTO accounts (id,
                      number,
                      opening_balance,
                      balance,
                      status,
                      type,
                      client_id)
VALUES ('a1c4b8e2-6a3e-4d71-9c9c-8e7b2f5e1234',
        '9845123476',
        1000.00,
        850.50,
        'ACTIVE',
        'CREDIT',
        '67fd5c0f-dc3e-4907-a5b8-80bfd5df5bdb');

INSERT INTO accounts (id,
                      number,
                      opening_balance,
                      balance,
                      status,
                      type,
                      client_id)
VALUES ('f3e9a2b1-2c4d-4f7a-8b1c-9d0e12345678',
        '1122334455',
        500.00,
        0.00,
        'INACTIVE',
        'DEBIT',
        '11111111-2222-3333-4444-555555555555');
