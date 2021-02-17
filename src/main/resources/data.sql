

CREATE TABLE if not exists users(
                      id INT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE ,
                      password Varchar(255) NOT NULL
);

CREATE TABLE if not exists record(
                       id INT PRIMARY KEY,
                       expression VARCHAR(255) NOT NULL ,
                       result Varchar(255) NOT NULL ,
                       timestamp DATETIME NOT NULL ,
                       userId INT NOT NULL ,
                       foreign key (userId) references users(id)
);


INSERT  INTO   users (id,username, password) VALUES
(1,'user1', '$2y$12$fIgG2PQ10yEE9xyFCzWtsuuWq6S2/BTSPdrwEZeMpFNKdBzLKMevC'),
(2,'user2', '$2y$12$jepfXKRfMsXtIkwIqNNfruEHtrIvdgubYDAN.tpt2PmRDiDPmBjsy' );