CREATE OR REPLACE TABLE ACCOUNT (
    account_id VARCHAR(250) PRIMARY KEY,
    password VARCHAR(100),
    image BLOB,
    display_name VARCHAR(15),
    creation_date DATE,
    last_connection_date DATETIME,
    admin BOOLEAN
);

CREATE OR REPLACE TABLE CATEGORY (
	category_id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100),
    title VARCHAR(20)
);



CREATE OR REPLACE TABLE TICKET (
	ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(125),
    message VARCHAR(1000),
    date DATETIME,
    author VARCHAR(15)    
);

CREATE OR REPLACE TABLE TICKET_CATEGORIES (
	category_id INT,
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id),
    ticket_id INT,
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id)
);

CREATE OR REPLACE TABLE COMMENT_LIKE (
    like_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id VARCHAR(250) NOT NULL,
    FOREIGN KEY (account_id) REFERENCES ACCOUNT(account_id),
    comment_id INT,
    FOREIGN KEY (comment_id) REFERENCES COMMENT(comment_id)
);

CREATE OR REPLACE TABLE COMMENT (
	comment_id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(250),
    date DATE,
    author VARCHAR(15),
    ticket_id INT,
    FOREIGN KEY (ticket_id) REFERENCES TICKET(ticket_id),
    like_id INT,
    FOREIGN KEY (like_id) REFERENCES COMMENT_LIKE(like_id)
)
