CREATE TABLE IF NOT EXISTS public.produtos
(
    id serial,
    hash uuid,
    nome character varying(255) COLLATE pg_catalog."default",
    descricao text COLLATE pg_catalog."default",
    ean13 character varying(13) COLLATE pg_catalog."default",
    preco numeric,
    quantidade numeric,
    estoque_min numeric,
    dtcreate timestamp with time zone,
    dtupdate timestamp with time zone,
    lativo boolean
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.produtos
    OWNER to postgres;

SELECT * from produtos;

INSERT INTO public.produtos(
	 hash, nome, descricao, ean13, preco, quantidade, estoque_min, dtcreate, dtupdate, lativo)
	VALUES ( 'd2d49a6c-4c1a-11ee-be56-0242ac120002', 'chocolate', 'barra de chocolate', 123456, 10.0, 5, 1 , now(), null, true);


	ALTER TABLE produtos ALTER COLUMN preco TYPE numeric(13,2);

	ALTER TABLE produtos ALTER COLUMN quantidade TYPE numeric(13,2);

	ALTER TABLE produtos RENAME COLUMN id TO id_produto;
