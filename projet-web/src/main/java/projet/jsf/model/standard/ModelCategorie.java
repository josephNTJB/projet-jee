package projet.jsf.model.standard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import projet.commun.dto.DtoCategorie;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceCategorie;
import projet.jsf.data.Categorie;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@ViewScoped
public class ModelCategorie implements Serializable {

	
	// Champs
	
	private List<Categorie>		liste;
	
	@EJB
	private IServiceCategorie	serviceCategorie;
	
	@Inject
	private IMapper				mapper;

	
	// Getters 
	
	public List<Categorie> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoCategorie dto : serviceCategorie.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}

	// Actions
	
	public String supprimer( Categorie item ) {
		try {
			serviceCategorie.supprimer( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e );
		}
		return null;
	}
	
}
