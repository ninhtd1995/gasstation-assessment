DROP TABLE IF EXISTS GasPump;

CREATE TABLE GasPump (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  gasType VARCHAR(250),
  amount number(250)
);