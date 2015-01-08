/* This script builds table named_positions based on location, airport, busstation and station tables
 * We extract position_fk, lat, lon and name. We add country code to better understand where this position is
 * is_* gets marked if this position is a know airport, but or train station.
 */


DROP TABLE IF EXISTS named_positions;

CREATE TABLE named_positions (
  position BIGINT,
  name VARCHAR(250),
  country_code VARCHAR(5),
  is_airport TINYINT,
  is_bus TINYINT,
  is_train TINYINT,
  lat VARCHAR(20),
  lon VARCHAR(20),
  
  PRIMARY KEY(position))
  CHARACTER SET utf8 COLLATE utf8_general_ci;


INSERT INTO named_positions (
  position,
  name,
  country_code,
  lat,
  lon
  )
SELECT 
  position_fk,
  location.name,
  country.code,
  lat,
  lon
FROM location
LEFT JOIN country
  ON (country.country_id = location.country_fk)
WHERE position_fk IS NOT NULL;


INSERT INTO named_positions (
  position,
  name,
  is_airport,
  lat,
  lon
  )
SELECT 
  position_fk,
  name,
  1,
  lat,
  lon
FROM airport
WHERE position_fk IS NOT NULL
ON DUPLICATE KEY UPDATE is_airport = 1;

INSERT INTO named_positions (
  position,
  name,
  is_bus,
  lat,
  lon
  )
SELECT 
  position_fk,
  name,
  1,
  lat,
  lon
FROM busstation
WHERE position_fk IS NOT NULL
ON DUPLICATE KEY UPDATE is_bus = 1;

INSERT INTO named_positions (
  position,
  name,
  is_train,
  lat,
  lon
  )
SELECT 
  position_fk,
  name,
  1,
  lat,
  lon
FROM station
WHERE position_fk IS NOT NULL
ON DUPLICATE KEY UPDATE is_train = 1;