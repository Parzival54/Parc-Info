package parc.vues.stagiaire;

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
import machine.Formation;
import machine.Stagiaire;
import parc.Principale;

@SuppressWarnings("unchecked")
public class StagiaireEditControleur {
	
	@FXML
	private TextField nomField;
	@FXML
	private TextField prenomField;
	@FXML
	private Label stgIdLabel;
	@FXML
	private TextField adresseField;
	@FXML
	private TextField numtelField;
	@FXML
	private TextField maIdField;
	@FXML
	private Label foNomLabel;
	
	private Stage editStage;
	private Stagiaire stagiaire;
	private boolean clickOK = false;
	private Principale principale;
	private int foId;
	
	private DAO<Formation> daoFormation = DaoFactory.creerDao(Classe.FORMATION);
	
	@FXML
	private void initialize(){
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setStagiaire(Stagiaire stagiaire){
		this.stagiaire = stagiaire;
		foId = stagiaire.getFoId();
		
		nomField.setText(stagiaire.getStgNom());
		prenomField.setText(stagiaire.getStgPrenom());
		if (stagiaire.getStgId() > 0){
			stgIdLabel.setText(Integer.toString(stagiaire.getStgId()));	
		} else {
			stgIdLabel.setText("En cours de création");
		}
		adresseField.setText(stagiaire.getStgAdresse());
		if (stagiaire.getStgNumtel() > 0){
			numtelField.setText(Integer.toString(stagiaire.getStgNumtel()));			
		}
		if (stagiaire.getMaId() > 0){
			maIdField.setText(Integer.toString(stagiaire.getMaId()));			
		}
		if (stagiaire.getFoId() > 0) {
			Formation formation = null;
			try {
				formation = daoFormation.select(stagiaire.getFoId());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			foNomLabel.setText(formation.getFoNom());
		} else {
			foNomLabel.setText("");
		}
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	@FXML
	public void handleOK(){
		if (isValid()){
			stagiaire.setStgNom(nomField.getText());
			stagiaire.setStgPrenom(prenomField.getText());
			if (stgIdLabel.getText() != "En cours de création")
			stagiaire.setStgId(Integer.parseInt(stgIdLabel.getText()));
			stagiaire.setStgAdresse(adresseField.getText());
			stagiaire.setStgNumtel(Integer.parseInt(numtelField.getText()));
			stagiaire.setMaId(Integer.parseInt(maIdField.getText()));
			stagiaire.setFoId(foId);
			
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}
	
	@FXML
	private void handleListeFormations() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeFormations();
		if (clickOK){
			foNomLabel.setText(principale.getFoNom());
			foId = principale.getFoId();
		}
	}
	
	@FXML
	private void handleListeMachines() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeMachines();
		if (clickOK){
			maIdField.setText(Integer.toString(principale.getMaId()));
		}
	}

	private boolean isValid() {
		String message = "";
		
		if (nomField.getText() == null || nomField.getText().length() == 0){
			message += "Nom non valide\n";
		}
		if (prenomField.getText() == null || prenomField.getText().length() == 0){
			message += "Prenom non valide\n";
		}
		if (stgIdLabel.getText() == null || stgIdLabel.getText().length() == 0){
			message += "Numéro d'identification non valide\n";
		}
		if (adresseField.getText() == null || adresseField.getText().length() == 0){
			message += "Adresse non valide\n";
		}
		if (numtelField.getText() == null || numtelField.getText().length() == 0){
			message += "Numéro de téléphone non valide\n";
		}
		if (maIdField.getText() == null || maIdField.getText().length() == 0){
			message += "Machine ID non valide\n";
		}
		if (foNomLabel.getText() == null || foNomLabel.getText().length() == 0){
			message += "Formation ID non valide\n";
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
