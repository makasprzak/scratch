/* List top 100 search corridors
 */

# Select 1000 most frequent corridors
SELECT 
  position1, 
  pname1.name as name1,
  pname1.country_code as country1,
  position2, 
  pname2.name as name2,
  pname2.country_code as country2,
  count(*) AS count,
  SUM(reversed) AS reversed
FROM search2
LEFT JOIN named_positions AS pname1
  ON (pname1.position = position1)
LEFT JOIN named_positions AS pname2
  ON (pname2.position = position2)
GROUP BY position1, position2
ORDER BY count DESC
LIMIT 1000;

# Least frequent searches
SELECT * 
FROM (
SELECT 
  position1, 
  pname1.name as name1,
  pname1.country_code as country1,
  position2, 
  pname2.name as name2,
  pname2.country_code as country2,
  count(*) AS count
FROM search2
LEFT JOIN named_positions AS pname1
  ON (pname1.position = position1)
LEFT JOIN named_positions AS pname2
  ON (pname2.position = position2)
GROUP BY position1, position2
) AS search_frequency
WHERE count = 1
;

# Count least frequent searches
SELECT 
  COUNT(*) 
FROM (
SELECT 
  position1, 
  pname1.name as name1,
  pname1.country_code as country1,
  position2, 
  pname2.name as name2,
  pname2.country_code as country2,
  count(*) AS count
FROM search2
LEFT JOIN named_positions AS pname1
  ON (pname1.position = position1)
LEFT JOIN named_positions AS pname2
  ON (pname2.position = position2)
GROUP BY position1, position2
) AS search_frequency
WHERE count = 1
;

# Count all records in this table
SELECT
  COUNT(*)
FROM
  search2
;