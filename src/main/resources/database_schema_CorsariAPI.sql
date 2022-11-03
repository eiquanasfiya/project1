--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2019-12-11 19:27:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 16394)
-- Name: corsarineri; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA corsarineri;


ALTER SCHEMA corsarineri OWNER TO postgres;

--
-- TOC entry 656 (class 1247 OID 16410)
-- Name: anno; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.anno AS integer
	CONSTRAINT anno_check CHECK ((VALUE > 1969));


ALTER DOMAIN public.anno OWNER TO postgres;

--
-- TOC entry 664 (class 1247 OID 16416)
-- Name: cap; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.cap AS character(5)
	CONSTRAINT cap_check CHECK ((VALUE ~ '^[0-9]$'::text));


ALTER DOMAIN public.cap OWNER TO postgres;

--
-- TOC entry 668 (class 1247 OID 16419)
-- Name: latitudinedd; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.latitudinedd AS character varying(50)
	CONSTRAINT latitudinedd_check CHECK (((VALUE)::text ~ '^[\+-]?(([1-8]?\d)(\.\d{1,})?|90)$'::text));


ALTER DOMAIN public.latitudinedd OWNER TO postgres;

--
-- TOC entry 672 (class 1247 OID 16422)
-- Name: longitudinedd; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.longitudinedd AS character varying(50)
	CONSTRAINT longitudinedd_check CHECK (((VALUE)::text ~ '^[\+-]?((1[0-7]\d|[1-9]?\d)(\.\d{1,})?|180)$'::text));


ALTER DOMAIN public.longitudinedd OWNER TO postgres;

--
-- TOC entry 692 (class 1247 OID 16438)
-- Name: dd; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.dd AS (
	latitudine public.latitudinedd,
	longitudine public.longitudinedd
);


ALTER TYPE public.dd OWNER TO postgres;

--
-- TOC entry 676 (class 1247 OID 16425)
-- Name: latitudinedmm; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.latitudinedmm AS character varying(50)
	CONSTRAINT latitudinedmm_check CHECK (((VALUE)::text ~ '^[\+-]?(([1-8]?\d)\s+[1-6]?\d(\.\d{1,})?|90(\s+0)?)$'::text));


ALTER DOMAIN public.latitudinedmm OWNER TO postgres;

--
-- TOC entry 680 (class 1247 OID 16428)
-- Name: longitudinedmm; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.longitudinedmm AS character varying(50)
	CONSTRAINT longitudinedmm_check CHECK (((VALUE)::text ~ '^[\+-]?((1[0-7]\d|[1-9]?\d)\s+[1-6]?\d(\.\d{1,})?|180(\s+0)?)$'::text));


ALTER DOMAIN public.longitudinedmm OWNER TO postgres;

--
-- TOC entry 695 (class 1247 OID 16441)
-- Name: dmm; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.dmm AS (
	latitudine public.latitudinedmm,
	longitudine public.longitudinedmm
);


ALTER TYPE public.dmm OWNER TO postgres;

--
-- TOC entry 684 (class 1247 OID 16431)
-- Name: latitudinedms; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.latitudinedms AS character varying(50)
	CONSTRAINT latitudinedms_check CHECK (((VALUE)::text ~ '^[\+-]?(([1-8]?\d)\D+([1-5]?\d|60)\D+([1-5]?\d|60)(\.\d+)?|90\D+0\D+0)\D+[NSns]$'::text));


ALTER DOMAIN public.latitudinedms OWNER TO postgres;

--
-- TOC entry 688 (class 1247 OID 16434)
-- Name: longitudinedms; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.longitudinedms AS character varying(50)
	CONSTRAINT longitudinedms_check CHECK (((VALUE)::text ~ '^[\+-]?([1-7]?\d{1,2}\D+([1-5]?\d|60)\D+([1-5]?\d|60)(\.\d+)?|180\D+0\D+0)\D+[EWew]$'::text));


