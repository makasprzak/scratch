SELECT
  position, p.name AS pos_name, count(1) as search_num
FROM (
       SELECT
         search_id,
         departure_position_fk AS position
       FROM
         search
       WHERE unix_timestamp(added_ts) >= unix_timestamp('2015-01-01','yyyy-mm-ddd')

       UNION ALL

       SELECT
         search_id,
         arrival_position_fk AS position
       FROM
         search
       WHERE unix_timestamp(added_ts) >= unix_timestamp('2015-01-01','yyyy-mm-ddd')
     ) AS s

  LEFT JOIN position AS p
    ON (p.position_id = s.position)

group by s.position, name
order by search_num desc
limit 10000
;