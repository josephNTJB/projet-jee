package projet.jsf.model.standard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
<<<<<<< HEAD
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
=======
>>>>>>> branch 'master' of https://github.com/josephNTJB/projet-jee.git
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import projet.commun.dto.DtoOuvrage;
import projet.commun.exception.ExceptionValidation;
import projet.commun.service.IServiceOuvrage;
import projet.jsf.data.Ouvrage;
import projet.jsf.data.mapper.IMapper;
import projet.jsf.util.CompteActif;
import projet.jsf.util.UtilJsf;


@SuppressWarnings("serial")
@Named
@RequestScoped
public class ModelOuvrage implements Serializable {

	
	// Champs
	
	private List<Ouvrage>	liste;
	
	private Ouvrage			courant;
	
	@EJB
	private IServiceOuvrage	serviceOuvrage;
	
	@Inject
	private IMapper			mapper;
	
	@Inject
	CompteActif compteActif;

	
	// Getters 
	
	public List<Ouvrage> getListe() {
		if ( liste == null ) {
			liste = new ArrayList<>();
			for ( DtoOuvrage dto : serviceOuvrage.listerTout() ) {
				liste.add( mapper.map( dto ) );
			}
		}
		return liste;
	}
	
	public List<Ouvrage> getListePourPersonne() {
		List<Ouvrage> liste = new ArrayList<Ouvrage>();
	
			for ( DtoOuvrage dto : serviceOuvrage.listerPourPersonne(compteActif.getId()) ) {
				liste.add( mapper.map( dto ) );
			}
		
		return liste;
	}
	
		public Ouvrage getCourant() {
			if ( courant == null ) {
				courant = new Ouvrage();
			}
			return courant;
		}
	
	
	// Initialisaitons
	
	public String actualiserCourant() {
		if ( courant != null ) {
			DtoOuvrage dto = serviceOuvrage.retrouver( courant.getId() ); 
			if ( dto == null ) {
				UtilJsf.messageError( "L'ouvrage demandé n'existe pas" );
				return "test/liste";
			} else {
				courant = mapper.map( dto );
			}
		}
		return null;
	}
	
	
	// Actions
	
	public String validerMiseAJour() {
		try {
			actualiserCourant();
			if ( courant.getId() == null) {
				serviceOuvrage.insererPourPersonne( mapper.map(courant) );
				upload();
			} else {
				serviceOuvrage.modifierPourPersonne( mapper.map(courant) );
				if(file!=null) {
					upload();
				}
				}
			UtilJsf.messageInfo( "Mise à jour effectuée avec succès." );
			return "liste";
		} catch (ExceptionValidation e) {
			UtilJsf.messageError(e);
			return null;
		}
	}
	
	public String supprimer( Ouvrage item ) {
		try {
			serviceOuvrage.supprimerPourPersonne( item.getId() );
			liste.remove(item);
			UtilJsf.messageInfo( "Suppression effectuée avec succès." );
		} catch (ExceptionValidation e) {
			UtilJsf.messageError( e ); 
		}
		return null;
	}
	private Part file;

    // Getter et Setter pour la propriété "file"
     
    public void upload() {
        try (InputStream input = file.getInputStream()) {
            // Nom du fichier d'origine
            String fileName = file.getSubmittedFileName();
            
         // Obtenez le chemin du dossier "Documents" de l'utilisateur actuel
            String userHome = System.getProperty("user.home");
            String documentsPath = userHome + File.separator + "ouvrages";

            // Définissez l'emplacement de destination dans le dossier "Documents"
            String destination = documentsPath + File.separator + fileName;
            Files.copy(input, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
            
            // Exemple de traitement supplémentaire du fichier
            // ...

            // Message de succès
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Upload réussi !"));
        } catch (IOException e) {
            // Gestion des erreurs de lecture du fichier
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur lors de l'upload du fichier", null));
        }
    }
    public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void download(String fileName) {
        try {
        	//pareil que pour le dossire d'upload
            String filePath = "/chemin/vers/dossier/destination/" + fileName;
            File file = new File(filePath);

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                OutputStream responseOutputStream = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    responseOutputStream.write(buffer, 0, bytesRead);
                }
                responseOutputStream.flush();
            }

            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            // Gestion des erreurs de téléchargement
            e.printStackTrace();
        }}
	
}
