SELECT
  position1,
  pname1.name as name1,
  position2,
  pname2.name as name2,
  count(1) AS count,
  SUM(reversed) AS reversed
FROM

(
SELECT
  search_id,
  if(departure_position_fk > arrival_position_fk, departure_position_fk, arrival_position_fk) AS position1,
  if(departure_position_fk > arrival_position_fk, arrival_position_fk, departure_position_fk) AS position2,
  if(departure_position_fk > arrival_position_fk, 0, 1) AS reversed
FROM
  search
WHERE unix_timestamp(added_ts) >= unix_timestamp('2015-01-01','yyyy-mm-ddd')
) AS s

LEFT JOIN position AS pname1
  ON (pname1.position_id = position1)
LEFT JOIN position AS pname2
  ON (pname2.position_id = position2)

GROUP BY position1, pname1.name, position2, pname2.name
ORDER BY count DESC

limit 10000
;