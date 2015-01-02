DROP TABLE IF EXISTS search2;

CREATE TABLE search2 AS
SELECT 
  search_id,
  added_ts AS ts,
  departure_date,
  return_date,
  trip_type,
  adults,
  children,
  if(departure_position_fk > arrival_position_fk, departure_position_fk, arrival_position_fk) AS position1,
  if(departure_position_fk > arrival_position_fk, arrival_position_fk, departure_position_fk) AS position2,
  if(departure_position_fk > arrival_position_fk, 0, 1) AS reversed,
  pname1.name as name1,
  pname1.country_code as country1,
  pname2.name as name2,
  pname2.country_code as country2
FROM 
  search
LEFT JOIN named_positions AS pname1
ON (pname1.position = if(departure_position_fk > arrival_position_fk, departure_position_fk, arrival_position_fk))
LEFT JOIN named_positions AS pname2
ON (pname2.position = if(departure_position_fk > arrival_position_fk, arrival_position_fk, departure_position_fk))
;