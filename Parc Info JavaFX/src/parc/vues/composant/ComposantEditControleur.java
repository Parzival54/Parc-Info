package parc.vues.composant;

import java.sql.SQLException;
import java.text.ParseException;

import composants.CarteMere;
import composants.Composant;
import composants.DisqueDur;
import composants.MemoireVive;
import composants.Os;
import composants.Processeur;
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
public class ComposantEditControleur {
	
	@FXML
	private Label idLabel;
	@FXML
	private TextField typeField;
	@FXML
	private TextField compIdField;
	@FXML
	private TextField fabricantField;
	@FXML
	private TextField referenceField;
	@FXML
	private TextField nomField;
	@FXML
	private TextField numSerieField;
	@FXML
	private TextField extra1Field;
	@FXML
	private TextField extra2Field;
	@FXML
	private Label extra1Label;
	@FXML
	private Label extra2Label;
	
	private Stage editStage;
	private Composant composant;
	private boolean clickOK = false;
	private Principale principale;
	
	private final String DISQUEDUR = "DisqueDur";
	private final String MEMOIREVIVE = "MemoireVive";
	private final String CARTEMERE = "CarteMere";
	private final String PROCESSEUR = "Processeur";
	private final String OS = "Os";
	
	@FXML
	private void initialize(){
		extra1Label.setText("");
		extra2Label.setText("");
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
	}
	
	public void setComposant(Composant composant){
		this.composant = composant;
		idLabel.setText("En cours de création");
		
		if (composant != null) {
			if (composant.getId() > 0){
				idLabel.setText(Integer.toString(composant.getId()));
			} else {
				idLabel.setText("En cours de création");
			}
			typeField.setText(composant.getType());
			if (composant.getCompId() > 0){
				idLabel.setText(Integer.toString(composant.getCompId()));
			}
			fabricantField.setText(composant.getFabricant());
			referenceField.setText(composant.getReference());
			nomField.setText(composant.getNom());
			numSerieField.setText(composant.getNumSerie());
			switch (composant.getClass().getSimpleName()) {
			case DISQUEDUR:
				DisqueDur disqueDur = (DisqueDur) composant;
				extra1Label.setText("Capacité");
				extra2Label.setText("Connectique");
				if (disqueDur.getDdCapacite() > 0 ){
					extra1Field.setText(Integer.toString(disqueDur.getDdCapacite()));
				}
				extra2Field.setText(disqueDur.getDdConnectique());
				break;
			case MEMOIREVIVE:
				MemoireVive memoireVive = (MemoireVive) composant;
				extra1Label.setText("Capacité");
				if (memoireVive.getMvCapacite() > 0 ){
					extra1Field.setText(Integer.toString(memoireVive.getMvCapacite()));
				}
				break;
			case CARTEMERE:
				CarteMere carteMere = (CarteMere) composant;
				extra1Label.setText("Nombre de slots");
				if (carteMere.getCmNbslots() > 0 ){
					extra1Field.setText(Integer.toString(carteMere.getCmNbslots()));
				}
				break;
			case PROCESSEUR:
				Processeur processeur = (Processeur) composant;
				extra1Label.setText("Fréquence");
				extra2Label.setText("Nombre de coeurs");
				if (processeur.getProcFrequence() > 0 ){
					extra1Field.setText(Integer.toString(processeur.getProcFrequence()));
				}
				if (processeur.getProcNbCoeurs() > 0 ){
					extra2Field.setText(Integer.toString(processeur.getProcNbCoeurs()));
				}
				break;
			case OS:
				Os os = (Os) composant;
				extra1Label.setText("Version");
				extra1Field.setText(os.getOsVersion());
				break;
			default:
				break;
			}
		}
		
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	@FXML
	public void handleOK(){
		if (isValid()){
			if (idLabel.getText() != "En cours de création") {
				composant.setId(Integer.parseInt(idLabel.getText()));				
			}
			composant.setType(typeField.getText());
			composant.setCompId(Integer.parseInt(compIdField.getText()));
			composant.setFabricant(fabricantField.getText());
			composant.setReference(referenceField.getText());
			composant.setNom(nomField.getText());
			composant.setNumSerie(numSerieField.getText());
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
		
		//TODO: faire la validation
		
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
