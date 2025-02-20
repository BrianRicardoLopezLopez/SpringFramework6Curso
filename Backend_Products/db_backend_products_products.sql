CREATE DATABASE `db_backend_products`;
USE `db_backend_products`;

CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` text,
  `price` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);