package parc.vues.structure;

import java.text.ParseException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import parc.Principale;
import structure.Structure;

public class ListeStructuresVueControleur {
	
	@FXML
	private TableView<Structure> structureTable;
	@FXML
	private TableColumn<Structure, Integer> idColonne;
	@FXML
	private TableColumn<Structure, String> nomColonne;
	
	private Principale principale;
	private Stage editStage;
	private boolean clickOK = false;
	private int strId;
	private String strNom;
	
	@FXML
	private void initialize(){
		idColonne.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
		nomColonne.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
	}
	
	public void setPrincipale(Principale principale){
		this.principale = principale;
		structureTable.setItems(principale.getStructureData());
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
	
	public int getId(){
		return strId;
	}
	
	public String getNom(){
		return strNom;
	}
	
	@FXML
	public void handleOK() throws ParseException{
		if (isValid()){
			Structure structure = structureTable.getSelectionModel().getSelectedItem();
			strId = structure.getId();
			strNom = structure.getNom();
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
