DROP DATABASE IF EXISTS hibernate_not_found;

CREATE DATABASE hibernate_not_found;


USE hibernate_not_found;

CREATE TABLE author(
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE book(
                     id INT NOT NULL AUTO_INCREMENT,
                     title VARCHAR(255) NOT NULL,
                     author_id INT NOT NULL,
                     PRIMARY KEY (id)
);


INSERT INTO author(name)VALUES ("author1");
INSERT INTO author(name)VALUES ("author1");

INSERT INTO book(title, author_id)VALUES ("book 1", 1);
INSERT INTO book(title, author_id)VALUES ("book 2", 1);

DELETE FROM author WHERE id = 1;