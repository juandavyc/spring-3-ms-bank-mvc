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

