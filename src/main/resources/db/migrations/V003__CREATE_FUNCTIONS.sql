CREATE OR REPLACE FUNCTION aggregate_time(timeseries_id int, tsequence text, afunction text, start timestamp with time zone, _end timestamp with time zone)
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
      AND ($4 is null or timestamp > $4)
      AND ($5 is null or timestamp < $5)
      GROUP BY 1
      ) AS row;
$BODY$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_range(timeseries_id int)
RETURNS jsonb AS
$BODY$
    SELECT jsonb_build_object(
        'last_entry', _end,
        'range', jsonb_build_object('start', start.timestamp, 'end', _end.timestamp)
    )
    FROM(
      SELECT value,timestamp FROM timeseries_data WHERE timeseries_id = $1 ORDER BY timestamp ASC LIMIT 1) start,
       (SELECT value,timestamp FROM timeseries_data WHERE timeseries_id = $1 ORDER BY timestamp DESC LIMIT 1) _end;
$BODY$
LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_timeseries(timeseries_id int, start timestamp with time zone, _end timestamp with time zone)
RETURNS SETOF timeseries_data AS
$BODY$
      select * from timeseries_data  where timeseries_id = $1
      and  ($2 is null or timestamp > $2)
      and  ($3 is null or timestamp < $3)
$BODY$
LANGUAGE SQL;