ALTER DOMAIN public.longitudinedms OWNER TO postgres;

--
-- TOC entry 698 (class 1247 OID 16444)
-- Name: dms; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.dms AS (
	latitudine public.latitudinedms,
	longitudine public.longitudinedms
);


ALTER TYPE public.dms OWNER TO postgres;

--
-- TOC entry 652 (class 1247 OID 16407)
-- Name: integergez; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.integergez AS integer
	CONSTRAINT integergez_check CHECK ((VALUE >= 0));


ALTER DOMAIN public.integergez OWNER TO postgres;

--
-- TOC entry 648 (class 1247 OID 16404)
-- Name: integergz; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.integergz AS integer
	CONSTRAINT integergz_check CHECK ((VALUE > 0));


ALTER DOMAIN public.integergz OWNER TO postgres;

--
-- TOC entry 642 (class 1247 OID 16400)
-- Name: stringl; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.stringl AS character varying(3000);


ALTER DOMAIN public.stringl OWNER TO postgres;

--
-- TOC entry 554 (class 1247 OID 16398)
-- Name: stringm; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.stringm AS character varying(1000);


ALTER DOMAIN public.stringm OWNER TO postgres;

--
-- TOC entry 551 (class 1247 OID 16396)
-- Name: strings; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.strings AS character varying(250);


ALTER DOMAIN public.strings OWNER TO postgres;

--
-- TOC entry 645 (class 1247 OID 16402)
-- Name: stringxl; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.stringxl AS character varying(6000);


ALTER DOMAIN public.stringxl OWNER TO postgres;

--
-- TOC entry 660 (class 1247 OID 16413)
-- Name: vettorealfabetico; Type: DOMAIN; Schema: public; Owner: postgres
--

CREATE DOMAIN public.vettorealfabetico AS character(26)
	CONSTRAINT vettorealfabetico_check CHECK ((VALUE ~ '^[A-Z]$'::text));


ALTER DOMAIN public.vettorealfabetico OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 207 (class 1259 OID 16447)
-- Name: biblioteca; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.biblioteca (
    id integer NOT NULL,
    titolo public.stringm NOT NULL,
    url public.stringxl NOT NULL,
    testo text,
    tag public.stringl
);


ALTER TABLE corsarineri.biblioteca OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16445)
-- Name: biblioteca_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.biblioteca_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.biblioteca_id_seq OWNER TO postgres;

--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 206
-- Name: biblioteca_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.biblioteca_id_seq OWNED BY corsarineri.biblioteca.id;


--
-- TOC entry 209 (class 1259 OID 16471)
-- Name: indizio; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.indizio (
    id integer NOT NULL,
    numero public.integergz NOT NULL,
    nome public.stringm NOT NULL,
    anno public.anno NOT NULL,
    aiuto_arrivato boolean,
    orario_ritiro_comitato timestamp without time zone NOT NULL,
    orario_arrivo_in_base timestamp without time zone NOT NULL,
    orario_aiuto timestamp without time zone NOT NULL,
    orario_soluzione timestamp without time zone NOT NULL,
    CONSTRAINT indizio_check CHECK ((orario_ritiro_comitato <= orario_arrivo_in_base)),
    CONSTRAINT indizio_check1 CHECK ((orario_aiuto <= orario_soluzione))
);


ALTER TABLE corsarineri.indizio OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16469)
-- Name: indizio_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.indizio_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.indizio_id_seq OWNER TO postgres;

--
-- TOC entry 2946 (class 0 OID 0)
-- Dependencies: 208
-- Name: indizio_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.indizio_id_seq OWNED BY corsarineri.indizio.id;


--
-- TOC entry 217 (class 1259 OID 24578)
-- Name: poi; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.poi (
    id integer NOT NULL,
    luogo public.stringm NOT NULL,
    lat numeric,
    lon numeric,
    tag public.stringl,
    cap public.cap,
    commento public.stringxl,
    frazione public.stringm,
    comune public.stringm
);


