package parc.vues.localisation;

import java.sql.SQLException;
import java.text.ParseException;

import dao.Classe;
import dao.DAO;
import dao.DaoFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parc.Principale;
import structure.Batiment;
import structure.Campus;
import structure.Localisation;
import structure.Salle;

@SuppressWarnings("unchecked")
public class LocalisationEditControleur {
	
	@FXML
	private Label locIdLabel;
	@FXML
	private TextField nomField;
	@FXML
	private Label campusLabel;
	@FXML
	private Label batimentLabel;
	@FXML
	private Label salleLabel;
	
	private Stage editStage;
	private Localisation localisation;
	private boolean clickOK = false;
	private Principale principale;
	private int batId, cpsId, salId;
	
	private DAO<Campus> daoCampus = DaoFactory.creerDao(Classe.CAMPUS);
	private DAO<Batiment> daoBatiment = DaoFactory.creerDao(Classe.BATIMENT);
	private DAO<Salle> daoSalle = DaoFactory.creerDao(Classe.SALLE);	
	
	@FXML
	private void initialize(){
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setLocalisation(Localisation localisation){
		this.localisation = localisation;
		cpsId = localisation.getCpsId();
		batId = localisation.getBatId();
		salId = localisation.getSalId();
		
		if (localisation.getLocId() > 0){
			locIdLabel.setText(Integer.toString(localisation.getLocId()));	
		} else {
			locIdLabel.setText("En cours de création");
		}
		nomField.setText(localisation.getLocNom());
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
		} else {
			campusLabel.setText("");
		}
		if (localisation.getBatId() > 0){
			Batiment batiment = null;
			try {
				batiment = daoBatiment.select(localisation.getBatId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			batimentLabel.setText(batiment.getNom());			
		} else {
			batimentLabel.setText("");
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
		} else {
			salleLabel.setText("");
		}
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			if (locIdLabel.getText() != "En cours de création")
				localisation.setLocId(Integer.parseInt(locIdLabel.getText()));
			localisation.setLocNom(nomField.getText());
			localisation.setCpsId(cpsId);
			localisation.setBatId(batId);
			localisation.setSalId(salId);
			
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}
	
	@FXML
	private void handleListeBatiments() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeStructures(Classe.BATIMENT);
		if (clickOK){
			batimentLabel.setText(principale.getBatNom());
			batId = principale.getBatId();
		}
	}
	
	@FXML
	private void handleListeCampus() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeStructures(Classe.CAMPUS);
		if (clickOK){
			campusLabel.setText(principale.getCpsNom());
			cpsId = principale.getCpsId();
		}
	}
	
	@FXML
	private void handleListeSalles() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeStructures(Classe.SALLE);
		if (clickOK){
			salleLabel.setText(principale.getSalNom());
			salId = principale.getSalId();
		}
	}

	private boolean isValid() {
		String message = "";
		
		if (nomField.getText() == null || nomField.getText().length() == 0){
			message += "Nom non valide\n";
		}
		if (campusLabel.getText() == null || campusLabel.getText().length() == 0){
			message += "Campus non valide\n";
		}
		if (batimentLabel.getText() == null || batimentLabel.getText().length() == 0){
			message += "Batiment non valide\n";
		}
		if (salleLabel.getText() == null || salleLabel.getText().length() == 0){
			message += "Salle non valide\n";
		}
		
		if (message.length() == 0){
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(editStage);
			alert.setTitle("Données non valides");
			alert.setHeaderText("Veuillez corriger les données");
			alert.setContentText(message);
			alert.showAndWait();
			return false;
		}
	}

}