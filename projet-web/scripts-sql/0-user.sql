

-- Supprime le schéma bibliotheque

DROP SCHEMA IF EXISTS bibliotheque CASCADE;


-- Crée l'utilisateur bibliotheque
-- (après l'avoir supprimé au préalable s'il existait déjà)

DO $code$
BEGIN
	IF EXISTS (SELECT  FROM pg_catalog.pg_roles WHERE rolname  = 'bibliotheque')
	THEN
		REVOKE CREATE ON DATABASE postgres FROM bibliotheque;
		DROP USER bibliotheque;
	END IF;
END
$code$;

CREATE USER bibliotheque WITH PASSWORD 'bibliotheque';
GRANT CREATE ON DATABASE postgres TO bibliotheque;

