CREATE EXTENSION IF NOT EXISTS postgis;

CREATE TABLE "stations"(
  id int NOT NULL,
  name TEXT,
  position geometry(Point,4326),
  CONSTRAINT stations_pkey PRIMARY KEY (id)
);

CREATE TABLE "parameters"(
  id int NOT NULL,
  name TEXT,
  unit TEXT,
  CONSTRAINT units_pkey PRIMARY KEY (id)
);

CREATE TABLE "stations_parameters"(
  stations_id int not null,
  parameters_id int not null,
  CONSTRAINT stations_parameters_pkey PRIMARY KEY (stations_id, parameters_id),
  CONSTRAINT stations_parameters_station_fkey FOREIGN KEY (stations_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION
      ON DELETE NO ACTION,
  CONSTRAINT stations_parameters_parameter_fkex FOREIGN KEY (parameters_id)
      REFERENCES public.parameters (id) MATCH SIMPLE
      ON UPDATE NO ACTION
      ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.parameter_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;

CREATE TABLE "timeseries_metadata"(
  id int not null,
  stations_id int not null,
  parameters_id int not null,
  CONSTRAINT timeseries_metadata_pkey PRIMARY KEY (id),
  CONSTRAINT timeseries_station_fkey FOREIGN KEY (stations_id)
      REFERENCES public.stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION
      ON DELETE NO ACTION,
  CONSTRAINT timeseries_parameter_fkex FOREIGN KEY (parameters_id)
      REFERENCES public.parameters (id) MATCH SIMPLE
      ON UPDATE NO ACTION
      ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.timeseries_metadata_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;

CREATE TABLE "timeseries_data"(
  id int not null,
  timeseries_id int not null,
  value NUMERIC,
  timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT timeseries_fkey FOREIGN KEY (timeseries_id)
      REFERENCES public.timeseries_metadata (id) MATCH SIMPLE
      ON UPDATE NO ACTION
      ON DELETE NO ACTION
) WITH (
  tsdb.hypertable,
  tsdb.create_default_indexes=false,
  tsdb.segmentby='timeseries_id',
  tsdb.orderby='timestamp DESC'
);

CREATE SEQUENCE IF NOT EXISTS public.timeseries_data_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;