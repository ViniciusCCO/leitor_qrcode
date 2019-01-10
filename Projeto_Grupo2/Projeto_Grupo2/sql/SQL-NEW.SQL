drop database if exists vanilla;

CREATE DATABASE vanilla DEFAULT CHARACTER SET utf8; 

USE vanilla; 

CREATE TABLE users 
  ( 
     `user_id`       INT(11) UNSIGNED NOT NULL AUTO_INCREMENT, 
     `fullname`      VARCHAR(100), 
     `cpf`           VARCHAR(11),
     `birth_date`    DATETIME,
     `create_at`     TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
     `updated_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, 
     PRIMARY KEY (`user_id`), 
     UNIQUE      (`cpf`)
  ); 

CREATE TABLE cnh 
  ( 
     `document_id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
     `type_document_id`      CHAR DEFAULT 'A' NOT NULL, 
     `user_id`               INT(11) UNSIGNED NOT NULL,
     `register`              VARCHAR(11) NOT NULL,
     `category`              VARCHAR(3) NOT NULL,
     `city`                  VARCHAR(100),
     `uf`                    VARCHAR(2),
     `emission_at`           DATETIME NOT NULL,
     `due_date_at`           DATETIME NOT NULL, 
     `biometry`              MEDIUMBLOB,
     `validate_code`		 CHAR(8) NOT NULL,
     PRIMARY KEY (`document_id`), 
     FOREIGN KEY (`user_id`) REFERENCES users(`user_id`) ON DELETE CASCADE,
     UNIQUE      (`register`),
     UNIQUE      (`validate_code`),
     UNIQUE      (`user_id`)
  ); 

CREATE TABLE ctps 
  ( 
     `document_id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
     `type_document_id`      CHAR DEFAULT 'B' NOT NULL,
     `user_id`               INT(11) UNSIGNED NOT NULL,
     `number`                VARCHAR(10) NOT NULL,
     `serie`                 VARCHAR(10) NOT NULL,
     `birth_city`            VARCHAR(100),
     `birth_uf`              VARCHAR(2),
     `emission_at`           DATETIME NOT NULL,
     `biometry`              MEDIUMBLOB, 
     `validate_code`		 CHAR(8) NOT NULL,
     PRIMARY KEY (`document_id`), 
     FOREIGN KEY (`user_id`) REFERENCES users(`user_id`) ON DELETE CASCADE,
     UNIQUE      (`validate_code`),
     UNIQUE      (`user_id`)
  ); 

CREATE TABLE etitulo 
  ( 
     `document_id`           INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
     `type_document_id`      CHAR DEFAULT 'C' NOT NULL,
     `user_id`               INT(11) UNSIGNED NOT NULL,
     `register`              VARCHAR(20) NOT NULL,
     `zone`                  INT(8) UNSIGNED NOT NULL,
     `section`               VARCHAR(5) NOT NULL,
     `city`                  VARCHAR(100),
     `uf`                    VARCHAR(2),
     `emission_at`           DATETIME NOT NULL,
     `biometry`              MEDIUMBLOB,
     `validate_code`		 CHAR(8) NOT NULL,
     PRIMARY KEY (`document_id`), 
     FOREIGN KEY (`user_id`) REFERENCES users(`user_id`) ON DELETE CASCADE,
     UNIQUE      (`register`),
     UNIQUE      (`validate_code`),
     UNIQUE      (`user_id`)
  ); 
 
CREATE TABLE `query` 
  ( 
     `query_id`                 INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
     `cnh_id`                   INT(11) UNSIGNED,
     `ctps_id`                  INT(11) UNSIGNED,
     `etitulo_id`               INT(11) UNSIGNED,
     `query_at`                 TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `result`                   ENUM('Valido', 'Invalido') DEFAULT 'Invalido',
     PRIMARY KEY (`query_id`), 
     FOREIGN KEY (`cnh_id`)     REFERENCES cnh(`document_id`) ON DELETE CASCADE,
     FOREIGN KEY (`etitulo_id`) REFERENCES etitulo(`document_id`) ON DELETE CASCADE,
     FOREIGN KEY (`ctps_id`)    REFERENCES ctps(`document_id`) ON DELETE CASCADE
  );