ALTER TABLE corsarineri.poi OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 24576)
-- Name: poi_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.poi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.poi_id_seq OWNER TO postgres;

--
-- TOC entry 2947 (class 0 OID 0)
-- Dependencies: 216
-- Name: poi_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.poi_id_seq OWNED BY corsarineri.poi.id;


--
-- TOC entry 213 (class 1259 OID 16504)
-- Name: soluzione; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.soluzione (
    id integer NOT NULL,
    indizio integer,
    ragionamento public.stringxl NOT NULL,
    stato public.strings NOT NULL,
    fase public.strings NOT NULL,
    scartato boolean,
    orario_invio_a_tc timestamp without time zone NOT NULL,
    orario_archiviazione timestamp without time zone,
    orario_stampa timestamp without time zone,
    semaforo public.strings,
    correzione public.stringxl,
    luogo_soluzione public.stringl,
    correzione_luogo_soluzione public.stringxl
);


ALTER TABLE corsarineri.soluzione OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16502)
-- Name: soluzione_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.soluzione_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.soluzione_id_seq OWNER TO postgres;

--
-- TOC entry 2948 (class 0 OID 0)
-- Dependencies: 212
-- Name: soluzione_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.soluzione_id_seq OWNED BY corsarineri.soluzione.id;


--
-- TOC entry 211 (class 1259 OID 16486)
-- Name: uri; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.uri (
    id integer NOT NULL,
    percorso public.stringl NOT NULL,
    indizio integer,
    tipo public.strings
);


ALTER TABLE corsarineri.uri OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16484)
-- Name: uri_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.uri_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.uri_id_seq OWNER TO postgres;

--
-- TOC entry 2949 (class 0 OID 0)
-- Dependencies: 210
-- Name: uri_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.uri_id_seq OWNED BY corsarineri.uri.id;


--
-- TOC entry 215 (class 1259 OID 16553)
-- Name: wordlist; Type: TABLE; Schema: corsarineri; Owner: postgres
--

CREATE TABLE corsarineri.wordlist (
    id integer NOT NULL,
    word character varying(50) NOT NULL
);


ALTER TABLE corsarineri.wordlist OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16551)
-- Name: wordlist_id_seq; Type: SEQUENCE; Schema: corsarineri; Owner: postgres
--

CREATE SEQUENCE corsarineri.wordlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corsarineri.wordlist_id_seq OWNER TO postgres;

--
-- TOC entry 2950 (class 0 OID 0)
-- Dependencies: 214
-- Name: wordlist_id_seq; Type: SEQUENCE OWNED BY; Schema: corsarineri; Owner: postgres
--

ALTER SEQUENCE corsarineri.wordlist_id_seq OWNED BY corsarineri.wordlist.id;



CREATE TABLE corsarineri.utente (
    id integer NOT NULL,
    nome strings NOT NULL,
	username strings,
	password strings,
	ultimo_ip strings,
	ultimo_host strings 
);


ALTER TABLE corsarineri.utente OWNER TO postgres;

CREATE SEQUENCE corsarineri.utente_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER TABLE corsarineri.utente_id_seq OWNER TO postgres;

ALTER SEQUENCE corsarineri.utente_id_seq OWNED BY corsarineri.utente.id;





CREATE TABLE corsarineri.ordine (
    id integer NOT NULL,
    nome strings NOT NULL,
	tavolo strings,
	ordine stringm,
	stato_ordine strings,
	orario_ordine timestamp without time zone
);


ALTER TABLE corsarineri.ordine OWNER TO postgres;

CREATE SEQUENCE corsarineri.ordine
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER TABLE corsarineri.ordine_id_seq OWNER TO postgres;

ALTER SEQUENCE corsarineri.ordine_id_seq OWNED BY corsarineri.ordine.id;

