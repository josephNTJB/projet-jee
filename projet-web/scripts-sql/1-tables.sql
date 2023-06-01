
SET search_path TO bibliotheque;



-- Schéma

------------------------------------------------------------
--        Script Postgre 
------------------------------------------------------------

DROP TABLE IF EXISTS associer;
DROP TABLE IF EXISTS emprunt;
DROP TABLE IF EXISTS etre_ami;
DROP TABLE IF EXISTS categorie ;
DROP TABLE IF EXISTS personne CASCADE;
DROP TABLE IF EXISTS ouvrage;
DROP TABLE IF EXISTS identifiant;

------------------------------------------------------------
-- Table: categorie
------------------------------------------------------------
CREATE TABLE  categorie(
	id_categorie     INT NOT NULL ,
	libelle        VARCHAR (50) NOT NULL  ,
	CONSTRAINT categorie_PK PRIMARY KEY (id_categorie)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: ouvrage
------------------------------------------------------------
CREATE TABLE  ouvrage(
	id_ouvrage      INT NOT NULL ,
	auteur        VARCHAR (50) NOT NULL ,
	nom           VARCHAR (50) NOT NULL ,
	id_personne   INT  NOT NULL  ,
	CONSTRAINT ouvrage_PK PRIMARY KEY (id_ouvrage)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: personne
------------------------------------------------------------
CREATE TABLE  personne(
	id_personne      INT NOT NULL ,
	nom              VARCHAR (50) NOT NULL ,
	prenom           VARCHAR (50) NOT NULL ,
	id_identifiant   INT  NULL,
	CONSTRAINT personne_PK PRIMARY KEY (id_personne)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: identifiant
------------------------------------------------------------
CREATE TABLE  identifiant(
	id_identifiant   INT NOT NULL ,
	username         VARCHAR (255) NOT NULL ,
	password         VARCHAR (255) NOT NULL ,
	id_personne      INT  NOT NULL  ,
	CONSTRAINT identifiant_PK PRIMARY KEY (id_identifiant)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: associer
------------------------------------------------------------
CREATE TABLE  associer(
	id_ouvrage     INT  NOT NULL ,
	id_categorie   INT  NOT NULL  ,
	CONSTRAINT associer_PK PRIMARY KEY (id_ouvrage,id_categorie)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: emprunt
------------------------------------------------------------
CREATE TABLE  emprunt(
	id_ouvrage    INT  NOT NULL ,
	id_personne   INT  NOT NULL  ,
	CONSTRAINT emprunt_PK PRIMARY KEY (id_ouvrage, id_personne)
)WITHOUT OIDS;


------------------------------------------------------------
-- Table: etre ami
------------------------------------------------------------
CREATE TABLE  etre_ami(
	 id_personne            INT  NOT NULL ,
	 id_personne_etre_ami   INT  NOT NULL  ,
	CONSTRAINT etre_ami_PK PRIMARY KEY ( id_personne, id_personne_etre_ami)
)WITHOUT OIDS;




ALTER TABLE  ouvrage
	ADD CONSTRAINT ouvrage_personne0_FK
	FOREIGN KEY ( id_personne)
	REFERENCES  personne( id_personne);


ALTER TABLE  identifiant
	ADD CONSTRAINT identifiant_personne0_FK
	FOREIGN KEY ( id_personne)
	REFERENCES  personne( id_personne);

ALTER TABLE  identifiant 
	ADD CONSTRAINT identifiant_personne0_AK 
	UNIQUE ( id_personne);

ALTER TABLE  associer
	ADD CONSTRAINT associer_ouvrage0_FK
	FOREIGN KEY (id_ouvrage)
	REFERENCES  ouvrage(id_ouvrage);

ALTER TABLE  associer
	ADD CONSTRAINT associer_categorie1_FK
	FOREIGN KEY (id_categorie)
	REFERENCES  categorie(id_categorie);

ALTER TABLE  emprunt
	ADD CONSTRAINT emprunt_ouvrage0_FK
	FOREIGN KEY (id_ouvrage)
	REFERENCES  ouvrage(id_ouvrage);

ALTER TABLE  emprunt
	ADD CONSTRAINT emprunt_personne1_FK
	FOREIGN KEY ( id_personne)
	REFERENCES  personne( id_personne);

ALTER TABLE  etre_ami
	ADD CONSTRAINT etre_ami_personne0_FK
	FOREIGN KEY ( id_personne)
	REFERENCES  personne( id_personne);

ALTER TABLE  etre_ami
	ADD CONSTRAINT etre_ami_personne1_FK
	FOREIGN KEY ( id_personne_etre_ami)
	REFERENCES  personne( id_personne);

ALTER TABLE personne	
	ADD CONSTRAINT personne_identifiant0_FK
	FOREIGN KEY (id_identifiant)
	REFERENCES  identifiant(id_identifiant);

ALTER TABLE  personne 
	ADD CONSTRAINT personne_identifiant0_AK 
	UNIQUE (id_identifiant);
