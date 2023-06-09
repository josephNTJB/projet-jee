SET search_path TO bibliotheque;


-- Supprime toutes les données
DELETE FROM emprunt;
DELETE FROM etre_ami;
DELETE FROM associer;
DELETE FROM ouvrage;
DELETE FROM identifiant;
DELETE FROM categorie;
DELETE FROM personne;


-- Insère les données

INSERT INTO personne (id_personne, nom, prenom) VALUES 
  (1, 'Doe', 'John'),
  (2, 'Smith', 'Jane'),
  (3, 'Williams', 'David'),
  (4, 'Johnson', 'Sarah'),
  (5, 'Brown', 'Michael');



INSERT INTO ouvrage (id_ouvrage,fichier,couverture, auteur, nom, id_personne) VALUES 
  (1,'fichier','couverture','George Orwell',  '1984', 1),
  (2,'fichier','couverture', 'Harper Lee', 'Ne tirez pas sur loiseau moqueur', 3),
  (3, 'fichier','couverture','F. Scott Fitzgerald', 'Gatsby le magnifique', 4),
  (4,'fichier','couverture', 'William Shakespeare', 'Romeo et Juliette', 5),
  (5,'fichier','couverture', 'Jane Austen', 'Orgueil et Prejuges', 3),
  (6,'fichier','couverture', 'Gabriel Garcia Marquez', 'Cent ans de solitude', 1),
  (7,'fichier','couverture', 'Markus Zusak', 'La voleuse de livres', 2),
  (8,'fichier','couverture', 'Ray Bradbury',  'Fahrenheit 451', 3),
  (9, 'fichier','couverture','Stephen King', 'Shining', 4),
  (10, 'fichier','couverture','Dan Brown',  'Da Vinci Code', 5),
  (11, 'fichier','couverture','J.K. Rowling', 'Harry Potter a lecole des sorciers', 2);

--ALTER TABLE ouvrage ALTER COLUMN id_ouvrage RESTART WITH 11;

  
  
INSERT INTO categorie (id_categorie, libelle ) VALUES 
  (1, 'Roman'),
  (2, 'Science-fiction'),
  (3, 'Thriller'),
  (4, 'Fantasy'),
  (5, 'Biographie');

--ALTER TABLE categorie ALTER COLUMN id_categorie RESTART WITH 6;


INSERT INTO associer (id_ouvrage, id_categorie) VALUES
  (11, 1),
  (1, 2),
  (2, 1),
  (3, 3),
  (4, 4),
  (5, 1),
  (6, 2),
  (7, 3),
  (8, 4),
  (9, 5),
  (10, 1);
  

  

INSERT INTO identifiant(ID_IDENTIFIANT, username, password, id_personne ) VALUES 
(1,'john.doe@biblio.fr', 'motdepasse1', 1),
(2,'jane.smith@biblio.fr', 'motdepasse2', 2),
(3,'david.williams@biblio.fr', 'motdepasse3', 3),
(4,'sarah.johnson@biblio.fr', 'motdepasse4', 4),
(5,'michael.brown@biblio.fr', 'motdepasse5', 5);

--ALTER TABLE identifiant ALTER COLUMN username,

INSERT INTO emprunt (id_ouvrage, id_personne) VALUES
  (1, 3),
  (2, 5),
  (9, 2);

INSERT INTO etre_ami (id_personne, id_ami,is_validate) VALUES
  (1, 3,true),
  (3, 5,true),
  (4, 2,false),
  (2, 3,false);
  
INSERT INTO role (idcompte, role) VALUES 
( 1, 'ADMINISTRATEUR' ),
( 1, 'UTILISATEUR' ),
( 2, 'UTILISATEUR' ),
( 3, 'UTILISATEUR' ),
( 4, 'UTILISATEUR' ),
( 5, 'UTILISATEUR' );

 UPDATE PERSONNE 
 SET id_identifiant = ID_PERSONNE;





 
