package parc.vues.localisation;

import java.sql.SQLException;
import java.text.ParseException;

import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parc.Principale;
import structure.Batiment;
import structure.Campus;
import structure.Localisation;
import structure.Salle;

public class LocalisationDetailsControleur {
	
	@FXML
	private Label nomLabel;
	@FXML
	private Label campusLabel;
	@FXML
	private Label batimentLabel;
	@FXML
	private Label salleLabel;
	
	private Stage detailsStage;
	private Localisation localisation;
	private Principale principale;
	
	@SuppressWarnings("unchecked")
	private DAO<Campus> daoCampus = DaoFactory.creerDao(Classe.CAMPUS);
	@SuppressWarnings("unchecked")
	private DAO<Batiment> daoBatiment = DaoFactory.creerDao(Classe.BATIMENT);
	@SuppressWarnings("unchecked")
	private DAO<Salle> daoSalle = DaoFactory.creerDao(Classe.SALLE);
	
	@FXML
	private void initialize(){
	}
	
	public void setDetailsStage(Stage detailsStage){
		this.detailsStage = detailsStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
		
		nomLabel.setText(localisation.getLocNom());
		if (localisation.getCpsId() > 0){
			Campus campus = null;
			try {
				campus = daoCampus.select(localisation.getCpsId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			campusLabel.setText(campus.getNom());
		}
		if (localisation.getSalId() > 0){
			Batiment batiment = null;
			try {
				batiment = daoBatiment.select(localisation.getBatId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			batimentLabel.setText(batiment.getNom());
		}
		if (localisation.getSalId() > 0){
			Salle salle = null;
			try {
				salle = daoSalle.select(localisation.getSalId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			salleLabel.setText(salle.getNom());
		}
	}
	
	@FXML
	public void handleOK(){
			detailsStage.close();
	}

}
