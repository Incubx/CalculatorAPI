DROP TABLE IF EXISTS users;

CREATE TABLE users (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              username VARCHAR(250) UNIQUE NOT NULL,
                              password VARCHAR(250) NOT NULL
);

INSERT INTO users (username, password) VALUES
('user1', '$2y$12$fIgG2PQ10yEE9xyFCzWtsuuWq6S2/BTSPdrwEZeMpFNKdBzLKMevC'),
('user2', '$2y$12$jepfXKRfMsXtIkwIqNNfruEHtrIvdgubYDAN.tpt2PmRDiDPmBjsy' );