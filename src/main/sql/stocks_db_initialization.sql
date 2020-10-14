
/**
  Create the stocks database
 */

/* Cleanup. Delete tables if they already exist */
DROP TABLE IF EXISTS person_quotes CASCADE;
DROP TABLE IF EXISTS quotes CASCADE;
DROP TABLE IF EXISTS person CASCADE;


/* Creates a quotes table to store stock quotes */
CREATE TABLE quotes(
                       id INT NOT NULL AUTO_INCREMENT,
                       symbol VARCHAR(4) NOT NULL,
                       time DATETIME NOT NULL,
                       price DECIMAL NOT NULL,
                       PRIMARY KEY ( id )
);

/* Adds sample data into the quotes table */
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2004-08-19 00:00:01','85.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2015-02-03 00:00:01','527.35');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2000-01-01 00:00:01','118.27');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2015-02-03 00:00:01','363.21');

INSERT INTO quotes (symbol,time,price) VALUES ('IBM','2010-04-29 00:00:01','95.50');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2001-02-21 00:00:01','252.35');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2004-7-31 00:00:01','121.21');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2002-01-31 00:00:01','75.34');

INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2013-03-29 00:00:01','411.14');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2019-02-03 00:00:01','722.35');
INSERT INTO quotes (symbol,time,price) VALUES ('IBM','2000-09-01 00:00:01','178.18');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2012-03-01 00:00:01','771.42');

INSERT INTO quotes (symbol,time,price) VALUES ('IBM','2011-11-11 00:00:01','185.00');
INSERT INTO quotes (symbol,time,price) VALUES ('GOOG','2007-10-31 00:00:01','431.35');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2011-05-17 00:00:01','395.32');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2014-04-05 00:00:01','116.21');

INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2004-07-18 00:00:01','376.00');
INSERT INTO quotes (symbol,time,price) VALUES ('IBM','2015-12-05 00:00:01','63.35');
INSERT INTO quotes (symbol,time,price) VALUES ('APPL','2002-09-18 00:00:01','250.50');
INSERT INTO quotes (symbol,time,price) VALUES ('AMZN','2020-06-22 00:00:01','660.66');

/* Creates a person table to store a list of people */
CREATE TABLE stocks.person
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(256) NOT NULL
);

/* Adds sample data into the person table */

INSERT INTO person (first_name, last_name) VALUES ('Sam', 'Malone');
INSERT INTO person (first_name, last_name) VALUES ('Diane', 'Chambers');
INSERT INTO person (first_name, last_name) VALUES ('Carla', 'Tortelli');
INSERT INTO person (first_name, last_name) VALUES ('Norm', 'Peterson');
INSERT INTO person (first_name, last_name) VALUES ('Cliff', 'Clavin');
INSERT INTO person (first_name, last_name) VALUES ('Frasier', 'Crane');
INSERT INTO person (first_name, last_name) VALUES ('Lilith', 'Sternin');
INSERT INTO person (first_name, last_name) VALUES ('Woody', 'Boyd');
INSERT INTO person (first_name, last_name) VALUES ('Rebecca', 'Howe');
INSERT INTO person (first_name, last_name) VALUES ('Coach', 'Pantuso');


/* Creates a table that maps person entries to quotes entries */
CREATE TABLE stocks.person_quotes
(
    ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    person_id INT NOT NULL,
    quote_id INT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (ID),
    FOREIGN KEY (quote_id) REFERENCES quotes (ID)
);

/* Adds sample data into the person_quotes table? MAYBE NOT... */

INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (1, 1, 1);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (2, 1, 2);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (3, 2, 3);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (4, 2, 4);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (5, 3, 5);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (6, 3, 6);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (7, 4, 7);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (8, 4, 8);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (9, 5, 9);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (10, 5, 10);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (11, 6, 11);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (12, 6, 12);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (13, 7, 13);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (14, 7, 14);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (15, 8, 15);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (16, 8, 16);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (17, 9, 17);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (18, 9, 18);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (19, 10, 19);
INSERT INTO person_quotes (ID, person_id, quote_id) VALUES (20, 10, 20);