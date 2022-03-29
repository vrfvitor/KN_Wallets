CREATE TABLE IF NOT EXISTS wallets
(
    id               UUID PRIMARY KEY,
    owner_first_name VARCHAR(255)     NOT NULL,
    owner_last_name  VARCHAR(255)     NOT NULL,
    owner_email      VARCHAR(255),
    balance_cents    BIGINT DEFAULT 0 NOT NULL
);