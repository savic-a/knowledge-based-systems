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
    (150.75, '2024-05-01 10:30:00', 0, 1, 0, TRUE),
    (75.50, '2024-05-02 14:15:00', 1, 2, 1, FALSE),
    (200.00, '2024-05-03 09:00:00', 0, 3, 2, TRUE),
    (500.00, '2024-05-04 18:45:00', 1, 4, 3, FALSE),
    (60.00, '2024-05-05 12:30:00', 1, 1, 4, TRUE);

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
    (55000, 4);

INSERT INTO financial_goal (name, description, generation_date, target_value, target_date, current_balance, start_balance, client_id)
VALUES 
('Save for Car', 'Saving money to buy a new car.', '2024-06-01 10:30:00', 20000.00, '2025-06-01 00:00:00', 5000.00, 1000.00, 1),
('Vacation Fund', 'Saving for a family vacation.', '2024-06-01 10:30:00', 5000.00, '2024-12-15 00:00:00', 1500.00, 500.00, 2),
('Emergency Fund', 'Emergency savings for unexpected expenses.', '2024-06-01 10:30:00', 10000.00, '2025-01-01 00:00:00', 2000.00, 1000.00, 3);