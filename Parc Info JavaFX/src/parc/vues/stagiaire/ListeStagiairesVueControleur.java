package parc.vues.stagiaire;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import machine.Stagiaire;
import parc.Principale;

public class ListeStagiairesVueControleur {
	
	@FXML
	private TableView<Stagiaire> stagiaireTable;
	@FXML
	private TableColumn<Stagiaire, Integer> idColonne;
	@FXML
	private TableColumn<Stagiaire, String> nomColonne;
	@FXML
	private TableColumn<Stagiaire, String> prenomColonne;
	
	private Principale principale;
	private Stage editStage;
	private boolean clickOK = false;
	private int stgId;
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().stgIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().stgNomProperty());
		prenomColonne.setCellValueFactory(cellData -> cellData.getValue().stgPrenomProperty());
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		stagiaireTable.setItems(principale.getStagiaireData());
	}
	
	public Principale getPrincipale(){
		return principale;
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	public int getStgId(){
		return stgId;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			Stagiaire stagiaire = stagiaireTable.getSelectionModel().getSelectedItem();
			stgId = stagiaire.getStgId();
			clickOK = true;
			editStage.close();
		}
	}
	
	@FXML
	public void handleAnnuler(){
		editStage.close();
	}

	private boolean isValid() {
		//TODO: faire la validation
		
		String message = "";
		
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
