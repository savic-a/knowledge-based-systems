
INSERT INTO client (id, name, surname, email, password) VALUES 
    (1, 'John', 'Doe', 'john.doe@example.com', 'password123'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com', 'securepass456'),
    (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'alicepassword'),
    (4, 'Bob', 'Brown', 'bob.brown@example.com', 'bobbypass789');

INSERT INTO credit_card (balance, client_id) VALUES
    (1000.00, 1),
    (2500.50, 2),
    (500.75, 3);

INSERT INTO transactions (value, date, type, client_id, category, is_processed) VALUES
    (150.75, '2024-05-01 10:30:00', 'INCOME', 1, 'SHOPPING', TRUE),
    (75.50, '2024-05-02 14:15:00', 'OUTCOME', 2, 'FUN', FALSE),
    (200.00, '2024-05-03 09:00:00', 'INCOME', 3, 'GROCERIES', TRUE),
    (500.00, '2024-05-04 18:45:00', 'OUTCOME', 4, 'RENT', FALSE),
    (60.00, '2024-05-05 12:30:00', 'OUTCOME', 1, 'UTILITES', TRUE);

INSERT INTO report (week_num, reason, generation_date, client_id)
VALUES 
    (1, 'Client is unreasonably spening money.', '2024-06-02 09:15:00', 2),
    (2, 'Client is not normal.', '2024-06-03 14:45:00', 3),
    (3, 'Client is delusional about his current finance situation.', '2024-06-04 12:00:00', 4);

INSERT INTO budget(value, client_id)
VALUES
    (50000, 1),
    (65000, 2),
    (70000, 3),
    (55000, 4),
    (100000, 5);
