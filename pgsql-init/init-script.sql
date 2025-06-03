CREATE DATABASE ordersys
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE IF NOT EXISTS public.product
(
    id integer NOT NULL DEFAULT nextval('product_id_seq'::regclass),
    name text COLLATE pg_catalog."default",
    price integer,
    category integer,
    description text COLLATE pg_catalog."default",
    image text COLLATE pg_catalog."default",
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT product_pkey PRIMARY KEY (id)
)