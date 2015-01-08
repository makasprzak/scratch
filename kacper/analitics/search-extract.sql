/* Build data on communication corridors (i.e. searches between two locations independent of the direction
 * Create table search2 with departure and arrival positions sorted numerically. 
 * If position1 is not a departure position reversed is set.
 */

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
  if(departure_position_fk > arrival_position_fk, 0, 1) AS reversed
FROM 
  search
;

/* Table to collect all hubs, largest travel cities
 */
DROP TABLE IF EXISTS search3;

CREATE TABLE search3 AS
SELECT 
  *
FROM (
SELECT 
  search_id,
  departure_position_fk AS position
FROM 
  search
  
UNION ALL

SELECT 
  search_id,
  arrival_position_fk
FROM 
  search
) AS search_doubled
;