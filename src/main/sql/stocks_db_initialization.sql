
/** create the stocks database */

DROP TABLE IF EXISTS quotes CASCADE;
CREATE TABLE quotes(
   id INT NOT NULL AUTO_INCREMENT,
   symbol VARCHAR(4) NOT NULL,
   time DATETIME NOT NULL,
   price DECIMAL NOT NULL,
   PRIMARY KEY ( id )
);

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