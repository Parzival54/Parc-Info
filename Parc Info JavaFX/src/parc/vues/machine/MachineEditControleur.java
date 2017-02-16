package parc.vues.machine;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import machine.Machine;
import parc.Principale;

public class MachineEditControleur {
	
	@FXML
	private TextField nomField;
	@FXML
	private Label stgIdLabel;
	@FXML
	private Label maIdLabel;
	@FXML
	private Label locIdLabel;
	@FXML
	private Label compIdLabel;
	@FXML
	private TextField numAfpaField;
	@FXML
	private TextField dateAchatField;
	@FXML
	private TextField dureeGarantieField;
	@FXML
	private TextField ipField;
	
	private Stage editStage;
	private Machine machine;
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
	
	public void setMachine(Machine machine){
		this.machine = machine;
		
		nomField.setText(machine.getMaNom());
		if (machine.getStgId() > 0) {
			stgIdLabel.setText(Integer.toString(machine.getStgId()));			
		} else {
			stgIdLabel.setText("");
		}
		if (machine.getMaId() > 0){
			maIdLabel.setText(Integer.toString(machine.getMaId()));	
		} else {
			maIdLabel.setText("En cours de création");
		}
		if (machine.getLocId() > 0){
			locIdLabel.setText(Integer.toString(machine.getLocId()));
		} else {
			locIdLabel.setText("");
		}
		if (machine.getCompId() > 0){
			compIdLabel.setText(Integer.toString(machine.getCompId()));
		} else {
			compIdLabel.setText("");
		}
		numAfpaField.setText(machine.getMaNumAfpa());
		if (machine.getMaDateAchat() != null){
			dateAchatField.setText(new SimpleDateFormat("dd/MM/yyyy").format(machine.getMaDateAchat()));			
		}
		if (machine.getMaDureeGarantie() > 0){
			dureeGarantieField.setText(Integer.toString(machine.getMaDureeGarantie()));			
		}
		ipField.setText(machine.getMaIP());
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	@FXML
	public void handleOK() throws ParseException {
		if (isValid()){
			machine.setMaNom(nomField.getText());
			if (!stgIdLabel.getText().equals("Label")){
				machine.setStgId(Integer.parseInt(stgIdLabel.getText()));								
			}
			if (!maIdLabel.getText().equals("En cours de création")) {
				machine.setMaId(Integer.parseInt(maIdLabel.getText()));				
			}
			if (!locIdLabel.getText().equals("Label")) {
				machine.setLocId(Integer.parseInt(locIdLabel.getText()));								
			}
			if (!compIdLabel.getText().equals("Label")) {				
				machine.setCompId(Integer.parseInt(compIdLabel.getText()));				
			}
			machine.setMaNumAfpa(numAfpaField.getText());
			machine.setMaDateAchat(new SimpleDateFormat("dd/MM/yyyy").parse(dateAchatField.getText()));
			machine.setMaDureeGarantie(Integer.parseInt(dureeGarantieField.getText()));
			machine.setMaIP(ipField.getText());
			
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}
	
	@FXML
	private void handleListeStagiaires() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeStagiaires();
		if (clickOK){
			stgIdLabel.setText(Integer.toString(principale.getStgId()));
		}
	}
	
	@FXML
	private void handleListeLocalisations() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeLocalisations();
		if (clickOK){
			locIdLabel.setText(Integer.toString(principale.getLocId()));
		}
	}
	
	@FXML
	private void handleListeComposants() throws SQLException, ParseException{
		boolean clickOK = principale.affichageListeComposants();
		if (clickOK){
			compIdLabel.setText(Integer.toString(principale.getCompId()));
		}
	}

	private boolean isValid() {
		String message = "";
		
		if (nomField.getText() == null || nomField.getText().length() == 0){
			message += "Nom non valide\n";
		}
		// Décommenter ci-dessous pour obliger l'association de la machine à un stagiaire
//		if (stgIdLabel.getText() == null || stgIdLabel.getText().length() == 0){
//			message += "Stagiaire non valide\n";
//		}
		if (maIdLabel.getText() == null || maIdLabel.getText().length() == 0){
			message += "Numéro d'identification non valide\n";
		}
		// Décommenter ci-dessous pour obliger l'association de la machine à une localisation
//		if (locIdLabel.getText() == null || locIdLabel.getText().length() == 0){
//			message += "Localisation non valide\n";
//		}
		// Décommenter ci-dessous pour obliger l'association de la machine à une liste de composants
//		if (compIdLabel.getText() == null || compIdLabel.getText().length() == 0){
//			message += "Liste de composants non valide\n";
//		}
		if (numAfpaField.getText() == null || numAfpaField.getText().length() == 0){
			message += "Numéro AFPA non valide\n";
		}
		if (dateAchatField.getText() == null || dateAchatField.getText().length() == 0){
			message += "Date d'achat non valide\n";
		}
		if (dureeGarantieField.getText() == null || dureeGarantieField.getText().length() == 0){
			message += "Durée de garantie non valide\n";
		}
		if (ipField.getText() == null || ipField.getText().length() == 0){
			message += "IP non valide\n";
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
