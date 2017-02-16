package parc.vues.formation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import machine.Formation;
import parc.Principale;

public class FormationEditControleur {
	
	@FXML
	private Label foIdLabel;
	@FXML
	private TextField nomField;
	@FXML
	private TextField dateDebutField;
	@FXML
	private TextField dateFinField;
	
	private Stage editStage;
	private Formation formation;
	private boolean clickOK = false;
	private Principale principale;
	
	@FXML
	private void initialize(){
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setFormation(Formation formation){
		this.formation = formation;
		if (formation.getFoId() > 0){
			foIdLabel.setText(Integer.toString(formation.getFoId()));	
		} else {
			foIdLabel.setText("En cours de création");
		}
		nomField.setText(formation.getFoNom());
		if (formation.getFoDatedebut() != null){
			dateDebutField.setText(new SimpleDateFormat("dd/MM/yyyy").format(formation.getFoDatedebut()));			
		}
		if (formation.getFoDatefin() != null){
			dateFinField.setText(new SimpleDateFormat("dd/MM/yyyy").format(formation.getFoDatefin()));			
		}
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			if (foIdLabel.getText() != "En cours de création")
			formation.setFoId(Integer.parseInt(foIdLabel.getText()));
			formation.setFoNom(nomField.getText());
			formation.setFoDatedebut(new SimpleDateFormat("dd/MM/yyyy").parse(dateDebutField.getText()));
			formation.setFoDatefin(new SimpleDateFormat("dd/MM/yyyy").parse(dateFinField.getText()));
			
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}

	private boolean isValid() {
		String message = "";
		
		if (nomField.getText() == null || nomField.getText().length() == 0){
			message += "Nom non valide\n";
		}
		if (dateDebutField.getText() == null || dateDebutField.getText().length() == 0){
			message += "Date de début non valide\n";
		}
		if (dateFinField.getText() == null || dateFinField.getText().length() == 0){
			message += "Date de fin non valide\n";
		}
		if (foIdLabel.getText() == null || foIdLabel.getText().length() == 0){
			message += "id non valide\n";
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