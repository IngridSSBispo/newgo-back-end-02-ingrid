-- Table: public.produtos

-- DROP TABLE IF EXISTS public.produtos;

CREATE TABLE IF NOT EXISTS public.produtos
(
    id_produto integer NOT NULL DEFAULT nextval('produtos_id_seq'::regclass),
    hash uuid,
    nome character varying(255) COLLATE pg_catalog."default",
    descricao text COLLATE pg_catalog."default",
    ean13 character varying(13) COLLATE pg_catalog."default",
    preco numeric(13,2),
    quantidade numeric(13,2),
    estoque_min numeric(13,2),
    dtcreate timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    dtupdate timestamp with time zone,
    lativo boolean
)


ALTER TABLE IF EXISTS public.produtos
    OWNER to postgres;