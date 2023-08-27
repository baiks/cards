CREATE DATABASE IF NOT EXISTS cards;
USE cards;
DROP TABLE IF EXISTS cards;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id bigint NOT NULL AUTO_INCREMENT,
  password varchar(255) NOT NULL,
  username varchar(30) NOT NULL,
  role varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_r43af9ap4edm43mmtq01oddj6 (username),
  UNIQUE KEY UKr43af9ap4edm43mmtq01oddj6 (username)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES users WRITE;
INSERT INTO users VALUES (1,'$2a$10$MtVfGOUoEeGb9qQbSAm.l.n2eXclrYZdXMzNcduEVQJVMAgXv1nby','baiks@gmail.com',''),(2,'$2a$10$ZDKYIv9zGaRs5Mtg7XKo3uxcdFr1ECHyUtI534EpsVTxfqy5fm/vu','baiks123@gmail.com',''),(3,'$2a$10$2i8YXxi6mwxIwaAR/4PoDuG6R1gb86YVPLRdvT/oyzT2widW99Ugy','paul@gmail.com','ADMIN'),(4,'$2a$10$.QZSqvl92xwc9ug54C5Tjujty.ALm0kfy9hUlpBsRsUAfO/JlA5U2','test@gmail.com','ADMIN'),(11,'$2a$10$a.uZSQG0LeOkEnOYdywe0eGxAfYPyXbVNQRdl8Hsiq6aOhnaUSoq.','admin@gmail.com','ADMIN'),(12,'$2a$10$nR2fXT9u5J5iSSWznmFij.4DWuO2NJGrnOlhwjhxkBYQwcDkZpI2G','member@gmail.com','MEMBER');
UNLOCK TABLES;

CREATE TABLE cards (
  id bigint NOT NULL AUTO_INCREMENT,
  color varchar(7) NOT NULL,
  name varchar(30) NOT NULL,
  status varchar(255) NOT NULL DEFAULT 'To do',
  description varchar(255) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  created_at date DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_26r1npuw72mnk52ha9lghxvey (name),
  UNIQUE KEY UK26r1npuw72mnk52ha9lghxvey (name),
  KEY FKcmanafgwbibfijy2o5isfk3d5 (user_id),	
  CONSTRAINT FKcmanafgwbibfijy2o5isfk3d5 FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES cards WRITE;
INSERT INTO cards VALUES (2,'#00000Y','TEST2','In Progress','Tests',12,'2023-08-27'),(4,'#00000Y','TEST34','To do','Tests',11,'2023-08-26'),(5,'#1rRONO','card 23','To do','23',12,'2023-08-27'),(6,'#1rRONO','card 22','To do','22',12,'2023-08-26'),(8,'#1rRONO','card 21','To do','22',12,'2023-08-27');
UNLOCK TABLES;

