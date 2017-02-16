package parc.vues.composant.liste;

import java.text.ParseException;

import composants.ListeComposants;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import parc.Principale;
import structure.Localisation;

public class ListeComposantVueControleur {
	
	@FXML
	private TableView<ListeComposants> listeComposantsTable;
	@FXML
	private TableColumn<ListeComposants, Integer> idColonne;
	@FXML
	private TableColumn<ListeComposants, Integer> machineColonne;
	
	private Principale principale;
	private Stage editStage;
	private int compId;
	private boolean clickOK = false;
	
	@FXML
	private void initialize() {
		idColonne.setCellValueFactory(cellData -> cellData.getValue().compIdProperty().asObject());
		machineColonne.setCellValueFactory(cellData -> {
			if (cellData.getValue().getMaId() != null){
				return cellData.getValue().maIdProperty().asObject();					
			} else {
				return new SimpleIntegerProperty(0).asObject();
			}
		});
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		listeComposantsTable.setItems(principale.getListeComposantsData());
	}
	
	public void setEditStage(Stage editStage){
		this.editStage = editStage;
	}
	
	public boolean isClickOK(){
		return clickOK;
	}
	
	public int getCompId(){
		return compId;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			ListeComposants listeComposants = listeComposantsTable.getSelectionModel().getSelectedItem();
			compId = listeComposants.getCompId();
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
