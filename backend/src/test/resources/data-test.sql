INSERT INTO wallets (id, owner_first_name, owner_last_name, owner_email, balance_cents)
VALUES ('3d3b1a58-cf7b-4c75-8daf-af62b6f1851a', 'Will', 'Smith', NULL, 50000),
       ('55560ee8-3536-46af-8525-027e93810476', 'John', 'Cena', NULL, 110000)
ON CONFLICT DO NOTHING;