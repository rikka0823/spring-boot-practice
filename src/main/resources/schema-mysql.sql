CREATE TABLE IF NOT EXISTS book (
	book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    author VARCHAR(256) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    published_date DATE NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL
);