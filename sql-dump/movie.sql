--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

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
-- Name: actor_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.actor_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actor_id_seq;

--
-- Name: actor_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.actor_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.actor_seq;

--
-- Name: cinema_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.cinema_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cinema_seq;

--
-- Name: genre_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.genre_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genre_id_seq;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: genres; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.genres (
    id bigint NOT NULL,
    deleted boolean DEFAULT false,
    secure_id character varying(255) NOT NULL,
    genre character varying(255) NOT NULL
);


ALTER TABLE public.genres;

--
-- Name: genres_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.genres_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.genres_id_seq;

--
-- Name: genres_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jason
--

ALTER SEQUENCE public.genres_id_seq OWNED BY public.genres.id;


--
-- Name: movie; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.movie (
    id bigint NOT NULL,
    deleted boolean DEFAULT false,
    secure_id character varying(255) NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.movie;

--
-- Name: movie_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.movie_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_id_seq;

--
-- Name: movie_id_seq1; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.movie_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_id_seq1;

--
-- Name: movie_id_seq1; Type: SEQUENCE OWNED BY; Schema: public; Owner: jason
--

ALTER SEQUENCE public.movie_id_seq1 OWNED BY public.movie.id;


--
-- Name: movies_acted; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.movies_acted (
    movie_id bigint NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.movies_acted;

--
-- Name: movies_directed; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.movies_directed (
    movie_id bigint NOT NULL,
    person_id bigint NOT NULL
);


ALTER TABLE public.movies_directed;

--
-- Name: movies_genre; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.movies_genre (
    movie_id bigint NOT NULL,
    genre_id bigint NOT NULL
);


ALTER TABLE public.movies_genre;

--
-- Name: person; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.person (
    id bigint NOT NULL,
    deleted boolean DEFAULT false,
    secure_id character varying(255) NOT NULL,
    age integer NOT NULL,
    person_name character varying(300) NOT NULL
);


ALTER TABLE public.person;

--
-- Name: person_generator; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.person_generator
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_generator;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.person_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.person_id_seq;

--
-- Name: review; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.review (
    id bigint NOT NULL,
    deleted boolean DEFAULT false,
    secure_id character varying(255) NOT NULL,
    comment character varying(500),
    star integer NOT NULL,
    movie_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.review;

--
-- Name: review_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.review_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.review_id_seq;

--
-- Name: review_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.review_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.review_seq;

--
-- Name: role; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.role;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jason
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: jason
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq;

--
-- Name: user_role; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.user_role (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_role;

--
-- Name: users; Type: TABLE; Schema: public; Owner: jason
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    deleted boolean DEFAULT false,
    secure_id character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    user_name character varying(255) NOT NULL
);


ALTER TABLE public.users;

--
-- Name: genres id; Type: DEFAULT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.genres ALTER COLUMN id SET DEFAULT nextval('public.genres_id_seq'::regclass);


--
-- Name: movie id; Type: DEFAULT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movie ALTER COLUMN id SET DEFAULT nextval('public.movie_id_seq1'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Data for Name: genres; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.genres (id, deleted, secure_id, genre) FROM stdin;
1	f	bed89c3c-ec8d-4d0b-b60e-aae3aec4820c	Action
3	f	5682b1f9-0b78-472b-b5c9-5a753d0f19d9	Sci-fi
5	f	3f15578d-7bfa-4def-a9db-55dbb90e0def	Adventure
6	f	19aea467-24df-4d11-b7bb-9930c35494ff	Drama
7	f	a2d1b270-d9ae-4069-90e5-aab6687964b9	Fantasy
8	f	412b7720-f9f9-4e49-bd5b-797c12ed80c6	Horror
9	f	b369531a-38ce-4524-ac5b-fdac01c1c904	Mystery
10	f	8493ec6b-3a93-467a-b9a2-2e9f95413d47	Romance
11	f	d311fa03-e2bb-483a-bf19-8b2728073016	Thriller
12	f	78859887-b577-4374-8926-6691c5a06ff8	Comedy
\.


--
-- Data for Name: movie; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.movie (id, deleted, secure_id, title) FROM stdin;
1	f	4c75d7ce-91b8-4ce6-89a1-be32f8995d72	Karate Kid
2	f	651c6e59-e851-40c5-98d2-7222a4bb490e	Spider-Man: No Way Home
3	f	03acbe49-1077-488d-98d8-73b2a96bd2cd	By The Sea
5	f	174976f0-70dd-4e41-a626-5543da7292be	Top Gun
6	f	899754fb-21ba-48d8-986b-94c1007a1ebd	Don't Look Up
7	f	f182b7e0-4ec5-42de-982a-c7f7ee4320f5	Avengers: Endgame
8	f	4d366836-be8f-4387-b21e-e8daf9e2d252	The Avengers
9	f	1ef042b9-09ec-47c0-ac93-996ca8ac6a29	Red Notice
\.


--
-- Data for Name: movies_acted; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.movies_acted (movie_id, person_id) FROM stdin;
1	52
1	103
2	152
2	153
3	306
5	252
6	303
6	304
7	152
7	302
7	305
8	302
8	305
9	308
9	309
\.


--
-- Data for Name: movies_directed; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.movies_directed (movie_id, person_id) FROM stdin;
1	102
2	154
3	306
5	252
6	352
7	353
7	354
8	353
8	354
9	355
\.


--
-- Data for Name: movies_genre; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.movies_genre (movie_id, genre_id) FROM stdin;
1	1
2	1
2	3
3	6
3	10
5	1
5	6
6	12
7	1
7	3
8	1
8	3
9	1
9	12
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.person (id, deleted, secure_id, age, person_name) FROM stdin;
52	f	49799878-9a51-49ec-8c6d-26900f1ff992	68	Jackie Chan
103	f	eeca4ee6-82a4-4d86-be4c-40f489f43481	25	Jaden Smith
102	f	5108302b-c943-4907-a766-fb6fe4456fd0	58	Harald Zwatz
152	f	3a790203-6a9e-4f82-a93a-ee7208473cd7	27	Tom Holland
153	f	c9586860-2b83-4464-a66b-be40a9f3f52e	26	Zendaya
154	f	00c75ed5-b190-40b7-b0f6-6deaad917d3e	42	Jon Watts
202	f	ece030ed-0df5-4ff1-8929-64f889874e78	31	Margot Robbie
252	f	7708a302-70cc-4ab9-a606-344da5237279	50	Tom Cruise
302	f	4cd94740-043f-4487-873f-3ab643893ac9	36	Scarlett Johansson
303	f	844fe732-ecf0-4d8b-b951-bee12266582c	46	Leonardo DiCaprio
304	f	2b098459-1dfa-461a-af02-7fceeeb8f43c	30	Jennifer Lawrence
305	f	4469ebda-2f1c-4b13-b20d-98a1c6757fa6	37	Chris Hemsworth
306	f	dd89fea6-b7a1-44f3-b24d-5bd6a2c5a5f6	46	Angelina Jolie
307	f	47a2b120-bf5d-4851-b0ea-ae4b36a5582b	52	Will Smith
308	f	4daa9489-085a-4b30-8099-c3fc0b3586f6	36	Gal Gadot
309	f	d8b8defe-783a-403f-837b-457ca696b9f6	49	Dwayne Johnson
310	f	07dc0505-02de-462f-8c52-e0f11d45f49b	31	Emma Watson
352	f	c81a2e7b-0ea8-4c16-a14b-d031161c8713	55	Adam Mckay
353	f	d08a0043-80f7-40f4-9671-f632ce89a529	52	Antony Russo
354	f	419526a9-1671-49c4-bc06-4d7925376af6	53	Joe Russo
355	f	4bcd9b77-83b0-4140-aa4c-8b4eebc5fb55	45	Rawson Marshall
\.


--
-- Data for Name: review; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.review (id, deleted, secure_id, comment, star, movie_id, user_id) FROM stdin;
2	f	52ffd5ff-a24a-4ac3-851c-28b504f3ed40	worth to watch!	5	1	1
52	f	0912bf2f-f860-4920-9edd-15295905aff4	not bad	4	2	2
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.role (id, name) FROM stdin;
1	BASIC
2	ADMIN
\.


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.user_role (user_id, role_id) FROM stdin;
1	1
2	2
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: jason
--

COPY public.users (id, deleted, secure_id, email, password, user_name) FROM stdin;
1	f	9908ddf2-f385-4c93-be22-2994ab53f183	alexanderjason526@gmail.com	$2a$10$.kiuXTuMMZnTgSKbFl23e.L6dmQseu50vt6CtwmMsw9VVCZkEozru	Jason123
2	f	a2a991b3-9aa0-4e4d-922a-ff24c406da84	admin@gmail.com	$2a$10$7ExaWQWNhtT/uKClPyhOkutYpGNL5isW93.aLsNCsZAGHa2OvXbg6	admin123
\.


--
-- Name: actor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.actor_id_seq', 1, false);


--
-- Name: actor_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.actor_seq', 1, false);


--
-- Name: cinema_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.cinema_seq', 1, false);


--
-- Name: genre_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.genre_id_seq', 1, true);


--
-- Name: genres_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.genres_id_seq', 12, true);


--
-- Name: movie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.movie_id_seq', 1, false);


--
-- Name: movie_id_seq1; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.movie_id_seq1', 9, true);


--
-- Name: person_generator; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.person_generator', 1, false);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.person_id_seq', 401, true);


--
-- Name: review_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.review_id_seq', 101, true);


--
-- Name: review_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.review_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jason
--

SELECT pg_catalog.setval('public.user_id_seq', 51, true);


--
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);


--
-- Name: movie movie_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT movie_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: review review_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT review_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: movie uk_73mpsctnm4fxg8r1nbgtk0226; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movie
    ADD CONSTRAINT uk_73mpsctnm4fxg8r1nbgtk0226 UNIQUE (secure_id);


--
-- Name: person uk_8rxd9fvkfww6kqi825h3a3aco; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT uk_8rxd9fvkfww6kqi825h3a3aco UNIQUE (secure_id);


--
-- Name: role uk_8sewwnpamngi6b1dwaa88askk; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_8sewwnpamngi6b1dwaa88askk UNIQUE (name);


--
-- Name: users uk_glpo98o4rtjds8gd6c07ps9ag; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_glpo98o4rtjds8gd6c07ps9ag UNIQUE (secure_id);


--
-- Name: review uk_r5vbaixr66o468lb592rc43kj; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT uk_r5vbaixr66o468lb592rc43kj UNIQUE (secure_id);


--
-- Name: genres uk_rxehn25a8j1g2v4qxwr4xlmrf; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT uk_rxehn25a8j1g2v4qxwr4xlmrf UNIQUE (secure_id);


--
-- Name: genres uk_s2mpyuc751lwbr5f12asnw8bl; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT uk_s2mpyuc751lwbr5f12asnw8bl UNIQUE (genre);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: uk_username; Type: INDEX; Schema: public; Owner: jason
--

CREATE INDEX uk_username ON public.users USING btree (user_name);


--
-- Name: movies_genre fk1puqu1c8vbnhj1vcu9jmeimad; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_genre
    ADD CONSTRAINT fk1puqu1c8vbnhj1vcu9jmeimad FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- Name: review fk6cpw2nlklblpvc7hyt7ko6v3e; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk6cpw2nlklblpvc7hyt7ko6v3e FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: movies_acted fk6i7uuy6p4j7vft004vni3w0n5; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_acted
    ADD CONSTRAINT fk6i7uuy6p4j7vft004vni3w0n5 FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- Name: movies_directed fk77dvgiw8p3s7l74yfpd82ths; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_directed
    ADD CONSTRAINT fk77dvgiw8p3s7l74yfpd82ths FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- Name: review fk8378dhlpvq0e9tnkn9mx0r64i; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.review
    ADD CONSTRAINT fk8378dhlpvq0e9tnkn9mx0r64i FOREIGN KEY (movie_id) REFERENCES public.movie(id);


--
-- Name: movies_directed fk9fedm0p6k6ve7s1jxlh5573pl; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_directed
    ADD CONSTRAINT fk9fedm0p6k6ve7s1jxlh5573pl FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- Name: user_role fka68196081fvovjhkek5m97n3y; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fka68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: user_role fkj345gk1bovqvfame88rcx7yyx; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.user_role
    ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: movies_acted fkqy8www0pnj0tde64n3wm5xtc5; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_acted
    ADD CONSTRAINT fkqy8www0pnj0tde64n3wm5xtc5 FOREIGN KEY (person_id) REFERENCES public.person(id);


--
-- Name: movies_genre fkt0hut1q5n87dirmb0gt98k2wp; Type: FK CONSTRAINT; Schema: public; Owner: jason
--

ALTER TABLE ONLY public.movies_genre
    ADD CONSTRAINT fkt0hut1q5n87dirmb0gt98k2wp FOREIGN KEY (genre_id) REFERENCES public.genres(id);


--
-- PostgreSQL database dump complete
--

