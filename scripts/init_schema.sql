-- Create the 'users' table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,            -- 'SERIAL' automatically creates a unique, auto-incrementing identifier.
    name VARCHAR(255) NOT NULL,        -- 'name' field that cannot be null.
    email VARCHAR(255) UNIQUE NOT NULL -- 'email' must be unique and is required.
);

-- Create the 'transactions' table
CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,             -- Auto-incrementing primary key.
    amount NUMERIC(10, 2) NOT NULL,      -- 'amount' field for the transaction value.
    timestamp TIMESTAMP NOT NULL,      -- 'timestamp' to record when the transaction occurred.
    user_id BIGINT,                    -- Foreign key to link to a user.
    CONSTRAINT fk_user                   -- Define a foreign key constraint.
      FOREIGN KEY(user_id) REFERENCES users(id)
);
