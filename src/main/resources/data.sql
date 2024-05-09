------ Clients -------
INSERT INTO client (name, document_number) VALUES ('Matheus Gustavo Ribeiro', '22025521464');
INSERT INTO client (name, document_number) VALUES ('Bernardo Antonio Guilherme Freitas', '60272176770');
INSERT INTO client (name, document_number) VALUES ('Marcela Amanda dos Santos', '32878308131');

------ Accounts -------
INSERT INTO account (account_number, balance, user_id) VALUES ('12131873', 500.0, 1);
INSERT INTO account (account_number, balance, user_id) VALUES ('2393514', 12000.0, 1);
INSERT INTO account (account_number, balance, user_id) VALUES ('450284', 1000.0, 2);
INSERT INTO account (account_number, balance, user_id) VALUES ('12160518', 1000.0, 3);

------ Transfer -------
INSERT INTO scheduling ( processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 24.50, 980.00, '2024-05-09 11:14:46.504754', 2, 1,  '2024-05-09');
INSERT INTO scheduling (processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 0.0, 250.00, '2024-05-09 11:14:59.317151', 3, 1,  '2024-05-09');
INSERT INTO scheduling (processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 34.50, 500.00, '2024-05-09 11:15:09.804541', 3, 1,  '2024-05-09');
INSERT INTO scheduling (processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 47.70, 100.00, '2024-05-09 11:15:09.804541', 4, 1,  '2024-05-09');
INSERT INTO scheduling (processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 10.20, 600.00, '2024-05-09 11:16:06.981704', 2, 1,  '2024-05-09');
INSERT INTO scheduling (processed, tax, transfer_value, data_transfer, destination_account,
origin_account, scheduling_date) VALUES (false, 13.60, 800.00, '2024-05-09 11:16:36.809697', 2, 1,  '2024-05-09');
