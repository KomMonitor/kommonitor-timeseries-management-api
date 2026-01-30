CREATE OR REPLACE FUNCTION aggregate_time(timeseries_id int, tsequence text, afunction text)
RETURNS jsonb AS
$BODY$
      SELECT jsonb_agg(row)
      FROM (
      SELECT
        time_bucket(cast($2 as interval), timestamp), 
        (CASE 
        WHEN $3='avg' THEN avg(value) 
        WHEN $3='min' THEN min(value) 
        WHEN $3='max' THEN max(value) 
        WHEN $3='sum' THEN sum(value) 
        END) 
        AS aggregated_values
      FROM timeseries_data 
      WHERE timeseries_id = $1
      GROUP BY 1
      ) AS row;
$BODY$
LANGUAGE SQL;
