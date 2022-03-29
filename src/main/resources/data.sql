INSERT INTO wallets (id, owner_first_name, owner_last_name, owner_email, balance_cents)
VALUES ('3d3b1a58-cf7b-4c75-8daf-af62b6f1851a', 'John', 'Smith', NULL, 27700),
       ('55560ee8-3536-46af-8525-027e93810476', 'Will', 'Cena', NULL, 11000),
       ('1f810060-d6b3-437d-b71d-765d7e80c141', 'Vitor', 'Ferreira', 'vrfvitor@hotmail.com', 250000)
ON CONFLICT DO NOTHING;