--
-- TOC entry 2791 (class 2604 OID 16450)
-- Name: biblioteca id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.biblioteca ALTER COLUMN id SET DEFAULT nextval('corsarineri.biblioteca_id_seq'::regclass);


--
-- TOC entry 2792 (class 2604 OID 16474)
-- Name: indizio id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.indizio ALTER COLUMN id SET DEFAULT nextval('corsarineri.indizio_id_seq'::regclass);


--
-- TOC entry 2798 (class 2604 OID 24581)
-- Name: poi id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.poi ALTER COLUMN id SET DEFAULT nextval('corsarineri.poi_id_seq'::regclass);


--
-- TOC entry 2796 (class 2604 OID 16507)
-- Name: soluzione id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.soluzione ALTER COLUMN id SET DEFAULT nextval('corsarineri.soluzione_id_seq'::regclass);


--
-- TOC entry 2795 (class 2604 OID 16489)
-- Name: uri id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.uri ALTER COLUMN id SET DEFAULT nextval('corsarineri.uri_id_seq'::regclass);


--
-- TOC entry 2797 (class 2604 OID 16556)
-- Name: wordlist id; Type: DEFAULT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.wordlist ALTER COLUMN id SET DEFAULT nextval('corsarineri.wordlist_id_seq'::regclass);

ALTER TABLE ONLY corsarineri.ordine ALTER COLUMN id SET DEFAULT nextval('corsarineri.ordine_id_seq'::regclass);

ALTER TABLE ONLY corsarineri.ordine
    ADD CONSTRAINT ordine_pkey PRIMARY KEY (id);

ALTER TABLE ONLY corsarineri.utente ALTER COLUMN id SET DEFAULT nextval('corsarineri.utente_id_seq'::regclass);

ALTER TABLE ONLY corsarineri.utente
    ADD CONSTRAINT utente_pkey PRIMARY KEY (id);
--
-- TOC entry 2800 (class 2606 OID 16455)
-- Name: biblioteca biblioteca_pkey; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.biblioteca
    ADD CONSTRAINT biblioteca_pkey PRIMARY KEY (id);


--
-- TOC entry 2802 (class 2606 OID 16483)
-- Name: indizio indizio_numero_key; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.indizio
    ADD CONSTRAINT indizio_numero_key UNIQUE (numero);


--
-- TOC entry 2804 (class 2606 OID 16481)
-- Name: indizio indizio_pkey; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.indizio
    ADD CONSTRAINT indizio_pkey PRIMARY KEY (id);


--
-- TOC entry 2812 (class 2606 OID 24586)
-- Name: poi poi_pkey; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.poi
    ADD CONSTRAINT poi_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 16512)
-- Name: soluzione soluzione_pkey; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.soluzione
    ADD CONSTRAINT soluzione_pkey PRIMARY KEY (id);


--
-- TOC entry 2806 (class 2606 OID 16496)
-- Name: uri uri_percorso_key; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.uri
    ADD CONSTRAINT uri_percorso_key UNIQUE (percorso);


--
-- TOC entry 2808 (class 2606 OID 16494)
-- Name: uri uri_pkey; Type: CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.uri
    ADD CONSTRAINT uri_pkey PRIMARY KEY (id);


--
-- TOC entry 2813 (class 2606 OID 16497)
-- Name: uri uri_indizio_fkey; Type: FK CONSTRAINT; Schema: corsarineri; Owner: postgres
--

ALTER TABLE ONLY corsarineri.uri
    ADD CONSTRAINT uri_indizio_fkey FOREIGN KEY (indizio) REFERENCES corsarineri.indizio(numero) DEFERRABLE;

-- new column addition
ALTER TABLE corsarineri.indizio
ADD aiuto_arrivato2 boolean;


-- Completed on 2019-12-11 19:27:20

--
-- PostgreSQL database dump complete
--

