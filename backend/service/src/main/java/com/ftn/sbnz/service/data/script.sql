drop table credit_card;
drop table transactions;
drop table budget;
drop table client_five_purchases;
drop table client_three_purchases;
drop table report;
drop table financial_goal;
drop table client;

CREATE TABLE client (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    flag3 BOOLEAN DEFAULT FALSE,
    flag4 BOOLEAN DEFAULT FALSE
);

CREATE TABLE client_three_purchases (
    id SERIAL PRIMARY KEY,
    client_id BIGINT,
    category INT NOT NULL
);

CREATE TABLE client_five_purchases (
    id SERIAL PRIMARY KEY,
    client_id BIGINT,
    category INT NOT NULL
);

CREATE TABLE credit_card (
    id SERIAL PRIMARY KEY,
    balance DOUBLE PRECISION,
    client_id BIGINT,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    value DOUBLE PRECISION NOT NULL,
    date TIMESTAMP NOT NULL,
    type INT NOT NULL,
    client_id BIGINT NOT NULL,
    category int NOT NULL,
    is_processed BOOLEAN NOT NULL,
	FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE report (
    id BIGSERIAL PRIMARY KEY,
    week_num INT NOT NULL,
    reason VARCHAR(255) NOT NULL,
    generation_date TIMESTAMP NOT NULL,
    client_id BIGINT NOT NULL,
	FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE budget (
    id BIGSERIAL PRIMARY KEY,
    value DOUBLE PRECISION NOT NULL,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

CREATE TABLE financial_goal (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    generation_date TIMESTAMP NOT NULL,
    target_value DOUBLE PRECISION NOT NULL,
    target_date TIMESTAMP,
    current_balance DOUBLE PRECISION NOT NULL,
    start_balance DOUBLE PRECISION NOT NULL,
    client_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES Client(id)
);

INSERT INTO client (id, name, surname, email, password) VALUES 
    (1, 'John', 'Doe', 'john.doe@example.com', '$2a$10$3iKmUIyLrRe.AfERgBoqSODrZtFMhKDIcQXsaL2qWxk2.SgoSKIVy'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com', 'securepass456'),
    (3, 'Alice', 'Johnson', 'alice.johnson@example.com', 'alicepassword'),
    (4, 'Bob', 'Brown', 'bob.brown@example.com', 'bobbypass789'),
	(5, 'Ana', 'Cekic', 'ana@example.com', '$2a$10$3iKmUIyLrRe.AfERgBoqSODrZtFMhKDIcQXsaL2qWxk2.SgoSKIVy');

INSERT INTO client_three_purchases (client_id, category) VALUES 
    (1, 0),
    (1, 1),
    (2, 2),
    (3, 3);

INSERT INTO client_five_purchases (client_id, category) VALUES 
    (1, 0),
    (1, 1),
    (1, 2),
    (1, 2),
    (1, 2),
    (2, 3),
    (3, 3);

INSERT INTO credit_card (balance, client_id) VALUES
    (1000.00, 1),
    (2500.50, 2),
    (500.75, 3),
	(2500.50, 4),
    (500.75, 5);

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
    (3, 'Client is delusional about his current finance situation.', '2024-06-04 12:00:00', 4),
	(4, 'Client is unreasonably spening money.', '2024-06-02 09:15:00', 1),
    (5, 'Client is not normal.', '2024-06-03 14:45:00', 1);

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

GRANT TEMPORARY, CONNECT ON DATABASE sbnz TO PUBLIC;

GRANT ALL ON DATABASE sbnz TO postgres;

GRANT ALL ON DATABASE sbnz TO root;

GRANT ALL PRIVILEGES ON TABLE credit_card TO root;
GRANT ALL PRIVILEGES ON TABLE client TO root;
GRANT ALL PRIVILEGES ON TABLE transactions TO root;
GRANT ALL PRIVILEGES ON TABLE report TO root;
GRANT ALL PRIVILEGES ON TABLE budget TO root;
GRANT ALL PRIVILEGES ON TABLE financial_goal TO root;
GRANT ALL PRIVILEGES ON TABLE client_three_purchases TO root;
GRANT ALL PRIVILEGES ON TABLE client_five_purchases TO root;
GRANT USAGE, SELECT ON SEQUENCE financial_goal_id_seq TO root;
GRANT USAGE, SELECT ON SEQUENCE transactions_id_seq TO root;
GRANT USAGE, SELECT ON SEQUENCE report_id_seq TO root;
