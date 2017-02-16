package parc.vues.localisation;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import parc.Principale;
import structure.Localisation;

public class ListeLocalisationsVueControleur {
	
	@FXML
	private TableView<Localisation> localisationTable;
	@FXML
	private TableColumn<Localisation, Integer> idColonne;
	@FXML
	private TableColumn<Localisation, String> nomColonne;
	
	private Principale principale;
	private Stage editStage;
	private boolean clickOK = false;
	private int locId;
	private String locNom;
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().locIdProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().locNomProperty());
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		localisationTable.setItems(principale.getLocalisationData());
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
	
	public int getLocId(){
		return locId;
	}
	
	public String getLocNom(){
		return locNom;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			Localisation localisation = localisationTable.getSelectionModel().getSelectedItem();
			locId = localisation.getLocId();
			locNom = localisation.getLocNom();
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
