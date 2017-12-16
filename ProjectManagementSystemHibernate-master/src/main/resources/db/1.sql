USE projectmanagementdb;

ALTER TABLE developers
  ADD salary INT NOT NULL
  AFTER name;

UPDATE developers
SET salary = 1780
WHERE id = 1;
UPDATE developers
SET salary = 1100
WHERE id = 2;
UPDATE developers
SET salary = 1050
WHERE id = 3;
UPDATE developers
SET salary = 1700
WHERE id = 4;
UPDATE developers
SET salary = 1400
WHERE id = 5;
UPDATE developers
SET salary = 1600
WHERE id = 6;
UPDATE developers
SET salary = 1230
WHERE id = 7;
UPDATE developers
SET salary = 1820
WHERE id = 8;
UPDATE developers
SET salary = 1060
WHERE id = 9;
UPDATE developers
SET salary = 1000
WHERE id = 10;