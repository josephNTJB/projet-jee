package projet.jsf.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import projet.commun.dto.DtoCategorie;
import projet.commun.dto.DtoCompte;
import projet.commun.dto.DtoOuvrage;
import projet.commun.dto.DtoPersonne;
import projet.ejb.data.Categorie;
import projet.ejb.data.Ouvrage;
import projet.ejb.data.Personne;
import projet.jsf.data.Compte;


@Mapper( componentModel = "cdi" )
public interface IMapper {
  

	// Compte
	
	Compte    map( DtoCompte source );
	
	DtoCompte map( Compte source );
	
	Compte duplicate( Compte source );

	Compte update( @MappingTarget Compte target, Compte source );
	
	// Personne
	
	Personne    map( DtoPersonne source );
	
	DtoPersonne map( Personne source );
	
	Personne duplicate( Personne source );

	Personne update( @MappingTarget Personne target, Personne source );
	
	// Categorie
	
	Categorie    map( DtoCategorie source );
	
	DtoCategorie map( Categorie source );
	
	Categorie duplicate( Categorie source );

	Categorie update( @MappingTarget Categorie target, Categorie source );
	
	/*
	
	Auteur    map( DtoAuteur source );
	
	DtoAuteur map( Auteur source );
	
	Auteur duplicate( Auteur source );

	Auteur update( @MappingTarget Auteur target, Auteur source );*/
	
	// Auteur
	
	Ouvrage    map( DtoOuvrage source );
	
	DtoOuvrage map( Ouvrage source );
	
	Ouvrage duplicate( Ouvrage source );

	Ouvrage update( @MappingTarget Ouvrage target, Ouvrage source );

}
