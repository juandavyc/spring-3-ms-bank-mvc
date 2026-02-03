DELETE FROM transactions;
DELETE FROM accounts;

INSERT INTO accounts (id, number, opening_balance, balance, status, type, client_id)
VALUES ('d024607e-bfbb-45f7-ada8-f6c33702c9f7', '5338581564', 123.00, 203.00, 'ACTIVE', 'DEBIT', 'e3f1c9c3-4b2a-4f8b-9b6d-1a2e3f4b5c6d');

INSERT INTO transactions (id, account_id, type, amount, status, timestamp)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'd024607e-bfbb-45f7-ada8-f6c33702c9f7', 'DEPOSIT', 50.00, 'APPROVED', '2024-01-05 10:15:00'),
    ('22222222-2222-2222-2222-222222222222', 'd024607e-bfbb-45f7-ada8-f6c33702c9f7', 'DEPOSIT', 30.00, 'APPROVED', '2024-01-15 14:30:00');

INSERT INTO accounts (id, number, opening_balance, balance, status, type, client_id)
VALUES ('a1c4b8e2-6a3e-4d71-9c9c-8e7b2f5e1234', '9845123476', 1000.00, 1650.00, 'ACTIVE', 'CREDIT', 'e3f1c9c3-4b2a-4f8b-9b6d-1a2e3f4b5c6d');

INSERT INTO transactions (id, account_id, type, amount, status, timestamp)
VALUES
    ('33333333-3333-3333-3333-333333333333', 'a1c4b8e2-6a3e-4d71-9c9c-8e7b2f5e1234', 'DEPOSIT', 500.00, 'APPROVED', '2024-01-03 09:00:00'),
    ('44444444-4444-4444-4444-444444444444', 'a1c4b8e2-6a3e-4d71-9c9c-8e7b2f5e1234', 'DEPOSIT', 150.00, 'APPROVED', '2024-02-01 18:45:00');

INSERT INTO accounts (id, number, opening_balance, balance, status, type, client_id)
VALUES ('f3e9a2b1-2c4d-4f7a-8b1c-9d0e12345678', '1122334455', 500.00, 400.00, 'INACTIVE', 'DEBIT', 'a1b2c3d4-5e6f-7a8b-9c0d-1e2f3a4b5c6d');

INSERT INTO transactions (id, account_id, type, amount, status, timestamp)
VALUES
    ('55555555-5555-5555-5555-555555555555', 'f3e9a2b1-2c4d-4f7a-8b1c-9d0e12345678', 'WITHDRAWAL', 100.00, 'APPROVED', '2023-12-20 11:00:00');