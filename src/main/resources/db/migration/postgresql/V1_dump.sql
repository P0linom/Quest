--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: reservation; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.reservation (
    id integer NOT NULL,
    user_id integer NOT NULL,
    date date NOT NULL,
    time_from time without time zone NOT NULL,
    time_to time without time zone NOT NULL,
    room_id integer,
    reservation_description text
);


ALTER TABLE public.reservation OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reservation_id_seq OWNER TO postgres;

--
-- Name: reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.reservation_id_seq OWNED BY public.reservation.id;


--
-- Name: room; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.room (
    id integer NOT NULL,
    name text NOT NULL
);


ALTER TABLE public.room OWNER TO postgres;

--
-- Name: room_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.room_id_seq OWNER TO postgres;

--
-- Name: room_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.room_id_seq OWNED BY public.room.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace:
--

CREATE TABLE public.users (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    role text
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation ALTER COLUMN id SET DEFAULT nextval('public.reservation_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.room ALTER COLUMN id SET DEFAULT nextval('public.room_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reservation (id, user_id, date, time_from, time_to, room_id, reservation_description) FROM stdin;
12	1	2019-10-30	03:00:00	10:00:00	2	Estate was tended ten boy nearer seemed. He felicity no an at packages answered opinions juvenile. Draw from upon here gone add one. Dissimilar admiration so terminated no in contrasted it. Fortune day out married parties.
10	1	2019-10-28	14:00:00	15:00:00	1	Their saved linen downs tears son add music. How one dull get busy dare fa
8	1	2019-10-28	15:00:00	16:00:00	1	Painful so he an comfort is manners. How one dull get busy dare far. Happiness remainder joy but earnestly for off.
5	1	2019-10-24	02:00:00	12:00:00	1	Girl quit if case mr sing as no have. If in so bred at dare rose lose good.
3	1	2019-10-24	00:00:00	01:30:00	1	Am wound worth water he linen at vexed
13	3	2019-10-31	11:00:00	11:30:00	1	An concluded sportsman offending so provision mr education. He in sportsman household otherwise it perceived instantly. Equally he minutes my hastily. Do play they miss give so up. Am wound worth water he linen at vexed.. Pain son rose more park way that. We me rent been part what. He felicity no an a
11	4	2019-10-29	07:00:00	15:00:00	1	Pain son rose more park way that. He felicity no an at packages answered opinions juvenile.
15	4	2019-11-05	10:00:00	14:00:00	1	Pain son rose more park way that. He felicity no an at packages answered opinions juvenile.
14	5	2019-11-01	08:00:00	10:00:00	3	Estate was tended ten boy nearer seemed. He felicity no an at packages answered opinions juvenile. Draw from upon here gone add one. Dissimilar admiration so terminated no in contrasted it. Fortune day out married parties.
9	5	2019-10-28	18:00:00	21:00:00	4	Estate was tended ten boy nearer seemed. He felicity no an at packages answered opinions juvenile. Draw from upon here gone add one. Dissimilar admiration so terminated no in contrasted it. Fortune day out married parties.
6	1	2019-10-21	09:00:00	10:00:00	2	Hi!Estate was tended ten boy nearer seemed. He felicity no an at packages answered opinions juvenile. Draw from upon here gone add one. Dissimilar admiration so terminated no in contrasted it. Fortune day out married parties.
16	1	2019-11-02	15:00:00	18:00:00	4	Pain son rose more park way that. He felicity no an at packages answered opinions juvenile.
17	1	2019-11-02	09:00:00	10:00:00	1
18	1	2019-10-31	09:00:00	10:00:00	1
\.


--
-- Name: reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reservation_id_seq', 18, true);


--
-- Data for Name: room; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.room (id, name) FROM stdin;
1	room1
2	room2
3	mainRoom
4	oldRoom
\.


--
-- Name: room_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.room_id_seq', 4, true);


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, login, password, role) FROM stdin;
1	user1	$2a$10$RsIveMrvycGbsTT.1oGUeuyLZTbmRkWuDzCiZ/Xm5Cv9WNB79F4eC	USER
3	user2	$2a$10$vyJFgA5TeX/kHQwn.483k.QSzaA2XtDp7EivYW/Zo5y/vaN3DdwXG	USER
4	user4	$2a$10$8VomPKCV7pt/VVCrM9bT7eBL6ASKamnd7Qpa/oSNqelHPcplffp4G	USER
5	user5	$2a$10$Tm484M5oMoTV0nWvz3kBvuV3POXvkkGDbvp.QTHM0GdpX6obkIyAy	USER
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 5, true);


--
-- Name: reservation_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pk PRIMARY KEY (id);


--
-- Name: room_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);


--
-- Name: users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace:
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


--
-- Name: date_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX date_idx ON public.reservation USING btree (date);


--
-- Name: login_idx; Type: INDEX; Schema: public; Owner: postgres; Tablespace:
--

CREATE INDEX login_idx ON public.users USING btree (login);


--
-- Name: reservation_fk0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_fk0 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: reservation_room_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_room_id_fkey FOREIGN KEY (room_id) REFERENCES public.room(id);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

