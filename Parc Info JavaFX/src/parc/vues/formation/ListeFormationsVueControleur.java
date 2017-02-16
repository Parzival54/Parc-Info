package parc.vues.formation;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import machine.Formation;
import parc.Principale;

public class ListeFormationsVueControleur {
	
	@FXML
	private TableView<Formation> formationTable;
	@FXML
	private TableColumn<Formation, Integer> idColonne;
	@FXML
	private TableColumn<Formation, String> nomColonne;
	
	private Principale principale;
	private Stage editStage;
	private boolean clickOK = false;
	private int foId;
	private String foNom;
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().foIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().foNomProperty());
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		formationTable.setItems(principale.getFormationData());
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
	
	public int getFoId(){
		return foId;
	}
	
	public String getFoNom(){
		return foNom;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			Formation formation = formationTable.getSelectionModel().getSelectedItem();
			foId = formation.getFoId();
			foNom = formation.getFoNom();
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
