/* List top 1000 search hubs, cities from where search originates or terminates
 */

# Select 1000 most frequent positions
SELECT 
  search3.position AS position, 
  pname.name as name,
  pname.country_code as country,
  count(*) AS count
FROM search3
LEFT JOIN named_positions AS pname
  ON (pname.position = search3.position)
GROUP BY position
ORDER BY count DESC
LIMIT 1000;
